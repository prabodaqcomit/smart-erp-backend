package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentDetailResponse;
import lk.quantacom.smarterpbackend.entity.GLPaymentDetail;
import lk.quantacom.smarterpbackend.entity.GLPaymentDetailTemplate;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GLPaymentDetailRepository;
import lk.quantacom.smarterpbackend.repository.GLPaymentDetailTemplateRepository;
import lk.quantacom.smarterpbackend.service.GLPaymentDetailService;
import lk.quantacom.smarterpbackend.service.GLPaymentDetailTemplateService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GLPaymentDetailTemplateServiceImpl implements GLPaymentDetailTemplateService {

    @Autowired
    private GLPaymentDetailTemplateRepository glPaymentDetailTemplateRepository;

    private static GLPaymentDetailResponse convert(GLPaymentDetailTemplate gLPaymentDetail) {

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

    @Override
    @Transactional
    public GLPaymentDetailResponse save(GLPaymentDetailRequest request) {

        GLPaymentDetailTemplate gLPaymentDetail = new GLPaymentDetailTemplate();
        gLPaymentDetail.setGlPayHeaderId(request.getGlPayHeaderId());
        gLPaymentDetail.setAccCode(request.getAccCode());
        gLPaymentDetail.setAccName(request.getAccName());
        gLPaymentDetail.setLedgerAmount(request.getLedgerAmount());
        gLPaymentDetail.setWht(request.getWht());
        gLPaymentDetail.setStampDuty(request.getStampDuty());
        gLPaymentDetail.setNetAmount(request.getNetAmount());
        gLPaymentDetail.setTotalAmount(request.getTotalAmount());
        gLPaymentDetail.setAmountInWord(request.getAmountInWord());
        gLPaymentDetail.setIsDeleted(Deleted.NO);
        GLPaymentDetailTemplate save = glPaymentDetailTemplateRepository.save(gLPaymentDetail);

        return convert(save);
    }

    @Override
    @Transactional
    public GLPaymentDetailResponse update(GLPaymentDetailUpdateRequest request) {

        GLPaymentDetailTemplate gLPaymentDetail = glPaymentDetailTemplateRepository.findById(request.getId()).orElse(null);
        if (gLPaymentDetail == null) {
            return null;
        }

        gLPaymentDetail.setId(request.getId());
        gLPaymentDetail.setGlPayHeaderId(request.getGlPayHeaderId());
        gLPaymentDetail.setAccCode(request.getAccCode());
        gLPaymentDetail.setAccName(request.getAccName());
        gLPaymentDetail.setLedgerAmount(request.getLedgerAmount());
        gLPaymentDetail.setWht(request.getWht());
        gLPaymentDetail.setStampDuty(request.getStampDuty());
        gLPaymentDetail.setNetAmount(request.getNetAmount());
        gLPaymentDetail.setTotalAmount(request.getTotalAmount());
        gLPaymentDetail.setAmountInWord(request.getAmountInWord());
        GLPaymentDetailTemplate updated = glPaymentDetailTemplateRepository.save(gLPaymentDetail);

        return (convert(updated));
    }

    @Override
    public GLPaymentDetailResponse getById(Long id) {

        return glPaymentDetailTemplateRepository.findById(id).map(GLPaymentDetailTemplateServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLPaymentDetailResponse> getAll() {

        return glPaymentDetailTemplateRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLPaymentDetailTemplateServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLPaymentDetailTemplate got = glPaymentDetailTemplateRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        glPaymentDetailTemplateRepository.save(got);

        return 1;
    }
}