package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsSaveAllRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsSaveListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ChequeReturnsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChequeReturnsServiceImpl implements ChequeReturnsService {

    @Autowired
    private ChequeReturnsRepository chequeReturnsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ReceivedChequesRepository receivedChequesRepository;

    @Autowired
    private LedgerBankHistoryRepository ledgerBankHistoryRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private LedgerBankAccountCreditRepository ledgerBankAccountCreditRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    private static ChequeReturnsResponse convert(ChequeReturns chequeReturns) {

        ChequeReturnsResponse typeResponse = new ChequeReturnsResponse();
        typeResponse.setBankCode(chequeReturns.getBankCode());
        typeResponse.setBranchCode(chequeReturns.getBranchCode());
        typeResponse.setBranchId(chequeReturns.getBranchId());
        typeResponse.setChequeNo(chequeReturns.getChequeNo());
        typeResponse.setChequePayDate(chequeReturns.getChequePayDate());
        typeResponse.setChequeReturns(chequeReturns.getChequeReturns());
        typeResponse.setCustomerId(chequeReturns.getCustomerId());
        typeResponse.setInvoiceNum(chequeReturns.getInvoiceNum());
        typeResponse.setRemarks(chequeReturns.getRemarks());
        typeResponse.setValue(chequeReturns.getValue());
        typeResponse.setId(chequeReturns.getId());
        typeResponse.setCreatedBy(chequeReturns.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(chequeReturns.getCreatedDateTime()));
        typeResponse.setModifiedBy(chequeReturns.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(chequeReturns.getModifiedDateTime()));
        typeResponse.setIsDeleted(chequeReturns.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ChequeReturnsResponse save(ChequeReturnsRequest request) {

        ChequeReturns chequeReturns = new ChequeReturns();
        chequeReturns.setBankCode(request.getBankCode());
        chequeReturns.setBranchCode(request.getBranchCode());
        chequeReturns.setBranchId(request.getBranchId());
        chequeReturns.setChequeNo(request.getChequeNo());
        chequeReturns.setChequePayDate(request.getChequePayDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequePayDate()));
        chequeReturns.setChequeReturns(request.getChequeReturns());
        chequeReturns.setCustomerId(request.getCustomerId());
        chequeReturns.setInvoiceNum(request.getInvoiceNum());
        chequeReturns.setRemarks(request.getRemarks());
        chequeReturns.setValue(request.getValue());
        chequeReturns.setIsDeleted(Deleted.NO);
        ChequeReturns save = chequeReturnsRepository.save(chequeReturns);

        return convert(save);
    }

    @Override
    @Transactional
    public ChequeReturnsResponse update(ChequeReturnsUpdateRequest request) {

        ChequeReturns chequeReturns = chequeReturnsRepository.findById(request.getId()).orElse(null);
        if (chequeReturns == null) {
            return null;
        }

        chequeReturns.setId(request.getId());
        chequeReturns.setBankCode(request.getBankCode());
        chequeReturns.setBranchCode(request.getBranchCode());
        chequeReturns.setBranchId(request.getBranchId());
        chequeReturns.setChequeNo(request.getChequeNo());
        chequeReturns.setChequePayDate(request.getChequePayDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequePayDate()));
        chequeReturns.setChequeReturns(request.getChequeReturns());
        chequeReturns.setCustomerId(request.getCustomerId());
        chequeReturns.setInvoiceNum(request.getInvoiceNum());
        chequeReturns.setRemarks(request.getRemarks());
        chequeReturns.setValue(request.getValue());
        ChequeReturns updated = chequeReturnsRepository.save(chequeReturns);

        return (convert(updated));
    }

    @Override
    public ChequeReturnsResponse getById(Long id) {

        return chequeReturnsRepository.findById(id).map(ChequeReturnsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ChequeReturnsResponse> getAll() {

        return chequeReturnsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ChequeReturnsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ChequeReturns got = chequeReturnsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        chequeReturnsRepository.save(got);

        return 1;
    }

    @Override
    public List<ChequeReturnsResponse> getByCustomerIdAndBranchId(Integer customerId, String branchId) {
        return chequeReturnsRepository.findByCustomerIdAndBranchId(customerId,branchId)
                .stream().map(ChequeReturnsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeReturnsResponse> getByBankCode(String bankCode) {
        return chequeReturnsRepository.findByBankCode(bankCode)
                .stream().map(ChequeReturnsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeReturnsResponse> getByBranchCode(String branchCode) {
        return chequeReturnsRepository.findByBranchCode(branchCode)
                .stream().map(ChequeReturnsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeReturnsResponse> saveCustomerChequeReturn(ChequeReturnsSaveListRequest request) {
        List<ChequeReturnsResponse> responses = new ArrayList<>();
        int intLineNo = 1;
        for(ChequeReturnsSaveAllRequest chequeReturnsRequest:request.getChequeReturnsRequests()){
            if(chequeReturnsRequest.getInvoiceNum()==null){
                ChequeReturns chequeReturns = new ChequeReturns();
                chequeReturns.setBankCode(chequeReturnsRequest.getBankCode());
                chequeReturns.setBranchCode(chequeReturnsRequest.getBranchCode());
                chequeReturns.setBranchId(chequeReturnsRequest.getBranchId());
                chequeReturns.setChequeNo(request.getChequeNo());
                chequeReturns.setChequePayDate(ConvertUtils.convertStrToDate("1900-01-01"));
                chequeReturns.setChequeReturns(1);
                chequeReturns.setCustomerId(chequeReturnsRequest.getCustomerId());
                chequeReturns.setInvoiceNum("0");
                chequeReturns.setRemarks(chequeReturnsRequest.getRemarks());
                chequeReturns.setValue(chequeReturnsRequest.getValue());
                chequeReturns.setIsDeleted(Deleted.NO);
                ChequeReturns save = chequeReturnsRepository.save(chequeReturns);
                responses.add(convert(save));

                ReceivedCheques cheques = receivedChequesRepository.findByIsDeletedAndChequeNo(Deleted.NO,request.getChequeNo());
                cheques.setStatus("RETURN");
                receivedChequesRepository.save(cheques);

                LedgerHistory ledgerHistory = new LedgerHistory();
                ledgerHistory.setBranchId(request.getBranchId());
                ledgerHistory.setLadgerAccountId(request.getLedgerDebitId());
                ledgerHistory.setUpdateFram("CUSTOMER CHEQUE RETURN");
                ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                ledgerHistory.setRemark(" Cheque Return of "+request.getChequeNo());
                ledgerHistory.setUpdateBalance(request.getChequeAmount());
                ledgerHistory.setTransType("DEBIT");
                ledgerHistory.setCreditAmount(0.00);
                ledgerHistory.setDebitAmount(request.getChequeAmount());
                ledgerHistory.setCreditColumnName(request.getCreditAccountName());
                ledgerHistory.setDebitColumnName("-");
                ledgerHistory.setIsDeleted(Deleted.NO);
                ledgerHistoryRepository.save(ledgerHistory);

                ledgerHistory.setBranchId(request.getBranchId());
                ledgerHistory.setLadgerAccountId(request.getLedgerCreditId());
                ledgerHistory.setUpdateFram("CUSTOMER CHEQUE RETURN");
                ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                ledgerHistory.setRemark(" Cheque Return of "+request.getChequeNo());
                ledgerHistory.setUpdateBalance(request.getChequeAmount());
                ledgerHistory.setTransType("CREDIT");
                ledgerHistory.setCreditAmount(request.getChequeAmount());
                ledgerHistory.setDebitAmount(0.00);
                ledgerHistory.setCreditColumnName(request.getCreditAccountName());
                ledgerHistory.setDebitColumnName("-");
                ledgerHistory.setIsDeleted(Deleted.NO);
                ledgerHistoryRepository.save(ledgerHistory);

                LedgerBankAccountCredit ledgerBankAccountCredit = new LedgerBankAccountCredit();
                ledgerBankAccountCredit.setBranchId(request.getBranchId());
                ledgerBankAccountCredit.setCashAmount(request.getChequeAmount());
                ledgerBankAccountCredit.setDescription(request.getDebitAccountName()+" (RETURN CHEQUE NO - "+request.getChequeNo()+")");
                ledgerBankAccountCredit.setOtherAccNo(request.getLedgerCreditId().toString());
                ledgerBankAccountCredit.setOtherAccountName(request.getCreditAccountName());
                ledgerBankAccountCredit.setPrNo("-");
                ledgerBankAccountCredit.setIsDeleted(Deleted.NO);
                ledgerBankAccountCreditRepository.save(ledgerBankAccountCredit);

                LedgerBankHistory ledgerBankHistory = new LedgerBankHistory();
                ledgerBankHistory.setAmount(request.getChequeAmount());
                ledgerBankHistory.setBankAccountId(request.getBankAccountId());
                ledgerBankHistory.setCreditAccAmount(request.getChequeAmount());
                ledgerBankHistory.setCreditAccId(request.getLedgerCreditId().intValue());
                ledgerBankHistory.setCurrentBalance(0.00);
                ledgerBankHistory.setDebitAccAmount(0.00);
                ledgerBankHistory.setDebitAccId(request.getLedgerDebitId().intValue());
                ledgerBankHistory.setDepoType("CHEQUE");
                ledgerBankHistory.setFramDocNo(save.getId().intValue());
                ledgerBankHistory.setFramName("CUSTOMER CHEQUE RETURN");
                ledgerBankHistory.setTransaction(" Cheque Return of "+request.getChequeNo());
                ledgerBankHistory.setIsDeleted(Deleted.NO);
                ledgerBankHistoryRepository.save(ledgerBankHistory);

            }
            else{
                ChequeReturns chequeReturns = new ChequeReturns();
                chequeReturns.setBankCode(chequeReturnsRequest.getBankCode());
                chequeReturns.setBranchCode(chequeReturnsRequest.getBranchCode());
                chequeReturns.setBranchId(chequeReturnsRequest.getBranchId());
                chequeReturns.setChequeNo(request.getChequeNo());
                chequeReturns.setChequePayDate(chequeReturnsRequest.getChequePayDate() == null ? null : ConvertUtils.convertStrToDate(chequeReturnsRequest.getChequePayDate()));
                chequeReturns.setChequeReturns(intLineNo);
                chequeReturns.setCustomerId(chequeReturnsRequest.getCustomerId());
                chequeReturns.setInvoiceNum(chequeReturnsRequest.getInvoiceNum());
                chequeReturns.setRemarks(chequeReturnsRequest.getRemarks());
                chequeReturns.setValue(chequeReturnsRequest.getValue());
                chequeReturns.setIsDeleted(Deleted.NO);
                ChequeReturns save =chequeReturnsRepository.save(chequeReturns);
                responses.add(convert(save));

                intLineNo++;

                Customer customer = customerRepository.getById(chequeReturnsRequest.getCustomerId().longValue());
                if(customer.getType().equals("Credit Customer")){
                    customer.setAvlbCreditLimit(customer.getAvlbCreditLimit()+chequeReturnsRequest.getPaidAmount());
                    customerRepository.save(customer);
                }
                ReceivedCheques cheques = receivedChequesRepository.findByIsDeletedAndChequeNo(Deleted.NO,request.getChequeNo());
                cheques.setStatus("RETURN");
                receivedChequesRepository.save(cheques);

                LedgerHistory ledgerHistory = new LedgerHistory();
                ledgerHistory.setBranchId(request.getBranchId());
                ledgerHistory.setLadgerAccountId(request.getLedgerDebitId());
                ledgerHistory.setUpdateFram("CUSTOMER CHEQUE RETURN");
                ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                ledgerHistory.setRemark(" Cheque Return of "+request.getChequeNo());
                ledgerHistory.setUpdateBalance(request.getChequeAmount());
                ledgerHistory.setTransType("DEBIT");
                ledgerHistory.setCreditAmount(0.00);
                ledgerHistory.setDebitAmount(request.getChequeAmount());
                ledgerHistory.setCreditColumnName(request.getCreditAccountName());
                ledgerHistory.setDebitColumnName("-");
                ledgerHistory.setIsDeleted(Deleted.NO);
                ledgerHistoryRepository.save(ledgerHistory);

                ledgerHistory.setBranchId(request.getBranchId());
                ledgerHistory.setLadgerAccountId(request.getLedgerCreditId());
                ledgerHistory.setUpdateFram("CUSTOMER CHEQUE RETURN");
                ledgerHistory.setUpdateFramDocNo(save.getId().toString());
                ledgerHistory.setRemark(" Cheque Return of "+request.getChequeNo());
                ledgerHistory.setUpdateBalance(request.getChequeAmount());
                ledgerHistory.setTransType("CREDIT");
                ledgerHistory.setCreditAmount(request.getChequeAmount());
                ledgerHistory.setDebitAmount(0.00);
                ledgerHistory.setCreditColumnName(request.getCreditAccountName());
                ledgerHistory.setDebitColumnName("-");
                ledgerHistory.setIsDeleted(Deleted.NO);
                ledgerHistoryRepository.save(ledgerHistory);

                LedgerBankAccountCredit ledgerBankAccountCredit = new LedgerBankAccountCredit();
                ledgerBankAccountCredit.setBranchId(request.getBranchId());
                ledgerBankAccountCredit.setCashAmount(request.getChequeAmount());
                ledgerBankAccountCredit.setDescription(request.getDebitAccountName()+" (RETURN CHEQUE NO - "+request.getChequeNo()+")");
                ledgerBankAccountCredit.setOtherAccNo(request.getLedgerCreditId().toString());
                ledgerBankAccountCredit.setOtherAccountName(request.getCreditAccountName());
                ledgerBankAccountCredit.setPrNo("-");
                ledgerBankAccountCredit.setIsDeleted(Deleted.NO);
                ledgerBankAccountCreditRepository.save(ledgerBankAccountCredit);

                LedgerBankHistory ledgerBankHistory = new LedgerBankHistory();
                ledgerBankHistory.setAmount(request.getChequeAmount());
                ledgerBankHistory.setBankAccountId(request.getBankAccountId());
                ledgerBankHistory.setCreditAccAmount(request.getChequeAmount());
                ledgerBankHistory.setCreditAccId(request.getLedgerCreditId().intValue());
                ledgerBankHistory.setCurrentBalance(0.00);
                ledgerBankHistory.setDebitAccAmount(0.00);
                ledgerBankHistory.setDebitAccId(request.getLedgerDebitId().intValue());
                ledgerBankHistory.setDepoType("CHEQUE");
                ledgerBankHistory.setFramDocNo(save.getId().intValue());
                ledgerBankHistory.setFramName("CUSTOMER CHEQUE RETURN");
                ledgerBankHistory.setTransaction(" Cheque Return of "+request.getChequeNo());
                ledgerBankHistory.setIsDeleted(Deleted.NO);
                ledgerBankHistoryRepository.save(ledgerBankHistory);

            }
        }
        saveLog("Customer Cheques Return ", "Data Deleted - "  );
        return responses;
    }

    @Override
    public List<getByOwnChequeReturnResponse> getByPayeeNameAndDateAndBranchId(String payeeName, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<getByOwnChequeReturnResponse> responses = chequeReturnsRepository.getByPayeeNameAndDateAndBranchId(payeeName,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<getByOwnChequeReturnResponse> getByChequeNoAndDateAndBranchId(String ChequeNO, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<getByOwnChequeReturnResponse> responses = chequeReturnsRepository.getByChequeNoAndDateAndBranchId(ChequeNO,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<getByOwnChequeReturnResponse> getByBankCodeAndDateAndBranchId(String bankCode, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<getByOwnChequeReturnResponse> responses = chequeReturnsRepository.getByBankCodeAndDateAndBranchId(bankCode,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<getByOwnChequeReturnResponse> getByBranchCodeAndDateAndBranchId(String branchCode, String startDate, String endDate, Integer branchId) {
        startDate = startDate+" 00:00:00";
        endDate = endDate+" 23:59:59";
        List<getByOwnChequeReturnResponse> responses = chequeReturnsRepository.getByBranchCodeAndDateAndBranchId(branchCode,startDate,endDate,branchId);
        return responses;
    }

    @Override
    public List<CustomerChequeReturnSearch> getForCusRtn() {
        return chequeReturnsRepository.getForCusChRtn();
    }

    @Override
    public List<GetReceivedCheckWiithCustomer> getFromReceivedCheques(String chequeNo) {
        return chequeReturnsRepository.getFromReceivedCheque(chequeNo);
    }

    @Override
    public List<GetInvOfCheque> getInvoicesOfCheque(String cheque_no) {

        Connection co=null;
        List<GetInvOfCheque> res=new ArrayList<>();
        try {
            co=JDBC.con();
            Statement st=co.createStatement();
            ResultSet rs=st.executeQuery("select * from sales_receipt inner join tblinvhed on sales_receipt.invoice_id=tblinvhed.fld_InvNo inner join tblpaydtl on tblpaydtl.fld_InvNo=tblpaydtl.fld_InvNo  WHERE sales_receipt.cheque_no='" + cheque_no +"' group by tblinvhed.fld_InvNo order by tblinvhed.fld_InvNo");
            while(rs.next()){

                GetInvOfCheque inv=new GetInvOfCheque();
                inv.setIdsales_receipt(rs.getInt("idsales_receipt"));
                inv.setFld_InvNo(rs.getString("tblinvhed.fld_InvNo"));
                inv.setIn_pay_date(rs.getString("in_pay_date"));
                inv.setFld_NetAmount(rs.getString("tblinvhed.fld_NetAmount"));
                inv.setFld_PayTypeAmt(rs.getString("tblpaydtl.fld_PayTypeAmt"));
                inv.setFld_CreditCust(rs.getString("tblinvhed.fld_CreditCust"));

            }rs.close();
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                co.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    @Override
    public LadgerAccountResponse getCreditAcc(String chequeNo) {

        List<ChequeBankDetail> lg= chequeReturnsRepository.getDepoBankDetail(chequeNo);
        if(lg!=null){
            if(!lg.isEmpty()){
                ChequeBankDetail detail=lg.get(0);
                LadgerAccount acc=ladgerAccountRepository.getById(Long.parseLong(detail.getBANK_LEDGER_ID()));
                return convert(acc);
            }
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

    private static LadgerAccountResponse convert(LadgerAccount ladgerAccount) {

        LadgerAccountResponse typeResponse = new LadgerAccountResponse();
        typeResponse.setAccountCategory(ladgerAccount.getAccountCategory());
        typeResponse.setAccType(ladgerAccount.getAccType());
        typeResponse.setSubAccType(ladgerAccount.getSubAccType());
        typeResponse.setAccNo(ladgerAccount.getAccNo());
        typeResponse.setAccName(ladgerAccount.getAccName());
        typeResponse.setObBalance(ladgerAccount.getObBalance());
        typeResponse.setCurrentBalance(ladgerAccount.getCurrentBalance());
        typeResponse.setGeneratedNo(ladgerAccount.getGeneratedNo());
        typeResponse.setOwnNo(ladgerAccount.getOwnNo());
        typeResponse.setIsDefault(ladgerAccount.getIsDefault());
        typeResponse.setBranchId(ladgerAccount.getBranch().getId());
        typeResponse.setBranchName(ladgerAccount.getBranch().getBranchName());
        typeResponse.setId(ladgerAccount.getId());
        typeResponse.setCreatedBy(ladgerAccount.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ladgerAccount.getCreatedDateTime()));
        typeResponse.setModifiedBy(ladgerAccount.getModifiedBy());
        typeResponse.setIsDeleted(ladgerAccount.getIsDeleted());

        return typeResponse;
    }

}