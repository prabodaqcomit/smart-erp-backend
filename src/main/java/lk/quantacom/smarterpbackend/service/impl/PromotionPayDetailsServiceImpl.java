package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionPayDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionPayDetailsResponse;
import lk.quantacom.smarterpbackend.entity.PromotionPayDetails;
import lk.quantacom.smarterpbackend.repository.PromotionPayDetailsRepository;
import lk.quantacom.smarterpbackend.service.PromotionPayDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionPayDetailsServiceImpl implements PromotionPayDetailsService {

    @Autowired
    private PromotionPayDetailsRepository promotionPayDetailsRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public PromotionPayDetailsResponse save(PromotionPayDetailsRequest request) {

        PromotionPayDetails promotionPayDetails = new PromotionPayDetails();
        promotionPayDetails.setDetailCode(request.getDetailCode());
        promotionPayDetails.setPaymentMode(request.getPaymentMode());
        promotionPayDetails.setPayDetailCode(request.getPayDetailCode());
        promotionPayDetails.setPaymentMin(request.getPaymentMin());
        promotionPayDetails.setPayemntMax(request.getPayemntMax());
        promotionPayDetails.setDiscPer(request.getDiscPer());
        promotionPayDetails.setDiscAmt(request.getDiscAmt());
        promotionPayDetails.setFreeIssueItem(request.getFreeIssueItem());
        promotionPayDetails.setTicketId(request.getTicketId());
        promotionPayDetails.setGroupCount(request.getGroupCount());
        promotionPayDetails.setMaxDiscAmt(request.getMaxDiscAmt());
        promotionPayDetails.setPopupMsg(request.getPopupMsg());
        promotionPayDetails.setDetType(request.getDetType());
        promotionPayDetails.setIsDeleted(Deleted.NO);
        PromotionPayDetails save = promotionPayDetailsRepository.save(promotionPayDetails);

        saveLog("PromotionPayDetails", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public PromotionPayDetailsResponse update(PromotionPayDetailsUpdateRequest request) {

        PromotionPayDetails promotionPayDetails = promotionPayDetailsRepository.findById(request.getId()).orElse(null);
        if (promotionPayDetails == null) {
            return null;
        }

        promotionPayDetails.setId(request.getId());
        promotionPayDetails.setDetailCode(request.getDetailCode());
        promotionPayDetails.setPaymentMode(request.getPaymentMode());
        promotionPayDetails.setPayDetailCode(request.getPayDetailCode());
        promotionPayDetails.setPaymentMin(request.getPaymentMin());
        promotionPayDetails.setPayemntMax(request.getPayemntMax());
        promotionPayDetails.setDiscPer(request.getDiscPer());
        promotionPayDetails.setDiscAmt(request.getDiscAmt());
        promotionPayDetails.setFreeIssueItem(request.getFreeIssueItem());
        promotionPayDetails.setTicketId(request.getTicketId());
        promotionPayDetails.setGroupCount(request.getGroupCount());
        promotionPayDetails.setMaxDiscAmt(request.getMaxDiscAmt());
        promotionPayDetails.setPopupMsg(request.getPopupMsg());
        promotionPayDetails.setDetType(request.getDetType());
        PromotionPayDetails updated = promotionPayDetailsRepository.save(promotionPayDetails);

        saveLog("PromotionPayDetails", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public PromotionPayDetailsResponse getById(Long id) {

        return promotionPayDetailsRepository.findById(id).map(PromotionPayDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PromotionPayDetailsResponse> getAll() {

        return promotionPayDetailsRepository.findAll()
                .stream().map(PromotionPayDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        PromotionPayDetails got = promotionPayDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        promotionPayDetailsRepository.save(got);

        saveLog("PromotionPayDetails", "Data Deleted - " + id);

        return 1;
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

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}