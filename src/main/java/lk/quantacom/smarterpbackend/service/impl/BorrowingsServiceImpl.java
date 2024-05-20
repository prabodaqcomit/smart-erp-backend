package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BorrowingsRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveAllRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsSaveReturnRequest;
import lk.quantacom.smarterpbackend.dto.request.BorrowingsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BorrowingsResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.BorrowingsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowingsServiceImpl implements BorrowingsService {

    @Autowired
    private BorrowingsRepository borrowingsRepository;

    @Autowired
    private ExpenceHistoryRepository expenceHistoryRepository;

    @Autowired
    private LedgerCashbookCreditRepository ledgerCashbookCreditRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private static BorrowingsResponse convert(Borrowings borrowings) {

        BorrowingsResponse typeResponse = new BorrowingsResponse();
        typeResponse.setAmountInWord(borrowings.getAmountInWord());
        typeResponse.setBalanceAmount(borrowings.getBalanceAmount());
        typeResponse.setBorrowDate(borrowings.getBorrowDate());
        typeResponse.setBorrowerName(borrowings.getBorrowerName());
        typeResponse.setBranchId(borrowings.getBranchId());
        typeResponse.setNoSemiPyingAmount(borrowings.getNoSemiPyingAmount());
        typeResponse.setPayingAmount(borrowings.getPayingAmount());
        typeResponse.setReason(borrowings.getReason());
        typeResponse.setReturnAmount(borrowings.getReturnAmount());
        typeResponse.setStatus(borrowings.getStatus());
        typeResponse.setId(borrowings.getId());
        typeResponse.setCreatedBy(borrowings.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(borrowings.getCreatedDateTime()));
        typeResponse.setModifiedBy(borrowings.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(borrowings.getModifiedDateTime()));
        typeResponse.setIsDeleted(borrowings.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public BorrowingsResponse save(BorrowingsRequest request) {

        Borrowings borrowings = new Borrowings();
        borrowings.setAmountInWord(request.getAmountInWord());
        borrowings.setBalanceAmount(request.getBalanceAmount());
        borrowings.setBorrowDate(request.getBorrowDate() == null ? null : ConvertUtils.convertStrToDate(request.getBorrowDate()));
        borrowings.setBorrowerName(request.getBorrowerName());
        borrowings.setBranchId(request.getBranchId());
        borrowings.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        borrowings.setPayingAmount(request.getPayingAmount());
        borrowings.setReason(request.getReason());
        borrowings.setReturnAmount(request.getReturnAmount());
        borrowings.setStatus(request.getStatus());
        borrowings.setIsDeleted(Deleted.NO);
        Borrowings save = borrowingsRepository.save(borrowings);

        return convert(save);
    }

    @Override
    @Transactional
    public BorrowingsResponse update(BorrowingsUpdateRequest request) {

        Borrowings borrowings = borrowingsRepository.findById(request.getId()).orElse(null);
        if (borrowings == null) {
            return null;
        }

        borrowings.setId(request.getId());
        borrowings.setAmountInWord(request.getAmountInWord());
        borrowings.setBalanceAmount(request.getBalanceAmount());
        borrowings.setBorrowDate(request.getBorrowDate() == null ? null : ConvertUtils.convertStrToDate(request.getBorrowDate()));
        borrowings.setBorrowerName(request.getBorrowerName());
        borrowings.setBranchId(request.getBranchId());
        borrowings.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        borrowings.setPayingAmount(request.getPayingAmount());
        borrowings.setReason(request.getReason());
        borrowings.setReturnAmount(request.getReturnAmount());
        borrowings.setStatus(request.getStatus());
        Borrowings updated = borrowingsRepository.save(borrowings);

        return (convert(updated));
    }

    @Override
    public BorrowingsResponse getById(Long id) {

        return borrowingsRepository.findById(id).map(BorrowingsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BorrowingsResponse> getAll() {
        return borrowingsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BorrowingsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Borrowings got = borrowingsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        borrowingsRepository.save(got);

        return 1;
    }

    @Override
    public List<BorrowingsResponse> getByBorrowerName(String borrowerName) {
        return borrowingsRepository.findByBorrowerName(borrowerName)
                .stream().map(BorrowingsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public BorrowingsResponse saveAll(BorrowingsSaveAllRequest request) {

        Borrowings borrowings = new Borrowings();
        borrowings.setAmountInWord(request.getAmountInWord());
        borrowings.setBalanceAmount(request.getPayingAmount());
        borrowings.setBorrowDate(request.getBorrowDate() == null ? null : ConvertUtils.convertStrToDate(request.getBorrowDate()));
        borrowings.setBorrowerName(request.getBorrowerName());
        borrowings.setBranchId(request.getBranchId());
        borrowings.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        borrowings.setPayingAmount(request.getPayingAmount());
        borrowings.setReason("REASON = "+ request.getReason());
        borrowings.setReturnAmount(0.00);
        borrowings.setStatus("NEW BORROWING");
        borrowings.setIsDeleted(Deleted.NO);
        Borrowings save = borrowingsRepository.save(borrowings);

        ExpenceHistory expenceHistory = new ExpenceHistory();
        expenceHistory.setDate(request.getBorrowDate() == null ? null : ConvertUtils.convertStrToDate(request.getBorrowDate()));
        expenceHistory.setDocNo(save.getId().intValue());
        expenceHistory.setDocType("BORROWINGS");
        expenceHistory.setDescription(request.getBorrowerName());
        expenceHistory.setPayAmount(request.getPayingAmount());
        expenceHistory.setPayMode("CASH");
        expenceHistory.setRemarks("REASON = "+request.getReason());
        expenceHistory.setBranchId(request.getBranchId());
        expenceHistory.setIsDeleted(Deleted.NO);
        expenceHistoryRepository.save(expenceHistory);

        LedgerHistory ledgerHistory = new LedgerHistory();
        ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
        ledgerHistory.setLadgerAccountId(request.getLedgerDebitId().longValue());
        ledgerHistory.setUpdateFram("BORROWINGS");
        ledgerHistory.setUpdateFramDocNo(save.getId().toString());
        ledgerHistory.setRemark(" cash " + " Borrowings to " + request.getBorrowerName());
        ledgerHistory.setUpdateBalance(request.getPayingAmount());
        ledgerHistory.setTransType("DEBIT");
        ledgerHistory.setCreditAmount(0.00);
        ledgerHistory.setDebitAmount(request.getPayingAmount());
        ledgerHistory.setCreditColumnName(request.getCreditAccName());
        ledgerHistory.setDebitColumnName("-");
        ledgerHistory.setIsDeleted(Deleted.NO);
        ledgerHistoryRepository.save(ledgerHistory);

        ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
        ledgerHistory.setLadgerAccountId(request.getLedgerCreditId().longValue());
        ledgerHistory.setUpdateFram("BORROWINGS");
        ledgerHistory.setUpdateFramDocNo(save.getId().toString());
        ledgerHistory.setRemark(" cash " + " Borrowings to " + request.getBorrowerName());
        ledgerHistory.setUpdateBalance(request.getPayingAmount());
        ledgerHistory.setTransType("CREDIT");
        ledgerHistory.setCreditAmount(request.getPayingAmount());
        ledgerHistory.setDebitAmount(0.00);
        ledgerHistory.setCreditColumnName("-");
        ledgerHistory.setDebitColumnName(request.getDebitAccName());
        ledgerHistory.setIsDeleted(Deleted.NO);
        ledgerHistoryRepository.save(ledgerHistory);

        LedgerCashbookCredit ledgerCashbookCredit = new LedgerCashbookCredit();
        ledgerCashbookCredit.setBankAmount(0.00);
        ledgerCashbookCredit.setBranchId(request.getBranchId());
        ledgerCashbookCredit.setCashAmount(request.getPayingAmount());
        ledgerCashbookCredit.setDescription(request.getDebitAccName());
        ledgerCashbookCredit.setDiscountAllowed(0.00);
        ledgerCashbookCredit.setOtherAccNo(request.getLedgerCreditId().toString());
        ledgerCashbookCredit.setOtherAccountName(request.getCreditAccName());
        ledgerCashbookCredit.setPrNo("-");
        ledgerCashbookCredit.setVcharNo(save.getId().toString());
        ledgerCashbookCredit.setIsDeleted(Deleted.NO);
        ledgerCashbookCreditRepository.save(ledgerCashbookCredit);

        saveLog("Borrowings", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    public List<BorrowingsResponse> getByReason(String reason) {
        return borrowingsRepository.findByReason(reason)
                .stream().map(BorrowingsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BorrowingsResponse> getByBorrowerNameAndStatusNwBr(String borrowerName) {
        return borrowingsRepository.findByBorrowerNameAndStatusOrderByBorrowDateAsc(borrowerName,"NEW BORROWING")
                .stream().map(BorrowingsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BorrowingsResponse> getByDateRangeOfBorrowDate(Date from, Date to) {
        return borrowingsRepository.findByBorrowDateBetweenOrderByIdAsc(from,to)
                .stream().map(BorrowingsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public BorrowingsResponse saveReturn(BorrowingsSaveReturnRequest request) {
        Borrowings borrowings = borrowingsRepository.getById(request.getId());
        borrowings.setBalanceAmount(request.getBalanceAmount());
        borrowings.setReturnAmount(request.getReturnAmount()+borrowings.getReturnAmount());
        borrowingsRepository.save(borrowings);

        Borrowings borrowing = new Borrowings();
        borrowing.setAmountInWord("0.00");
        borrowing.setBalanceAmount(request.getBalanceAmount());
        borrowing.setBorrowDate(request.getReturnDate() == null ? null : ConvertUtils.convertStrToDate(request.getReturnDate()));
        borrowing.setBorrowerName(request.getBorrowerName());
        borrowing.setBranchId(request.getBranchId());
        borrowing.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        borrowing.setPayingAmount(0.00);
        borrowing.setReason(request.getReason());
        borrowing.setReturnAmount(request.getReturnAmount());
        borrowing.setStatus("PAYING");
        borrowing.setIsDeleted(Deleted.NO);
        Borrowings save = borrowingsRepository.save(borrowing);

        LedgerHistory ledgerHistory = new LedgerHistory();
        ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
        ledgerHistory.setLadgerAccountId(request.getLedgerDebitId().longValue());
        ledgerHistory.setUpdateFram("BORROWINGS");
        ledgerHistory.setUpdateFramDocNo(save.getId().toString());
        ledgerHistory.setRemark(" cash " + " Borrowings to " + request.getBorrowerName());
        ledgerHistory.setUpdateBalance(request.getPayingAmount());
        ledgerHistory.setTransType("DEBIT");
        ledgerHistory.setCreditAmount(0.00);
        ledgerHistory.setDebitAmount(request.getPayingAmount());
        ledgerHistory.setCreditColumnName(request.getCreditAccName());
        ledgerHistory.setDebitColumnName("-");
        ledgerHistory.setIsDeleted(Deleted.NO);
        ledgerHistoryRepository.save(ledgerHistory);

        ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
        ledgerHistory.setLadgerAccountId(request.getLedgerCreditId().longValue());
        ledgerHistory.setUpdateFram("BORROWINGS");
        ledgerHistory.setUpdateFramDocNo(save.getId().toString());
        ledgerHistory.setRemark(" cash " + " Borrowings to " + request.getBorrowerName());
        ledgerHistory.setUpdateBalance(request.getPayingAmount());
        ledgerHistory.setTransType("CREDIT");
        ledgerHistory.setCreditAmount(request.getPayingAmount());
        ledgerHistory.setDebitAmount(0.00);
        ledgerHistory.setCreditColumnName("-");
        ledgerHistory.setDebitColumnName(request.getDebitAccName());
        ledgerHistory.setIsDeleted(Deleted.NO);
        ledgerHistoryRepository.save(ledgerHistory);

        LedgerCashbookCredit ledgerCashbookCredit = new LedgerCashbookCredit();
        ledgerCashbookCredit.setBankAmount(0.00);
        ledgerCashbookCredit.setBranchId(request.getBranchId());
        ledgerCashbookCredit.setCashAmount(request.getPayingAmount());
        ledgerCashbookCredit.setDescription(request.getDebitAccName());
        ledgerCashbookCredit.setDiscountAllowed(0.00);
        ledgerCashbookCredit.setOtherAccNo(request.getLedgerCreditId().toString());
        ledgerCashbookCredit.setOtherAccountName(request.getCreditAccName());
        ledgerCashbookCredit.setPrNo("-");
        ledgerCashbookCredit.setVcharNo(save.getId().toString());
        ledgerCashbookCredit.setIsDeleted(Deleted.NO);
        ledgerCashbookCreditRepository.save(ledgerCashbookCredit);

        saveLog("BORROWING RETURN", "Return Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    public List<String> getBorrowerNames() {

        return borrowingsRepository.getBorrowerNames();
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

}
