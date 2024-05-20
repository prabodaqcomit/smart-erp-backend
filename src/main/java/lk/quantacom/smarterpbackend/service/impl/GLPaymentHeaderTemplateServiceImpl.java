package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderService;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderTemplateService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GLPaymentHeaderTemplateServiceImpl implements GLPaymentHeaderTemplateService {

    @Autowired
    private GLPaymentHeaderTemplateRepository gLPaymentHeaderRepository;

    @Autowired
    private GLPaymentDetailTemplateRepository gLPaymentDetailRepository;

    @Autowired
    private GLSupPayDetailTemplateRepository gLSupPayDetailRepository;

    @Autowired
    private GLPayMethodDetailsTemplateRepository gLPayMethodDetailsRepository;

    private static GLPaymentHeaderResponse convert(GLPaymentHeaderTemplate gLPaymentHeader) {

        GLPaymentHeaderResponse typeResponse = new GLPaymentHeaderResponse();
        typeResponse.setPaymentDate(ConvertUtils.convertDateToStr(gLPaymentHeader.getPaymentDate()));
        typeResponse.setPayeeId(gLPaymentHeader.getPayeeId());
        typeResponse.setPayeeName(gLPaymentHeader.getPayeeName());
        typeResponse.setVoucherNumber(gLPaymentHeader.getVoucherNumber());
        typeResponse.setPaymentDetails(gLPaymentHeader.getPaymentDetails());
        typeResponse.setGlPayModId(gLPaymentHeader.getGlPayModId());
        typeResponse.setGlPayModName(gLPaymentHeader.getGlPayModName());
        typeResponse.setGlPayModAmount(gLPaymentHeader.getGlPayModAmount());
        typeResponse.setGlTotalAmount(gLPaymentHeader.getGlTotalAmount());
        typeResponse.setGlTotalAmtWord(gLPaymentHeader.getGlTotalAmtWord());
        typeResponse.setGlPaymentDetailId(gLPaymentHeader.getGlPaymentDetailId());
        typeResponse.setGlPaymentDetailName(gLPaymentHeader.getGlPaymentDetailName());
        typeResponse.setId(gLPaymentHeader.getId());
        typeResponse.setCreatedBy(gLPaymentHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(gLPaymentHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(gLPaymentHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(gLPaymentHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(gLPaymentHeader.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse save(GLPaymentHeaderRequest request) {

        GLPaymentHeaderTemplate gLPaymentHeader = new GLPaymentHeaderTemplate();
        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeaderTemplate save = gLPaymentHeaderRepository.save(gLPaymentHeader);

//        for (GLPaymentDetailRequest detailRequest: request.getRequestList()){
//
//            GLPaymentDetail gLPaymentDetail = new GLPaymentDetail();
//            gLPaymentDetail.setGlPayHeaderId(save.getId());
//            gLPaymentDetail.setAccCode(detailRequest.getAccCode());
//            gLPaymentDetail.setAccName(detailRequest.getAccName());
//            gLPaymentDetail.setLedgerAmount(detailRequest.getLedgerAmount());
//            gLPaymentDetail.setWht(detailRequest.getWht());
//            gLPaymentDetail.setStampDuty(detailRequest.getStampDuty());
//            gLPaymentDetail.setNetAmount(detailRequest.getNetAmount());
//            gLPaymentDetail.setTotalAmount(detailRequest.getTotalAmount());
//            gLPaymentDetail.setAmountInWord(detailRequest.getAmountInWord());
//            gLPaymentDetail.setIsDeleted(Deleted.NO);
//            gLPaymentDetailRepository.save(gLPaymentDetail);
//        }

        return convert(save);
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse update(GLPaymentHeaderUpdateRequest request) {

        GLPaymentHeaderTemplate gLPaymentHeader = gLPaymentHeaderRepository.findById(request.getId()).orElse(null);
        if (gLPaymentHeader == null) {
            return null;
        }

        gLPaymentHeader.setId(request.getId());
        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        GLPaymentHeaderTemplate updated = gLPaymentHeaderRepository.save(gLPaymentHeader);

        return (convert(updated));
    }

    @Override
    public GLPaymentHeaderResponse getById(Long id) {

        return gLPaymentHeaderRepository.findById(id).map(GLPaymentHeaderTemplateServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLPaymentHeaderResponse> getAll() {

        return gLPaymentHeaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLPaymentHeaderTemplateServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLPaymentHeaderTemplate got = gLPaymentHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        gLPaymentHeaderRepository.save(got);

        return 1;
    }

    @Override
    public String getMaxVoucherNum() {
        String newMax = "00001";
        Integer max = gLPaymentHeaderRepository.getMaxId();
        if (max != null) {
            newMax = "V" + new SimpleDateFormat("yy").format(new Date()) + "-" + String.format("%05d", max + 1);
        } else {
            newMax = "V" + new SimpleDateFormat("yy").format(new Date()) + "-" + newMax;
        }
        return newMax;
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse saveGeneral(GLPaymentHeaderGeneralRequest request) {

        GLPaymentHeaderTemplate gLPaymentHeader = new GLPaymentHeaderTemplate();
        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeaderTemplate save = gLPaymentHeaderRepository.save(gLPaymentHeader);

        for (GLPaymentDetailRequest detailRequest: request.getRequestList()){

            GLPaymentDetailTemplate gLPaymentDetail = new GLPaymentDetailTemplate();
            gLPaymentDetail.setGlPayHeaderId(save.getId());
            gLPaymentDetail.setAccCode(detailRequest.getAccCode());
            gLPaymentDetail.setAccName(detailRequest.getAccName());
            gLPaymentDetail.setLedgerAmount(detailRequest.getLedgerAmount());
            gLPaymentDetail.setWht(detailRequest.getWht());
            gLPaymentDetail.setStampDuty(detailRequest.getStampDuty());
            gLPaymentDetail.setNetAmount(detailRequest.getNetAmount());
            gLPaymentDetail.setTotalAmount(detailRequest.getTotalAmount());
            gLPaymentDetail.setAmountInWord(detailRequest.getAmountInWord());
            gLPaymentDetail.setIsDeleted(Deleted.NO);
            gLPaymentDetailRepository.save(gLPaymentDetail);
        }

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()){

            GLPayMethodDetailsTemplate gLPayMethodDetails = new GLPayMethodDetailsTemplate();
            gLPayMethodDetails.setGlPayHeaderId(save.getId());
            gLPayMethodDetails.setPayMethodId(glPaymentRequest.getPayMethodId());
            gLPayMethodDetails.setPayMethodName(glPaymentRequest.getPayMethodName());
            gLPayMethodDetails.setAmount(glPaymentRequest.getAmount());
            gLPayMethodDetails.setChequeDate(glPaymentRequest.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getChequeDate()));
            gLPayMethodDetails.setBank(glPaymentRequest.getBank());
            gLPayMethodDetails.setAccount(glPaymentRequest.getAccount());
            gLPayMethodDetails.setChequeNumber(glPaymentRequest.getChequeNumber());
            gLPayMethodDetails.setReference(glPaymentRequest.getReference());
            gLPayMethodDetails.setTransferDate(glPaymentRequest.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getTransferDate()));
            gLPayMethodDetails.setFromBank(glPaymentRequest.getFromBank());
            gLPayMethodDetails.setFromAccount(glPaymentRequest.getFromAccount());
            gLPayMethodDetails.setToBank(glPaymentRequest.getToBank());
            gLPayMethodDetails.setToAccount(glPaymentRequest.getToAccount());
            gLPayMethodDetails.setFromWallet(glPaymentRequest.getFromWallet());
            gLPayMethodDetails.setToWallet(glPaymentRequest.getToWallet());
            gLPayMethodDetails.setFromCard(glPaymentRequest.getFromCard());
            gLPayMethodDetails.setIsDeleted(Deleted.NO);
            gLPayMethodDetailsRepository.save(gLPayMethodDetails);
        }

        return convert(save);
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse saveSupplier(GLPaymentHeaderSupplierRequest request) {

        GLPaymentHeaderTemplate gLPaymentHeader = new GLPaymentHeaderTemplate();
        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeaderTemplate save = gLPaymentHeaderRepository.save(gLPaymentHeader);

        for (GLSupPayDetailRequest detailRequest: request.getRequestList()){


            GLSupPayDetailTemplate gLSupPayDetail = new GLSupPayDetailTemplate();
            gLSupPayDetail.setGlPayHeaderId(save.getId());
            gLSupPayDetail.setSupInvDate(detailRequest.getSupInvDate() == null ? null : ConvertUtils.convertStrToDate(detailRequest.getSupInvDate()));
            gLSupPayDetail.setInvNumber(detailRequest.getInvNumber());
            gLSupPayDetail.setPendingAmount(detailRequest.getPendingAmount());
            gLSupPayDetail.setPayAmount(detailRequest.getPayAmount());
            gLSupPayDetail.setGrossAmount(detailRequest.getGrossAmount());
            gLSupPayDetail.setWht(detailRequest.getWht());
            gLSupPayDetail.setStampDuty(detailRequest.getStampDuty());
            gLSupPayDetail.setNetAmount(detailRequest.getNetAmount());
            gLSupPayDetail.setAmountInWord(detailRequest.getAmountInWord());
            gLSupPayDetail.setIsDeleted(Deleted.NO);
            gLSupPayDetailRepository.save(gLSupPayDetail);

        }

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()){
            GLPayMethodDetailsTemplate gLPayMethodDetails = new GLPayMethodDetailsTemplate();
            gLPayMethodDetails.setGlPayHeaderId(gLPayMethodDetails.getGlPayHeaderId());
            gLPayMethodDetails.setPayMethodId(glPaymentRequest.getPayMethodId());
            gLPayMethodDetails.setPayMethodName(glPaymentRequest.getPayMethodName());
            gLPayMethodDetails.setAmount(glPaymentRequest.getAmount());
            gLPayMethodDetails.setChequeDate(glPaymentRequest.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getChequeDate()));
            gLPayMethodDetails.setBank(glPaymentRequest.getBank());
            gLPayMethodDetails.setAccount(glPaymentRequest.getAccount());
            gLPayMethodDetails.setChequeNumber(glPaymentRequest.getChequeNumber());
            gLPayMethodDetails.setReference(glPaymentRequest.getReference());
            gLPayMethodDetails.setTransferDate(glPaymentRequest.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getTransferDate()));
            gLPayMethodDetails.setFromBank(glPaymentRequest.getFromBank());
            gLPayMethodDetails.setFromAccount(glPaymentRequest.getFromAccount());
            gLPayMethodDetails.setToBank(glPaymentRequest.getToBank());
            gLPayMethodDetails.setToAccount(glPaymentRequest.getToAccount());
            gLPayMethodDetails.setFromWallet(glPaymentRequest.getFromWallet());
            gLPayMethodDetails.setToWallet(glPaymentRequest.getToWallet());
            gLPayMethodDetails.setFromCard(glPaymentRequest.getFromCard());
            gLPayMethodDetails.setIsDeleted(Deleted.NO);
            gLPayMethodDetailsRepository.save(gLPayMethodDetails);
        }

        return convert(save);
    }

    @Override
    public List<GLPaymentHeaderResponse> filteredSearch(GLPaymentFilterSearchRequest request) {

        List<GLPaymentHeaderResponse> resp = new ArrayList<>();

        String sql = " select * from glpayment_header where is_deleted=0  " ;

        if (request.getDateFrom()!=null){
            sql=sql+" and payment_date>'"+request.getDateFrom()+"' ";
        }else {
            sql=sql+" ";
        }

        if (request.getDateTo()!=null){
            sql=sql+" and payment_date<'"+request.getDateTo()+"' ";
        }else {
            sql=sql+" ";
        }

        if (request.getAmount()!=null){
            sql=sql+" and payee_name='"+request.getAmount()+"' ";
        }else {
            sql=sql+" ";
        }

        if (request.getPayeeName()!=null){
            sql=sql+" and gl_pay_mod_amount='"+request.getPayeeName()+"' ";
        }else {
            sql=sql+" ";
        }

        if (request.getVoucherNumber()!=null){
            sql=sql+" and voucher_number='"+request.getVoucherNumber()+"' ";
        }else {
            sql=sql+" ";
        }

        sql = sql + "  order by id desc ";

        System.out.println(sql);

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                GLPaymentHeaderResponse typeResponse = new GLPaymentHeaderResponse();

                typeResponse.setId(rs.getLong("id"));
                typeResponse.setPaymentDate(ConvertUtils.convertDateToStr(rs.getDate("payment_date")));
                typeResponse.setPayeeId(rs.getInt("payee_id"));
                typeResponse.setPayeeName(rs.getString("payee_name"));
                typeResponse.setVoucherNumber(rs.getString("voucher_number"));
                typeResponse.setPaymentDetails(rs.getString("payment_details"));
                typeResponse.setGlPayModId(rs.getLong("gl_pay_mod_id"));
                typeResponse.setGlPayModName(rs.getString("gl_pay_mod_name"));
                typeResponse.setGlPayModAmount(rs.getDouble("gl_pay_mod_amount"));
                typeResponse.setGlTotalAmount(rs.getDouble("gl_total_amount"));
                typeResponse.setGlTotalAmtWord(rs.getString("gl_total_amt_word"));
                typeResponse.setGlPaymentDetailId(rs.getLong("gl_payment_detail_id"));
                typeResponse.setGlPaymentDetailName(rs.getString("gl_payment_detail_name"));
                typeResponse.setCreatedBy(rs.getString("created_by"));
                typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(rs.getDate("created_date_time")));
                typeResponse.setModifiedBy(rs.getString("modified_by"));
                typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(rs.getDate("modified_date_time")));
                if (rs.getInt("is_deleted") == 1) {
                    typeResponse.setIsDeleted(Deleted.YES);
                } else {
                    typeResponse.setIsDeleted(Deleted.NO);
                }

                resp.add(typeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }
        return resp;
    }


}