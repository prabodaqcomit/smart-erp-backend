package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ChequeDepositAndReceivedRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeDepositUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepoReq;
import lk.quantacom.smarterpbackend.dto.response.ChequeDepositResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ChequeDepositService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChequeDepositServiceImpl implements ChequeDepositService {

    @Autowired
    private ReceivedChequesRepository receivedChequesRepository;

    @Autowired
    private ChequeDepositRepository chequeDepositRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private LedgerBankAccountDebitRepository ledgerBankAccountDebitRepository;

    @Autowired
    private LedgerBankHistoryRepository ledgerBankHistoryRepository;

    @Autowired
    private LedgerCashbookCreditRepository ledgerCashbookCreditRepository;


    @Override
    @Transactional
    public ChequeDepositResponse save(ChequeDepositRequest request) {

        ChequeDeposit chequeDeposit = new ChequeDeposit();
        chequeDeposit.setBranchId(request.getBranchId());
        chequeDeposit.setChequeDeposit(request.getChequeDeposit());
        chequeDeposit.setCustomerId(request.getCustomerId());
        chequeDeposit.setDepositAmount(request.getDepositAmount());
        chequeDeposit.setGrandTotal(request.getGrandTotal());
        chequeDeposit.setLineNo(request.getLineNo());
        chequeDeposit.setDate(request.getDate()== null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        chequeDeposit.setReceivedChequesId(request.getReceivedChequesId());
        chequeDeposit.setIsDeleted(Deleted.NO);
        ChequeDeposit save = chequeDepositRepository.save(chequeDeposit);

        return convert(save);
    }

    @Override
    @Transactional
    public ChequeDepositResponse update(ChequeDepositUpdateRequest request) {

        ChequeDeposit chequeDeposit = chequeDepositRepository.findById(request.getId()).orElse(null);
        if (chequeDeposit == null) {
            return null;
        }

        chequeDeposit.setId(request.getId());
        chequeDeposit.setBranchId(request.getBranchId());
        chequeDeposit.setChequeDeposit(request.getChequeDeposit());
        chequeDeposit.setCustomerId(request.getCustomerId());
        chequeDeposit.setDepositAmount(request.getDepositAmount());
        chequeDeposit.setGrandTotal(request.getGrandTotal());
        chequeDeposit.setLineNo(request.getLineNo());
        chequeDeposit.setReceivedChequesId(request.getReceivedChequesId());
        ChequeDeposit updated = chequeDepositRepository.save(chequeDeposit);

        return (convert(updated));
    }

    @Override
    public ChequeDepositResponse getById(Long id) {

        return chequeDepositRepository.findById(id).map(ChequeDepositServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ChequeDepositResponse> getAll() {

        return chequeDepositRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ChequeDepositServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ChequeDeposit got = chequeDepositRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        chequeDepositRepository.save(got);

        return 1;
    }

    @Override
    public List<ChequeDepositResponse> saveAll(List<ChequeDepositAndReceivedRequest> requests) {

        List<ChequeDepositResponse> Responses = new ArrayList<>();

        int intLineNo = 1;

        for (ChequeDepositAndReceivedRequest request:requests){

            ChequeDeposit chequeDeposit = new ChequeDeposit();
            chequeDeposit.setBranchId(request.getBranchId());
            chequeDeposit.setChequeDeposit(request.getChequeDeposit());
            chequeDeposit.setCustomerId(request.getCustomerId());
            chequeDeposit.setDepositAmount(request.getDepositAmount());
            chequeDeposit.setGrandTotal(request.getGrandTotal());
            chequeDeposit.setDepoBankId(Integer.parseInt(request.getDepoBank()));
            chequeDeposit.setLineNo(intLineNo);
            chequeDeposit.setReceivedChequesId(request.getReceivedChequesId());
            chequeDeposit.setIsDeleted(Deleted.NO);
            chequeDeposit.setDate(request.getDepoDate()== null ? null : ConvertUtils.convertStrToDate(request.getDepoDate()));
            ChequeDeposit save = chequeDepositRepository.save(chequeDeposit);
            Responses.add(convert(save));

            intLineNo++;

            ReceivedCheques cheques = receivedChequesRepository.findByIdAndChequeNo(request.getReceivedChequesId(),request.getChequeDeposit());
            if(cheques!=null){
                cheques.setStatus("DEPOSIT ON BANK");
                cheques.setDepoBank(request.getDepoBank());
                cheques.setDepoDate(request.getDepoDate()== null ? null : ConvertUtils.convertStrToDate(request.getDepoDate()));
                cheques.setRemarks(request.getRemarks());
                receivedChequesRepository.save(cheques);
            }

            LedgerHistory ledgerHistory = new LedgerHistory();
            ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
            ledgerHistory.setLadgerAccountId(request.getLedgerDebitId());
            ledgerHistory.setUpdateFram("Cheque Deposit Note");
            ledgerHistory.setUpdateFramDocNo(save.getId().toString());
            ledgerHistory.setRemark("Cheque No = "+request.getChequeDeposit());
            ledgerHistory.setUpdateBalance(request.getDepositAmount());
            ledgerHistory.setTransType("DEBIT");
            ledgerHistory.setCreditAmount(0.00);
            ledgerHistory.setDebitAmount(request.getDepositAmount());
            ledgerHistory.setCreditColumnName(request.getCreditAccountName());
            ledgerHistory.setDebitColumnName("-");
            ledgerHistory.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerHistory);


            ledgerHistory.setBranchId(Long.parseLong(request.getBranchId()));
            ledgerHistory.setLadgerAccountId(request.getLedgerDebitId());
            ledgerHistory.setUpdateFram("Cheque Deposit Note");
            ledgerHistory.setUpdateFramDocNo(save.getId().toString());
            ledgerHistory.setRemark("Cheque No = "+request.getChequeDeposit());
            ledgerHistory.setUpdateBalance(request.getDepositAmount());
            ledgerHistory.setTransType("CREDIT");
            ledgerHistory.setCreditAmount(request.getDepositAmount());
            ledgerHistory.setDebitAmount(0.00);
            ledgerHistory.setCreditColumnName("-");
            ledgerHistory.setDebitColumnName(request.getDebitAccountName());
            ledgerHistory.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerHistory);

            LedgerBankAccountDebit ledgerBankAccountDebit = new LedgerBankAccountDebit();
            ledgerBankAccountDebit.setBranchId(Long.parseLong(request.getBranchId()));
            ledgerBankAccountDebit.setCashAmount(request.getDepositAmount());
            ledgerBankAccountDebit.setDescription("CHEQUE DEPOSIT");
            ledgerBankAccountDebit.setOtherAccNo(request.getLedgerDebitId().toString());
            ledgerBankAccountDebit.setOtherAccountName(request.getDebitAccountName());
            ledgerBankAccountDebit.setPrNo("-");
            ledgerBankAccountDebit.setIsDeleted(Deleted.NO);
            ledgerBankAccountDebitRepository.save(ledgerBankAccountDebit);

            LedgerBankHistory ledgerBankHistory = new LedgerBankHistory();
            ledgerBankHistory.setAmount(request.getDepositAmount());
            ledgerBankHistory.setBankAccountId(Integer.parseInt(request.getDepoBank()));
            ledgerBankHistory.setCreditAccAmount(0.00);
            ledgerBankHistory.setCreditAccId(Integer.parseInt(request.getLedgerCreditId().toString()));
            ledgerBankHistory.setCurrentBalance(0.00);
            ledgerBankHistory.setDebitAccAmount(request.getDepositAmount());
            ledgerBankHistory.setDebitAccId(Integer.parseInt(request.getLedgerDebitId().toString()));
            ledgerBankHistory.setDepoType("CASH");
            ledgerBankHistory.setFramDocNo(Integer.parseInt(save.getId().toString()));
            ledgerBankHistory.setFramName("Cheque Deposit Note");
            ledgerBankHistory.setTransaction("Cheque No = "+request.getChequeDeposit());
            ledgerBankHistory.setIsDeleted(Deleted.NO);
            ledgerBankHistoryRepository.save(ledgerBankHistory);

            LedgerCashbookCredit ledgerCashbookCredit = new LedgerCashbookCredit();
            ledgerCashbookCredit.setBankAmount(0.00);
            ledgerCashbookCredit.setBranchId(request.getBranchId());
            ledgerCashbookCredit.setCashAmount(request.getDepositAmount());
            ledgerCashbookCredit.setDescription(request.getDebitAccountName());
            ledgerCashbookCredit.setDiscountAllowed(0.00);
            ledgerCashbookCredit.setOtherAccNo(request.getLedgerCreditId().toString());
            ledgerCashbookCredit.setOtherAccountName(request.getCreditAccountName());
            ledgerCashbookCredit.setPrNo("-");
            ledgerCashbookCredit.setVcharNo(save.getId().toString());
            ledgerCashbookCredit.setIsDeleted(Deleted.NO);
            ledgerCashbookCreditRepository.save(ledgerCashbookCredit);

            saveLog("Cheque Deposit Note", "Data Deleted - " + save.getId());
        }

        return Responses;
    }

    @Override
    public List<ChequeDepoReq> getForChRtn() {

        return chequeDepositRepository.getCh();
    }

    private static ChequeDepositResponse convert(ChequeDeposit chequeDeposit) {

        ChequeDepositResponse typeResponse = new ChequeDepositResponse();
        typeResponse.setBranchId(chequeDeposit.getBranchId());
        typeResponse.setChequeDeposit(chequeDeposit.getChequeDeposit());
        typeResponse.setCustomerId(chequeDeposit.getCustomerId());
        typeResponse.setDepositAmount(chequeDeposit.getDepositAmount());
        typeResponse.setGrandTotal(chequeDeposit.getGrandTotal());
        typeResponse.setLineNo(chequeDeposit.getLineNo());
        typeResponse.setReceivedChequesId(chequeDeposit.getReceivedChequesId());
        typeResponse.setId(chequeDeposit.getId());
        typeResponse.setCreatedBy(chequeDeposit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(chequeDeposit.getCreatedDateTime()));
        typeResponse.setModifiedBy(chequeDeposit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(chequeDeposit.getModifiedDateTime()));
        typeResponse.setIsDeleted(chequeDeposit.getIsDeleted());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}