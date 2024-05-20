package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterListRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterRequest;
import lk.quantacom.smarterpbackend.dto.request.ChequeBookRegisterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankAccountResponse;
import lk.quantacom.smarterpbackend.dto.response.ChequeBookRegisterResponse;
import lk.quantacom.smarterpbackend.entity.ChequeBookRegister;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ChequeBookRegisterRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.ChequeBookRegisterService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChequeBookRegisterServiceImpl implements ChequeBookRegisterService {

    @Autowired
    private ChequeBookRegisterRepository chequeBookRegisterRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public ChequeBookRegisterResponse save(ChequeBookRegisterRequest request) {

        ChequeBookRegister chequeBookRegister = new ChequeBookRegister();
        chequeBookRegister.setBankAccountId(request.getBankAccountId());
        chequeBookRegister.setChequeAmount(request.getChequeAmount());
        chequeBookRegister.setChequeBookNo(request.getChequeBookNo());
        chequeBookRegister.setChequeBookRegisterId(request.getChequeBookRegisterId());
        chequeBookRegister.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        chequeBookRegister.setChequeNo(request.getChequeNo());
        chequeBookRegister.setPayIssueDate(request.getPayIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getPayIssueDate()));
        chequeBookRegister.setPayIssueTime(request.getPayIssueTime() == null ? null : ConvertUtils.convertStrToDate(request.getPayIssueTime()));
        chequeBookRegister.setPayWindow(request.getPayWindow());
        chequeBookRegister.setPayeeName(request.getPayeeName());
        chequeBookRegister.setRegisterDate(request.getRegisterDate() == null ? null : ConvertUtils.convertStrToDate(request.getRegisterDate()));
        chequeBookRegister.setStatus(request.getStatus());
        chequeBookRegister.setIsDeleted(Deleted.NO);
        ChequeBookRegister save = chequeBookRegisterRepository.save(chequeBookRegister);

        return convert(save);
    }

    @Override
    public List<ChequeBookRegisterResponse> saveList(List<ChequeBookRegisterListRequest> requests) {
        Integer max = chequeBookRegisterRepository.getMaxChequeBookRegId();
        if (max == null) {
            max = 1;
        } else {
            max = max + 1;
        }

        List<ChequeBookRegisterResponse> Responses = new ArrayList<>();
        for(ChequeBookRegisterListRequest request:requests){
            ChequeBookRegister chequeBookRegister = new ChequeBookRegister();
            chequeBookRegister.setBankAccountId(request.getBankAccountId());
            chequeBookRegister.setChequeAmount(0.00);
            chequeBookRegister.setChequeBookNo(request.getChequeBookNo());
            chequeBookRegister.setChequeBookRegisterId(max);
            chequeBookRegister.setChequeDate(ConvertUtils.convertStrToDate("1900-01-01 00:00:00"));
            chequeBookRegister.setChequeNo(request.getChequeNo());
            chequeBookRegister.setPayIssueDate(ConvertUtils.convertStrToDate("1900-01-01 00:00:00"));
            chequeBookRegister.setPayIssueTime(ConvertUtils.convertStrToDate("1900-01-01 00:00:00"));
            chequeBookRegister.setPayWindow("-");
            chequeBookRegister.setPayeeName("-");
            chequeBookRegister.setRegisterDate(request.getRegisterDate() == null ? null : ConvertUtils.convertStrToDate(request.getRegisterDate()));
            chequeBookRegister.setStatus("READY TO ISSUE");
            chequeBookRegister.setIsDeleted(Deleted.NO);
            ChequeBookRegister save = chequeBookRegisterRepository.save(chequeBookRegister);
            Responses.add(convert(save));

            saveLog("Cheque Book Register", "Data saved - List " + save.getId() );
        }
        return Responses;
    }

    @Override
    @Transactional
    public ChequeBookRegisterResponse update(ChequeBookRegisterUpdateRequest request) {

        ChequeBookRegister chequeBookRegister = chequeBookRegisterRepository.findById(request.getId()).orElse(null);
        if (chequeBookRegister == null) {
            return null;
        }

        chequeBookRegister.setId(request.getId());
        chequeBookRegister.setBankAccountId(request.getBankAccountId());
        chequeBookRegister.setChequeAmount(request.getChequeAmount());
        chequeBookRegister.setChequeBookNo(request.getChequeBookNo());
        chequeBookRegister.setChequeBookRegisterId(request.getChequeBookRegisterId());
        chequeBookRegister.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        chequeBookRegister.setChequeNo(request.getChequeNo());
        chequeBookRegister.setPayIssueDate(request.getPayIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getPayIssueDate()));
        chequeBookRegister.setPayIssueTime(request.getPayIssueTime() == null ? null : ConvertUtils.convertStrToDate(request.getPayIssueTime()));
        chequeBookRegister.setPayWindow(request.getPayWindow());
        chequeBookRegister.setPayeeName(request.getPayeeName());
        chequeBookRegister.setRegisterDate(request.getRegisterDate() == null ? null : ConvertUtils.convertStrToDate(request.getRegisterDate()));
        chequeBookRegister.setStatus(request.getStatus());
        ChequeBookRegister updated = chequeBookRegisterRepository.save(chequeBookRegister);

        return (convert(updated));
    }

    @Override
    public ChequeBookRegisterResponse getById(Long id) {

        return chequeBookRegisterRepository.findById(id).map(ChequeBookRegisterServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ChequeBookRegisterResponse> getAll() {

        return chequeBookRegisterRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ChequeBookRegisterServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ChequeBookRegisterResponse> getByBankAccountId(String bankAccountId) {
        return chequeBookRegisterRepository.findByBankAccountId(bankAccountId)
                .stream().map(ChequeBookRegisterServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        ChequeBookRegister got = chequeBookRegisterRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        chequeBookRegisterRepository.save(got);

        return 1;
    }

    private static ChequeBookRegisterResponse convert(ChequeBookRegister chequeBookRegister) {

        ChequeBookRegisterResponse typeResponse = new ChequeBookRegisterResponse();
        typeResponse.setBankAccountId(chequeBookRegister.getBankAccountId());
        typeResponse.setChequeAmount(chequeBookRegister.getChequeAmount());
        typeResponse.setChequeBookNo(chequeBookRegister.getChequeBookNo());
        typeResponse.setChequeBookRegisterId(chequeBookRegister.getChequeBookRegisterId());
        typeResponse.setChequeDate(chequeBookRegister.getChequeDate());
        typeResponse.setChequeNo(chequeBookRegister.getChequeNo());
        typeResponse.setPayIssueDate(chequeBookRegister.getPayIssueDate());
        typeResponse.setPayIssueTime(chequeBookRegister.getPayIssueTime());
        typeResponse.setPayWindow(chequeBookRegister.getPayWindow());
        typeResponse.setPayeeName(chequeBookRegister.getPayeeName());
        typeResponse.setRegisterDate(chequeBookRegister.getRegisterDate());
        typeResponse.setStatus(chequeBookRegister.getStatus());
        typeResponse.setId(chequeBookRegister.getId());
        typeResponse.setCreatedBy(chequeBookRegister.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(chequeBookRegister.getCreatedDateTime()));
        typeResponse.setModifiedBy(chequeBookRegister.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(chequeBookRegister.getModifiedDateTime()));
        typeResponse.setIsDeleted(chequeBookRegister.getIsDeleted());

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