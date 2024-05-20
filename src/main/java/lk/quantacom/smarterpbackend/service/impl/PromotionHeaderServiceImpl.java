package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.PromotionHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.PromotionPayDetailsResponse;
import lk.quantacom.smarterpbackend.entity.PromotionDetails;
import lk.quantacom.smarterpbackend.entity.PromotionHeader;
import lk.quantacom.smarterpbackend.entity.PromotionPayDetails;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.PromotionDetailsRepository;
import lk.quantacom.smarterpbackend.repository.PromotionHeaderRepository;
import lk.quantacom.smarterpbackend.repository.PromotionPayDetailsRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.PromotionHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionHeaderServiceImpl implements PromotionHeaderService {

    @Autowired
    private PromotionHeaderRepository promotionHeaderRepository;

    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;

    @Autowired
    private PromotionPayDetailsRepository promotionPayDetailsRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private void saveLog(String form,String action){
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PromotionHeaderResponse save(PromotionHeaderRequest request) {

        PromotionHeader promotionHeader = new PromotionHeader();
        promotionHeader.setPromoHedDesc(request.getPromoHedDesc());
        promotionHeader.setPromoHedActive(request.getPromoHedActive());
        promotionHeader.setPromoHedStartDateTime(request.getPromoHedStartDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getPromoHedStartDateTime()));
        promotionHeader.setPromoHedEndDateTime(request.getPromoHedEndDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getPromoHedEndDateTime()));
        promotionHeader.setPromoHedDaysOfWeek(request.getPromoHedDaysOfWeek());
        promotionHeader.setPromoHedCheckBillNetValue(request.getPromoHedCheckBillNetValue());
        promotionHeader.setPromoHedCheckCustomer(request.getPromoHedCheckCustomer());
        promotionHeader.setPromoHedCheckCustomerProfile(request.getPromoHedCheckCustomerProfile());
        promotionHeader.setPromoHedCheckPaymentMode(request.getPromoHedCheckPaymentMode());
        promotionHeader.setPromoHedCheckCoupon(request.getPromoHedCheckCoupon());
        promotionHeader.setPromoHedCheckNoOfBillsPerCustomer(request.getPromoHedCheckNoOfBillsPerCustomer());
        promotionHeader.setPromoHedCheckSupplier(request.getPromoHedCheckSupplier());
        promotionHeader.setPromoHedCheckGroup(request.getPromoHedCheckGroup());
        promotionHeader.setPromoHedCheckItem(request.getPromoHedCheckItem());
        promotionHeader.setPtomoHedCheckValidItem(request.getPtomoHedCheckValidItem());
        promotionHeader.setPromoHedBillValStart(request.getPromoHedBillValStart());
        promotionHeader.setPromoHedBillValEnd(request.getPromoHedBillValEnd());
        promotionHeader.setPromoHedLocationId(request.getPromoHedLocationId());
        promotionHeader.setPromoHedCode(request.getPromoHedCode());
        promotionHeader.setIsDeleted(Deleted.NO);
        PromotionHeader save = promotionHeaderRepository.save(promotionHeader);

        for(PromotionDetailsRequest detailsRequest: request.getPromotionDetails()){

            PromotionDetails promotionDetails = new PromotionDetails();
            promotionDetails.setPromoDetCode(detailsRequest.getPromoDetCode());
            promotionDetails.setPromoDetCouple(detailsRequest.getPromoDetCouple());
            promotionDetails.setPromoDetCouponRegex(detailsRequest.getPromoDetCouponRegex());
            promotionDetails.setPromoDetCustomerGroup(detailsRequest.getPromoDetCustomerGroup());
            promotionDetails.setPromoDetCustomerProfile(detailsRequest.getPromoDetCustomerProfile());
            promotionDetails.setPromoDetDiscAmt(detailsRequest.getPromoDetDiscAmt());
            promotionDetails.setPromoDetDiscPer(detailsRequest.getPromoDetDiscPer());
            promotionDetails.setPromoDetEndQty(detailsRequest.getPromoDetEndQty());
            promotionDetails.setPromoDetFreeIssueItm(detailsRequest.getPromoDetFreeIssueItm());
            promotionDetails.setPromoDetGroupCode(detailsRequest.getPromoDetGroupCode());
            promotionDetails.setPromoDetGroupCount(detailsRequest.getPromoDetGroupCount());
            promotionDetails.setPromoDetGroupLevel(detailsRequest.getPromoDetGroupLevel());
            promotionDetails.setPromoDetItem(detailsRequest.getPromoDetItem());
            promotionDetails.setPromoDetMaxDisAmt(detailsRequest.getPromoDetMaxDisAmt());
            promotionDetails.setPromoDetMaxQty(detailsRequest.getPromoDetMaxQty());
            promotionDetails.setPromoDetPopupMessage(detailsRequest.getPromoDetPopupMessage());
            promotionDetails.setPromoDetStartQty(detailsRequest.getPromoDetStartQty());
            promotionDetails.setPromoDetSupCode(detailsRequest.getPromoDetSupCode());
            promotionDetails.setPromoDetTicketId(detailsRequest.getPromoDetTicketId());
            promotionDetails.setPromoDetType(detailsRequest.getPromoDetType());
            promotionDetails.setPromoHead(save);
            promotionDetails.setIsDeleted(Deleted.NO);

            promotionDetailsRepository.save(promotionDetails);

        }

        for(PromotionPayDetailsRequest payDetailsRequest:request.getPromotionPayDetails()){

            PromotionPayDetails promotionPayDetails=new PromotionPayDetails();
            promotionPayDetails.setDetailCode(payDetailsRequest.getDetailCode());
            promotionPayDetails.setPaymentMode(payDetailsRequest.getPaymentMode());
            promotionPayDetails.setPayDetailCode(payDetailsRequest.getPayDetailCode());
            promotionPayDetails.setPaymentMin(payDetailsRequest.getPaymentMin());
            promotionPayDetails.setPayemntMax(payDetailsRequest.getPayemntMax());
            promotionPayDetails.setDiscPer(payDetailsRequest.getDiscPer());
            promotionPayDetails.setDiscAmt(payDetailsRequest.getDiscAmt());
            promotionPayDetails.setFreeIssueItem(payDetailsRequest.getFreeIssueItem());
            promotionPayDetails.setTicketId(payDetailsRequest.getTicketId());
            promotionPayDetails.setGroupCount(payDetailsRequest.getGroupCount());
            promotionPayDetails.setMaxDiscAmt(payDetailsRequest.getMaxDiscAmt());
            promotionPayDetails.setPopupMsg(payDetailsRequest.getPopupMsg());
            promotionPayDetails.setDetType(payDetailsRequest.getDetType());
            promotionPayDetails.setPromoHead(save);
            promotionPayDetails.setIsDeleted(Deleted.NO);
            promotionPayDetailsRepository.save(promotionPayDetails);
        }

        saveLog("Promotion Header/Details/Payment Details","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public PromotionHeaderResponse update(PromotionHeaderUpdateRequest request) {

        PromotionHeader promotionHeader = promotionHeaderRepository.findById(request.getId()).orElse(null);
        if (promotionHeader == null) {
            return null;
        }

        promotionHeader.setId(request.getId());
        promotionHeader.setPromoHedDesc(request.getPromoHedDesc());
        promotionHeader.setPromoHedActive(request.getPromoHedActive());
        promotionHeader.setPromoHedStartDateTime(request.getPromoHedStartDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getPromoHedStartDateTime()));
        promotionHeader.setPromoHedEndDateTime(request.getPromoHedEndDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getPromoHedEndDateTime()));
        promotionHeader.setPromoHedDaysOfWeek(request.getPromoHedDaysOfWeek());
        promotionHeader.setPromoHedCheckBillNetValue(request.getPromoHedCheckBillNetValue());
        promotionHeader.setPromoHedCheckCustomer(request.getPromoHedCheckCustomer());
        promotionHeader.setPromoHedCheckCustomerProfile(request.getPromoHedCheckCustomerProfile());
        promotionHeader.setPromoHedCheckPaymentMode(request.getPromoHedCheckPaymentMode());
        promotionHeader.setPromoHedCheckCoupon(request.getPromoHedCheckCoupon());
        promotionHeader.setPromoHedCheckNoOfBillsPerCustomer(request.getPromoHedCheckNoOfBillsPerCustomer());
        promotionHeader.setPromoHedCheckSupplier(request.getPromoHedCheckSupplier());
        promotionHeader.setPromoHedCheckGroup(request.getPromoHedCheckGroup());
        promotionHeader.setPromoHedCheckItem(request.getPromoHedCheckItem());
        promotionHeader.setPtomoHedCheckValidItem(request.getPtomoHedCheckValidItem());
        promotionHeader.setPromoHedBillValStart(request.getPromoHedBillValStart());
        promotionHeader.setPromoHedBillValEnd(request.getPromoHedBillValEnd());
        promotionHeader.setPromoHedLocationId(request.getPromoHedLocationId());
        promotionHeader.setPromoHedCode(request.getPromoHedCode());
        PromotionHeader updated = promotionHeaderRepository.save(promotionHeader);

        List<PromotionDetails> promotionDetailsList= promotionDetailsRepository.findByIsDeletedAndPromoHead(Deleted.NO,promotionHeader);
        if(promotionDetailsList!=null){
            for(PromotionDetails details :promotionDetailsList){
                details.setIsDeleted(Deleted.YES);
                promotionDetailsRepository.save(details);
            }
        }

        for(PromotionDetailsRequest detailsRequest: request.getPromotionDetails()){

            PromotionDetails promotionDetails = new PromotionDetails();
            promotionDetails.setPromoDetCode(detailsRequest.getPromoDetCode());
            promotionDetails.setPromoDetCouple(detailsRequest.getPromoDetCouple());
            promotionDetails.setPromoDetCouponRegex(detailsRequest.getPromoDetCouponRegex());
            promotionDetails.setPromoDetCustomerGroup(detailsRequest.getPromoDetCustomerGroup());
            promotionDetails.setPromoDetCustomerProfile(detailsRequest.getPromoDetCustomerProfile());
            promotionDetails.setPromoDetDiscAmt(detailsRequest.getPromoDetDiscAmt());
            promotionDetails.setPromoDetDiscPer(detailsRequest.getPromoDetDiscPer());
            promotionDetails.setPromoDetEndQty(detailsRequest.getPromoDetEndQty());
            promotionDetails.setPromoDetFreeIssueItm(detailsRequest.getPromoDetFreeIssueItm());
            promotionDetails.setPromoDetGroupCode(detailsRequest.getPromoDetGroupCode());
            promotionDetails.setPromoDetGroupCount(detailsRequest.getPromoDetGroupCount());
            promotionDetails.setPromoDetGroupLevel(detailsRequest.getPromoDetGroupLevel());
            promotionDetails.setPromoDetItem(detailsRequest.getPromoDetItem());
            promotionDetails.setPromoDetMaxDisAmt(detailsRequest.getPromoDetMaxDisAmt());
            promotionDetails.setPromoDetMaxQty(detailsRequest.getPromoDetMaxQty());
            promotionDetails.setPromoDetPopupMessage(detailsRequest.getPromoDetPopupMessage());
            promotionDetails.setPromoDetStartQty(detailsRequest.getPromoDetStartQty());
            promotionDetails.setPromoDetSupCode(detailsRequest.getPromoDetSupCode());
            promotionDetails.setPromoDetTicketId(detailsRequest.getPromoDetTicketId());
            promotionDetails.setPromoDetType(detailsRequest.getPromoDetType());
            promotionDetails.setPromoHead(updated);
            promotionDetails.setIsDeleted(Deleted.NO);

            promotionDetailsRepository.save(promotionDetails);

        }

        List<PromotionPayDetails> promotionPayDetailsList= promotionPayDetailsRepository.findByIsDeletedAndPromoHead(Deleted.NO,promotionHeader);
        if(promotionPayDetailsList!=null){
            for(PromotionPayDetails details :promotionPayDetailsList){
                details.setIsDeleted(Deleted.YES);
                promotionPayDetailsRepository.save(details);
            }
        }

        for(PromotionPayDetailsRequest payDetailsRequest:request.getPromotionPayDetails()){

            PromotionPayDetails promotionPayDetails=new PromotionPayDetails();
            promotionPayDetails.setDetailCode(payDetailsRequest.getDetailCode());
            promotionPayDetails.setPaymentMode(payDetailsRequest.getPaymentMode());
            promotionPayDetails.setPayDetailCode(payDetailsRequest.getPayDetailCode());
            promotionPayDetails.setPaymentMin(payDetailsRequest.getPaymentMin());
            promotionPayDetails.setPayemntMax(payDetailsRequest.getPayemntMax());
            promotionPayDetails.setDiscPer(payDetailsRequest.getDiscPer());
            promotionPayDetails.setDiscAmt(payDetailsRequest.getDiscAmt());
            promotionPayDetails.setFreeIssueItem(payDetailsRequest.getFreeIssueItem());
            promotionPayDetails.setTicketId(payDetailsRequest.getTicketId());
            promotionPayDetails.setGroupCount(payDetailsRequest.getGroupCount());
            promotionPayDetails.setMaxDiscAmt(payDetailsRequest.getMaxDiscAmt());
            promotionPayDetails.setPopupMsg(payDetailsRequest.getPopupMsg());
            promotionPayDetails.setDetType(payDetailsRequest.getDetType());
            promotionPayDetails.setPromoHead(updated);
            promotionPayDetails.setIsDeleted(Deleted.NO);
            promotionPayDetailsRepository.save(promotionPayDetails);
        }

        saveLog("Promotion Header/Details/Payement Details","Data Updated - "+updated.getId());


        return (convert(updated));
    }

    @Override
    public PromotionHeaderResponse getById(Long id) {

        return promotionHeaderRepository.findById(id).map(PromotionHeaderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PromotionHeaderResponse> getAll() {

        return promotionHeaderRepository.findAll()
                .stream().map(PromotionHeaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public String getMaxCode() {

        String max=promotionHeaderRepository.getMaxCode();
        if(max!=null){
            return (Integer.parseInt(max)+1)+"";
        }

        return "1000";
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        PromotionHeader got = promotionHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        promotionHeaderRepository.save(got);

        List<PromotionDetails> promotionDetailsList= promotionDetailsRepository.findByIsDeletedAndPromoHead(Deleted.NO,got);
        if(promotionDetailsList!=null){
            for(PromotionDetails details :promotionDetailsList){
                details.setIsDeleted(Deleted.YES);
                promotionDetailsRepository.save(details);
            }
        }

        saveLog("Promotion Header/Details","Data Deleted - "+id);

        return 1;
    }

    private static PromotionHeaderResponse convert(PromotionHeader promotionHeader) {

        PromotionHeaderResponse typeResponse = new PromotionHeaderResponse();
        typeResponse.setPromoHedDesc(promotionHeader.getPromoHedDesc());
        typeResponse.setPromoHedActive(promotionHeader.getPromoHedActive());
        typeResponse.setPromoHedStartDateTime(promotionHeader.getPromoHedStartDateTime());
        typeResponse.setPromoHedEndDateTime(promotionHeader.getPromoHedEndDateTime());
        typeResponse.setPromoHedDaysOfWeek(promotionHeader.getPromoHedDaysOfWeek());
        typeResponse.setPromoHedCheckBillNetValue(promotionHeader.getPromoHedCheckBillNetValue());
        typeResponse.setPromoHedCheckCustomer(promotionHeader.getPromoHedCheckCustomer());
        typeResponse.setPromoHedCheckCustomerProfile(promotionHeader.getPromoHedCheckCustomerProfile());
        typeResponse.setPromoHedCheckPaymentMode(promotionHeader.getPromoHedCheckPaymentMode());
        typeResponse.setPromoHedCheckCoupon(promotionHeader.getPromoHedCheckCoupon());
        typeResponse.setPromoHedCheckNoOfBillsPerCustomer(promotionHeader.getPromoHedCheckNoOfBillsPerCustomer());
        typeResponse.setPromoHedCheckSupplier(promotionHeader.getPromoHedCheckSupplier());
        typeResponse.setPromoHedCheckGroup(promotionHeader.getPromoHedCheckGroup());
        typeResponse.setPromoHedCheckItem(promotionHeader.getPromoHedCheckItem());
        typeResponse.setPtomoHedCheckValidItem(promotionHeader.getPtomoHedCheckValidItem());
        typeResponse.setPromoHedBillValStart(promotionHeader.getPromoHedBillValStart());
        typeResponse.setPromoHedBillValEnd(promotionHeader.getPromoHedBillValEnd());
        typeResponse.setPromoHedLocationId(promotionHeader.getPromoHedLocationId());
        typeResponse.setPromoHedCode(promotionHeader.getPromoHedCode());
        typeResponse.setId(promotionHeader.getId());
        typeResponse.setCreatedBy(promotionHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(promotionHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(promotionHeader.getModifiedBy());
        typeResponse.setIsDeleted(promotionHeader.getIsDeleted());

        List<PromotionDetailsResponse> promotionDetailsResponses=new ArrayList<>();
        if(promotionHeader.getPromotionDetails()!=null){
            for(PromotionDetails promotionDetails:promotionHeader.getPromotionDetails()){
                if(promotionDetails.getIsDeleted()==Deleted.NO){
                    promotionDetailsResponses.add(convert(promotionDetails));
                }
            }
        }

        typeResponse.setPromotionDetails(promotionDetailsResponses);

        List<PromotionPayDetailsResponse> promotionPayDetailsResponses=new ArrayList<>();

        if(promotionHeader.getPromotionPayDetails()!=null){
            for(PromotionPayDetails promotionPayDetails:promotionHeader.getPromotionPayDetails()){
                if(promotionPayDetails.getIsDeleted()==Deleted.NO){
                    promotionPayDetailsResponses.add(convert(promotionPayDetails));
                }
            }
        }

        typeResponse.setPromotionPayDetails(promotionPayDetailsResponses);

        return typeResponse;
    }

    private static PromotionDetailsResponse convert(PromotionDetails promotionDetails) {

        PromotionDetailsResponse typeResponse = new PromotionDetailsResponse();
        typeResponse.setPromoDetCode(promotionDetails.getPromoDetCode());
        typeResponse.setPromoDetCouple(promotionDetails.getPromoDetCouple());
        typeResponse.setPromoDetCouponRegex(promotionDetails.getPromoDetCouponRegex());
        typeResponse.setPromoDetCustomerGroup(promotionDetails.getPromoDetCustomerGroup());
        typeResponse.setPromoDetCustomerProfile(promotionDetails.getPromoDetCustomerProfile());
        typeResponse.setPromoDetDiscAmt(promotionDetails.getPromoDetDiscAmt());
        typeResponse.setPromoDetDiscPer(promotionDetails.getPromoDetDiscPer());
        typeResponse.setPromoDetEndQty(promotionDetails.getPromoDetEndQty());
        typeResponse.setPromoDetFreeIssueItm(promotionDetails.getPromoDetFreeIssueItm());
        typeResponse.setPromoDetGroupCode(promotionDetails.getPromoDetGroupCode());
        typeResponse.setPromoDetGroupCount(promotionDetails.getPromoDetGroupCount());
        typeResponse.setPromoDetGroupLevel(promotionDetails.getPromoDetGroupLevel());
        typeResponse.setPromoDetItem(promotionDetails.getPromoDetItem());
        typeResponse.setPromoDetMaxDisAmt(promotionDetails.getPromoDetMaxDisAmt());
        typeResponse.setPromoDetMaxQty(promotionDetails.getPromoDetMaxQty());
        typeResponse.setPromoDetPopupMessage(promotionDetails.getPromoDetPopupMessage());
        typeResponse.setPromoDetStartQty(promotionDetails.getPromoDetStartQty());
        typeResponse.setPromoDetSupCode(promotionDetails.getPromoDetSupCode());
        typeResponse.setPromoDetTicketId(promotionDetails.getPromoDetTicketId());
        typeResponse.setPromoDetType(promotionDetails.getPromoDetType());
        typeResponse.setPromoHeadId(promotionDetails.getPromoHead().getId());
        typeResponse.setId(promotionDetails.getId());
        typeResponse.setCreatedBy(promotionDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(promotionDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(promotionDetails.getModifiedBy());
        typeResponse.setIsDeleted(promotionDetails.getIsDeleted());

        return typeResponse;
    }

    private static PromotionPayDetailsResponse convert(PromotionPayDetails promotionPayDetails) {

        PromotionPayDetailsResponse typeResponse = new PromotionPayDetailsResponse();
        typeResponse.setDetailCode(promotionPayDetails.getDetailCode());
        typeResponse.setPaymentMode(promotionPayDetails.getPaymentMode());
        typeResponse.setPayDetailCode(promotionPayDetails.getPayDetailCode());
        typeResponse.setPaymentMin(promotionPayDetails.getPaymentMin());
        typeResponse.setPayemntMax(promotionPayDetails.getPayemntMax());
        typeResponse.setDiscPer(promotionPayDetails.getDiscPer());
        typeResponse.setDiscAmt(promotionPayDetails.getDiscAmt());
        typeResponse.setFreeIssueItem(promotionPayDetails.getFreeIssueItem());
        typeResponse.setTicketId(promotionPayDetails.getTicketId());
        typeResponse.setGroupCount(promotionPayDetails.getGroupCount());
        typeResponse.setMaxDiscAmt(promotionPayDetails.getMaxDiscAmt());
        typeResponse.setPopupMsg(promotionPayDetails.getPopupMsg());
        typeResponse.setDetType(promotionPayDetails.getDetType());
        typeResponse.setId(promotionPayDetails.getId());
        typeResponse.setCreatedBy(promotionPayDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(promotionPayDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(promotionPayDetails.getModifiedBy());
        typeResponse.setIsDeleted(promotionPayDetails.getIsDeleted());

        return typeResponse;
    }
}