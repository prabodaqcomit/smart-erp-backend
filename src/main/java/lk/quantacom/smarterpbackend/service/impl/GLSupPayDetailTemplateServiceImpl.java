package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLSupPayDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLSupPayDetailResponse;
import lk.quantacom.smarterpbackend.entity.GLSupPayDetail;
import lk.quantacom.smarterpbackend.entity.GLSupPayDetailTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GLSupPayDetailRepository;
import lk.quantacom.smarterpbackend.repository.GLSupPayDetailTemplateRepository;
import lk.quantacom.smarterpbackend.service.GLSupPayDetailService;
import lk.quantacom.smarterpbackend.service.GLSupPayDetailTemplateService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GLSupPayDetailTemplateServiceImpl implements GLSupPayDetailTemplateService {

    @Autowired
    private GLSupPayDetailTemplateRepository gLSupPayDetailRepository;

    private static GLSupPayDetailResponse convert(GLSupPayDetailTemplate gLSupPayDetail) {

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

        return typeResponse;
    }

    @Override
    @Transactional
    public GLSupPayDetailResponse save(GLSupPayDetailRequest request) {

        GLSupPayDetailTemplate gLSupPayDetail = new GLSupPayDetailTemplate();
        gLSupPayDetail.setGlPayHeaderId(request.getGlPayHeaderId());
        gLSupPayDetail.setSupInvDate(request.getSupInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInvDate()));
        gLSupPayDetail.setInvNumber(request.getInvNumber());
        gLSupPayDetail.setPendingAmount(request.getPendingAmount());
        gLSupPayDetail.setPayAmount(request.getPayAmount());
        gLSupPayDetail.setGrossAmount(request.getGrossAmount());
        gLSupPayDetail.setWht(request.getWht());
        gLSupPayDetail.setStampDuty(request.getStampDuty());
        gLSupPayDetail.setNetAmount(request.getNetAmount());
        gLSupPayDetail.setAmountInWord(request.getAmountInWord());
        gLSupPayDetail.setIsDeleted(Deleted.NO);
        GLSupPayDetailTemplate save = gLSupPayDetailRepository.save(gLSupPayDetail);

        return convert(save);
    }

    @Override
    @Transactional
    public GLSupPayDetailResponse update(GLSupPayDetailUpdateRequest request) {

        GLSupPayDetailTemplate gLSupPayDetail = gLSupPayDetailRepository.findById(request.getId()).orElse(null);
        if (gLSupPayDetail == null) {
            return null;
        }

        gLSupPayDetail.setId(request.getId());
        gLSupPayDetail.setGlPayHeaderId(request.getGlPayHeaderId());
        gLSupPayDetail.setSupInvDate(request.getSupInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInvDate()));
        gLSupPayDetail.setInvNumber(request.getInvNumber());
        gLSupPayDetail.setPendingAmount(request.getPendingAmount());
        gLSupPayDetail.setPayAmount(request.getPayAmount());
        gLSupPayDetail.setGrossAmount(request.getGrossAmount());
        gLSupPayDetail.setWht(request.getWht());
        gLSupPayDetail.setStampDuty(request.getStampDuty());
        gLSupPayDetail.setNetAmount(request.getNetAmount());
        gLSupPayDetail.setAmountInWord(request.getAmountInWord());
        GLSupPayDetailTemplate updated = gLSupPayDetailRepository.save(gLSupPayDetail);

        return (convert(updated));
    }

    @Override
    public GLSupPayDetailResponse getById(Long id) {

        return gLSupPayDetailRepository.findById(id).map(GLSupPayDetailTemplateServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLSupPayDetailResponse> getAll() {

        return gLSupPayDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLSupPayDetailTemplateServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLSupPayDetailTemplate got = gLSupPayDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        gLSupPayDetailRepository.save(got);

        return 1;
    }
}