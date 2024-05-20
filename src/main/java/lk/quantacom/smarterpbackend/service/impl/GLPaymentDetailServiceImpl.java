package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.GLPaymentDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPaymentDetailResponse;
import lk.quantacom.smarterpbackend.entity.GLPaymentDetail;
import lk.quantacom.smarterpbackend.repository.GLPaymentDetailRepository;
import lk.quantacom.smarterpbackend.service.GLPaymentDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GLPaymentDetailServiceImpl implements GLPaymentDetailService {

    @Autowired
    private GLPaymentDetailRepository gLPaymentDetailRepository;

    private static GLPaymentDetailResponse convert(GLPaymentDetail gLPaymentDetail) {

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

        GLPaymentDetail gLPaymentDetail = new GLPaymentDetail();
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
        GLPaymentDetail save = gLPaymentDetailRepository.save(gLPaymentDetail);

        return convert(save);
    }

    @Override
    @Transactional
    public GLPaymentDetailResponse update(GLPaymentDetailUpdateRequest request) {

        GLPaymentDetail gLPaymentDetail = gLPaymentDetailRepository.findById(request.getId()).orElse(null);
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
        GLPaymentDetail updated = gLPaymentDetailRepository.save(gLPaymentDetail);

        return (convert(updated));
    }

    @Override
    public GLPaymentDetailResponse getById(Long id) {

        return gLPaymentDetailRepository.findById(id).map(GLPaymentDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GLPaymentDetailResponse> getAll() {

        return gLPaymentDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GLPaymentDetailServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GLPaymentDetail got = gLPaymentDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        gLPaymentDetailRepository.save(got);

        return 1;
    }
}