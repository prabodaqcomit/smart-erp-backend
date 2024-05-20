package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.PromotionDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PromotionDetailsResponse;
import lk.quantacom.smarterpbackend.entity.PromotionDetails;
import lk.quantacom.smarterpbackend.entity.PromotionHeader;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.PromotionDetailsRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.PromotionDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionDetailsServiceImpl implements PromotionDetailsService {

    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;



    @Override
    @Transactional
    public PromotionDetailsResponse save(PromotionDetailsRequest request) {

        PromotionDetails promotionDetails = new PromotionDetails();
        promotionDetails.setPromoDetCode(request.getPromoDetCode());
        promotionDetails.setPromoDetCouple(request.getPromoDetCouple());
        promotionDetails.setPromoDetCouponRegex(request.getPromoDetCouponRegex());
        promotionDetails.setPromoDetCustomerGroup(request.getPromoDetCustomerGroup());
        promotionDetails.setPromoDetCustomerProfile(request.getPromoDetCustomerProfile());
        promotionDetails.setPromoDetDiscAmt(request.getPromoDetDiscAmt());
        promotionDetails.setPromoDetDiscPer(request.getPromoDetDiscPer());
        promotionDetails.setPromoDetEndQty(request.getPromoDetEndQty());
        promotionDetails.setPromoDetFreeIssueItm(request.getPromoDetFreeIssueItm());
        promotionDetails.setPromoDetGroupCode(request.getPromoDetGroupCode());
        promotionDetails.setPromoDetGroupCount(request.getPromoDetGroupCount());
        promotionDetails.setPromoDetGroupLevel(request.getPromoDetGroupLevel());
        promotionDetails.setPromoDetItem(request.getPromoDetItem());
        promotionDetails.setPromoDetMaxDisAmt(request.getPromoDetMaxDisAmt());
        promotionDetails.setPromoDetMaxQty(request.getPromoDetMaxQty());
        promotionDetails.setPromoDetPopupMessage(request.getPromoDetPopupMessage());
        promotionDetails.setPromoDetStartQty(request.getPromoDetStartQty());
        promotionDetails.setPromoDetSupCode(request.getPromoDetSupCode());
        promotionDetails.setPromoDetTicketId(request.getPromoDetTicketId());
        promotionDetails.setPromoDetType(request.getPromoDetType());
        PromotionHeader header=new PromotionHeader();
        header.setId(request.getPromoHeadId());
        promotionDetails.setPromoHead(header);
        promotionDetails.setIsDeleted(Deleted.NO);
        PromotionDetails save = promotionDetailsRepository.save(promotionDetails);

        //saveLog("Category","Data Saved - "+save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public PromotionDetailsResponse update(PromotionDetailsUpdateRequest request) {

        PromotionDetails promotionDetails = promotionDetailsRepository.findById(request.getId()).orElse(null);
        if (promotionDetails == null) {
            return null;
        }

        promotionDetails.setId(request.getId());
        promotionDetails.setPromoDetCode(request.getPromoDetCode());
        promotionDetails.setPromoDetCouple(request.getPromoDetCouple());
        promotionDetails.setPromoDetCouponRegex(request.getPromoDetCouponRegex());
        promotionDetails.setPromoDetCustomerGroup(request.getPromoDetCustomerGroup());
        promotionDetails.setPromoDetCustomerProfile(request.getPromoDetCustomerProfile());
        promotionDetails.setPromoDetDiscAmt(request.getPromoDetDiscAmt());
        promotionDetails.setPromoDetDiscPer(request.getPromoDetDiscPer());
        promotionDetails.setPromoDetEndQty(request.getPromoDetEndQty());
        promotionDetails.setPromoDetFreeIssueItm(request.getPromoDetFreeIssueItm());
        promotionDetails.setPromoDetGroupCode(request.getPromoDetGroupCode());
        promotionDetails.setPromoDetGroupCount(request.getPromoDetGroupCount());
        promotionDetails.setPromoDetGroupLevel(request.getPromoDetGroupLevel());
        promotionDetails.setPromoDetItem(request.getPromoDetItem());
        promotionDetails.setPromoDetMaxDisAmt(request.getPromoDetMaxDisAmt());
        promotionDetails.setPromoDetMaxQty(request.getPromoDetMaxQty());
        promotionDetails.setPromoDetPopupMessage(request.getPromoDetPopupMessage());
        promotionDetails.setPromoDetStartQty(request.getPromoDetStartQty());
        promotionDetails.setPromoDetSupCode(request.getPromoDetSupCode());
        promotionDetails.setPromoDetTicketId(request.getPromoDetTicketId());
        promotionDetails.setPromoDetType(request.getPromoDetType());
        //promotionDetails.setPromoHeadId(request.getPromoHeadId());
        PromotionDetails updated = promotionDetailsRepository.save(promotionDetails);

        return (convert(updated));
    }

    @Override
    public PromotionDetailsResponse getById(Long id) {

        return promotionDetailsRepository.findById(id).map(PromotionDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PromotionDetailsResponse> getAll() {

        return promotionDetailsRepository.findAll()
                .stream().map(PromotionDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        PromotionDetails got = promotionDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        promotionDetailsRepository.save(got);

        return 1;
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
}