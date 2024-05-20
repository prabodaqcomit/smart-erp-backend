package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.BankDepositHeaderService;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import java.io.File;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BankDepositHeaderServiceImpl implements BankDepositHeaderService {

    protected final String DepositNumberFormat = "@BD@SEQ(1,6)"; //See the lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils

    @Autowired
    private BankDepositHeaderRepository bankDepositHeaderRepository;

    @Autowired
    private BankDepositDetailRepository bankDepositDetailRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Autowired
    private UnDepositedFundReferenceHeaderRepository unDepositedFundReferenceHeaderRepository;

    @Autowired
    private UnDepositedFundReferenceDetailRepository unDepositedFundReferenceDetailRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BankDepositHeaderDocumentNumberResponse getNextDocumentNumber() {
        //String _journalNumberFormat = this.JournalNumberFormat;
        String _maxDepositNumber = bankDepositHeaderRepository.getMaximumDepositNumber();
        String _depositNumber = RunningNumberFormatUtils.FormatRunningNumber(_maxDepositNumber, this.DepositNumberFormat);

        BankDepositHeaderDocumentNumberResponse documentNumberResponse = new BankDepositHeaderDocumentNumberResponse();
        documentNumberResponse.setGeneratedDate(new Date());
        documentNumberResponse.setDocumentNumber(_depositNumber);
        documentNumberResponse.setIsDisplayOnly(true);

        return documentNumberResponse;
    }

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    //@Transactional
    public BankDepositHeaderResponse save(BankDepositHeaderRequest request) {

        BankDepositHeaderUpdateRequest updateRequest = new BankDepositHeaderUpdateRequest();
        updateRequest.setId(null);
        updateRequest.setBranchId(request.getBranchId());
        //updateRequest.setDepositNumber(request.getDepositNumber());
        updateRequest.setTransactionDate(request.getTransactionDate());
        updateRequest.setFromAccountId(request.getFromAccountId());
        //updateRequest.setFromAccountBeforeBalance(request.getFromAccountBeforeBalance());
        //updateRequest.setFromAccountAfterBalance(request.getFromAccountAfterBalance());
        updateRequest.setToAccountId(request.getToAccountId());
        //updateRequest.setToAccountBeforeBalance(request.getToAccountBeforeBalance());
        //updateRequest.setToAccountAfterBalance(request.getToAccountAfterBalance());
        updateRequest.setRemark(request.getRemark());
        updateRequest.setTransactionDetail(request.getTransactionDetail());

        if (request.getBankDepositDetails() == null || request.getBankDepositDetails().isEmpty()) {
            try {
                throw new Exception("Bank Deposit Details can not be empty!");
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        List<BankDepositDetailUpdateRequest> detailUpdateRequests = new ArrayList<>();
        for (BankDepositDetailRequest detailRequest : request.getBankDepositDetails()) {
            BankDepositDetailUpdateRequest detailUpdateRequest = new BankDepositDetailUpdateRequest();
            detailUpdateRequest.setId(null);
            detailUpdateRequest.setLineNumber(detailRequest.getLineNumber());
            detailUpdateRequest.setUnDepositedFundReferenceId(detailRequest.getUnDepositedFundReferenceId());
            detailUpdateRequest.setUnDepositedFundReferenceLineNumber(detailRequest.getUnDepositedFundReferenceLineNumber());
            detailUpdateRequest.setDepositedAmount(detailRequest.getDepositedAmount());
            detailUpdateRequest.setLineRemark(detailRequest.getLineRemark());

            detailUpdateRequests.add(detailUpdateRequest);
        }
        updateRequest.setBankDepositDetails(detailUpdateRequests);

        BankDepositHeader saved = _saveHeaderAndDetails(updateRequest);
        if (saved == null) {
            return null;
        }

        return convert(saved);
    }

    @Override
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    //@Transactional
    public BankDepositHeaderResponse update(BankDepositHeaderUpdateRequest request) {

        //TODO: Need to handle the accounts impact on save -> set to previous state and then apply the new amounts

        BankDepositHeader updated = _saveHeaderAndDetails(request);
        if (updated == null) {
            return null;
        }

        return convert(updated);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    //@Transactional
    private BankDepositHeader _saveHeaderAndDetails(BankDepositHeaderUpdateRequest request) {
        BankDepositHeader bankDepositHeader = null;
        if (request.getId() == null) {
            String _depositNumber = this.getNextDocumentNumber().getDocumentNumber();

            bankDepositHeader = new BankDepositHeader();
            bankDepositHeader.setDepositNumber(_depositNumber);
        } else {
            bankDepositHeader = bankDepositHeaderRepository.findById(request.getId()).orElse(null);
            //bankDepositHeader.setId(request.getId());
        }

        if (bankDepositHeader == null) {
//            try {
//                throw new Exception("Bank Deposit header not found!");
//                //This will roll back the transaction
//            }catch (Exception ex){
//                ex.printStackTrace();
//                return null;
//            }
            return null;
        }

        BranchNetwork branch = null;
        try {
            branch = branchNetworkRepository.getById(request.getBranchId());
            bankDepositHeader.setBranch(branch);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        double _totalAmount = _calculateTotal(request.getBankDepositDetails());

        try {
            LadgerAccount fromAccount = ladgerAccountRepository.getById(request.getFromAccountId());
            bankDepositHeader.setFromAccount(fromAccount);
            //bankDepositHeader.setFromAccountBeforeBalance(request.getFromAccountBeforeBalance());
            //bankDepositHeader.setFromAccountAfterBalance(request.getFromAccountAfterBalance());

            bankDepositHeader.setFromAccountBeforeBalance(fromAccount.getCurrentBalance()); //Before set new balance
            fromAccount.setCurrentBalance(
                    fromAccount.getCurrentBalance() - _totalAmount
            );
            bankDepositHeader.setFromAccountAfterBalance(fromAccount.getCurrentBalance()); //After set new balance

            //Update from Ledger Account
            ladgerAccountRepository.save(fromAccount);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            LadgerAccount toAccount = ladgerAccountRepository.getById(request.getToAccountId());
            bankDepositHeader.setToAccount(toAccount);
            bankDepositHeader.setToAccountCode(toAccount.getAccNo());
            bankDepositHeader.setToAccountName(toAccount.getAccName());
            //bankDepositHeader.setToAccountBeforeBalance(request.getToAccountBeforeBalance());
            //bankDepositHeader.setToAccountAfterBalance(request.getToAccountAfterBalance());

            bankDepositHeader.setToAccountBeforeBalance(toAccount.getCurrentBalance()); //Before set new balance
            toAccount.setCurrentBalance(
                    toAccount.getCurrentBalance() + _totalAmount
            );
            bankDepositHeader.setToAccountAfterBalance(toAccount.getCurrentBalance()); //After set new balance

            //Update to Ledger Account
            ladgerAccountRepository.save(toAccount);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        bankDepositHeader.setTransactionDate(request.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransactionDate()));
        bankDepositHeader.setRemark(request.getRemark());
        bankDepositHeader.setTransactionDetail(request.getTransactionDetail());
        bankDepositHeader.setTotalAmount(this._calculateTotal(request.getBankDepositDetails()));
        bankDepositHeader.setIsDeleted(Deleted.NO);
        BankDepositHeader savedHeader = bankDepositHeaderRepository.save(bankDepositHeader);

        try {
            if (request.getBankDepositDetails() != null && !request.getBankDepositDetails().isEmpty()) {
                _saveDetails(request, savedHeader);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


//            try {
//                List<BankDepositDetail> details = bankDepositDetailRepository.findByIsDeletedAndBankDepositHeaderId(
//                        Deleted.NO,
//                        savedHeader.getId()
//                );
//                //savedHeader.setBankDepositDetails(details);
//            } catch (Exception ignored) {
//            }

        return savedHeader;
    }

//    @Transactional
//    private BankDepositHeader _saveHeader(BankDepositHeaderUpdateRequest request) throws Exception {
//
//
//    }

    @Transactional
    private List<BankDepositDetail> _saveDetails(BankDepositHeaderUpdateRequest request, BankDepositHeader savedHeader) {
        if (request.getBankDepositDetails() == null || request.getBankDepositDetails().isEmpty()) {
            try {
                throw new Exception("Bank Deposit Details can not be blank!");
                //This will roll back the transaction
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        //Delete existing details before save, update new records
        //entityManager.joinTransaction();
        bankDepositDetailRepository.deleteBankDepositDetailsByHeaderId(savedHeader.getId());

        List<BankDepositDetail> savedDetails = new ArrayList<>();

        for (BankDepositDetailUpdateRequest detailRequest : request.getBankDepositDetails()) {
            BankDepositDetail detail = new BankDepositDetail();
            detail.setBankDepositHeader(savedHeader);
            detail.setDepositNumber(savedHeader.getDepositNumber());
            detail.setUnDepositedFundReferenceLineNumber(detailRequest.getUnDepositedFundReferenceLineNumber());
            detail.setLineNumber(detailRequest.getLineNumber());

            UnDepositedFundReferenceHeader unDepositedFundReferenceHeader = null;
            UnDepositedFundReferenceDetail unDepositedFundReferenceDetail = null;


            unDepositedFundReferenceHeader = unDepositedFundReferenceHeaderRepository
                    .findById(
                            detailRequest.getUnDepositedFundReferenceId()
                    ).orElse(null);

            unDepositedFundReferenceDetail =
                    unDepositedFundReferenceDetailRepository
                            .findByUnDepositedFundReferenceHeaderIdAndLineNumber(
                                    detailRequest.getUnDepositedFundReferenceId(),
                                    detail.getUnDepositedFundReferenceLineNumber()
                            );

            if (unDepositedFundReferenceHeader == null || unDepositedFundReferenceDetail == null) {
                try {
                    throw new Exception("Unable to find the UnDepositedFundReferenceHeader or UnDepositedFundReferenceDetail");
                    //This will roll back the transaction
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
            }

            //Update UnDeposited Fund Reference Header and details
            try {

                unDepositedFundReferenceDetail.setUnDepositedBalance(
                        unDepositedFundReferenceDetail.getUnDepositedBalance()
                                -
                                detailRequest.getDepositedAmount()
                );
                unDepositedFundReferenceHeader.setUnDepositedTotalBalance(
                        unDepositedFundReferenceHeader.getUnDepositedTotalBalance()
                                -
                                detailRequest.getDepositedAmount()
                );

                unDepositedFundReferenceHeaderRepository.save(unDepositedFundReferenceHeader);
                unDepositedFundReferenceDetailRepository.save(unDepositedFundReferenceDetail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


            detail.setTransactionBranchId(unDepositedFundReferenceHeader.getTransactionBranch().getId());
            detail.setTransactionId(unDepositedFundReferenceHeader.getTransactionId());
            detail.setTransactionDate(unDepositedFundReferenceHeader.getTransactionDate());
            detail.setTransactionIsInvoice(unDepositedFundReferenceHeader.getTransactionIsInvoice());
            detail.setTransactionIsReceipt(unDepositedFundReferenceHeader.getTransactionIsReceipt());
            detail.setTransactionTypeDescription(unDepositedFundReferenceHeader.getTransactionTypeDescription());
            detail.setTransactionAmount(unDepositedFundReferenceHeader.getTransactionAmount());
            detail.setCurrencyType(unDepositedFundReferenceDetail.getCurrencyType());
            detail.setCurrencyTypeExchangeRate(unDepositedFundReferenceDetail.getCurrencyTypeExchangeRate());
            detail.setPaymentMode(unDepositedFundReferenceDetail.getPaymentMode());
            detail.setUnDepositAmount(unDepositedFundReferenceDetail.getPaymentModePaidAmount());
            detail.setDepositedAmount(detailRequest.getDepositedAmount());
            detail.setUnDepositBalance(
                    unDepositedFundReferenceDetail.getUnDepositedBalance()
                            -
                            detailRequest.getDepositedAmount()
            );
            detail.setPaymentModeRemark(detailRequest.getLineRemark());
            detail.setUnDepositedFundReferenceId(unDepositedFundReferenceHeader.getId());
            detail.setUnDepositedFundReferenceLineNumber(unDepositedFundReferenceDetail.getLineNumber());

            BankDepositDetail savedDetail = bankDepositDetailRepository.save(detail);
            savedDetails.add(savedDetail);
        }

        return savedDetails;
    }


    @Override
    public BankDepositHeaderResponse getById(Long id) {

        BankDepositHeader header = bankDepositHeaderRepository.findById(id).orElse(null);

        if (header == null) {
            return null;
        }

//        try {
//
//            List<BankDepositDetail> details = bankDepositDetailRepository.findByIsDeletedAndBankDepositHeaderId(
//                    Deleted.NO,
//                    id
//            );
//            header.setBankDepositDetails(details);
//        } catch (Exception ignored) {
//        }

        return BankDepositHeaderServiceImpl.convert(header);
    }

    @Override
    public List<BankDepositHeaderResponse> getAll() {

        List<BankDepositHeader> headers = bankDepositHeaderRepository.findByIsDeleted(Deleted.NO);

        if (headers == null) {
            return null;
        }

//        try {
//            for(BankDepositHeader header: headers){
//                List<BankDepositDetail> details = bankDepositDetailRepository.findByIsDeletedAndBankDepositHeaderId(
//                        Deleted.NO,
//                        header.getId()
//                );
//                header.setBankDepositDetails(details);
//            }
//        } catch (Exception ignored) {
//        }

        return headers.stream().map(BankDepositHeaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public Page<BankDepositHeaderResponse> getPaginatedAll(int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        Page<BankDepositHeader> _pgResult = bankDepositHeaderRepository.findByIsDeleted(Deleted.NO, pgRequest);

        List<BankDepositHeaderResponse> _lstResult = _pgResult
                .stream().map(BankDepositHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<BankDepositHeaderResponse> getPaginatedAll(Long branchId, int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);
        Page<BankDepositHeader> _pgResult = bankDepositHeaderRepository.findByIsDeletedAndBranchId(Deleted.NO, branchId, pgRequest);

        List<BankDepositHeaderResponse> _lstResult = _pgResult
                .stream().map(BankDepositHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<BankDepositHeaderResponse> getSearchPaginated(int pageNumber, int countPerPage, BankDepositHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<BankDepositHeader> _unPagedHeaders = bankDepositHeaderRepository.searchCustomFilterBankDeposits(
                Deleted.NO,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                sort
        );

        int _fromIndex = pageNumber * countPerPage;
        int _toIndex = ((pageNumber == 0 ? countPerPage : (pageNumber * countPerPage)) - 1);
        _toIndex = Math.min(_toIndex, _unPagedHeaders.size());
        List<BankDepositHeader> _pagedHeaders = _unPagedHeaders.subList(
                _fromIndex,
                _toIndex
        );
        */

        Page<BankDepositHeader> _pagedHeaders = bankDepositHeaderRepository.searchCustomFilterBankDeposits(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getDepositNumber(),
                searchRequest.getToAccountId(),
                searchRequest.getTransactionBranchId(),
                searchRequest.getTransactionId(),
                searchRequest.getAmount(),
                _pgRequest
        );

        List<BankDepositHeaderResponse> _pagedResponses = _pagedHeaders
                .stream().map(BankDepositHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _pagedHeaders.getTotalElements());
    }

    @Override
    public Page<BankDepositHeaderResponse> getSearchPaginated(Long branchId, int pageNumber, int countPerPage, BankDepositHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable _pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<BankDepositHeader> _unPagedHeaders = bankDepositHeaderRepository.searchCustomFilterBankDeposits(
                Deleted.NO,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                sort
        );

        int _fromIndex = pageNumber * countPerPage;
        int _toIndex = ((pageNumber == 0 ? countPerPage : (pageNumber * countPerPage)) - 1);
        _toIndex = Math.min(_toIndex, _unPagedHeaders.size());
        List<BankDepositHeader> _pagedHeaders = _unPagedHeaders.subList(
                _fromIndex,
                _toIndex
        );
        */

        Page<BankDepositHeader> _pagedHeaders = bankDepositHeaderRepository.searchCustomFilterBankDeposits(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getDepositNumber(),
                searchRequest.getToAccountId(),
                searchRequest.getTransactionBranchId(),
                searchRequest.getTransactionId(),
                searchRequest.getAmount(),
                branchId,
                _pgRequest
        );

        List<BankDepositHeaderResponse> _pagedResponses = _pagedHeaders
                .stream().map(BankDepositHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_pagedResponses, _pgRequest, _pagedHeaders.getTotalElements());
    }

    @Override
    public File printSingle(Long id) {
        File out = null;
        Connection co = null;

        try {
            File file = new File("JRXML/report/leader_reports/rpt_Bank_Deposit.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("prm_HeaderId", id);
            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            File tmpDir = new File("TMP/");
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

//            String filepath = filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
//            String filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
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


//    @Override
//    @Transactional
//    public Integer delete(Long id) {
//
//        BankDepositHeader got = bankDepositHeaderRepository.findById(id).orElse(null);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        bankDepositHeaderRepository.save(got);
//
//        return 1;
//    }

    private double _calculateTotal(List<BankDepositDetailUpdateRequest> details) {
        if (details == null || details.isEmpty()) {
            return 0.00;
        }
        double _total = 0.00;

        for (BankDepositDetailUpdateRequest detail : details) {
            _total += detail.getDepositedAmount();
        }

        return _total;
    }

    private static BankDepositHeaderResponse convert(BankDepositHeader bankDepositHeader) {

        BankDepositHeaderResponse typeResponse = new BankDepositHeaderResponse();
        typeResponse.setBranch(BranchNetworkServiceImpl.convert(bankDepositHeader.getBranch()));
        typeResponse.setDepositNumber(bankDepositHeader.getDepositNumber());

        typeResponse.setFromAccount(LadgerAccountServiceImpl.convert(bankDepositHeader.getFromAccount()));
        typeResponse.setFromAccountCode(bankDepositHeader.getToAccountCode());
        typeResponse.setFromAccountName(bankDepositHeader.getFromAccount().getAccName());
        typeResponse.setFromAccountBeforeBalance(bankDepositHeader.getFromAccountBeforeBalance());
        typeResponse.setFromAccountAfterBalance(bankDepositHeader.getFromAccountAfterBalance());

        typeResponse.setToAccount(LadgerAccountServiceImpl.convert(bankDepositHeader.getToAccount()));
        typeResponse.setToAccountCode(bankDepositHeader.getToAccountCode());
        typeResponse.setToAccountName(bankDepositHeader.getToAccountName());
        typeResponse.setToAccountBeforeBalance(bankDepositHeader.getToAccountBeforeBalance());
        typeResponse.setToAccountAfterBalance(bankDepositHeader.getToAccountAfterBalance());

        typeResponse.setTransactionDate(new DateFormatConverter().patternDate(bankDepositHeader.getTransactionDate()));
        typeResponse.setRemark(bankDepositHeader.getRemark());
        typeResponse.setTransactionDetail(bankDepositHeader.getTransactionDetail());
        typeResponse.setFromAccountAfterBalance(bankDepositHeader.getFromAccountAfterBalance());


        typeResponse.setTotalAmount(bankDepositHeader.getTotalAmount());
        typeResponse.setId(bankDepositHeader.getId());
        typeResponse.setCreatedBy(bankDepositHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bankDepositHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(bankDepositHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bankDepositHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(bankDepositHeader.getIsDeleted());

        List<BankDepositDetailResponse> details = new ArrayList<>();
        if (bankDepositHeader.getBankDepositDetails() != null && !bankDepositHeader.getBankDepositDetails().isEmpty()) {
            details = bankDepositHeader.getBankDepositDetails()
                    .stream().map(BankDepositDetailServiceImpl::convert).collect(Collectors.toList());
        }
        typeResponse.setBankDepositDetails(details);

        return typeResponse;
    }
}