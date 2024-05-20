package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.ExpenceHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ExpenceHistoryResponse;
import lk.quantacom.smarterpbackend.entity.ExpenceHistory;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ExpenceHistoryRepository;
import lk.quantacom.smarterpbackend.service.ExpenceHistoryService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenceHistoryServiceImpl implements ExpenceHistoryService {

    @Autowired
    private ExpenceHistoryRepository expenceHistoryRepository;

    private static ExpenceHistoryResponse convert(ExpenceHistory expenceHistory) {

        ExpenceHistoryResponse typeResponse = new ExpenceHistoryResponse();
        typeResponse.setDate(expenceHistory.getDate());
        typeResponse.setDocNo(expenceHistory.getDocNo());
        typeResponse.setDocType(expenceHistory.getDocType());
        typeResponse.setDescription(expenceHistory.getDescription());
        typeResponse.setPayAmount(expenceHistory.getPayAmount());
        typeResponse.setPayMode(expenceHistory.getPayMode());
        typeResponse.setRemarks(expenceHistory.getRemarks());
        typeResponse.setBranchId(expenceHistory.getBranchId());
        typeResponse.setId(expenceHistory.getId());
        typeResponse.setCreatedBy(expenceHistory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(expenceHistory.getCreatedDateTime()));
        typeResponse.setModifiedBy(expenceHistory.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(expenceHistory.getModifiedDateTime()));
        typeResponse.setIsDeleted(expenceHistory.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ExpenceHistoryResponse save(ExpenceHistoryRequest request) {

        ExpenceHistory expenceHistory = new ExpenceHistory();
        expenceHistory.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        expenceHistory.setDocNo(request.getDocNo());
        expenceHistory.setDocType(request.getDocType());
        expenceHistory.setDescription(request.getDescription());
        expenceHistory.setPayAmount(request.getPayAmount());
        expenceHistory.setPayMode(request.getPayMode());
        expenceHistory.setRemarks(request.getRemarks());
        expenceHistory.setBranchId(request.getBranchId());
        expenceHistory.setIsDeleted(Deleted.NO);
        ExpenceHistory save = expenceHistoryRepository.save(expenceHistory);

        return convert(save);
    }

    @Override
    @Transactional
    public ExpenceHistoryResponse update(ExpenceHistoryUpdateRequest request) {

        ExpenceHistory expenceHistory = expenceHistoryRepository.findById(request.getId()).orElse(null);
        if (expenceHistory == null) {
            return null;
        }

        expenceHistory.setId(request.getId());
        expenceHistory.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        expenceHistory.setDocNo(request.getDocNo());
        expenceHistory.setDocType(request.getDocType());
        expenceHistory.setDescription(request.getDescription());
        expenceHistory.setPayAmount(request.getPayAmount());
        expenceHistory.setPayMode(request.getPayMode());
        expenceHistory.setRemarks(request.getRemarks());
        expenceHistory.setBranchId(request.getBranchId());
        ExpenceHistory updated = expenceHistoryRepository.save(expenceHistory);

        return (convert(updated));
    }

    @Override
    public ExpenceHistoryResponse getById(Long id) {

        return expenceHistoryRepository.findById(id).map(ExpenceHistoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ExpenceHistoryResponse> getAll() {

        return expenceHistoryRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ExpenceHistoryServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ExpenceHistory got = expenceHistoryRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        expenceHistoryRepository.save(got);

        return 1;
    }
}