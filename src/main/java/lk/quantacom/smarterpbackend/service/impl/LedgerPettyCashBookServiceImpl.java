package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerPettyCashBookUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerPettyCashBookResponse;
import lk.quantacom.smarterpbackend.entity.LedgerHistory;
import lk.quantacom.smarterpbackend.entity.LedgerPettyCashBook;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerHistoryRepository;
import lk.quantacom.smarterpbackend.repository.LedgerPettyCashBookRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.LedgerPettyCashBookService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerPettyCashBookServiceImpl implements LedgerPettyCashBookService {

    @Autowired
    private LedgerPettyCashBookRepository ledgerPettyCashBookRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private static LedgerPettyCashBookResponse convert(LedgerPettyCashBook ledgerPettyCashBook) {

        LedgerPettyCashBookResponse typeResponse = new LedgerPettyCashBookResponse();
        typeResponse.setBranchId(ledgerPettyCashBook.getBranchId());
        typeResponse.setCartage(ledgerPettyCashBook.getCartage());
        typeResponse.setCashReceived(ledgerPettyCashBook.getCashReceived());
        typeResponse.setCbFollow(ledgerPettyCashBook.getCbFollow());
        typeResponse.setDesctription(ledgerPettyCashBook.getDesctription());
        typeResponse.setEntartainment(ledgerPettyCashBook.getEntartainment());
        typeResponse.setFoods(ledgerPettyCashBook.getFoods());
        typeResponse.setGrandTotal(ledgerPettyCashBook.getGrandTotal());
        typeResponse.setLedgerAcc(ledgerPettyCashBook.getLedgerAcc());
        typeResponse.setLedgerPettyCashBookId(ledgerPettyCashBook.getLedgerPettyCashBookId());
        typeResponse.setLineNo(ledgerPettyCashBook.getLineNo());
        typeResponse.setPayeeName(ledgerPettyCashBook.getPayeeName());
        typeResponse.setPayingAmount(ledgerPettyCashBook.getPayingAmount());
        typeResponse.setPettyCashDate(ledgerPettyCashBook.getPettyCashDate());
        typeResponse.setPostage(ledgerPettyCashBook.getPostage());
        typeResponse.setStationary(ledgerPettyCashBook.getStationary());
        typeResponse.setTimeTake(ledgerPettyCashBook.getTimeTake());
        typeResponse.setTravel(ledgerPettyCashBook.getTravel());
        typeResponse.setVoucherNo(ledgerPettyCashBook.getVoucherNo());
        typeResponse.setId(ledgerPettyCashBook.getId());
        typeResponse.setCreatedBy(ledgerPettyCashBook.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerPettyCashBook.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerPettyCashBook.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerPettyCashBook.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerPettyCashBook.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public LedgerPettyCashBookResponse save(LedgerPettyCashBookRequest request) {

        int ex=1;
        Integer max=ledgerPettyCashBookRepository.getMax();
        if(max!=null){
            ex=max+1;
        }

        LedgerPettyCashBook ledgerPettyCashBook = new LedgerPettyCashBook();
        ledgerPettyCashBook.setBranchId(request.getBranchId());
        ledgerPettyCashBook.setCartage(request.getCartage());
        ledgerPettyCashBook.setCashReceived(request.getCashReceived());
        ledgerPettyCashBook.setCbFollow(request.getCbFollow());
        ledgerPettyCashBook.setDesctription(request.getDesctription());
        ledgerPettyCashBook.setEntartainment(request.getEntartainment());
        ledgerPettyCashBook.setFoods(request.getFoods());
        ledgerPettyCashBook.setGrandTotal(request.getGrandTotal());
        ledgerPettyCashBook.setLedgerAcc(request.getLedgerAcc());
        ledgerPettyCashBook.setLedgerPettyCashBookId(ex);
        ledgerPettyCashBook.setLineNo(request.getLineNo());
        ledgerPettyCashBook.setPayeeName(request.getPayeeName());
        ledgerPettyCashBook.setPayingAmount(request.getPayingAmount());
        ledgerPettyCashBook.setPettyCashDate(request.getPettyCashDate() == null ? null : ConvertUtils.convertStrToDate(request.getPettyCashDate()));
        ledgerPettyCashBook.setPostage(request.getPostage());
        ledgerPettyCashBook.setStationary(request.getStationary());
        ledgerPettyCashBook.setTimeTake(request.getTimeTake()== null ? null : ConvertUtils.convertStrToDate(request.getTimeTake()));
        ledgerPettyCashBook.setTravel(request.getTravel());
        ledgerPettyCashBook.setVoucherNo(request.getVoucherNo());
        ledgerPettyCashBook.setIsDeleted(Deleted.NO);
        LedgerPettyCashBook save = ledgerPettyCashBookRepository.save(ledgerPettyCashBook);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerPettyCashBookResponse update(LedgerPettyCashBookUpdateRequest request) {

        LedgerPettyCashBook ledgerPettyCashBook = ledgerPettyCashBookRepository.findById(request.getId()).orElse(null);
        if (ledgerPettyCashBook == null) {
            return null;
        }

        ledgerPettyCashBook.setId(request.getId());
        ledgerPettyCashBook.setBranchId(request.getBranchId());
        ledgerPettyCashBook.setCartage(request.getCartage());
        ledgerPettyCashBook.setCashReceived(request.getCashReceived());
        ledgerPettyCashBook.setCbFollow(request.getCbFollow());
        ledgerPettyCashBook.setDesctription(request.getDesctription());
        ledgerPettyCashBook.setEntartainment(request.getEntartainment());
        ledgerPettyCashBook.setFoods(request.getFoods());
        ledgerPettyCashBook.setGrandTotal(request.getGrandTotal());
        ledgerPettyCashBook.setLedgerAcc(request.getLedgerAcc());
        ledgerPettyCashBook.setLedgerPettyCashBookId(request.getLedgerPettyCashBookId());
        ledgerPettyCashBook.setLineNo(request.getLineNo());
        ledgerPettyCashBook.setPayeeName(request.getPayeeName());
        ledgerPettyCashBook.setPayingAmount(request.getPayingAmount());
        ledgerPettyCashBook.setPettyCashDate(request.getPettyCashDate() == null ? null : ConvertUtils.convertStrToDate(request.getPettyCashDate()));
        ledgerPettyCashBook.setPostage(request.getPostage());
        ledgerPettyCashBook.setStationary(request.getStationary());
        ledgerPettyCashBook.setTimeTake(request.getTimeTake()== null ? null : ConvertUtils.convertStrToDate(request.getTimeTake()));
        ledgerPettyCashBook.setTravel(request.getTravel());
        ledgerPettyCashBook.setVoucherNo(request.getVoucherNo());
        LedgerPettyCashBook updated = ledgerPettyCashBookRepository.save(ledgerPettyCashBook);

        return (convert(updated));
    }

    @Override
    public LedgerPettyCashBookResponse getById(Long id) {

        return ledgerPettyCashBookRepository.findById(id).map(LedgerPettyCashBookServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerPettyCashBookResponse> getAll() {

        return ledgerPettyCashBookRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerPettyCashBookServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerPettyCashBook got = ledgerPettyCashBookRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerPettyCashBookRepository.save(got);

        return 1;
    }

    @Override
    public List<LedgerPettyCashBookResponse> saveAll1(List<LedgerPettyCashBookSaveListRequest> saveListRequests) {
        int ex=1;
        Integer max=ledgerPettyCashBookRepository.getMax();
        if(max!=null){
            ex=max+1;
        }
        List<LedgerPettyCashBookResponse> responseList = new ArrayList<>();
        int intLineNo = 1;
        for(LedgerPettyCashBookSaveListRequest request:saveListRequests){
            LedgerPettyCashBook ledgerPettyCashBook = new LedgerPettyCashBook();
            ledgerPettyCashBook.setBranchId(request.getBranchId());
            ledgerPettyCashBook.setCartage(request.getCartage());
            ledgerPettyCashBook.setCashReceived(0.00);
            ledgerPettyCashBook.setCbFollow("0");
            ledgerPettyCashBook.setDesctription(request.getDesctription());
            ledgerPettyCashBook.setEntartainment(request.getEntartainment());
            ledgerPettyCashBook.setFoods(request.getFoods());
            ledgerPettyCashBook.setGrandTotal(request.getGrandTotal());
            ledgerPettyCashBook.setLedgerAcc(request.getLedgerAcc());
            ledgerPettyCashBook.setLedgerPettyCashBookId(ex);
            ledgerPettyCashBook.setLineNo(intLineNo);
            ledgerPettyCashBook.setPayeeName(request.getPayeeName());
            ledgerPettyCashBook.setPayingAmount(request.getPayingAmount());
            ledgerPettyCashBook.setPettyCashDate(request.getPettyCashDate() == null ? null : ConvertUtils.convertStrToDate(request.getPettyCashDate()));
            ledgerPettyCashBook.setPostage(request.getPostage());
            ledgerPettyCashBook.setStationary(request.getStationary());
            ledgerPettyCashBook.setTimeTake(request.getTimeTake()== null ? null : ConvertUtils.convertStrToDate(request.getTimeTake()));
            ledgerPettyCashBook.setTravel(request.getTravel());
            ledgerPettyCashBook.setVoucherNo(request.getVoucherNo());
            ledgerPettyCashBook.setIsDeleted(Deleted.NO);
            LedgerPettyCashBook save = ledgerPettyCashBookRepository.save(ledgerPettyCashBook);
            responseList.add(convert(save));

            intLineNo++;

            LedgerHistory ledgerHistory = new LedgerHistory();
            ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
            ledgerHistory.setLadgerAccountId(request.getLedgerDebitId());
            ledgerHistory.setUpdateFram("PETTY CASH VOUCHER");
            ledgerHistory.setUpdateFramDocNo(save.getId().toString());
            ledgerHistory.setRemark(" Petty cash info of "+request.getPettyCashDate());
            ledgerHistory.setUpdateBalance(request.getPayingAmount());
            ledgerHistory.setTransType("DEBIT");
            ledgerHistory.setCreditAmount(0.00);
            ledgerHistory.setDebitAmount(request.getPayingAmount());
            ledgerHistory.setCreditColumnName(request.getCreditAccName());
            ledgerHistory.setDebitColumnName("-");
            ledgerHistory.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerHistory);

            LedgerHistory ledgerHistory1 = new LedgerHistory();
            ledgerHistory1.setBranchId(Long.parseLong(request.getBranchId()));
            ledgerHistory1.setLadgerAccountId(request.getLedgerCreditId());
            ledgerHistory1.setUpdateFram("PETTY CASH VOUCHER");
            ledgerHistory1.setUpdateFramDocNo(save.getId().toString());
            ledgerHistory1.setRemark(" Petty cash info of "+request.getPettyCashDate());
            ledgerHistory1.setUpdateBalance(request.getPayingAmount());
            ledgerHistory1.setTransType("CREDIT");
            ledgerHistory1.setCreditAmount(request.getPayingAmount());
            ledgerHistory1.setDebitAmount(0.00);
            ledgerHistory1.setCreditColumnName("-");
            ledgerHistory1.setDebitColumnName(request.getDebitAccName());
            ledgerHistory1.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerHistory1);

            saveLog("Petty Cash Voucher", "Data Saved - "+ save.getId());
        }


        return responseList;
    }

    @Override
    public List<LedgerPettyCashBookResponse> getByDates(String fromDate, String toDate, Long branchId) {

       List<LedgerPettyCashBook> pettyCashBooks= ledgerPettyCashBookRepository.getByDateRanges(fromDate,toDate,branchId);
       if(pettyCashBooks!=null){
           return pettyCashBooks.stream().map(LedgerPettyCashBookServiceImpl::convert).collect(Collectors.toList());
       }
        return null;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}