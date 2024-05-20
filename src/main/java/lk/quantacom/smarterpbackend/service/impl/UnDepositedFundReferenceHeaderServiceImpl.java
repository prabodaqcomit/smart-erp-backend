package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceHeaderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.UnDepositedFundReferenceHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UnDepositedFundReferenceHeaderServiceImpl implements UnDepositedFundReferenceHeaderService {

    @Autowired
    private UnDepositedFundReferenceHeaderRepository unDepositedFundReferenceHeaderRepository;

    @Autowired
    private UnDepositedFundReferenceDetailRepository unDepositedFundReferenceDetailRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private TblpaymodeRepository tblpaymodeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    private void _checkUnsavedUnDepositedFundReferences() {
        _checkUnsavedUnDepositedFundReferenceInvoice();
        _checkUnsavedUnDepositedFundReferenceReceipt();
    }

    @Transactional
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    private void _checkUnsavedUnDepositedFundReferenceInvoice() {

        //This will reduce the weight to database
        final Date _lastCheckedDate = unDepositedFundReferenceHeaderRepository.getUnSavedInvoiceLastCheckDate();

        //List<UnDepositedFundReferenceHeader> _unsavedHeaders = unDepositedFundReferenceHeaderRepository.getUnSavedInvoiceUnDepositedFundHeaders(_lastCheckedDate);
        List<UnDepositedFundReferenceHeader> _unsavedHeaders = getUnSavedInvoiceUnDepositedFundHeaders(_lastCheckedDate);

        if (_unsavedHeaders == null || _unsavedHeaders.isEmpty()) {
            return;
        }

        _saveUnSavedInvoiceUnDepositedFundReferences(_unsavedHeaders);
    }

    @Transactional
    //@Transactional(isolation = Isolation.READ_COMMITTED)
    private void _checkUnsavedUnDepositedFundReferenceReceipt() {

        //This will reduce the weight to database
        final Date _lastCheckedDate = unDepositedFundReferenceHeaderRepository.getUnSavedReceiptLastCheckDate();

        List<UnDepositedFundReferenceHeader> _unsavedHeaders = getUnSavedReceiptUnDepositedFundHeaders(_lastCheckedDate);

        if (_unsavedHeaders == null || _unsavedHeaders.isEmpty()) {
            return;
        }

        _saveUnSavedReceiptUnDepositedFundReferences(_unsavedHeaders);
    }

    private List<UnDepositedFundReferenceHeader> getUnSavedInvoiceUnDepositedFundHeaders(Date lastCheckedDate) {

        List<UnDepositedFundReferenceHeader> unsavedHeaders = null;

        try {

            List<Map<String, Object>> queryResult = new ArrayList<>();

            queryResult = jdbcTemplate.queryForList(
                    "SELECT \n" +
                            "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                            "h.fld_Location transaction_branch_id, \n" +
                            "h.fld_InvNo transaction_id, \n" +
                            "h.fld_Date transaction_date, \n" +
                            "true transaction_is_invoice, \n" +
                            "false transaction_is_receipt, \n" +
                            "'Invoice' transaction_type_description, \n" +
                            "MAX(h.fld_NetAmount) transaction_amount, \n" +
                            "SUM(pd.fld_PayTypeAmt) un_deposited_total_amount, \n" +
                            "SUM(pd.fld_PayTypeAmt) un_deposited_total_balance \n" +
                            "FROM \n" +
                            "tblinvhed h \n" +
                            "INNER JOIN tblpaydtl pd ON \n" +
                            "h.fld_Location = pd.fld_Location AND h.fld_InvNo = pd.fld_InvNo \n" +
                            "AND pd.fld_PayType IN ( 'cash', 'cheque' ) \n" +
                            "LEFT OUTER JOIN undeposited_fund_reference_header udfh ON \n" +
                            "udfh.transaction_is_invoice = 1 \n" +
                            "AND h.fld_Location = udfh.transaction_branch_id AND h.fld_InvNo = udfh.transaction_id \n" +
                            "WHERE \n" +
                            "udfh.id IS NULL \n" +
                            //"AND h.fld_Date > 1? \n" +
                            "AND h.fld_Date > '" + new DateFormatConverter().patternDate(lastCheckedDate) + "'\n" +
                            "GROUP BY h.fld_Location, h.fld_InvNo \n" +
                            "ORDER BY h.fld_Location, h.fld_InvNo"
                    //,lastCheckedDate
            );

            if (queryResult.isEmpty()) {
                return null;
            }

            unsavedHeaders = _convertMapObjectsToUDFRH(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedHeaders;
    }

    private List<UnDepositedFundReferenceHeader> getUnSavedReceiptUnDepositedFundHeaders(Date lastCheckedDate) {

        List<UnDepositedFundReferenceHeader> unsavedHeaders = null;

        try {

            List<Map<String, Object>> queryResult = new ArrayList<>();

            queryResult = jdbcTemplate.queryForList(
                    "SELECT \n" +
                            "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                            "rh.branch_id transaction_branch_id, \n" +
                            "rh.id transaction_id, \n" +
                            "rh.transaction_date transaction_date, \n" +
                            "false transaction_is_invoice, \n" +
                            "true transaction_is_receipt, \n" +
                            "'Receipt' transaction_type_description, \n" +
                            "MAX(rh.total_amount) transaction_amount, \n" +
                            "SUM(rpm.paid_amount) un_deposited_total_amount, \n" +
                            "SUM(rpm.paid_amount) un_deposited_total_balance \n" +
                            "FROM \n" +
                            "receipt_header rh \n" +
                            "INNER JOIN receipt_payment_method rpm ON \n" +
                            "rh.id = rpm.receipt_header_id \n" +
                            "LEFT OUTER JOIN undeposited_fund_reference_header udfh ON \n" +
                            "rh.branch_id = udfh.transaction_branch_id AND rh.id = udfh.transaction_id \n" +
                            "WHERE \n" +
                            "udfh.id IS NULL \n" +
                            //"AND rh.transaction_date > 1? \n" +
                            "AND rh.transaction_date > '" + new DateFormatConverter().patternDate(lastCheckedDate) + "'\n" +
                            "GROUP BY rh.branch_id, rh.id \n" +
                            "ORDER BY rh.branch_id, rh.id"
                    //,lastCheckedDate
            );

            if (queryResult.isEmpty()) {
                return null;
            }

            unsavedHeaders = _convertMapObjectsToUDFRH(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedHeaders;
    }

    private List<UnDepositedFundReferenceHeader> _convertMapObjectsToUDFRH(List<Map<String, Object>> queryResult) {
        List<UnDepositedFundReferenceHeader> unsavedHeaders = new ArrayList<>();

        try {

            if (queryResult.isEmpty()) {
                return null;
            }

            for (Map<String, Object> row : queryResult) {

                UnDepositedFundReferenceHeader header = new UnDepositedFundReferenceHeader();
                //header.setId(Long.valueOf(row.get("id").toString()));
                //header.setCreatedBy(row.get("created_by").toString());
                //header.setCreatedDateTime(new DateFormatConverter().convert(row.get("created_date_time").toString()));
                //header.setModifiedBy(row.get("modified_by").toString());
                //header.setModifiedDateTime(new DateFormatConverter().convert(row.get("modified_date_time").toString()));

                BranchNetwork branch = null;
                try {
                    branch = branchNetworkRepository.getById(Long.valueOf(row.get("transaction_branch_id").toString()));
                } catch (Exception ignored) {
                    branch = new BranchNetwork();
                    branch.setId(Long.valueOf(row.get("transaction_branch_id").toString()));
                }
                header.setTransactionBranch(branch);

                header.setTransactionId(row.get("transaction_id").toString());
                header.setTransactionDate(new DateFormatConverter().convert(row.get("transaction_date").toString()));
                header.setTransactionIsInvoice(Integer.parseInt(row.get("transaction_is_invoice").toString()) == 1);
                header.setTransactionIsReceipt(Integer.parseInt(row.get("transaction_is_receipt").toString()) == 1);
                header.setTransactionTypeDescription(row.get("transaction_type_description").toString());
                header.setTransactionAmount(Double.valueOf(row.get("transaction_amount").toString()));
                header.setUnDepositedTotalAmount(Double.valueOf(row.get("un_deposited_total_amount").toString()));
                header.setUnDepositedTotalBalance(Double.valueOf(row.get("un_deposited_total_balance").toString()));

                unsavedHeaders.add(header);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedHeaders;
    }

    private List<UnDepositedFundReferenceDetail> getUnSavedInvoiceUnDepositedFundDetails(String transactionBranch, String transactionId) {
        List<UnDepositedFundReferenceDetail> unsavedDetails = null;

        try {

            List<Map<String, Object>> queryResult = new ArrayList<>();

            queryResult = jdbcTemplate.queryForList(
                    "SELECT \n" +
                            "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                            "NULL undeposited_fund_reference_header_id, \n" +
                            "ROW_NUMBER() OVER (ORDER BY pd.fld_InvPayDtlKey) line_number, \n" +
                            "pd.fld_PayMode payment_mode_id, \n" +
                            "1 currency_type_id, \n" +
                            "CASE \n" +
                            "   WHEN pd.fld_ExchngRate IS NULL THEN 1 \n" +
                            "   WHEN pd.fld_ExchngRate = 0 THEN 1 \n" +
                            "   ELSE pd.fld_ExchngRate \n" +
                            "END currency_type_exchange_rate, \n" +
                            "pd.fld_CrdCardNo payment_mode_remark, \n" +
                            "pd.fld_PayTypeAmt payment_mode_paid_amount, \n" +
                            "pd.fld_PayTypeAmt * (CASE  WHEN pd.fld_ExchngRate IS NULL THEN 1 WHEN pd.fld_ExchngRate = 0 THEN 1 ELSE pd.fld_ExchngRate END) paid_amount_value, \n" +
                            "pd.fld_PayTypeAmt undeposited_balance \n" +
                            "FROM \n" +
                            "tblpaydtl pd \n" +
                            "WHERE \n" +
                            "pd.fld_Location = '" + transactionBranch.trim() + "' \n" +
                            "AND pd.fld_InvNo = '" + transactionId.trim() + "' \n" +
                            "ORDER BY pd.fld_InvPayDtlKey ASC"
            );

            if (queryResult.isEmpty()) {
                return null;
            }

            unsavedDetails = _convertMapObjectsToUDFRD(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedDetails;
    }

    private List<UnDepositedFundReferenceDetail> getUnSavedReceiptUnDepositedFundDetails(String transactionBranch, String transactionId) {
        List<UnDepositedFundReferenceDetail> unsavedDetails = null;

        try {

            List<Map<String, Object>> queryResult = new ArrayList<>();

            queryResult = jdbcTemplate.queryForList(
                    "SELECT \n" +
                            "NULL id, NULL created_by, NULL created_date_time, 0 is_deleted, NULL modified_by, NULL modified_date_time, \n" +
                            "NULL undeposited_fund_reference_header_id, \n" +
                            "ROW_NUMBER() OVER (ORDER BY rpm.id) line_number, \n" +
                            "rpm.payment_method_id payment_mode_id, \n" +
                            "IFNULL(rpm.currency_type_id, 1) currency_type_id, \n" +
                            "CASE  \n" +
                            "   WHEN rpm.currency_to_local_currency_rate IS NULL THEN 1 \n" +
                            "   WHEN rpm.currency_to_local_currency_rate = 0 THEN 1 \n" +
                            "   ELSE rpm.currency_to_local_currency_rate \n" +
                            "END currency_type_exchange_rate, \n" +
                            "rpm.reference payment_mode_remark, \n" +
                            "rpm.paid_amount payment_mode_paid_amount, \n" +
                            "rpm.paid_amount * (CASE  WHEN rpm.currency_to_local_currency_rate IS NULL THEN 1 WHEN rpm.currency_to_local_currency_rate = 0 THEN 1 ELSE rpm.currency_to_local_currency_rate END) paid_amount_value, \n" +
                            "rpm.paid_amount undeposited_balance \n" +
                            "FROM \n" +
                            "receipt_payment_method rpm \n" +
                            "WHERE \n" +
                            //"rpm.branch_id = 1? AND rpm.receipt_header_id = 2? \n" +
                            "rpm.branch_id ='" + transactionBranch.trim() + "' \n" +
                            "AND rpm.receipt_header_id = '" + transactionId.trim() + "' \n" +
                            "ORDER BY rpm.id ASC \n"
            );

            if (queryResult.isEmpty()) {
                return null;
            }

            unsavedDetails = _convertMapObjectsToUDFRD(queryResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedDetails;
    }

    private List<UnDepositedFundReferenceDetail> _convertMapObjectsToUDFRD(List<Map<String, Object>> queryResult) {
        List<UnDepositedFundReferenceDetail> unsavedDetails = new ArrayList<>();

        try {

            if (queryResult.isEmpty()) {
                return null;
            }

            for (Map<String, Object> row : queryResult) {


                UnDepositedFundReferenceDetail detail = new UnDepositedFundReferenceDetail();
                detail.setUnDepositedFundReferenceHeader(null);
                detail.setLineNumber(Integer.valueOf(row.get("line_number").toString()));
                try {
//                    Tblpaymode tblpaymode = tblpaymodeRepository.getById(
//                            Long.valueOf(row.get("payment_mode_id").toString())
//                    );
                    Tblpaymode tblpaymode = tblpaymodeRepository.findByPaymodeCode(
                            row.get("payment_mode_id").toString()
                    );
                    detail.setPaymentMode(tblpaymode);
                } catch (Exception ignored) {
                }
                try {
                    Currency currencyType = currencyRepository.getById(
                            Long.valueOf(row.get("currency_type_id").toString())
                    );
                    detail.setCurrencyType(currencyType);
                } catch (Exception ignored) {
                }
                detail.setCurrencyTypeExchangeRate(Double.valueOf(row.get("currency_type_exchange_rate").toString()));
                detail.setPaymentModeRemark(row.get("currency_type_exchange_rate").toString());
                detail.setPaymentModePaidAmount(Double.valueOf(row.get("payment_mode_paid_amount").toString()));
                detail.setPaidAmountValue(Double.valueOf(row.get("paid_amount_value").toString()));
                detail.setUnDepositedBalance(Double.valueOf(row.get("undeposited_balance").toString()));

                unsavedDetails.add(detail);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return unsavedDetails;
    }

    private void _saveUnSavedInvoiceUnDepositedFundReferences(List<UnDepositedFundReferenceHeader> _unsavedHeaders) {
        //Return if no unsaved entries found
        if (_unsavedHeaders == null || _unsavedHeaders.isEmpty()) {
            return;
        }

        for (UnDepositedFundReferenceHeader header : _unsavedHeaders) {
            List<UnDepositedFundReferenceDetail> details =
                    getUnSavedInvoiceUnDepositedFundDetails(
                            header.getTransactionBranch().getId().toString(),
                            header.getTransactionId()
                    );
            if (details == null || details.isEmpty()) {
                continue;
            }

            UnDepositedFundReferenceHeader savedHeader = unDepositedFundReferenceHeaderRepository.save(header);
            for (UnDepositedFundReferenceDetail detail : details) {
                detail.setUnDepositedFundReferenceHeader(savedHeader);
                detail.setIsDeleted(Deleted.NO);

                unDepositedFundReferenceDetailRepository.save(detail);
            }
        }
    }

    private void _saveUnSavedReceiptUnDepositedFundReferences(List<UnDepositedFundReferenceHeader> _unsavedHeaders) {
        //Return if no unsaved entries found
        if (_unsavedHeaders == null || _unsavedHeaders.isEmpty()) {
            return;
        }

        for (UnDepositedFundReferenceHeader header : _unsavedHeaders) {
            List<UnDepositedFundReferenceDetail> details =
                    getUnSavedReceiptUnDepositedFundDetails(
                            header.getTransactionBranch().getId().toString(),
                            header.getTransactionId()
                    );
            if (details == null || details.isEmpty()) {
                continue;
            }

            UnDepositedFundReferenceHeader savedHeader = unDepositedFundReferenceHeaderRepository.save(header);
            for (UnDepositedFundReferenceDetail detail : details) {
                detail.setUnDepositedFundReferenceHeader(savedHeader);
                detail.setIsDeleted(Deleted.NO);

                unDepositedFundReferenceDetailRepository.save(detail);
            }
        }
    }

//    @Override
//    @Transactional
//    public UnDepositedFundReferenceHeaderResponse save(UnDepositedFundReferenceHeaderRequest request) {
//
//        UnDepositedFundReferenceHeader unDepositedFundReferenceHeader = new UnDepositedFundReferenceHeader();
//        unDepositedFundReferenceHeader.setTransactionTypeDescription(request.getTransactionTypeDescription());
//        unDepositedFundReferenceHeader.setTransactionIsInvoice(request.getTransactionIsInvoice());
//        unDepositedFundReferenceHeader.setTransactionIsReceipt(request.getTransactionIsReceipt());
//
//        BranchNetwork branch = null;
//        try {
//            branch = branchNetworkRepository.getById(Long.valueOf(request.getTransactionBranchId()));
//        } catch (Exception ignored) {
//        }
//        unDepositedFundReferenceHeader.setTransactionBranch(branch);
//
//        unDepositedFundReferenceHeader.setTransactionId(request.getTransactionId());
//        unDepositedFundReferenceHeader.setTransactionDate(new DateFormatConverter().convert(request.getTransactionDate()));
//        unDepositedFundReferenceHeader.setTransactionAmount(request.getTransactionAmount());
//        unDepositedFundReferenceHeader.setUnDepositedTotalAmount(request.getUnDepositedTotalAmount());
//        unDepositedFundReferenceHeader.setUnDepositedTotalBalance(request.getUnDepositedTotalBalance());
//        unDepositedFundReferenceHeader.setIsDeleted(Deleted.NO);
//        UnDepositedFundReferenceHeader save = unDepositedFundReferenceHeaderRepository.save(unDepositedFundReferenceHeader);
//
//        return convert(save);
//    }
//
//    @Override
//    @Transactional
//    public UnDepositedFundReferenceHeaderResponse update(UnDepositedFundReferenceHeaderUpdateRequest request) {
//
//        UnDepositedFundReferenceHeader unDepositedFundReferenceHeader = unDepositedFundReferenceHeaderRepository.findById(request.getId()).orElse(null);
//        if (unDepositedFundReferenceHeader == null) {
//            return null;
//        }
//
//        unDepositedFundReferenceHeader.setId(request.getId());
//        unDepositedFundReferenceHeader.setTransactionTypeDescription(request.getTransactionTypeDescription());
//        unDepositedFundReferenceHeader.setTransactionIsInvoice(request.getTransactionIsInvoice());
//        unDepositedFundReferenceHeader.setTransactionIsReceipt(request.getTransactionIsReceipt());
//
//        BranchNetwork branch = null;
//        try {
//            branch = branchNetworkRepository.getById(Long.valueOf(request.getTransactionBranchId()));
//        } catch (Exception ignored) {
//        }
//        unDepositedFundReferenceHeader.setTransactionBranch(branch);
//
//        unDepositedFundReferenceHeader.setTransactionId(request.getTransactionId());
//        unDepositedFundReferenceHeader.setTransactionDate(new DateFormatConverter().convert(request.getTransactionDate()));
//        unDepositedFundReferenceHeader.setTransactionAmount(request.getTransactionAmount());
//        unDepositedFundReferenceHeader.setUnDepositedTotalAmount(request.getUnDepositedTotalAmount());
//        unDepositedFundReferenceHeader.setUnDepositedTotalBalance(request.getUnDepositedTotalBalance());
//        UnDepositedFundReferenceHeader updated = unDepositedFundReferenceHeaderRepository.save(unDepositedFundReferenceHeader);
//
//        return convert(updated);
//    }

    @Override
    public UnDepositedFundReferenceHeaderResponse getById(Long id) {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();

        return unDepositedFundReferenceHeaderRepository.findById(id).map(UnDepositedFundReferenceHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<UnDepositedFundReferenceHeaderResponse> getAll() {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();


        return unDepositedFundReferenceHeaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(UnDepositedFundReferenceHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<UnDepositedFundReferenceHeaderResponse> getPendingFunds() {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();


        return unDepositedFundReferenceHeaderRepository.getPendingUnDepositedFundReference()
                .stream().map(UnDepositedFundReferenceHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<UnDepositedFundReferenceHeaderResponse> getPendingFundsByBranch(Long branchId) {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();


        return unDepositedFundReferenceHeaderRepository.getPendingUnDepositedFundReferenceByBranch(branchId)
                .stream().map(UnDepositedFundReferenceHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public Page<UnDepositedFundReferenceHeaderResponse> getPagedPendingFunds(int pageNumber, int countPerPage) {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();

        Pageable pageable = PageRequest.of(pageNumber, countPerPage
                , Sort.by(new Sort.Order(Sort.Direction.ASC, "id")));

        Page<UnDepositedFundReferenceHeader> _pagedResponses = unDepositedFundReferenceHeaderRepository.getPendingUnDepositedFundReference(pageable);

        return new PageImpl<>(
                _pagedResponses.stream().map(UnDepositedFundReferenceHeaderServiceImpl::convert).collect(Collectors.toList())
                , pageable, _pagedResponses.getTotalElements());
    }

    @Override
    public Page<UnDepositedFundReferenceHeaderResponse> getPagedPendingFundsByBranch(int pageNumber, int countPerPage, Long branchId) {
        //Check for unsaved data
        _checkUnsavedUnDepositedFundReferences();

        Pageable pageable = PageRequest.of(pageNumber, countPerPage
                , Sort.by(new Sort.Order(Sort.Direction.ASC, "id")));

        Page<UnDepositedFundReferenceHeader> _pagedResponses = unDepositedFundReferenceHeaderRepository.getPendingUnDepositedFundReferenceByBranch(branchId, pageable);

        return new PageImpl<>(
                _pagedResponses.stream().map(UnDepositedFundReferenceHeaderServiceImpl::convert).collect(Collectors.toList())
                , pageable, _pagedResponses.getTotalElements());
    }

//    @Override
//    @Transactional
//    public Integer delete(Long id) {
//
//        UnDepositedFundReferenceHeader got = unDepositedFundReferenceHeaderRepository.findById(id).orElse(null);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        unDepositedFundReferenceHeaderRepository.save(got);
//
//        return 1;
//    }

    private static UnDepositedFundReferenceHeaderResponse convert(UnDepositedFundReferenceHeader unDepositedFundReferenceHeader) {

        UnDepositedFundReferenceHeaderResponse typeResponse = new UnDepositedFundReferenceHeaderResponse();
        typeResponse.setTransactionTypeDescription(unDepositedFundReferenceHeader.getTransactionTypeDescription());
        typeResponse.setTransactionIsInvoice(unDepositedFundReferenceHeader.getTransactionIsInvoice());
        typeResponse.setTransactionIsReceipt(unDepositedFundReferenceHeader.getTransactionIsReceipt());
        typeResponse.setTransactionBranch(BranchNetworkServiceImpl.convert(unDepositedFundReferenceHeader.getTransactionBranch()));
        typeResponse.setTransactionId(unDepositedFundReferenceHeader.getTransactionId());
        typeResponse.setTransactionDate(new DateFormatConverter().patternDate(unDepositedFundReferenceHeader.getTransactionDate()));
        typeResponse.setTransactionAmount(unDepositedFundReferenceHeader.getTransactionAmount());
        typeResponse.setUnDepositedTotalAmount(unDepositedFundReferenceHeader.getUnDepositedTotalAmount());
        typeResponse.setUnDepositedTotalBalance(unDepositedFundReferenceHeader.getUnDepositedTotalBalance());
        typeResponse.setId(unDepositedFundReferenceHeader.getId());
        typeResponse.setCreatedBy(unDepositedFundReferenceHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(unDepositedFundReferenceHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(unDepositedFundReferenceHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(unDepositedFundReferenceHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(unDepositedFundReferenceHeader.getIsDeleted());

        List<UnDepositedFundReferenceDetailResponse> detailResponses = new ArrayList<>();
        if (unDepositedFundReferenceHeader.getUnDepositedFundReferenceDetails() != null &&
                !unDepositedFundReferenceHeader.getUnDepositedFundReferenceDetails().isEmpty()) {

            for (UnDepositedFundReferenceDetail detail : unDepositedFundReferenceHeader.getUnDepositedFundReferenceDetails()) {
                UnDepositedFundReferenceDetailResponse detailResponse = UnDepositedFundReferenceDetailServiceImpl.convert(detail);
                detailResponses.add(detailResponse);
            }

        }

        typeResponse.setUnDepositedFundReferenceDetails(detailResponses);

        return typeResponse;
    }
}