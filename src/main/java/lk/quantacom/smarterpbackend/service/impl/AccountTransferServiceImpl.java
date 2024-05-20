package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferSearchRequest;
import lk.quantacom.smarterpbackend.dto.request.AccountTransferUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.AccountTransfer;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.repository.AccountTransferRepository;
import lk.quantacom.smarterpbackend.repository.BranchNetworkRepository;
import lk.quantacom.smarterpbackend.repository.LadgerAccountRepository;
import lk.quantacom.smarterpbackend.service.AccountTransferService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    protected final String AccountTransferNumberFormat = "@AT@SEQ(1,6)"; //See the lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils

    @Autowired
    private AccountTransferRepository accountTransferRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Override
    public AccountTransferDocumentNumberResponse getNextDocumentNumber() {
        String _maxJournalNumber = accountTransferRepository.getMaximumReceiptNumber();
        String _documentNumber = RunningNumberFormatUtils.FormatRunningNumber(_maxJournalNumber, this.AccountTransferNumberFormat);

        AccountTransferDocumentNumberResponse documentNumberResponse = new AccountTransferDocumentNumberResponse();
        documentNumberResponse.setGeneratedDate(new Date());
        documentNumberResponse.setDocumentNumber(_documentNumber);
        documentNumberResponse.setIsDisplayOnly(true);

        return documentNumberResponse;
    }

    @Override
    @Transactional
    public AccountTransferResponse save(AccountTransferRequest request) {
        try {
            AccountTransferUpdateRequest updateRequest = new AccountTransferUpdateRequest();
            updateRequest.setId(null);
            updateRequest.setBranchId(request.getBranchId());
            updateRequest.setTransferDate(request.getTransferDate());
            updateRequest.setFromAccountId(request.getFromAccountId());
            updateRequest.setToAccountId(request.getToAccountId());
            updateRequest.setAmount(request.getAmount());
            updateRequest.setRemark(request.getRemark());
            updateRequest.setTransferDetail(request.getTransferDetail());

            AccountTransfer saved = _saveTransfer(updateRequest);

            return convert(saved);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public AccountTransferResponse update(AccountTransferUpdateRequest request) {
        try {
            AccountTransfer transfer = _saveTransfer(request);

            return convert(transfer);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    private AccountTransfer _saveTransfer(AccountTransferUpdateRequest request) {

        String _transferNumber = this.getNextDocumentNumber().getDocumentNumber();

        AccountTransfer accountTransfer = null;
        if (request.getId() == null) {
            accountTransfer = new AccountTransfer();
            accountTransfer.setTransferNumber(_transferNumber);
        } else {
            accountTransfer = accountTransferRepository.findById(request.getId()).orElse(null);

            if (accountTransfer == null) {
                return null;
            }
        }

        accountTransfer.setTransactionDate(request.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransferDate()));

        BranchNetwork branch = branchNetworkRepository.getById(request.getBranchId());
        accountTransfer.setBranch(branch);

        try {
            LadgerAccount fromAccount = ladgerAccountRepository.getById(request.getFromAccountId());
            accountTransfer.setFromAccount(fromAccount);
            accountTransfer.setFromAccountCode(fromAccount.getAccNo());
            accountTransfer.setFromAccountName(fromAccount.getAccName());

            accountTransfer.setFromAccountBeforeBalance(fromAccount.getCurrentBalance()); //Before set new balance
            fromAccount.setCurrentBalance(
                    fromAccount.getCurrentBalance() - request.getAmount()
            );
            accountTransfer.setFromAccountAfterBalance(fromAccount.getCurrentBalance()); //After set new balance

            //Update from Ledger Account
            ladgerAccountRepository.save(fromAccount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            LadgerAccount toAccount = ladgerAccountRepository.getById(request.getToAccountId());
            accountTransfer.setToAccount(toAccount);
            accountTransfer.setToAccountCode(toAccount.getAccNo());
            accountTransfer.setToAccountName(toAccount.getAccName());

            accountTransfer.setToAccountBeforeBalance(toAccount.getCurrentBalance()); //Before set new balance
            toAccount.setCurrentBalance(
                    toAccount.getCurrentBalance() + request.getAmount()
            );
            accountTransfer.setToAccountAfterBalance(toAccount.getCurrentBalance()); //After set new balance

            //Update to Ledger Account
            ladgerAccountRepository.save(toAccount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        accountTransfer.setRemark(request.getRemark());
        accountTransfer.setTransactionDetail(request.getTransferDetail());
        accountTransfer.setAmount(request.getAmount());
        accountTransfer.setIsDeleted(Deleted.NO);
        AccountTransfer updated = accountTransferRepository.save(accountTransfer);

        return updated;
    }

    @Override
    public AccountTransferResponse getById(Long id) {

        return accountTransferRepository.findById(id).map(AccountTransferServiceImpl::convert).orElse(null);
    }

    @Override
    public List<AccountTransferResponse> getAll() {

        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));
        return accountTransferRepository.findByIsDeleted(Deleted.NO, sort)
                .stream().map(AccountTransferServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<AccountTransferResponse> getPaginatedAll(int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        List<AccountTransfer> _unPagedResult = accountTransferRepository.findByIsDeleted(
                Deleted.NO,
                sort
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _unPagedResult.size());
        List<AccountTransfer> _pagedTransfers = _unPagedResult.subList(
                _fromIndex,
                _toIndex
        );

        List<AccountTransferResponse> _lstPagedResult = _pagedTransfers
                .stream().map(AccountTransferServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstPagedResult, pgRequest, _unPagedResult.size());
    }

    @Override
    public Page<AccountTransferResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        List<AccountTransfer> _unPagedResult = accountTransferRepository.findByIsDeletedAndBranchId(
                Deleted.NO,
                branchId,
                sort
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _unPagedResult.size());
        List<AccountTransfer> _pagedTransfers = _unPagedResult.subList(
                _fromIndex,
                _toIndex
        );

        List<AccountTransferResponse> _lstPagedResult = _pagedTransfers
                .stream().map(AccountTransferServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstPagedResult, pgRequest, _unPagedResult.size());
    }

    @Override
    public Page<AccountTransferResponse> getSearchPaginated(int pageNumber, int countPerPage, AccountTransferSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        List<AccountTransfer> _unPagedTransfers = accountTransferRepository.searchCustomFilter(
                //Deleted.NO,
                ConvertUtils.convertStrToDate(searchRequest.getFromTransactionDate()),
                ConvertUtils.convertStrToDate(searchRequest.getToTransactionDate()),
                searchRequest.getFromAccountId(),
                searchRequest.getToAccountId(),
                searchRequest.getAmount()
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _unPagedTransfers.size());
        List<AccountTransfer> _pagedTransfers = _unPagedTransfers.subList(
                _fromIndex,
                _toIndex
        );

        List<AccountTransferResponse> _pagedResponses = _pagedTransfers
                .stream().map(AccountTransferServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _unPagedTransfers.size());
    }

    @Override
    public Page<AccountTransferResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, AccountTransferSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        List<AccountTransfer> _unPagedTransfers = accountTransferRepository.searchCustomFilterBranch(
                //Deleted.NO,
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getFromTransactionDate()),
                ConvertUtils.convertStrToDate(searchRequest.getFromTransactionDate()),
                searchRequest.getFromAccountId(),
                searchRequest.getToAccountId(),
                searchRequest.getAmount()
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _unPagedTransfers.size());
        List<AccountTransfer> _pagedTransfers = _unPagedTransfers.subList(
                _fromIndex,
                _toIndex
        );

        List<AccountTransferResponse> _pagedResponses = _pagedTransfers
                .stream().map(AccountTransferServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _unPagedTransfers.size());
    }

    @Override
    public File printSingle(Long id) {
        File out = null;
        Connection co = null;

        try {
            File file = new File("JRXML/report/leader_reports/rpt_AccountTransfer.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("prm_HeaderId", id);
            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            File tmpDir = new File("TMP/");
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";

            JasperExportManager.exportReportToPdfFile(print, filepath);
            out = new File(filepath);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception ignored) {
                }
            }
        }
        return out;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        AccountTransfer got = accountTransferRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        accountTransferRepository.save(got);

        return 1;
    }

    private static AccountTransferResponse convert(AccountTransfer accountTransfer) {

        AccountTransferResponse typeResponse = new AccountTransferResponse();
        typeResponse.setTransferDate(new DateFormatConverter().patternDate(accountTransfer.getTransactionDate()));
        typeResponse.setTransferNumber(accountTransfer.getTransferNumber());

        typeResponse.setBranch(BranchNetworkServiceImpl.convert(accountTransfer.getBranch()));

        typeResponse.setFromAccount(LadgerAccountServiceImpl.convert(accountTransfer.getFromAccount()));

        typeResponse.setToAccount(LadgerAccountServiceImpl.convert(accountTransfer.getToAccount()));

        typeResponse.setRemark(accountTransfer.getRemark());
        typeResponse.setTransferDetail(accountTransfer.getTransactionDetail());
        typeResponse.setAmount(accountTransfer.getAmount());
        typeResponse.setId(accountTransfer.getId());
        typeResponse.setCreatedBy(accountTransfer.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(accountTransfer.getCreatedDateTime()));
        typeResponse.setModifiedBy(accountTransfer.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(accountTransfer.getModifiedDateTime()));
        typeResponse.setIsDeleted(accountTransfer.getIsDeleted());

        return typeResponse;
    }

}