package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ReceiptHeaderService;
import lk.quantacom.smarterpbackend.service.UtilityService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReceiptHeaderServiceImpl implements ReceiptHeaderService {

    protected final String ReceiptNumberFormat = "@RC@SEQ(1,6)"; //See the lk.quantacom.smarterpbackend.utils.RunningNumberFormatUtils

    @Autowired
    private ReceiptHeaderRepository receiptHeaderRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Autowired
    private TblinvhedRepository tblinvhedRepository;

    @Autowired
    private UtilityService utilityService;

    @Autowired
    private ReceiptSettleInvoiceRepository receiptSettleInvoiceRepository;

    @Autowired
    private TblpaydtlRepository tblpaydtlRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OnlineWalletRepository onlineWalletRepository;

    @Autowired
    ReceiptPaymentMethodRepository receiptPaymentMethodRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    TblpaymodeRepository tblpaymodeRepository;


    @Override
    public ReceiptHeaderDocumentNumberResponse getNextDocumentNumber() {
        //String _journalNumberFormat = this.JournalNumberFormat;
        String _maxJournalNumber = receiptHeaderRepository.getMaximumReceiptNumber();
        String _journalNumber = RunningNumberFormatUtils.FormatRunningNumber(_maxJournalNumber, this.ReceiptNumberFormat);

        ReceiptHeaderDocumentNumberResponse documentNumberResponse = new ReceiptHeaderDocumentNumberResponse();
        documentNumberResponse.setGeneratedDate(new Date());
        documentNumberResponse.setDocumentNumber(_journalNumber);
        documentNumberResponse.setIsDisplayOnly(true);

        return documentNumberResponse;
    }

    @Override
    public List<String> getAvailableVoucherNumbers(String searchNumber) {

        List<String> receiptNumbers = receiptHeaderRepository.getAvailableReceiptNumbers(searchNumber);

        return receiptNumbers;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ReceiptHeaderResponse save(ReceiptHeaderRequest request) {

        //Single set of methods will used to handle the db save / update
        //Most suitable object to use is update request object
        //Therefor ReceiptHeaderRequest will be mapped to ReceiptHeaderUpdateRequest
        ReceiptHeaderUpdateRequest updateRequest = new ReceiptHeaderUpdateRequest();
        updateRequest.setId(null);
        updateRequest.setBranchId(request.getBranchId());
        updateRequest.setTransactionDate(request.getTransactionDate());
        updateRequest.setPayerContactId(request.getPayerContactId());
        updateRequest.setCustomerId(request.getCustomerId());
        updateRequest.setRemark(request.getRemark());
        updateRequest.setReceiptDetail(request.getReceiptDetail());

        List<ReceiptDetailUpdateRequest> receiptDetails = new ArrayList<>();
        for(ReceiptDetailRequest detail: request.getReceiptDetails()){
            ReceiptDetailUpdateRequest detailRequest  = new ReceiptDetailUpdateRequest();
            detailRequest.setId(null);
            detailRequest.setLineNumber(detail.getLineNumber());
            detailRequest.setAccountId(detail.getAccountId());
            detailRequest.setInvoiceBranchId(detail.getInvoiceBranchId());
            detailRequest.setInvoiceNumber(detail.getInvoiceNumber());
            detailRequest.setWithholdingTaxAmount(detail.getWithholdingTaxAmount());
            detailRequest.setStampDutyAmount(detail.getStampDutyAmount());
            detailRequest.setPaidAmount(detail.getPaidAmount());
            //detailRequest.setNetAmount(detail.getNetAmount());

            receiptDetails.add(detailRequest);
        }
        updateRequest.setReceiptDetails(receiptDetails);

        List<ReceiptPaymentMethodUpdateRequest> paymentMethods = new ArrayList<>();
        for(ReceiptPaymentMethodRequest paymentMethod: request.getPaymentMethods()){
            ReceiptPaymentMethodUpdateRequest paymentMethodRequest = new ReceiptPaymentMethodUpdateRequest();
            paymentMethodRequest.setId(null);
            paymentMethodRequest.setLineNumber(paymentMethod.getLineNumber());
            paymentMethodRequest.setPaymentDate(paymentMethod.getPaymentDate());
            paymentMethodRequest.setPaymentMethodId(paymentMethod.getPaymentMethodId());
            paymentMethodRequest.setCurrencyTypeId(paymentMethod.getCurrencyTypeId());
            paymentMethodRequest.setCurrencyToLocalCurrencyRate(paymentMethod.getCurrencyToLocalCurrencyRate());
            paymentMethodRequest.setChequeDate(paymentMethod.getChequeDate());
            paymentMethodRequest.setChequeNumber(paymentMethod.getChequeNumber());
            paymentMethodRequest.setFromBankId(paymentMethod.getFromBankId());
            paymentMethodRequest.setToBankId(paymentMethod.getToBankId());
            paymentMethodRequest.setFromBankAccountName(paymentMethod.getFromBankAccountName());
            paymentMethodRequest.setFromBankCardNumber(paymentMethod.getFromBankCardNumber());
            paymentMethodRequest.setFromWalletId(paymentMethod.getFromWalletId());
            paymentMethodRequest.setToWalletId(paymentMethod.getToWalletId());
            paymentMethodRequest.setToBankAccountId(paymentMethod.getToBankAccountId());
            paymentMethodRequest.setReference(paymentMethod.getReference());
            paymentMethodRequest.setPaidAmount(paymentMethod.getPaidAmount());

            paymentMethods.add(paymentMethodRequest);
        }
        updateRequest.setPaymentMethods(paymentMethods);


        //Save Header
        ReceiptHeader savedHeader =_saveReceiptHeader(updateRequest);
        if(savedHeader == null){
            //throw new Exception("Unable to save the receipt header.");
            return null;
        }

        boolean saveDetailResult = false;

        //Save Details
        saveDetailResult = _saveReceiptDetails(updateRequest, savedHeader);
        if(!saveDetailResult){
            //throw new Exception("Unable to save the receipt details.");
            return null;
        }

        //Save Payment methods
        saveDetailResult = _saveReceiptPaymentMethods(updateRequest, savedHeader);
        if(!saveDetailResult){
            //throw new Exception("Unable to save the receipt payment modes.");
            return null;
        }

        ReceiptHeader responseReceiptHeader = new ReceiptHeader();
        responseReceiptHeader = receiptHeaderRepository.getById(savedHeader.getId());

        List<ReceiptDetail> details = receiptDetailRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, responseReceiptHeader.getId());
        responseReceiptHeader.setReceiptDetails(details);

        //TODO when payment methods add to header it throws stack overflow error. need to check why??? (No effect when getAll !)
//        List<ReceiptPaymentMethod> paymentMethods = receiptPaymentMethodRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, responseReceiptHeader.getId());
//        responseReceiptHeader.setPaymentMethods(paymentMethods);

        return convert(responseReceiptHeader);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ReceiptHeaderResponse update(ReceiptHeaderUpdateRequest request) {

        ReceiptHeader receiptHeader = null;
        try{
            receiptHeader = receiptHeaderRepository.getById(request.getId());
        }catch (Exception ignored){}

        if(receiptHeader == null){
            return null;
        }

        //Save Header
        ReceiptHeader savedHeader =_saveReceiptHeader(request);
        if(savedHeader == null){
            //throw new Exception("Unable to save the receipt header.");
            return null;
        }

        boolean saveDetailResult = false;

        //Save Details
        saveDetailResult = _saveReceiptDetails(request, savedHeader);
        if(!saveDetailResult){
            //throw new Exception("Unable to save the receipt details.");
            return null;
        }

        //Save Payment methods
        saveDetailResult = _saveReceiptPaymentMethods(request, savedHeader);
        if(!saveDetailResult){
            //throw new Exception("Unable to save the receipt payment modes.");
            return null;
        }

        return convert(savedHeader);

        /*

        ReceiptHeader responseReceiptHeader = new ReceiptHeader();
        responseReceiptHeader = receiptHeaderRepository.getById(savedHeader.getId());

        List<ReceiptDetail> details = receiptDetailRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, savedHeader.getId());
        responseReceiptHeader.setReceiptDetails(details);

        //TODO when payment methods add to header it throws stack overflow error. need to check why??? (No effect when getAll !)
//        List<ReceiptPaymentMethod> paymentMethods = receiptPaymentMethodRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, responseReceiptHeader.getId());
//        responseReceiptHeader.setPaymentMethods(paymentMethods);

        return convert(responseReceiptHeader);
        */
    }


    private ReceiptHeader _saveReceiptHeader(ReceiptHeaderUpdateRequest request){

        ReceiptHeader receiptHeader = null;
        if(request.getId() != null){
            try{
                receiptHeader = receiptHeaderRepository.getById(request.getId());
            }catch (Exception ignored){}

            if(receiptHeader == null){
                return null;
            }

        }else{
            receiptHeader = new ReceiptHeader();
            String _receiptNumber = this.getNextDocumentNumber().getDocumentNumber();
            receiptHeader.setReceiptNumber(_receiptNumber);
        }

        receiptHeader.setTransactionDate(request.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransactionDate()));

        BranchNetwork branch = branchNetworkRepository.getById(request.getBranchId());
        receiptHeader.setBranch(branch);

        receiptHeader.setRemark(request.getRemark());
        receiptHeader.setReceiptDetail(request.getReceiptDetail());

        Contact payerContact = null;
        try {
            if (request.getPayerContactId() != null) {
                payerContact = contactRepository.getById(request.getPayerContactId());
                receiptHeader.setPayerContact(payerContact);
            }
        } catch (Exception ignored) {
        }

        Customer customer = null;
        try {
            if (request.getCustomerId() != null) {
                customer = customerRepository.getById(request.getCustomerId());
                receiptHeader.setCustomer(customer);
            }
        } catch (Exception ignored) {
        }

//        receiptHeader.setPayerContactId(request.getPayerContactId());
//        receiptHeader.setCustomerId(request.getCustomerId());
        receiptHeader.setTotalAmount(this._CalculateTotal_OnUpdate(request.getReceiptDetails()));

        NumberToWordRequest toWordRequest = new NumberToWordRequest();
        toWordRequest.setLanguage("EN");
        toWordRequest.setNumber(receiptHeader.getTotalAmount().longValue());
        NumberToWordResponse wordResponse = utilityService.convertNumberToWord(toWordRequest);
        String numberToWord = wordResponse.getNumberInWord();
        receiptHeader.setTotalAmountInWord(numberToWord);

        receiptHeader.setIsDeleted(Deleted.NO);
        ReceiptHeader savedHeader = receiptHeaderRepository.save(receiptHeader);

        return  savedHeader;
    }

    private boolean _saveReceiptDetails(ReceiptHeaderUpdateRequest request, ReceiptHeader savedHeader) {
        if (request.getReceiptDetail() == null) {
            return false;
        }

        //Delete existing details
        receiptDetailRepository.deleteReceiptDetailsByHeaderId(savedHeader.getId());

        for (ReceiptDetailUpdateRequest detailRequest : request.getReceiptDetails()) {
            ReceiptDetail detail = new ReceiptDetail();

            /*
            if(detailRequest.getId() != null ){
                try{
                    detail = receiptDetailRepository.getById( detailRequest.getId() );
                }catch (Exception ignored){}

                if(detail == null){
                    return false;
                }
            }else{
                detail = new ReceiptDetail();
            }
            */

            double _netAmount = (detailRequest.getPaidAmount() - detailRequest.getWithholdingTaxAmount()) + detailRequest.getStampDutyAmount();

            detail.setReceiptHeader(savedHeader);
            detail.setReceiptNumber(savedHeader.getReceiptNumber());
            detail.setLineNumber(detailRequest.getLineNumber());

            //If General Receipt
            LadgerAccount account = ladgerAccountRepository.findById(detailRequest.getAccountId()).orElse(null);
            if (account != null) {
                detail.setAccountId(account.getId());
                detail.setAccountCode(account.getAccNo());
                detail.setAccountName(account.getAccName());
            }

            //If Customer Payment
            if (detailRequest.getInvoiceNumber() != null) {

                Tblinvhed invoiceHeader = tblinvhedRepository.findByFldLocationAndFldInvno(
                        detailRequest.getInvoiceBranchId().toString(),
                        detailRequest.getInvoiceNumber()
                );
                if (invoiceHeader != null) {

                    //Update Invoice details
                    BranchNetwork invoiceBranch = branchNetworkRepository.findById(detailRequest.getInvoiceBranchId()).orElse(null);
                    if(invoiceBranch != null){
                        detail.setInvoiceBranchId(invoiceBranch.getId());
                    }

                    detail.setInvoiceNumber(detailRequest.getInvoiceNumber());
                    detail.setInvoiceDateTime(invoiceHeader.getFldDate());
                    detail.setInvoiceAmount(invoiceHeader.getFldNetamount());
                    Double _pendingAmount = invoiceHeader.getFldNetamount() - detailRequest.getPaidAmount();
                    detail.setInvoicePendingAmount(_pendingAmount);
                }

            }

            detail.setPaidAmount(detailRequest.getPaidAmount());
            detail.setWithholdingTaxAmount(detailRequest.getWithholdingTaxAmount());
            detail.setStampDutyAmount(detailRequest.getStampDutyAmount());
            detail.setNetAmount(_netAmount);
            detail.setIsDeleted(Deleted.NO);
            ReceiptDetail savedDetail = receiptDetailRepository.save(detail);
        }

        return true;
    }

    private boolean _saveReceiptPaymentMethods(ReceiptHeaderUpdateRequest request, ReceiptHeader savedHeader) {
        if (request.getPaymentMethods() == null) {
            return false;
        }

        //Delete existing payments
        receiptPaymentMethodRepository.deleteReceiptPaymentsByHeaderId(savedHeader.getId());

        //Save Payment methods
        for (ReceiptPaymentMethodUpdateRequest paymentMethodRequest : request.getPaymentMethods()) {

            ReceiptPaymentMethod paymentMethod = new ReceiptPaymentMethod();

            /*
            if(paymentMethodRequest.getId() != null){
                try{
                    paymentMethod = receiptPaymentMethodRepository.getById( paymentMethodRequest.getId() );
                }catch (Exception ignored){}
                if( paymentMethod == null ){
                    return false;
                }
            }else{
                paymentMethod = new ReceiptPaymentMethod();
            }
            */

            BranchNetwork branch = null;
            try {
                branch = branchNetworkRepository.getById(savedHeader.getBranch().getId());
                paymentMethod.setBranch(branch);
            } catch (Exception ignored) {
            }

            paymentMethod.setReceiptHeader(savedHeader);
            paymentMethod.setReceiptNumber(savedHeader.getReceiptNumber());
            paymentMethod.setLineNumber(paymentMethodRequest.getLineNumber());
            paymentMethod.setPaymentDate(new DateFormatConverter().convert(paymentMethodRequest.getPaymentDate()));
            paymentMethod.setPaymentMethodId(paymentMethodRequest.getPaymentMethodId());
            //TODO Set base currency and exchange rate date
            paymentMethod.setCurrencyAccount(null);
            paymentMethod.setCurrencyToLocalCurrencyRate(1.00);
            paymentMethod.setPaidAmount(paymentMethodRequest.getPaidAmount());
            paymentMethod.setIsDeleted(Deleted.NO);

            Bank fromBank = null;
            try {
                if (paymentMethodRequest.getFromBankId() != null) {
                    fromBank = bankRepository.getById(paymentMethodRequest.getFromBankId());
                    paymentMethod.setFromBank(fromBank);
                }
            } catch (Exception ignored) {
            }

            Bank toBank = null;
            try {
                if (paymentMethodRequest.getToBankId() != null) {
                    toBank = bankRepository.getById(paymentMethodRequest.getToBankId());
                    paymentMethod.setToBank(toBank);
                }
            } catch (Exception ignored) {
            }

            OnlineWallet fromWallet = null;
            try {
                if (paymentMethodRequest.getFromWalletId() != null) {
                    fromWallet = onlineWalletRepository.getById(paymentMethodRequest.getFromWalletId());
                    paymentMethod.setFromWallet(fromWallet);
                }
            } catch (Exception ignored) {
            }

            LadgerAccount toWallet = null;
            try {
                if (paymentMethodRequest.getToWalletId() != null) {
                    toWallet = ladgerAccountRepository.getById(paymentMethodRequest.getToWalletId());
                    paymentMethod.setToWallet(toWallet);
                }
            } catch (Exception ignored) {
            }

            LadgerAccount toBankAccount = null;
            try {
                if (paymentMethodRequest.getToWalletId() != null) {
                    toBankAccount = ladgerAccountRepository.getById(paymentMethodRequest.getToBankAccountId());
                    paymentMethod.setToBankAccount(toBankAccount);
                }
            } catch (Exception ignored) {
            }

            ReceiptPaymentMethod savedPaymentMethod = receiptPaymentMethodRepository.save(paymentMethod);
        }


        //Update Invoice Payments
        //Create ReceiptSettleInvoices
        if (request.getCustomerId() == null) {
            return true; // No need to continue if not an invoice
        }

        Tblpaydtl invoicePayDetail = null;
        if (request.getPaymentMethods().size() == 1) {
            //Inv Payment using actual payment
            invoicePayDetail = new Tblpaydtl();

            Tblpaymode paymentMethodType = null;
            try {
                //paymentMethodType = tblpaymodeRepository.getById(request.getPaymentMethods().get(0).getPaymentMethodId());

                //invoicePayDetail.setFldPaymode( paymentMethodType.getPaymodeCode() );
                invoicePayDetail.setFldPaymode("");
                invoicePayDetail.setFldPaytype("");
                invoicePayDetail.setFldPaytypecode("");
                invoicePayDetail.setFldCrdcardno("");
                invoicePayDetail.setFldFcuramt(0.00);
                invoicePayDetail.setFldPaytypeamt(0.00);
                invoicePayDetail.setFldExchngrate(0.00);
                invoicePayDetail.setFldDatatransfer(false);
                invoicePayDetail.setFldGvdisno("");
                invoicePayDetail.setFldCreditno("");
                invoicePayDetail.setFldComno("");
                invoicePayDetail.setFldCashierid("");
                invoicePayDetail.setFldEncrkey("");
                invoicePayDetail.setBlnconfirmed(false);
                invoicePayDetail.setBlnmodechange(false);
                invoicePayDetail.setFldCancel(false);
                invoicePayDetail.setFldAccessupdate(0.00);
                invoicePayDetail.setFldMiddlewareuuid("");
                invoicePayDetail.setFldMiddlewarestatus(0);
                invoicePayDetail.setFldMiddlewarestatusCreditcust(0);
                invoicePayDetail.setFldMiddlewareuuidBw("");
                //invoicePayDetail.setCreatedon(new Date());
                invoicePayDetail.setIsOverrideByReceipt(false);

            } catch (Exception ignored) {
            }

        } else {
            //Inv Payment using false payment

            invoicePayDetail = new Tblpaydtl();
            invoicePayDetail.setFldPaymode("");
            invoicePayDetail.setFldPaytype("");
            invoicePayDetail.setFldPaytypecode("");
            invoicePayDetail.setFldCrdcardno("");
            invoicePayDetail.setFldFcuramt(0.00);
            invoicePayDetail.setFldPaytypeamt(0.00);
            invoicePayDetail.setFldExchngrate(0.00);
            invoicePayDetail.setFldDatatransfer(false);
            invoicePayDetail.setFldGvdisno("");
            invoicePayDetail.setFldCreditno("");
            invoicePayDetail.setFldComno("");
            invoicePayDetail.setFldCashierid("");
            invoicePayDetail.setFldEncrkey("");
            invoicePayDetail.setBlnconfirmed(false);
            invoicePayDetail.setBlnmodechange(false);
            invoicePayDetail.setFldCancel(false);
            invoicePayDetail.setFldAccessupdate(0.00);
            invoicePayDetail.setFldMiddlewareuuid("");
            invoicePayDetail.setFldMiddlewarestatus(0);
            invoicePayDetail.setFldMiddlewarestatusCreditcust(0);
            invoicePayDetail.setFldMiddlewareuuidBw("");
            //invoicePayDetail.setCreatedon(new Date());
            invoicePayDetail.setIsOverrideByReceipt(false);
        }

        for (ReceiptDetailUpdateRequest detailRequest : request.getReceiptDetails()) {

            Tblinvhed invoiceHeader = null;
            try {
                invoiceHeader = tblinvhedRepository.findByFldLocationAndFldInvno(
                        detailRequest.getInvoiceBranchId().toString(),
                        detailRequest.getInvoiceNumber()
                );

                ReceiptSettleInvoice lastsettlement = null;
                Double _pendingAmount = invoiceHeader.getFldNetamount(); //
                try {
                    lastsettlement = receiptSettleInvoiceRepository.getLastPaidReceiptByInvoiceLocationAndInvoiceNumber(
                            detailRequest.getInvoiceBranchId().toString(),
                            detailRequest.getInvoiceNumber()
                    );
                    _pendingAmount = lastsettlement.getBalanceAmount();
                } catch (Exception ignored) {
                }

                //Create Receipt Settle Invoice
                ReceiptSettleInvoice receiptSettleInvoice = new ReceiptSettleInvoice();
                receiptSettleInvoice.setReceiptId(savedHeader.getId());
                receiptSettleInvoice.setInvoiceLocation(invoiceHeader.getFldLocation());
                receiptSettleInvoice.setInvoiceNumber(invoiceHeader.getFldInvno());
                receiptSettleInvoice.setCustomerId(savedHeader.getCustomer().getId());
                receiptSettleInvoice.setInvoiceAmount(invoiceHeader.getFldNetamount());
                receiptSettleInvoice.setSettleAmount(detailRequest.getPaidAmount());
                receiptSettleInvoice.setBalanceAmount(_pendingAmount);
                receiptSettleInvoice.setIsReturnedInvoice((invoiceHeader.getFldNetamount() < 0));
                receiptSettleInvoiceRepository.save(receiptSettleInvoice);

                //boolean _isFullySettled = (_pendingAmount == 0);
                //TODO update all other credit payments for this invoice -> setIsOverrideByReceipt(true)

                TblpaydtlPK tblpaydtlPK = new TblpaydtlPK();
                tblpaydtlPK.setFldDate(new Date());
                tblpaydtlPK.setFldLocation(detailRequest.getInvoiceBranchId().toString());
                tblpaydtlPK.setFldInvno(detailRequest.getInvoiceNumber());

                invoicePayDetail.setTblpaydtlPK(tblpaydtlPK);

                tblpaydtlRepository.save(invoicePayDetail);

            } catch (Exception ignored) {
            }
        }



        return true;
    }



    @Override
    public ReceiptHeaderResponse getById(Long id) {
        return receiptHeaderRepository.findById(id).map(ReceiptHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ReceiptHeaderResponse> getAll() {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "id"));

        return receiptHeaderRepository.findByIsDeleted(Deleted.NO, sort)
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<ReceiptHeaderResponse> getPaginatedGeneralReceipts(int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<ReceiptHeader> _pgResult = receiptHeaderRepository.findByIsDeleted(Deleted.NO, sort);

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _pgResult.size());
        List<ReceiptHeader> _pagedHeaders = _pgResult.subList(
                _fromIndex,
                _toIndex
        );

        List<ReceiptHeaderResponse> _lstResult = _pagedHeaders
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.size());
        */

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.getGeneralReceipts(pgRequest);

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<ReceiptHeaderResponse> getPaginatedGeneralReceipts(Long branchId, int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<ReceiptHeader> _pgResult = receiptHeaderRepository.findByIsDeletedAndBranchId(Deleted.NO, branchId, sort);

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, _pgResult.size());
        List<ReceiptHeader> _pagedHeaders = _pgResult.subList(
                _fromIndex,
                _toIndex
        );

        List<ReceiptHeaderResponse> _lstResult = _pagedHeaders
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.size());
        */

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.getGeneralReceipts(branchId, pgRequest);

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());

    }

    @Override
    public Page<ReceiptHeaderResponse> getPaginatedCustomerReceipts(int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.getCustomerReceipts(pgRequest);

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<ReceiptHeaderResponse> getPaginatedCustomerReceipts(Long branchId, int pageNumber, int countPerPage) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.getCustomerReceipts(branchId, pgRequest);

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());

    }

    @Override
    public Page<ReceiptHeaderResponse> getSearchPaginatedGeneralReceipts(int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<ReceiptHeader> unPagedHeaders = receiptHeaderRepository.searchCustomFilter(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber()//,
                //Long.valueOf((pageNumber -1) * countPerPage),
                //countPerPage
        );

        Long totalElements = receiptHeaderRepository.countSearchCustomFilter(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber()
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, unPagedHeaders.size());
        List<ReceiptHeader> pagedHeaders = unPagedHeaders.subList(
                _fromIndex,
                //(((pageNumber - 1) * countPerPage) + countPerPage) - 1
                _toIndex
        );

        List<ReceiptHeaderResponse> pagedResponses = pagedHeaders
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(pagedResponses, pgRequest, totalElements);
        */


        /*
        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilter(
                        ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                        ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                        searchRequest.getPayerId(),
                        searchRequest.getReceiptAmount(),
                        searchRequest.getReceiptNumber(),
                        pgRequest
                );
        List<ReceiptHeaderResponse> _lstResult = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
        */

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilterGeneralReceipts(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber(),
                pgRequest
        );

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<ReceiptHeaderResponse> getSearchPaginatedGeneralReceipts(Long branchId, int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        /*
        List<ReceiptHeader> unPagedHeaders = receiptHeaderRepository.searchCustomFilterBranch(
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber()//,
                //Long.valueOf((pageNumber - 1) * countPerPage),
                //countPerPage
        );

        Long totalElements = receiptHeaderRepository.countSearchCustomFilterBranch(
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber()
        );

        int _fromIndex = (pageNumber - 1) * countPerPage;
        int _toIndex = (pageNumber * countPerPage);
        _toIndex = Math.min(_toIndex, unPagedHeaders.size());
        List<ReceiptHeader> pagedHeaders = unPagedHeaders.subList(
                _fromIndex,
                _toIndex
        );

        List<ReceiptHeaderResponse> pagedResponses = pagedHeaders
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(pagedResponses, pgRequest, unPagedHeaders.size());
        */

        /*
        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilter(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber(),
                pgRequest
        );
        List<ReceiptHeaderResponse> _lstResult = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList()
                );

        return new PageImpl<>(_lstResult, pgRequest, _pgResult.getTotalElements());
        */

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilterGeneralReceipts(
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber(),
                pgRequest
        );

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<ReceiptHeaderResponse> getSearchPaginatedCustomerReceipts(int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilterCustomerReceipts(
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber(),
                pgRequest
        );

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public Page<ReceiptHeaderResponse> getSearchPaginatedCustomerReceipts(Long branchId, int pageNumber, int countPerPage, ReceiptHeaderSearchRequest searchRequest) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pgRequest = PageRequest.of(pageNumber, countPerPage, sort);

        Page<ReceiptHeader> _pgResult = receiptHeaderRepository.searchCustomFilterCustomerReceipts(
                branchId,
                ConvertUtils.convertStrToDate(searchRequest.getTransactionFromDate()),
                ConvertUtils.convertStrToDate(searchRequest.getTransactionToDate()),
                searchRequest.getPayerId(),
                searchRequest.getReceiptAmount(),
                searchRequest.getReceiptNumber(),
                pgRequest
        );

        List<ReceiptHeaderResponse> _lstResponse = _pgResult
                .stream().map(ReceiptHeaderServiceImpl::convert).collect(Collectors.toList());

        return new PageImpl<>(_lstResponse, pgRequest, _pgResult.getTotalElements());
    }

    @Override
    public File printSingle(Long id) {
        File out = null;
        Connection co = null;
        ReceiptHeader header = receiptHeaderRepository.findById(id).orElse(null);

        try {
            File file = new File("JRXML/report/leader_reports/rpt_Receipt_General_Receipt.jrxml");

            if (header != null && header.getCustomer() != null) {
                file = new File("JRXML/report/leader_reports/rpt_Receipt_Customer_Receipt.jrxml");
            }

            Map<String, Object> map = new HashMap<>();
            map.put("prm_HeaderId", id);
            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());

            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            File tmpDir = new File("TMP/");
            if (!tmpDir.exists()) {
                tmpDir.mkdirs();
            }

            String filepath = filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
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

        ReceiptHeader got = receiptHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        receiptHeaderRepository.save(got);

        return 1;
    }

    private double _CalculateTotal_OnSave(List<ReceiptDetailRequest> details) {
        double _total = 0.00;

        for (ReceiptDetailRequest detail : details) {
            double _netAmount = (detail.getPaidAmount() - detail.getWithholdingTaxAmount() - detail.getStampDutyAmount());
            _total += _netAmount;
        }

        return _total;
    }

    private double _CalculateTotal_OnUpdate(List<ReceiptDetailUpdateRequest> details) {
        double _total = 0.00;

        for (ReceiptDetailUpdateRequest detail : details) {
            double _netAmount = (detail.getPaidAmount() - detail.getWithholdingTaxAmount() - detail.getStampDutyAmount());
            _total += _netAmount;
        }

        return _total;
    }

    private static ReceiptHeaderResponse convert(ReceiptHeader receiptHeader) {

        ReceiptHeaderResponse typeResponse = new ReceiptHeaderResponse();
        typeResponse.setReceiptNumber(receiptHeader.getReceiptNumber());
        typeResponse.setTransactionDate(receiptHeader.getTransactionDate());
        typeResponse.setBranch(BranchNetworkServiceImpl.convert(receiptHeader.getBranch()));
        typeResponse.setRemark(receiptHeader.getRemark());
        typeResponse.setReceiptDetail(receiptHeader.getReceiptDetail());

        ContactResponse payerContact = null;
        if (receiptHeader.getPayerContact() != null) {
            payerContact = ContactServiceImpl.convert(receiptHeader.getPayerContact());
        }
        typeResponse.setPayerContact(payerContact);

        CustomerResponse customer = null;
        if (receiptHeader.getCustomer() != null) {
            customer = CustomerServiceImpl.convert(receiptHeader.getCustomer());
        }
        typeResponse.setCustomer(customer);

        typeResponse.setTotalAmount(receiptHeader.getTotalAmount());
        typeResponse.setTotalAmountInWord(receiptHeader.getTotalAmountInWord());
        typeResponse.setId(receiptHeader.getId());
        typeResponse.setCreatedBy(receiptHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(receiptHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(receiptHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(receiptHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(receiptHeader.getIsDeleted());

        //Details
        List<ReceiptDetailResponse> detailResponses = new ArrayList<>();
        if (receiptHeader.getReceiptDetails() != null && !receiptHeader.getReceiptDetails().isEmpty()) {
            for (ReceiptDetail receiptDetail : receiptHeader.getReceiptDetails()) {
                detailResponses.add(ReceiptDetailServiceImpl.convert(receiptDetail));
            }
        }
        typeResponse.setReceiptDetails(detailResponses);

        //Payment methods
        List<ReceiptPaymentMethodResponse> receiptPaymentMethods = new ArrayList<>();
        if (receiptHeader.getPaymentMethods() != null && !receiptHeader.getPaymentMethods().isEmpty()) {
            for (ReceiptPaymentMethod receiptPaymentMethod : receiptHeader.getPaymentMethods()) {
                receiptPaymentMethods.add(ReceiptPaymentMethodServiceImpl.convert(receiptPaymentMethod));
            }
        }
        typeResponse.setPaymentMethods(receiptPaymentMethods);

        return typeResponse;
    }

}