package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceLedgerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerOpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerAndCusOpeningBalResponse;
import lk.quantacom.smarterpbackend.dto.response.CustomerOpeningBalanceResponse;
import lk.quantacom.smarterpbackend.entity.CustomerOpeningBalance;
import lk.quantacom.smarterpbackend.entity.LedgerHistory;
import lk.quantacom.smarterpbackend.entity.LedgerSalesJournal;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.CustomerOpeningBalanceRepository;
import lk.quantacom.smarterpbackend.repository.LedgerHistoryRepository;
import lk.quantacom.smarterpbackend.repository.LedgerSalesJournalRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.CustomerOpeningBalanceService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerOpeningBalanceServiceImpl implements CustomerOpeningBalanceService {

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private LedgerSalesJournalRepository ledgerSalesJournalRepository;

    @Autowired
    private CustomerOpeningBalanceRepository customerOpeningBalanceRepository;

    private static CustomerOpeningBalanceResponse convert(CustomerOpeningBalance customerOpeningBalance) {

        CustomerOpeningBalanceResponse typeResponse = new CustomerOpeningBalanceResponse();
        typeResponse.setCustomerOpeningBalanceId(customerOpeningBalance.getCustomerOpeningBalanceId());
        typeResponse.setFldCustomerId(customerOpeningBalance.getFldCustomerId());
        typeResponse.setFldDate(ConvertUtils.convertDateToStr(customerOpeningBalance.getFldDate()));
        typeResponse.setFldDueBalance(customerOpeningBalance.getFldDueBalance());
        typeResponse.setFldNetAmount(customerOpeningBalance.getFldNetAmount());
        typeResponse.setFldPaidAmount(customerOpeningBalance.getFldPaidAmount());
        typeResponse.setFldTime(customerOpeningBalance.getFldTime());
        typeResponse.setCustomerOpeningBalanceId(customerOpeningBalance.getCustomerOpeningBalanceId());
        typeResponse.setCreatedBy(customerOpeningBalance.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(customerOpeningBalance.getCreatedDateTime()));
        typeResponse.setModifiedBy(customerOpeningBalance.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(customerOpeningBalance.getModifiedDateTime()));
        typeResponse.setIsDeleted(customerOpeningBalance.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public CustomerOpeningBalanceResponse save(CustomerOpeningBalanceRequest request) {

        Integer max = customerOpeningBalanceRepository.getMaxCuOpBalId();
        if (max == null) {
            max = 1;
        } else {
            max = max + 1;
        }

        CustomerOpeningBalance customerOpeningBalance = new CustomerOpeningBalance();
        customerOpeningBalance.setCustomerOpeningBalanceId(max);
        customerOpeningBalance.setFldCustomerId(request.getFldCustomerId());
        customerOpeningBalance.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        customerOpeningBalance.setFldDueBalance(request.getFldDueBalance());
        customerOpeningBalance.setFldNetAmount(request.getFldNetAmount());
        customerOpeningBalance.setFldPaidAmount(request.getFldPaidAmount());
        customerOpeningBalance.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        customerOpeningBalance.setIsDeleted(Deleted.NO);
        CustomerOpeningBalance save = customerOpeningBalanceRepository.save(customerOpeningBalance);

        return convert(save);
    }

    @Override
    @Transactional
    public CustomerOpeningBalanceResponse update(CustomerOpeningBalanceUpdateRequest request) {

        CustomerOpeningBalance customerOpeningBalance = customerOpeningBalanceRepository.findById(request.getCustomerOpeningBalanceId()).orElse(null);
        if (customerOpeningBalance == null) {
            return null;
        }

        customerOpeningBalance.setCustomerOpeningBalanceId(request.getCustomerOpeningBalanceId());
        customerOpeningBalance.setCustomerOpeningBalanceId(request.getCustomerOpeningBalanceId());
        customerOpeningBalance.setFldCustomerId(request.getFldCustomerId());
        customerOpeningBalance.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        customerOpeningBalance.setFldDueBalance(request.getFldDueBalance());
        customerOpeningBalance.setFldNetAmount(request.getFldNetAmount());
        customerOpeningBalance.setFldPaidAmount(request.getFldPaidAmount());
        customerOpeningBalance.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        CustomerOpeningBalance updated = customerOpeningBalanceRepository.save(customerOpeningBalance);

        return (convert(updated));
    }

    @Override
    public CustomerOpeningBalanceResponse getById(Integer id) {

        return customerOpeningBalanceRepository.findById(id).map(CustomerOpeningBalanceServiceImpl::convert).orElse(null);
    }

    @Override
    public List<CustomerOpeningBalanceResponse> getAll() {

        return customerOpeningBalanceRepository.findByIsDeleted(Deleted.NO)
                .stream().map(CustomerOpeningBalanceServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<CustomerOpeningBalanceResponse> saveCustomerOpeningBalance(List<CustomerOpeningBalanceLedgerRequest> customerOpeningBalanceLedgerRequests) {
        List<CustomerOpeningBalanceResponse> Responses = new ArrayList<>();
        Integer max = customerOpeningBalanceRepository.getMaxCuOpBalId();
        if (max == null) {
            max = 1;
        } else {
            max = max + 1;
        }
        for (CustomerOpeningBalanceLedgerRequest request : customerOpeningBalanceLedgerRequests) {
//            String cusId = customerOpeningBalanceRepository.getFldCustomerId(request.getFldCustomerId());
//            if(!(cusId.equals(request.getFldCustomerId()))){
            CustomerOpeningBalance customerOpeningBalance = new CustomerOpeningBalance();
            customerOpeningBalance.setCustomerOpeningBalanceId(max);
            customerOpeningBalance.setFldCustomerId(request.getFldCustomerId());
            customerOpeningBalance.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
            customerOpeningBalance.setFldDueBalance(request.getFldDueBalance());
            customerOpeningBalance.setFldNetAmount(request.getFldNetAmount());
            customerOpeningBalance.setFldPaidAmount(0.00);
            customerOpeningBalance.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
            customerOpeningBalance.setIsDeleted(Deleted.NO);
            CustomerOpeningBalance save = customerOpeningBalanceRepository.save(customerOpeningBalance);

            LedgerHistory ledgerCHistory = new LedgerHistory();
            ledgerCHistory.setBranchId(request.getBranchId());
            ledgerCHistory.setLadgerAccountId(request.getLadgerCrediAccountId());
            ledgerCHistory.setUpdateFram("CUSTOMER OPENNING BALANCE");
            ledgerCHistory.setUpdateFramDocNo(request.getUpdateFramDocNo());
            ledgerCHistory.setRemark(" Customer Openning Balance of " + request.getRemark());
            ledgerCHistory.setUpdateBalance(request.getUpdateBalance());
            ledgerCHistory.setTransType("CREDIT");
            ledgerCHistory.setCreditAmount(request.getCreditAmount());
            ledgerCHistory.setDebitAmount(0.0);
            ledgerCHistory.setCreditColumnName(request.getCreditColumnName());
            ledgerCHistory.setDebitColumnName("-");
            ledgerCHistory.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerCHistory);

            LedgerHistory ledgerHistory = new LedgerHistory();
            ledgerHistory.setBranchId(request.getBranchId());
            ledgerHistory.setLadgerAccountId(request.getLadgerDebitAccountId());
            ledgerHistory.setUpdateFram("CUSTOMER OPENNING BALANCE");
            ledgerHistory.setUpdateFramDocNo(request.getUpdateFramDocNo());
            ledgerHistory.setRemark(" Customer Openning Balance of " + request.getRemark());
            ledgerHistory.setUpdateBalance(request.getUpdateBalance());
            ledgerHistory.setTransType("DEBIT");
            ledgerHistory.setCreditAmount(0.0);
            ledgerHistory.setDebitAmount(request.getDebitAmount());
            ledgerHistory.setCreditColumnName("-");
            ledgerHistory.setDebitColumnName(request.getDebitColumnName());
            ledgerHistory.setIsDeleted(Deleted.NO);
            ledgerHistoryRepository.save(ledgerHistory);

            LedgerSalesJournal ledgerSalesJournal = new LedgerSalesJournal();
            ledgerSalesJournal.setBranchId(request.getBranchId().toString());
            ledgerSalesJournal.setCashAmount(request.getCashAmount());
            ledgerSalesJournal.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
            ledgerSalesJournal.setDescription(request.getDescription());
            ledgerSalesJournal.setInvoiceDate(request.getInvoiceDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvoiceDate()));
            ledgerSalesJournal.setInvoiceNo(request.getInvoiceNo());
            ledgerSalesJournal.setLedgerSalesJournalId(request.getLedgerSalesJournalId());
            ledgerSalesJournal.setPrNo(request.getPrNo());
            ledgerSalesJournal.setTime(request.getTime() == null ? null : ConvertUtils.convertStrToDate(request.getTime()));
            ledgerSalesJournal.setIsDeleted(Deleted.NO);
            ledgerSalesJournalRepository.save(ledgerSalesJournal);

            saveLog("Customer Openning Outstanding", "Data saved - " + save.getId());
            //    }
        }
        return Responses;
    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        CustomerOpeningBalance got = customerOpeningBalanceRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        customerOpeningBalanceRepository.save(got);

        return 1;
    }

    @Override
    @Transactional
    public String getFldCustomerId(String fldCustomerId) {
        String got = customerOpeningBalanceRepository.getFldCustomerId(fldCustomerId);
        return got;
    }

    @Override
    public List<CustomerAndCusOpeningBalResponse> getCustomerAndCusOpeningBal() {
        List<CustomerAndCusOpeningBalResponse> responses = customerOpeningBalanceRepository.getCustomerAndCusOpeningBal();
        return responses;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}