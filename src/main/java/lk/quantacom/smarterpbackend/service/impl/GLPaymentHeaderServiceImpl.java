package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GLSupPayDetailResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.GLPaymentHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.utils.JDBC;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GLPaymentHeaderServiceImpl implements GLPaymentHeaderService {

    @Autowired
    private GLPaymentHeaderRepository gLPaymentHeaderRepository;

    @Autowired
    private GLPaymentDetailRepository gLPaymentDetailRepository;

    @Autowired
    private GLSupPayDetailRepository gLSupPayDetailRepository;

    @Autowired
    private GLPayMethodDetailsRepository gLPayMethodDetailsRepository;

    @Autowired
    BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static GLPaymentHeaderResponse convert(GLPaymentHeader gLPaymentHeader) {

        GLPaymentHeaderResponse typeResponse = new GLPaymentHeaderResponse();
        typeResponse.setPaymentDate(ConvertUtils.convertDateToStr(gLPaymentHeader.getPaymentDate()));
        typeResponse.setPayeeId(gLPaymentHeader.getPayeeId());
        typeResponse.setBranchId(gLPaymentHeader.getBranch().getId());
        typeResponse.setPayeeName(gLPaymentHeader.getPayeeName());
        typeResponse.setVoucherNumber(gLPaymentHeader.getVoucherNumber());
        typeResponse.setPaymentDetails(gLPaymentHeader.getPaymentDetails());
        typeResponse.setRemark(gLPaymentHeader.getRemark());
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

        if (gLPaymentHeader.getGlPaymentDetail() != null) {
            List<GLPaymentDetailResponse> glPaymentDetailResponseList = new ArrayList<>();
            for (GLPaymentDetail glPaymentDetail : gLPaymentHeader.getGlPaymentDetail()) {
                if (glPaymentDetail.getIsDeleted() == Deleted.NO) {
                    glPaymentDetailResponseList.add(convertDtl(glPaymentDetail));
                }
            }
            typeResponse.setGlPaymentDetailResponseList(glPaymentDetailResponseList);
        }

        if (gLPaymentHeader.getGlPayMethodDetail() != null) {
            List<GLPayMethodDetailsResponse> glPayMethodDetailsResponseList = new ArrayList<>();
            for (GLPayMethodDetails glPayMethodDetails : gLPaymentHeader.getGlPayMethodDetail()) {
                if (glPayMethodDetails.getIsDeleted() == Deleted.NO) {
                    glPayMethodDetailsResponseList.add(convertPay(glPayMethodDetails));
                }
            }
            typeResponse.setGlPayMethodDetailsResponseList(glPayMethodDetailsResponseList);
        }

        if (gLPaymentHeader.getGlSupPayDetail() != null) {
            List<GLSupPayDetailResponse> glSupPayDetailResponseList = new ArrayList<>();
            for (GLSupPayDetail glSupPayDetail : gLPaymentHeader.getGlSupPayDetail()) {
                if (glSupPayDetail.getIsDeleted() == Deleted.NO) {
                    glSupPayDetailResponseList.add(convertSup(glSupPayDetail));
                }
            }
            typeResponse.setGlSupPayDetailResponseList(glSupPayDetailResponseList);
        }


        return typeResponse;
    }

    private static GLPaymentDetailResponse convertDtl(GLPaymentDetail gLPaymentDetail) {

        GLPaymentDetailResponse typeResponse = new GLPaymentDetailResponse();
        typeResponse.setGlPayHeaderId(gLPaymentDetail.getGlPayHeaderId());
        typeResponse.setAccCode(gLPaymentDetail.getAccCode());
        typeResponse.setAccName(gLPaymentDetail.getAccName());
        typeResponse.setLedgerAmount(gLPaymentDetail.getLedgerAmount());
        typeResponse.setWht(gLPaymentDetail.getWht());
        typeResponse.setStampDuty(gLPaymentDetail.getStampDuty());
        typeResponse.setNetAmount(gLPaymentDetail.getNetAmount());
        typeResponse.setTotalAmount(gLPaymentDetail.getTotalAmount());
        typeResponse.setAmountInWord(gLPaymentDetail.getAmountInWord());
        typeResponse.setId(gLPaymentDetail.getId());
        typeResponse.setCreatedBy(gLPaymentDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(gLPaymentDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(gLPaymentDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(gLPaymentDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(gLPaymentDetail.getIsDeleted());

        return typeResponse;
    }

    private static GLPayMethodDetailsResponse convertPay(GLPayMethodDetails gLPayMethodDetails) {

        GLPayMethodDetailsResponse typeResponse = new GLPayMethodDetailsResponse();

        typeResponse.setPayMethodId(gLPayMethodDetails.getPayMethodId());
        typeResponse.setGlPayHeaderId(gLPayMethodDetails.getGlPayHeaderId());
        typeResponse.setPayMethodName(gLPayMethodDetails.getPayMethodName());
        typeResponse.setAmount(gLPayMethodDetails.getAmount());
        typeResponse.setChequeDate(gLPayMethodDetails.getChequeDate());
        typeResponse.setBank(gLPayMethodDetails.getBank());
        typeResponse.setAccount(gLPayMethodDetails.getAccount());
        typeResponse.setChequeNumber(gLPayMethodDetails.getChequeNumber());
        typeResponse.setReference(gLPayMethodDetails.getReference());
        typeResponse.setTransferDate(gLPayMethodDetails.getTransferDate());
        typeResponse.setFromBank(gLPayMethodDetails.getFromBank());
        typeResponse.setFromAccount(gLPayMethodDetails.getFromAccount());
        typeResponse.setToBank(gLPayMethodDetails.getToBank());
        typeResponse.setToAccount(gLPayMethodDetails.getToAccount());
        typeResponse.setFromWallet(gLPayMethodDetails.getFromWallet());
        typeResponse.setToWallet(gLPayMethodDetails.getToWallet());
        typeResponse.setFromCard(gLPayMethodDetails.getFromCard());
        typeResponse.setId(gLPayMethodDetails.getId());
        typeResponse.setCreatedBy(gLPayMethodDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(gLPayMethodDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(gLPayMethodDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(gLPayMethodDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(gLPayMethodDetails.getIsDeleted());

        return typeResponse;
    }

    private static GLSupPayDetailResponse convertSup(GLSupPayDetail gLSupPayDetail) {

        GLSupPayDetailResponse typeResponse = new GLSupPayDetailResponse();
        typeResponse.setGlPayHeaderId(gLSupPayDetail.getGlPayHeaderId());
        typeResponse.setSupInvDate(gLSupPayDetail.getSupInvDate());
        typeResponse.setInvNumber(gLSupPayDetail.getInvNumber());
        typeResponse.setPendingAmount(gLSupPayDetail.getPendingAmount());
        typeResponse.setPayAmount(gLSupPayDetail.getPayAmount());
        typeResponse.setGrossAmount(gLSupPayDetail.getGrossAmount());
        typeResponse.setWht(gLSupPayDetail.getWht());
        typeResponse.setStampDuty(gLSupPayDetail.getStampDuty());
        typeResponse.setNetAmount(gLSupPayDetail.getNetAmount());
        typeResponse.setAmountInWord(gLSupPayDetail.getAmountInWord());
        typeResponse.setId(gLSupPayDetail.getId());
        typeResponse.setCreatedBy(gLSupPayDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(gLSupPayDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(gLSupPayDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(gLSupPayDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(gLSupPayDetail.getIsDeleted());
        typeResponse.setInvoiceAmount(gLSupPayDetail.getInvoiceAmount());

        return typeResponse;
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse save(GLPaymentHeaderRequest request) {

        GLPaymentHeader gLPaymentHeader = new GLPaymentHeader();

        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));

        BranchNetwork branch = null;
        try {
            branch = branchNetworkRepository.getById(request.getBranchId());
            gLPaymentHeader.setBranch(branch);
        } catch (Exception ignored) {
        }

        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setRemark(request.getRemark());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);

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

        GLPaymentHeader gLPaymentHeader = gLPaymentHeaderRepository.findById(request.getId()).orElse(null);
        if (gLPaymentHeader == null) {
            return null;
        }

        BranchNetwork branch = null;
        try {
            branch = branchNetworkRepository.getById(request.getBranchId());
            gLPaymentHeader.setBranch(branch);
        } catch (Exception ignored) {
        }

        gLPaymentHeader.setId(request.getId());
        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
        gLPaymentHeader.setPayeeId(request.getPayeeId());
        gLPaymentHeader.setPayeeName(request.getPayeeName());
        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
        gLPaymentHeader.setRemark(request.getRemark());
        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
        GLPaymentHeader updated = gLPaymentHeaderRepository.save(gLPaymentHeader);

        return (convert(updated));
    }

    @Override
    public GLPaymentHeaderResponse getById(Long id) {

        return gLPaymentHeaderRepository.findById(id).map(GLPaymentHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLPaymentHeaderResponse> getAll() {

        return gLPaymentHeaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLPaymentHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLPaymentHeader got = gLPaymentHeaderRepository.findById(id).orElse(null);
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
    public List<String> getAvailableVoucherNumbers(String searchNumber) {
        return gLPaymentHeaderRepository.getAvailableVoucherNumbers(searchNumber);
    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse saveGeneral(GLPaymentHeaderGeneralRequest request) {

        GLPaymentHeader gLPaymentHeader = new GLPaymentHeader();

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        gLPaymentHeader.setBranch(branchNetwork);

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
        gLPaymentHeader.setGlPaymentDetailType("p");
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);

        for (GLPaymentDetailRequest detailRequest : request.getRequestList()) {

            GLPaymentDetail gLPaymentDetail = new GLPaymentDetail();
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

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()) {
            GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
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

        GLPaymentHeader gLPaymentHeader = new GLPaymentHeader();

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        gLPaymentHeader.setBranch(branchNetwork);

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
        gLPaymentHeader.setGlPaymentDetailType("s");
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);

        for (GLSupPayDetailRequest detailRequest : request.getRequestList()) {


            GLSupPayDetail gLSupPayDetail = new GLSupPayDetail();
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
            gLSupPayDetail.setInvoiceAmount(detailRequest.getInvoiceAmount());
            gLSupPayDetail.setIsDeleted(Deleted.NO);
            gLSupPayDetailRepository.save(gLSupPayDetail);

        }

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()) {
            GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
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

//    @Override
//    @Transactional
//    public GLPaymentHeaderResponse updateGeneral(GLPaymentHeaderGeneralUpdateRequest request) {
//        GLPaymentHeader gLPaymentHeader = null;
//        try {
//            gLPaymentHeader = gLPaymentHeaderRepository.findById(request.getId()).orElse(null);
//        } catch (Exception ignored) {
//        }
//
//        if (gLPaymentHeader == null) {
//            return null;
//        }
//
//        BranchNetwork branchNetwork = new BranchNetwork();
//        branchNetwork.setId(request.getBranchId());
//        gLPaymentHeader.setBranch(branchNetwork);
//
//        gLPaymentHeader.setPaymentDate(ConvertUtils.convertStrToDate(request.getPaymentDate()));
//        gLPaymentHeader.setPayeeId(request.getPayeeId());
//        gLPaymentHeader.setPayeeName(request.getPayeeName());
//        gLPaymentHeader.setVoucherNumber(request.getVoucherNumber());
//        gLPaymentHeader.setPaymentDetails(request.getPaymentDetails());
//        gLPaymentHeader.setGlPayModId(request.getGlPayModId());
//        gLPaymentHeader.setGlPayModName(request.getGlPayModName());
//        gLPaymentHeader.setGlPayModAmount(request.getGlPayModAmount());
//        gLPaymentHeader.setGlTotalAmount(request.getGlTotalAmount());
//        gLPaymentHeader.setGlTotalAmtWord(request.getGlTotalAmtWord());
//        gLPaymentHeader.setGlPaymentDetailId(request.getGlPaymentDetailId());
//        gLPaymentHeader.setGlPaymentDetailName(request.getGlPaymentDetailName());
//        gLPaymentHeader.setGlPaymentDetailType("p");
//        gLPaymentHeader.setIsDeleted(Deleted.NO);
//        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);
//
//        //Delete existing general payment details
//        gLPaymentDetailRepository.deletePaymentGeneralPaymentDetailsByHeaderId(gLPaymentHeader.getId());
//        for (GLPaymentDetailUpdateRequest detailRequest : request.getRequestList()) {
//            GLPaymentDetail gLPaymentDetail = new GLPaymentDetail();
//
//            /*
//            if (detailRequest.getId() == null) {
//                gLPaymentDetail = new GLPaymentDetail();
//            } else {
//                gLPaymentDetail = gLPaymentDetailRepository.getById(detailRequest.getId());
//            }
//            */
//
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
//
//            gLPaymentDetailRepository.save(gLPaymentDetail);
//        }
//
//        //Delete existing payment method detail details
//        gLPayMethodDetailsRepository.deletePaymentPaymentDetailsByHeaderId(gLPaymentHeader.getId());
//        for (GLPayMethodDetailsUpdateRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()) {
//            GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
//
//            /*
//            if (glPaymentRequest.getId() == null) {
//                gLPayMethodDetails = new GLPayMethodDetails();
//            } else {
//                gLPayMethodDetails = gLPayMethodDetailsRepository.getById(glPaymentRequest.getId());
//            }
//            */
//
//            gLPayMethodDetails.setGlPayHeaderId(save.getId());
//            gLPayMethodDetails.setPayMethodId(glPaymentRequest.getPayMethodId());
//            gLPayMethodDetails.setPayMethodName(glPaymentRequest.getPayMethodName());
//            gLPayMethodDetails.setAmount(glPaymentRequest.getAmount());
//            gLPayMethodDetails.setChequeDate(glPaymentRequest.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getChequeDate()));
//            gLPayMethodDetails.setBank(glPaymentRequest.getBank());
//            gLPayMethodDetails.setAccount(glPaymentRequest.getAccount());
//            gLPayMethodDetails.setChequeNumber(glPaymentRequest.getChequeNumber());
//            gLPayMethodDetails.setReference(glPaymentRequest.getReference());
//            gLPayMethodDetails.setTransferDate(glPaymentRequest.getTransferDate() == null ? null : ConvertUtils.convertStrToDate(glPaymentRequest.getTransferDate()));
//            gLPayMethodDetails.setFromBank(glPaymentRequest.getFromBank());
//            gLPayMethodDetails.setFromAccount(glPaymentRequest.getFromAccount());
//            gLPayMethodDetails.setToBank(glPaymentRequest.getToBank());
//            gLPayMethodDetails.setToAccount(glPaymentRequest.getToAccount());
//            gLPayMethodDetails.setFromWallet(glPaymentRequest.getFromWallet());
//            gLPayMethodDetails.setToWallet(glPaymentRequest.getToWallet());
//            gLPayMethodDetails.setFromCard(glPaymentRequest.getFromCard());
//            gLPayMethodDetails.setIsDeleted(Deleted.NO);
//
//            gLPayMethodDetailsRepository.save(gLPayMethodDetails);
//        }
//
//        return convert(save);
//    }

    @Override
    @Transactional
    public GLPaymentHeaderResponse updateGeneral(GLPaymentHeaderGeneralUpdateRequest request) {

        List<GLPayMethodDetails> methodDetails = gLPayMethodDetailsRepository.findByGlPayHeaderIdAndIsDeleted(request.getId(), Deleted.NO);
        for (GLPayMethodDetails details: methodDetails){
            details.setIsDeleted(Deleted.YES);
            gLPayMethodDetailsRepository.save(details);
        }

        List<GLPaymentDetail> gLPaymentDetail1 = gLPaymentDetailRepository.findByGlPayHeaderIdAndIsDeleted(request.getId(),Deleted.NO);
        for (GLPaymentDetail paymentDetail:gLPaymentDetail1){
            paymentDetail.setIsDeleted(Deleted.YES);
            gLPaymentDetailRepository.save(paymentDetail);
        }

        GLPaymentHeader gLPaymentHeader = gLPaymentHeaderRepository.findByIdAndGlPaymentDetailTypeAndIsDeleted(request.getId(), "p",Deleted.NO);

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        gLPaymentHeader.setBranch(branchNetwork);

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
        gLPaymentHeader.setGlPaymentDetailType("p");
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);


        for (GLPaymentDetailRequest detailRequest : request.getRequestList()) {


            GLPaymentDetail gLPaymentDetail = new GLPaymentDetail();
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

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()) {



            GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
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
    public GLPaymentHeaderResponse updateSupplier(GLPaymentHeaderSupplierUpdateRequest request) {

        List<GLSupPayDetail> glSupPayDetails = gLSupPayDetailRepository.findByGlPayHeaderIdAndIsDeleted(request.getId(),Deleted.NO);
        for (GLSupPayDetail glSupPayDetail:glSupPayDetails){
            glSupPayDetail.setIsDeleted(Deleted.YES);
            gLSupPayDetailRepository.save(glSupPayDetail);
        }

        List<GLPayMethodDetails> methodDetails = gLPayMethodDetailsRepository.findByGlPayHeaderIdAndIsDeleted(request.getId(), Deleted.NO);
        for (GLPayMethodDetails details: methodDetails){
            details.setIsDeleted(Deleted.YES);
            gLPayMethodDetailsRepository.save(details);
        }

        GLPaymentHeader gLPaymentHeader = gLPaymentHeaderRepository.findByIdAndGlPaymentDetailTypeAndIsDeleted(request.getId(), "s",Deleted.NO);

//        GLPaymentHeader gLPaymentHeader = new GLPaymentHeader();

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        gLPaymentHeader.setBranch(branchNetwork);

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
        gLPaymentHeader.setGlPaymentDetailType("s");
        gLPaymentHeader.setIsDeleted(Deleted.NO);
        GLPaymentHeader save = gLPaymentHeaderRepository.save(gLPaymentHeader);

        for (GLSupPayDetailRequest detailRequest : request.getRequestList()) {

            GLSupPayDetail gLSupPayDetail = new GLSupPayDetail();
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
            gLSupPayDetail.setInvoiceAmount(detailRequest.getInvoiceAmount());
            gLSupPayDetail.setIsDeleted(Deleted.NO);
            gLSupPayDetailRepository.save(gLSupPayDetail);

        }

        for (GLPayMethodDetailsRequest glPaymentRequest : request.getGlPayMethodDetailsRequests()) {

            GLPayMethodDetails gLPayMethodDetails = new GLPayMethodDetails();
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
    public List<GLPaymentHeaderResponse> getAllGeneral() {

        return gLPaymentHeaderRepository.findByGlPaymentDetailTypeAndIsDeleted("p", Deleted.NO)
                .stream().map(GLPaymentHeaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<GLPaymentHeaderResponse> getAllSup() {

        return gLPaymentHeaderRepository.findByGlPaymentDetailTypeAndIsDeleted("s", Deleted.NO)
                .stream().map(GLPaymentHeaderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public GLPaymentHeaderResponse getGeneralPayById(Long id) {
        GLPaymentHeader glPaymentHeader = gLPaymentHeaderRepository.findByIdAndGlPaymentDetailTypeAndIsDeleted(id, "p", Deleted.NO);
        return convert(glPaymentHeader);
    }

    @Override
    public GLPaymentHeaderResponse getSupPayById(Long id) {
        GLPaymentHeader glPaymentHeader = gLPaymentHeaderRepository.findByIdAndGlPaymentDetailTypeAndIsDeleted(id, "s", Deleted.NO);
        return convert(glPaymentHeader);
    }

    @Override
    public List<GLPaymentHeaderResponse> filteredSearchGeneral(GLPaymentFilterSearchRequest request) {

        List<GLPaymentHeaderResponse> resp = new ArrayList<>();

        String sql = " select * from glpayment_header where is_deleted=0 and gl_payment_detail_type='p'  ";

        if (request.getDateFrom() != null) {
            sql = sql + " and payment_date>'" + request.getDateFrom() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getDateTo() != null) {
            sql = sql + " and payment_date<'" + request.getDateTo() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getAmount() != null) {
            sql = sql + " and gl_pay_mod_amount='" + request.getAmount() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getPayeeName() != null) {
            sql = sql + " and payee_name='" + request.getPayeeName() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getVoucherNumber() != null) {
            sql = sql + " and voucher_number='" + request.getVoucherNumber() + "' ";
        } else {
            sql = sql + " ";
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

                List<GLPaymentDetailResponse> glPaymentDetailResponseList = new ArrayList<>();
                List<GLPaymentDetail> glPaymentDetails = gLPaymentDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPaymentDetail glPaymentDetail : glPaymentDetails) {
                    GLPaymentDetailResponse response = convertDtl(glPaymentDetail);
                    glPaymentDetailResponseList.add(response);
                }
                typeResponse.setGlPaymentDetailResponseList(glPaymentDetailResponseList);


                List<GLPayMethodDetailsResponse> glPayMethodDetailsResponseList = new ArrayList<>();
                List<GLPayMethodDetails> glPayMethodDetails = gLPayMethodDetailsRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPayMethodDetails glPaymentDetail : glPayMethodDetails) {
                    GLPayMethodDetailsResponse response = convertPay(glPaymentDetail);
                    glPayMethodDetailsResponseList.add(response);
                }
                typeResponse.setGlPayMethodDetailsResponseList(glPayMethodDetailsResponseList);


                List<GLSupPayDetailResponse> glSupPayDetailResponseList = new ArrayList<>();
                List<GLSupPayDetail> glSupPayDetails = gLSupPayDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLSupPayDetail glSupPayDetail : glSupPayDetails) {
                    GLSupPayDetailResponse response = convertSup(glSupPayDetail);
                    glSupPayDetailResponseList.add(response);
                }
                typeResponse.setGlSupPayDetailResponseList(glSupPayDetailResponseList);


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

    @Override
    public List<GLPaymentHeaderResponse> filteredSearch(GLPaymentFilterSearchRequest request) {

        List<GLPaymentHeaderResponse> resp = new ArrayList<>();

        String sql = " select * from glpayment_header where is_deleted = 0  ";

        if (request.getDateFrom() != null) {
            sql = sql + " and payment_date>'" + request.getDateFrom() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getDateTo() != null) {
            sql = sql + " and payment_date<'" + request.getDateTo() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getAmount() != null) {
            sql = sql + " and gl_pay_mod_amount='" + request.getAmount() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getPayeeName() != null) {
            sql = sql + " and payee_name='" + request.getPayeeName() + "' ";
        } else {
            sql = sql + " ";
        }

        if (request.getVoucherNumber() != null) {
            sql = sql + " and voucher_number='" + request.getVoucherNumber() + "' ";
        } else {
            sql = sql + " ";
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

                List<GLPaymentDetailResponse> glPaymentDetailResponseList = new ArrayList<>();
                List<GLPaymentDetail> glPaymentDetails = gLPaymentDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPaymentDetail glPaymentDetail : glPaymentDetails) {
                    GLPaymentDetailResponse response = convertDtl(glPaymentDetail);
                    glPaymentDetailResponseList.add(response);
                }
                typeResponse.setGlPaymentDetailResponseList(glPaymentDetailResponseList);


                List<GLPayMethodDetailsResponse> glPayMethodDetailsResponseList = new ArrayList<>();
                List<GLPayMethodDetails> glPayMethodDetails = gLPayMethodDetailsRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPayMethodDetails glPaymentDetail : glPayMethodDetails) {
                    GLPayMethodDetailsResponse response = convertPay(glPaymentDetail);
                    glPayMethodDetailsResponseList.add(response);
                }
                typeResponse.setGlPayMethodDetailsResponseList(glPayMethodDetailsResponseList);


                List<GLSupPayDetailResponse> glSupPayDetailResponseList = new ArrayList<>();
                List<GLSupPayDetail> glSupPayDetails = gLSupPayDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLSupPayDetail glSupPayDetail : glSupPayDetails) {
                    GLSupPayDetailResponse response = convertSup(glSupPayDetail);
                    glSupPayDetailResponseList.add(response);
                }
                typeResponse.setGlSupPayDetailResponseList(glSupPayDetailResponseList);


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

    @Override
    public List<GLPaymentHeaderResponse> filteredSearchSupplier(GLPaymentFilterSearchRequest request) {

        List<GLPaymentHeaderResponse> resp = new ArrayList<>();

        String sql = " select * from glpayment_header where is_deleted = 0 and gl_payment_detail_type = 's'  ";

        if (request.getDateFrom() != null) {
            sql = sql + " and payment_date>'" + request.getDateFrom() + "' ";
        } else {
            sql = sql + "";
        }

        if (request.getDateTo() != null) {
            sql = sql + " and payment_date<'" + request.getDateTo() + "' ";
        } else {
            sql = sql + "";
        }

        if (request.getAmount() != null) {
            sql = sql + " and payee_name='" + request.getAmount() + "' ";
        } else {
            sql = sql + "";
        }

        if (request.getPayeeName() != null) {
            sql = sql + " and gl_pay_mod_amount='" + request.getPayeeName() + "' ";
        } else {
            sql = sql + "";
        }

        if (request.getVoucherNumber() != null) {
            sql = sql + " and voucher_number='" + request.getVoucherNumber() + "' ";
        } else {
            sql = sql + "";
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

                List<GLPaymentDetailResponse> glPaymentDetailResponseList = new ArrayList<>();
                List<GLPaymentDetail> glPaymentDetails = gLPaymentDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPaymentDetail glPaymentDetail : glPaymentDetails) {
                    GLPaymentDetailResponse response = convertDtl(glPaymentDetail);
                    glPaymentDetailResponseList.add(response);
                }
                typeResponse.setGlPaymentDetailResponseList(glPaymentDetailResponseList);


                List<GLPayMethodDetailsResponse> glPayMethodDetailsResponseList = new ArrayList<>();
                List<GLPayMethodDetails> glPayMethodDetails = gLPayMethodDetailsRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLPayMethodDetails glPaymentDetail : glPayMethodDetails) {
                    GLPayMethodDetailsResponse response = convertPay(glPaymentDetail);
                    glPayMethodDetailsResponseList.add(response);
                }
                typeResponse.setGlPayMethodDetailsResponseList(glPayMethodDetailsResponseList);


                List<GLSupPayDetailResponse> glSupPayDetailResponseList = new ArrayList<>();
                List<GLSupPayDetail> glSupPayDetails = gLSupPayDetailRepository.findByGlPayHeaderIdAndIsDeleted(rs.getLong("id"), Deleted.NO);
                for (GLSupPayDetail glSupPayDetail : glSupPayDetails) {
                    GLSupPayDetailResponse response = convertSup(glSupPayDetail);
                    glSupPayDetailResponseList.add(response);
                }
                typeResponse.setGlSupPayDetailResponseList(glSupPayDetailResponseList);


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

    @Override
    public File printSingle(Long id) {
        File out = null;
        Connection co = null;

        try {

            File file = null;

            //Is General Payment
            List<GLPaymentDetail> _genPayDetails = gLPaymentDetailRepository.findByIsDeletedAndGlPayHeaderId(Deleted.NO, id);
            if (_genPayDetails != null && !_genPayDetails.isEmpty()) {
                file = new File("JRXML/report/leader_reports/rpt_Payment_General_Payment.jrxml");
            }

            //Is Supplier Payment
            //TODO: check file == null before query db again
            List<GLSupPayDetail> _supPayDetails = gLSupPayDetailRepository.findByIsDeletedAndGlPayHeaderId(Deleted.NO, id);
            if (_supPayDetails != null && !_supPayDetails.isEmpty()) {
                file = new File("JRXML/report/leader_reports/rpt_Payment_Supplier_Payment.jrxml");
            }

            if (file == null) {
                return null;
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



}

