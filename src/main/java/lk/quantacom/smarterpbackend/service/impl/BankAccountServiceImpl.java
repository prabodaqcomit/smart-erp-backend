package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.BankAccountResponse;
import lk.quantacom.smarterpbackend.dto.response.LedgerCommonSelectionsResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.objects.ClsLedgerCommonSelections;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.BankAccountService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Autowired
    private LedgerBankHistoryRepository ledgerBankHistoryRepository;

    @Autowired
    private LedgerBankAccountCreditRepository ledgerBankAccountCreditRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private LedgerCommonSelectionsRepository ledgerCommonSelectionsRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private LedgerBankAccountDebitRepository ledgerBankAccountDebitRepository;

    @Override
    @Transactional
    public BankAccountResponse save(BankAccountRequest request) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccDate(request.getAccDate() == null ? null : ConvertUtils.convertStrToDate(request.getAccDate()));
        bankAccount.setAccName(request.getAccName());
        bankAccount.setAccNo(request.getAccNo());
        bankAccount.setBankCode(request.getBankCode());
        bankAccount.setBankLedgerId(request.getBankLedgerId());
        bankAccount.setBankName(request.getBankName());
        bankAccount.setBranchCode(request.getBranchCode());
        bankAccount.setBranchName(request.getBranchName());
        bankAccount.setCurrentBalance(request.getCurrentBalance());
        //bankAccount.setDeleteMe(request.getDeleteMe());
        bankAccount.setObBalance(request.getObBalance());
        bankAccount.setIsDeleted(Deleted.NO);
        BankAccount save = bankAccountRepository.save(bankAccount);

        return convert(save);
    }

    @Override
    @Transactional
    public BankAccountResponse update(BankAccountUpdateRequest request) {

        BankAccount bankAccount = bankAccountRepository.findById(request.getId()).orElse(null);
        if (bankAccount == null) {
            return null;
        }

        bankAccount.setId(request.getId());
        bankAccount.setAccDate(request.getAccDate() == null ? null : ConvertUtils.convertStrToDate(request.getAccDate()));
        bankAccount.setAccName(request.getAccName());
        bankAccount.setAccNo(request.getAccNo());
        bankAccount.setBankCode(request.getBankCode());
        bankAccount.setBankLedgerId(request.getBankLedgerId());
        bankAccount.setBankName(request.getBankName());
        bankAccount.setBranchCode(request.getBranchCode());
        bankAccount.setBranchName(request.getBranchName());
        bankAccount.setCurrentBalance(request.getCurrentBalance());
        //bankAccount.setDeleteMe(request.getDeleteMe());
        bankAccount.setObBalance(request.getObBalance());
        BankAccount updated = bankAccountRepository.save(bankAccount);

        return (convert(updated));
    }

    @Override
    public BankAccountResponse getById(Long id) {

        return bankAccountRepository.findById(id).map(BankAccountServiceImpl::convert).orElse(null);
    }

    @Override
    public BankAccountResponse getByAccNum(String accNo) {
        BankAccount account =bankAccountRepository.findByIsDeletedAndAccNo(Deleted.NO,accNo);
        return convert(account);
    }

    @Override
    public BankAccountResponse updateBankLedgerAccount(LedgerAccountNameUpdateRequest request) {

        BankAccount bankAccount = bankAccountRepository.getById(request.getId());

        bankAccount.setBankCode(request.getBankCode());
        bankAccount.setBranchCode(request.getBranchCode());
        bankAccount.setAccNo(request.getAccNo());
        bankAccount.setBankName(request.getBankName());
        bankAccount.setBranchName(request.getBranchName());
        bankAccount.setAccName(request.getAccName());
        BankAccount update = bankAccountRepository.save(bankAccount);

        LadgerAccount ladgerAccount=ladgerAccountRepository.getById(request.getLedgerAccountId());
        ladgerAccount.setAccName(request.getBankName()+" - "+request.getAccNo());
        ladgerAccountRepository.save(ladgerAccount);
        saveLog("Company Bank Account", "Data update - " + update.getId());

        return convert(update);
    }

    @Override
    public List<BankAccountResponse> getByBankName(String bankName) {
        return bankAccountRepository.findByBankNameOrderByBankNameAsc(bankName)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountResponse> getByBranchName(String branchName) {
        return bankAccountRepository.findByBranchName(branchName)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountResponse> getByAccName(String accName) {
        return bankAccountRepository.findByAccName(accName)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountResponse> getAll() {

        return bankAccountRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        BankAccount got = bankAccountRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bankAccountRepository.save(got);

        return 1;
    }

    @Override
    public List<BankAccountResponse> getByBankNameAndAccNo(String bankName, String accNo) {
        return bankAccountRepository.findByBankNameAndAccNoOrderByAccNo(bankName,accNo)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountResponse> getByAccNo(String accNo) {
        return bankAccountRepository.findByAccNo(accNo)
                .stream().map(BankAccountServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BankAccountResponse> saveWithLedgerAccountName(List<LedgerAccountNameRequest> requests) {
        List<BankAccountResponse> responses = new ArrayList<>();

        for(LedgerAccountNameRequest request:requests){
            BankAccount account = bankAccountRepository.findByIsDeletedAndAccNo(Deleted.NO,request.getAccNo());

            if(account==null){

                String ownNo=ladgerAccountRepository.getMaxOwnNoBank();
                if(ownNo==null){
                    ownNo="1";
                }else{
                    ownNo=(Integer.parseInt(ownNo)+1)+"";
                }

                LadgerAccount ladgerAccount = new LadgerAccount();
                ladgerAccount.setAccountCategory("ASSETS");
                ladgerAccount.setAccType("CONVERTIBILITY - CURRENT ASSET");
                ladgerAccount.setSubAccType("BANK");
                ladgerAccount.setAccNo("1001114"+ownNo);
                ladgerAccount.setAccName(request.getBankName()+"-"+request.getAccNo());
                ladgerAccount.setObBalance(request.getObBalance());
                ladgerAccount.setCurrentBalance(request.getObBalance());
                ladgerAccount.setGeneratedNo("1001114");
                ladgerAccount.setOwnNo(ownNo);
                ladgerAccount.setIsDefault(false);
                ladgerAccount.setIsDeleted(Deleted.NO);
                BranchNetwork branch = new BranchNetwork();
                branch.setId(request.getBranchId());
                ladgerAccount.setBranch(branch);
                ladgerAccountRepository.save(ladgerAccount);

                BankAccount bankAccount = new BankAccount();
                bankAccount.setAccDate(request.getAccDate() == null ? null : ConvertUtils.convertStrToDate(request.getAccDate()));
                bankAccount.setAccName(request.getAccName());
                bankAccount.setAccNo(request.getAccNo());
                bankAccount.setBankCode(request.getBankCode());
                bankAccount.setBankLedgerId(request.getBankLedgerId());
                bankAccount.setBankName(request.getBankName());
                bankAccount.setBranchCode(request.getBranchCode());
                bankAccount.setBranchName(request.getBranchName());
                bankAccount.setCurrentBalance(request.getCurrentBalance());
                //bankAccount.setDeleteMe(request.getDeleteMe());
                bankAccount.setObBalance(request.getObBalance());
                bankAccount.setIsDeleted(Deleted.NO);
                BankAccount save = bankAccountRepository.save(bankAccount);
                responses.add(convert(save));

                //Ledger common Selection Save service
                saveLedger(request.getBankLedgerId(),request.getAccNo());

                if(request.getObBalanceType().equals("AS A DEBIT BALANCE")){

                    LedgerHistory ledgerHistory = new LedgerHistory();
                    ledgerHistory.setBranchId(request.getBranchId());
                    ledgerHistory.setLadgerAccountId(Long.parseLong(request.getBankLedgerId()));
                    ledgerHistory.setUpdateFram("NEW COMPANY BANK ACCOUNT");
                    ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                    ledgerHistory.setRemark(" New Bank Account created under acc No = "+ request.getAccNo());
                    ledgerHistory.setUpdateBalance(request.getObBalance());
                    ledgerHistory.setTransType("DEBIT");
                    ledgerHistory.setCreditAmount(0.00);
                    ledgerHistory.setDebitAmount(request.getObBalance());
                    ledgerHistory.setCreditColumnName("-");
                    ledgerHistory.setDebitColumnName("-");
                    ledgerHistory.setIsDeleted(Deleted.NO);
                    ledgerHistoryRepository.save(ledgerHistory);

                    LedgerBankAccountDebit ledgerBankAccountDebit = new LedgerBankAccountDebit();
                    ledgerBankAccountDebit.setBranchId(request.getBranchId());
                    ledgerBankAccountDebit.setCashAmount(request.getObBalance());
                    ledgerBankAccountDebit.setDescription("BANK OPENNING BALANCE");
                    ledgerBankAccountDebit.setOtherAccNo(request.getBankLedgerId());
                    ledgerBankAccountDebit.setOtherAccountName(request.getBankName()+" - "+request.getAccNo());
                    ledgerBankAccountDebit.setPrNo("-");
                    ledgerBankAccountDebit.setIsDeleted(Deleted.NO);
                    ledgerBankAccountDebitRepository.save(ledgerBankAccountDebit);

                    LedgerBankHistory ledgerBankHistory = new LedgerBankHistory();
                    ledgerBankHistory.setAmount(request.getObBalance());
                    ledgerBankHistory.setBankAccountId(Integer.parseInt(save.getId().toString()));
                    ledgerBankHistory.setCreditAccAmount(0.00);
                    ledgerBankHistory.setCreditAccId(Integer.parseInt(request.getBankLedgerId()));
                    ledgerBankHistory.setCurrentBalance(0.00);
                    ledgerBankHistory.setDebitAccAmount(request.getObBalance());
                    ledgerBankHistory.setDebitAccId(Integer.parseInt(request.getBankLedgerId()));
                    ledgerBankHistory.setDepoType("CASH");
                    ledgerBankHistory.setFramDocNo(Integer.parseInt(save.getId().toString()));
                    ledgerBankHistory.setFramName("NEW COMPANY BANK ACCOUNT");
                    ledgerBankHistory.setTransaction("BANK OPENNING BALANCE");
                    ledgerBankHistory.setIsDeleted(Deleted.NO);
                    ledgerBankHistoryRepository.save(ledgerBankHistory);

                }
                else {
                    LedgerHistory ledgerHistory = new LedgerHistory();
                    ledgerHistory.setBranchId(request.getBranchId());
                    ledgerHistory.setLadgerAccountId(Long.parseLong(request.getBankLedgerId()));
                    ledgerHistory.setUpdateFram("NEW COMPANY BANK ACCOUNT");
                    ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                    ledgerHistory.setRemark(" New Bank Account created under acc No = "+ request.getAccNo());
                    ledgerHistory.setUpdateBalance(request.getObBalance());
                    ledgerHistory.setTransType("CREDIT");
                    ledgerHistory.setCreditAmount(request.getObBalance());
                    ledgerHistory.setDebitAmount(0.00);
                    ledgerHistory.setCreditColumnName("-");
                    ledgerHistory.setDebitColumnName(request.getBankName()+" - "+request.getAccNo());
                    ledgerHistory.setIsDeleted(Deleted.NO);
                    ledgerHistoryRepository.save(ledgerHistory);

                    LedgerBankAccountCredit ledgerBankAccountCredit = new LedgerBankAccountCredit();
                    ledgerBankAccountCredit.setBranchId(request.getBranchId());
                    ledgerBankAccountCredit.setCashAmount(request.getObBalance());
                    ledgerBankAccountCredit.setDescription("BANK OPENNING BALANCE");
                    ledgerBankAccountCredit.setOtherAccNo(request.getBankLedgerId());
                    ledgerBankAccountCredit.setOtherAccountName(request.getBankName()+" - "+request.getAccNo());
                    ledgerBankAccountCredit.setPrNo("-");
                    ledgerBankAccountCredit.setIsDeleted(Deleted.NO);
                    ledgerBankAccountCreditRepository.save(ledgerBankAccountCredit);
                }
                saveLog("Company Bank Account", "Data Saved - " + save.getId());
            }

        }


        return responses;
    }

    public static BankAccountResponse convert(BankAccount bankAccount) {

        BankAccountResponse typeResponse = new BankAccountResponse();
        typeResponse.setAccDate(bankAccount.getAccDate());
        typeResponse.setAccName(bankAccount.getAccName());
        typeResponse.setAccNo(bankAccount.getAccNo());
        typeResponse.setBankCode(bankAccount.getBankCode());
        typeResponse.setBankLedgerId(bankAccount.getBankLedgerId());
        typeResponse.setBankName(bankAccount.getBankName());
        typeResponse.setBranchCode(bankAccount.getBranchCode());
        typeResponse.setBranchName(bankAccount.getBranchName());
        typeResponse.setCurrentBalance(bankAccount.getCurrentBalance());
        //typeResponse.setDeleteMe(bankAccount.getDeleteMe());
        typeResponse.setObBalance(bankAccount.getObBalance());
        typeResponse.setId(bankAccount.getId());
        typeResponse.setCreatedBy(bankAccount.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bankAccount.getCreatedDateTime()));
        typeResponse.setModifiedBy(bankAccount.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bankAccount.getModifiedDateTime()));
        typeResponse.setIsDeleted(bankAccount.getIsDeleted());

        return typeResponse;
    }

    public  List<LedgerCommonSelectionsResponse> saveLedger(String strBankLedgerId,String strBankLedgerAccNum) {

        List<LedgerCommonSelectionsResponse> responses = new ArrayList<>();

        ClsLedgerCommonSelections clbc1 = new ClsLedgerCommonSelections("M233", "NEW BANK ACCOUNT", "open a new bank account with cash", "1", strBankLedgerId, strBankLedgerAccNum, "1", "001", "B");
        ClsLedgerCommonSelections clbc2 = new ClsLedgerCommonSelections("M233", "NEW BANK ACCOUNT", "open a new bank account with investment", "1", strBankLedgerId, strBankLedgerAccNum, "0", "0", "B");
        ClsLedgerCommonSelections clbc3 = new ClsLedgerCommonSelections("M144", "CUSTOMER CHEQUE RETURNS", "return a customer cheque", "3", "0", "0", strBankLedgerId, strBankLedgerAccNum, "B");
        ClsLedgerCommonSelections clbc4 = new ClsLedgerCommonSelections("M161", "GENERAL PAYMENTS", "Paying a amount from a own cheque", "3", "0", "0", strBankLedgerId, strBankLedgerAccNum, "B");
        ClsLedgerCommonSelections clbc5 = new ClsLedgerCommonSelections("M138", "Payment Receipt", "Deposit a customer cheque on bank another day", "3", strBankLedgerId, strBankLedgerAccNum, "0", "0", "B");
        ClsLedgerCommonSelections clbc6 = new ClsLedgerCommonSelections("M157", "SUPPLIER PAYMENTS", "Paying a amount from a own cheque", "3", "0", "0", strBankLedgerId, strBankLedgerAccNum, "B");
        ClsLedgerCommonSelections clbc7 = new ClsLedgerCommonSelections("M147", "CUSTOMER CHEQUE DEPOSIT NOTE", "deposit cheques in another day", "3", strBankLedgerId, strBankLedgerAccNum, "8", "10011150001", "B");
        ClsLedgerCommonSelections clbc8 = new ClsLedgerCommonSelections("M150", "OWN CHEQUE RETURNS", "own cheque return note", "3", strBankLedgerId, strBankLedgerAccNum, "0", "0", "B");
        ClsLedgerCommonSelections clbc9 = new ClsLedgerCommonSelections("M153", "CASH DEPOSIT NOTE", "deposit sales cash on bank", "1", strBankLedgerId, strBankLedgerAccNum, "1", "001", "B");

        ClsLedgerCommonSelections[] CommonAccountArray = new ClsLedgerCommonSelections[9];
        CommonAccountArray[0] = clbc1;
        CommonAccountArray[1] = clbc2;
        CommonAccountArray[2] = clbc3;
        CommonAccountArray[3] = clbc4;
        CommonAccountArray[4] = clbc5;
        CommonAccountArray[5] = clbc6;
        CommonAccountArray[6] = clbc7;
        CommonAccountArray[7] = clbc8;
        CommonAccountArray[8] = clbc9;

        for(ClsLedgerCommonSelections CommonAccountArray1 : CommonAccountArray) {
            LedgerCommonSelections ledgerCommonSelections = new LedgerCommonSelections();
            ledgerCommonSelections.setCreditAccId(Integer.parseInt(CommonAccountArray1.getCredit_acc_id_STRING()));
            ledgerCommonSelections.setCreditAccNum(CommonAccountArray1.getCredit_acc_num_STRING());

            ledgerCommonSelections.setDebitAccId(Integer.parseInt(CommonAccountArray1.getDebit_acc_id_STRING()));
            ledgerCommonSelections.setDebitAccNum(CommonAccountArray1.getDebit_acc_num_STRING());

            ledgerCommonSelections.setDescription(CommonAccountArray1.getDescription_STRING());
            ledgerCommonSelections.setFramName(CommonAccountArray1.getFram_name_STRING());
            ledgerCommonSelections.setFrameId(CommonAccountArray1.getFrame_id_STRING());
            ledgerCommonSelections.setPayMode(CommonAccountArray1.getPay_mode_STRING());
            ledgerCommonSelections.setRecordRight(CommonAccountArray1.getRecord_right_STRING());
            LedgerCommonSelections save = ledgerCommonSelectionsRepository.save(ledgerCommonSelections);
            responses.add(convertLedger(save));
        }

         return responses;
    }

    private static LedgerCommonSelectionsResponse convertLedger(LedgerCommonSelections ledgerCommonSelections) {

        LedgerCommonSelectionsResponse typeResponse = new LedgerCommonSelectionsResponse();
        typeResponse.setCreditAccId(ledgerCommonSelections.getCreditAccId());
        typeResponse.setCreditAccNum(ledgerCommonSelections.getCreditAccNum());
        typeResponse.setDebitAccId(ledgerCommonSelections.getDebitAccId());
        typeResponse.setDebitAccNum(ledgerCommonSelections.getDebitAccNum());
        typeResponse.setDescription(ledgerCommonSelections.getDescription());
        typeResponse.setFramName(ledgerCommonSelections.getFramName());
        typeResponse.setFrameId(ledgerCommonSelections.getFrameId());
        typeResponse.setLedgerCommonSelectionsId(ledgerCommonSelections.getLedgerCommonSelectionsId());
        typeResponse.setPayMode(ledgerCommonSelections.getPayMode());
        typeResponse.setRecordRight(ledgerCommonSelections.getRecordRight());
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