package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeIssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ChequeIssueNoteResponse;
import lk.quantacom.smarterpbackend.entity.ChequeIssueNote;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ChequeIssueNoteRepository;
import lk.quantacom.smarterpbackend.service.ChequeIssueNoteService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChequeIssueNoteServiceImpl implements ChequeIssueNoteService {

    @Autowired
    private ChequeIssueNoteRepository chequeIssueNoteRepository;

    private static ChequeIssueNoteResponse convert(ChequeIssueNote chequeIssueNote) {

        ChequeIssueNoteResponse typeResponse = new ChequeIssueNoteResponse();
        typeResponse.setAccName(chequeIssueNote.getAccName());
        typeResponse.setAmount(chequeIssueNote.getAmount());
        typeResponse.setBankAccountId(chequeIssueNote.getBankAccountId());
        typeResponse.setBankCode(chequeIssueNote.getBankCode());
        typeResponse.setBranchCode(chequeIssueNote.getBranchCode());
        typeResponse.setFramNo(chequeIssueNote.getFramNo());
        typeResponse.setChequeDate(chequeIssueNote.getChequeDate());
        typeResponse.setChequeNo(chequeIssueNote.getChequeNo());
        typeResponse.setFramDocNo(chequeIssueNote.getFramDocNo());
        typeResponse.setIssueDate(chequeIssueNote.getIssueDate());
        typeResponse.setPayeeName(chequeIssueNote.getPayeeName());
        typeResponse.setStatus(chequeIssueNote.getStatus());
        typeResponse.setStatusDate(chequeIssueNote.getStatusDate());
        typeResponse.setUpdateWindow(chequeIssueNote.getUpdateWindow());
        typeResponse.setId(chequeIssueNote.getId());
        typeResponse.setCreatedBy(chequeIssueNote.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(chequeIssueNote.getCreatedDateTime()));
        typeResponse.setModifiedBy(chequeIssueNote.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(chequeIssueNote.getModifiedDateTime()));
        typeResponse.setIsDeleted(chequeIssueNote.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public ChequeIssueNoteResponse save(ChequeIssueNoteRequest request) {

        ChequeIssueNote chequeIssueNote = new ChequeIssueNote();
        chequeIssueNote.setAccName(request.getAccName());
        chequeIssueNote.setAmount(request.getAmount());
        chequeIssueNote.setBankAccountId(request.getBankAccountId());
        chequeIssueNote.setBankCode(request.getBankCode());
        chequeIssueNote.setBranchCode(request.getBranchCode());
        chequeIssueNote.setFramNo(request.getFramNo());
        chequeIssueNote.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        chequeIssueNote.setChequeNo(request.getChequeNo());
        chequeIssueNote.setFramDocNo(request.getFramDocNo());
        chequeIssueNote.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
        chequeIssueNote.setPayeeName(request.getPayeeName());
        chequeIssueNote.setStatus(request.getStatus());
        chequeIssueNote.setStatusDate(request.getStatusDate() == null ? null : ConvertUtils.convertStrToDate(request.getStatusDate()));
        chequeIssueNote.setUpdateWindow(request.getUpdateWindow());
        chequeIssueNote.setIsDeleted(Deleted.NO);
        ChequeIssueNote save = chequeIssueNoteRepository.save(chequeIssueNote);

        return convert(save);
    }

    @Override
    @Transactional
    public ChequeIssueNoteResponse update(ChequeIssueNoteUpdateRequest request) {

        ChequeIssueNote chequeIssueNote = chequeIssueNoteRepository.findById(request.getId()).orElse(null);
        if (chequeIssueNote == null) {
            return null;
        }

        chequeIssueNote.setId(request.getId());
        chequeIssueNote.setAccName(request.getAccName());
        chequeIssueNote.setAmount(request.getAmount());
        chequeIssueNote.setBankAccountId(request.getBankAccountId());
        chequeIssueNote.setBankCode(request.getBankCode());
        chequeIssueNote.setBranchCode(request.getBranchCode());
        chequeIssueNote.setFramNo(request.getFramNo());
        chequeIssueNote.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        chequeIssueNote.setChequeNo(request.getChequeNo());
        chequeIssueNote.setFramDocNo(request.getFramDocNo());
        chequeIssueNote.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
        chequeIssueNote.setPayeeName(request.getPayeeName());
        chequeIssueNote.setStatus(request.getStatus());
        chequeIssueNote.setStatusDate(request.getStatusDate() == null ? null : ConvertUtils.convertStrToDate(request.getStatusDate()));
        chequeIssueNote.setUpdateWindow(request.getUpdateWindow());
        ChequeIssueNote updated = chequeIssueNoteRepository.save(chequeIssueNote);

        return (convert(updated));
    }

    @Override
    public ChequeIssueNoteResponse getById(Long id) {

        return chequeIssueNoteRepository.findById(id).map(ChequeIssueNoteServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ChequeIssueNoteResponse> getAll() {

        return chequeIssueNoteRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ChequeIssueNote got = chequeIssueNoteRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        chequeIssueNoteRepository.save(got);

        return 1;
    }

    @Override
    public List<ChequeIssueNoteResponse> getByUpdateWindow(String updateWindow) {
        return chequeIssueNoteRepository.findByUpdateWindow(updateWindow)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> getByPayeeName(String payeeName) {
        return chequeIssueNoteRepository.findByPayeeName(payeeName)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> getByAccName(String accName) {
        return chequeIssueNoteRepository.findByAccName(accName)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> getByBankCode(String bankCode) {
        return chequeIssueNoteRepository.findByBankCode(bankCode)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByBranchCode(String branchCode) {
        return chequeIssueNoteRepository.findByBranchCode(branchCode)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByIsDeletedAndChequeNo(String chequeNo) {
        return chequeIssueNoteRepository.findByIsDeletedAndChequeNo(Deleted.NO,chequeNo)
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<String> getByAccNo(String chequeNo) {
        List<String>  stringList= chequeIssueNoteRepository.getByAccNo(chequeNo);
        return stringList;
    }

    @Override
    public List<ChequeIssueNoteResponse> findByPayeeNameAndBranchCodeAndIssueDateBetween(String payeeName, String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByPayeeNameAndBranchCodeAndIssueDateBetween(payeeName,Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByUpdateWindowAndBranchCodeAndIssueDateBetween(String updateWindow, String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByUpdateWindowAndBranchCodeAndIssueDateBetween(updateWindow,Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByBankCodeAndBranchCodeAndIssueDateBetween(String bankCode, String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByBankCodeAndBranchCodeAndIssueDateBetween(bankCode,Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByChequeNoAndBranchCodeAndIssueDateBetween(String branchCode, String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByChequeNoAndBranchCodeAndIssueDateBetween(branchCode,Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndIssueDateBetween(String accName, String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByChequeNoAndBranchCodeAndIssueDateBetween(accName,Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndIssueDateBetween(String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByBranchCodeAndIssueDateBetween(Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<ChequeIssueNoteResponse> findByAccNameAndBranchCodeAndChequeDateBetween(String Branch, String from, String to) {
        return chequeIssueNoteRepository.findByBranchCodeAndChequeDateBetween(Branch,ConvertUtils.convertStrToDate(from),ConvertUtils.convertStrToDate(to))
                .stream().map(ChequeIssueNoteServiceImpl::convert).collect(Collectors.toList());
    }


}