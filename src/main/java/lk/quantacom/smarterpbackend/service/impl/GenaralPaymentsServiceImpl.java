package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GenaralPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GenaralPaymentsResponse;
import lk.quantacom.smarterpbackend.entity.GenaralPayments;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GenaralPaymentsRepository;
import lk.quantacom.smarterpbackend.service.GenaralPaymentsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenaralPaymentsServiceImpl implements GenaralPaymentsService {

    @Autowired
    private GenaralPaymentsRepository genaralPaymentsRepository;

    private static GenaralPaymentsResponse convert(GenaralPayments genaralPayments) {

        GenaralPaymentsResponse typeResponse = new GenaralPaymentsResponse();
        typeResponse.setAmountInWord(genaralPayments.getAmountInWord());
        typeResponse.setBankCode(genaralPayments.getBankCode());
        typeResponse.setBranchCode(genaralPayments.getBranchCode());
        typeResponse.setBranchId(genaralPayments.getBranchId());
        typeResponse.setChequeNo(genaralPayments.getChequeNo());
        typeResponse.setGenaralDate(genaralPayments.getGenaralDate());
        typeResponse.setNoSemiPyingAmount(genaralPayments.getNoSemiPyingAmount());
        typeResponse.setPayMode(genaralPayments.getPayMode());
        typeResponse.setPayeeName(genaralPayments.getPayeeName());
        typeResponse.setPayingAmount(genaralPayments.getPayingAmount());
        typeResponse.setReason(genaralPayments.getReason());
        typeResponse.setRemarks(genaralPayments.getRemarks());
        typeResponse.setId(genaralPayments.getId());
        typeResponse.setCreatedBy(genaralPayments.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(genaralPayments.getCreatedDateTime()));
        typeResponse.setModifiedBy(genaralPayments.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(genaralPayments.getModifiedDateTime()));
        typeResponse.setIsDeleted(genaralPayments.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public GenaralPaymentsResponse save(GenaralPaymentsRequest request) {

        GenaralPayments genaralPayments = new GenaralPayments();
        genaralPayments.setAmountInWord(request.getAmountInWord());
        genaralPayments.setBankCode(request.getBankCode());
        genaralPayments.setBranchCode(request.getBranchCode());
        genaralPayments.setBranchId(request.getBranchId());
        genaralPayments.setChequeNo(request.getChequeNo());
        genaralPayments.setGenaralDate(request.getGenaralDate() == null ? null : ConvertUtils.convertStrToDate(request.getGenaralDate()));
        genaralPayments.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        genaralPayments.setPayMode(request.getPayMode());
        genaralPayments.setPayeeName(request.getPayeeName());
        genaralPayments.setPayingAmount(request.getPayingAmount());
        genaralPayments.setReason(request.getReason());
        genaralPayments.setRemarks(request.getRemarks());
        genaralPayments.setIsDeleted(Deleted.NO);
        GenaralPayments save = genaralPaymentsRepository.save(genaralPayments);

        return convert(save);
    }

    @Override
    @Transactional
    public GenaralPaymentsResponse update(GenaralPaymentsUpdateRequest request) {

        GenaralPayments genaralPayments = genaralPaymentsRepository.findById(request.getId()).orElse(null);
        if (genaralPayments == null) {
            return null;
        }

        genaralPayments.setId(request.getId());
        genaralPayments.setAmountInWord(request.getAmountInWord());
        genaralPayments.setBankCode(request.getBankCode());
        genaralPayments.setBranchCode(request.getBranchCode());
        genaralPayments.setBranchId(request.getBranchId());
        genaralPayments.setChequeNo(request.getChequeNo());
        genaralPayments.setGenaralDate(request.getGenaralDate() == null ? null : ConvertUtils.convertStrToDate(request.getGenaralDate()));
        genaralPayments.setNoSemiPyingAmount(request.getNoSemiPyingAmount());
        genaralPayments.setPayMode(request.getPayMode());
        genaralPayments.setPayeeName(request.getPayeeName());
        genaralPayments.setPayingAmount(request.getPayingAmount());
        genaralPayments.setReason(request.getReason());
        genaralPayments.setRemarks(request.getRemarks());
        GenaralPayments updated = genaralPaymentsRepository.save(genaralPayments);

        return (convert(updated));
    }

    @Override
    public GenaralPaymentsResponse getById(Long id) {

        return genaralPaymentsRepository.findById(id).map(GenaralPaymentsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GenaralPaymentsResponse> getAll() {

        return genaralPaymentsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GenaralPaymentsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GenaralPayments got = genaralPaymentsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        genaralPaymentsRepository.save(got);

        return 1;
    }
}