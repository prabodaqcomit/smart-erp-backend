package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsRequest;
import lk.quantacom.smarterpbackend.dto.request.OwnChequeReturnsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.OwnChequeReturns;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.OwnChequeReturnsRepository;
import lk.quantacom.smarterpbackend.service.OwnChequeReturnsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnChequeReturnsServiceImpl implements OwnChequeReturnsService {

    @Autowired
    private OwnChequeReturnsRepository ownChequeReturnsRepository;

    private static OwnChequeReturnsResponse convert(OwnChequeReturns ownChequeReturns) {

        OwnChequeReturnsResponse typeResponse = new OwnChequeReturnsResponse();
        typeResponse.setBankCode(ownChequeReturns.getBankCode());
        typeResponse.setBranchCode(ownChequeReturns.getBranchCode());
        typeResponse.setBranchId(ownChequeReturns.getBranchId());
        typeResponse.setChequeIssueNoteId(ownChequeReturns.getChequeIssueNoteId());
        typeResponse.setChequeNo(ownChequeReturns.getChequeNo());
        typeResponse.setFramNo(ownChequeReturns.getFramNo());
        typeResponse.setIdownChequeReturns(ownChequeReturns.getIdownChequeReturns());
        typeResponse.setRemarks(ownChequeReturns.getRemarks());
        typeResponse.setValue(ownChequeReturns.getValue());
        typeResponse.setId(ownChequeReturns.getId());
        typeResponse.setCreatedBy(ownChequeReturns.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ownChequeReturns.getCreatedDateTime()));
        typeResponse.setModifiedBy(ownChequeReturns.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ownChequeReturns.getModifiedDateTime()));
        typeResponse.setIsDeleted(ownChequeReturns.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public OwnChequeReturnsResponse save(OwnChequeReturnsRequest request) {

        OwnChequeReturns ownChequeReturns = new OwnChequeReturns();
        ownChequeReturns.setBankCode(request.getBankCode());
        ownChequeReturns.setBranchCode(request.getBranchCode());
        ownChequeReturns.setBranchId(request.getBranchId());
        ownChequeReturns.setChequeIssueNoteId(request.getChequeIssueNoteId());
        ownChequeReturns.setChequeNo(request.getChequeNo());
        ownChequeReturns.setFramNo(request.getFramNo());
        ownChequeReturns.setIdownChequeReturns(request.getIdownChequeReturns());
        ownChequeReturns.setRemarks(request.getRemarks());
        ownChequeReturns.setValue(request.getValue());
        ownChequeReturns.setIsDeleted(Deleted.NO);
        OwnChequeReturns save = ownChequeReturnsRepository.save(ownChequeReturns);

        return convert(save);
    }

    @Override
    @Transactional
    public OwnChequeReturnsResponse update(OwnChequeReturnsUpdateRequest request) {

        OwnChequeReturns ownChequeReturns = ownChequeReturnsRepository.findById(request.getId()).orElse(null);
        if (ownChequeReturns == null) {
            return null;
        }

        ownChequeReturns.setId(request.getId());
        ownChequeReturns.setBankCode(request.getBankCode());
        ownChequeReturns.setBranchCode(request.getBranchCode());
        ownChequeReturns.setBranchId(request.getBranchId());
        ownChequeReturns.setChequeIssueNoteId(request.getChequeIssueNoteId());
        ownChequeReturns.setChequeNo(request.getChequeNo());
        ownChequeReturns.setFramNo(request.getFramNo());
        ownChequeReturns.setIdownChequeReturns(request.getIdownChequeReturns());
        ownChequeReturns.setRemarks(request.getRemarks());
        ownChequeReturns.setValue(request.getValue());
        OwnChequeReturns updated = ownChequeReturnsRepository.save(ownChequeReturns);

        return (convert(updated));
    }

    @Override
    public OwnChequeReturnsResponse getById(Long id) {

        return ownChequeReturnsRepository.findById(id).map(OwnChequeReturnsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<OwnChequeReturnsResponse> getAll() {

        return ownChequeReturnsRepository.findByIsDeleted(Deleted.NO)
                .stream().map(OwnChequeReturnsServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        OwnChequeReturns got = ownChequeReturnsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ownChequeReturnsRepository.save(got);

        return 1;
    }

    @Override
    public List<OwnChequeReturnsResponse> findByChequeNo(String chequeNo) {
        return ownChequeReturnsRepository.findByChequeNo(chequeNo)
                .stream().map(OwnChequeReturnsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<OwnChequeReturnsResponse> findByChequeNoAndBranchId(String chequeNo, String branchId) {
        return ownChequeReturnsRepository.findByChequeNoAndBranchId(chequeNo,branchId)
                .stream().map(OwnChequeReturnsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<getByPayeeNameResponse> getByPayeeName(String payeeName) {
        List<getByPayeeNameResponse> responseList= ownChequeReturnsRepository.getByPayeeName(payeeName);
        return responseList;
    }

    @Override
    public List<getByChequeNoResponse> getByChequeNo(String chequeNo) {
        List<getByChequeNoResponse> responseList= ownChequeReturnsRepository.getByChequeNo(chequeNo);
        return responseList;
    }

    @Override
    public List<getByBankCodeResponse> getByBankCode(String bankCode) {
        List<getByBankCodeResponse> responseList= ownChequeReturnsRepository.getByBankCode(bankCode);
        return responseList;
    }

    @Override
    public List<getByBranchCodeResponse> getByBranchCode(String BranchCode) {
        List<getByBranchCodeResponse> responseList= ownChequeReturnsRepository.getByBranchCode(BranchCode);
        return responseList;
    }
}