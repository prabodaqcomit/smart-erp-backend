package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerHistoryResponse;
import lk.quantacom.smarterpbackend.entity.LedgerHistory;
import lk.quantacom.smarterpbackend.repository.LedgerHistoryRepository;
import lk.quantacom.smarterpbackend.service.LedgerHistoryService;
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
public class LedgerHistoryServiceImpl implements LedgerHistoryService {

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public LedgerHistoryResponse save(LedgerHistoryRequest request) {

        LedgerHistory ledgerHistory = new LedgerHistory();
        ledgerHistory.setBranchId(request.getBranchId());
        ledgerHistory.setLadgerAccountId(request.getLadgerAccountId());
        ledgerHistory.setUpdateFram(request.getUpdateFram());
        ledgerHistory.setUpdateFramDocNo(request.getUpdateFramDocNo());
        ledgerHistory.setRemark(request.getRemark());
        ledgerHistory.setUpdateBalance(request.getUpdateBalance());
        ledgerHistory.setTransType(request.getTransType());
        ledgerHistory.setCreditAmount(request.getCreditAmount());
        ledgerHistory.setDebitAmount(request.getDebitAmount());
        ledgerHistory.setCreditColumnName(request.getCreditColumnName());
        ledgerHistory.setDebitColumnName(request.getDebitColumnName());
        ledgerHistory.setIsDeleted(Deleted.NO);
        LedgerHistory save = ledgerHistoryRepository.save(ledgerHistory);

        saveLog("LedgerHistory", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerHistoryResponse update(LedgerHistoryUpdateRequest request) {

        LedgerHistory ledgerHistory = ledgerHistoryRepository.findById(request.getId()).orElse(null);
        if (ledgerHistory == null) {
            return null;
        }

        ledgerHistory.setId(request.getId());
        ledgerHistory.setBranchId(request.getBranchId());
        ledgerHistory.setLadgerAccountId(request.getLadgerAccountId());
        ledgerHistory.setUpdateFram(request.getUpdateFram());
        ledgerHistory.setUpdateFramDocNo(request.getUpdateFramDocNo());
        ledgerHistory.setRemark(request.getRemark());
        ledgerHistory.setUpdateBalance(request.getUpdateBalance());
        ledgerHistory.setTransType(request.getTransType());
        ledgerHistory.setCreditAmount(request.getCreditAmount());
        ledgerHistory.setDebitAmount(request.getDebitAmount());
        ledgerHistory.setCreditColumnName(request.getCreditColumnName());
        ledgerHistory.setDebitColumnName(request.getDebitColumnName());
        LedgerHistory updated = ledgerHistoryRepository.save(ledgerHistory);

        saveLog("LedgerHistory", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public LedgerHistoryResponse getById(Long id) {

        return ledgerHistoryRepository.findById(id).map(LedgerHistoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerHistoryResponse> getAll() {

        return ledgerHistoryRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerHistoryServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerHistory got = ledgerHistoryRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerHistoryRepository.save(got);

        saveLog("LedgerHistory", "Data Deleted - " + id);

        return 1;
    }

    private static LedgerHistoryResponse convert(LedgerHistory ledgerHistory) {

        LedgerHistoryResponse typeResponse = new LedgerHistoryResponse();
        typeResponse.setBranchId(ledgerHistory.getBranchId());
        typeResponse.setLadgerAccountId(ledgerHistory.getLadgerAccountId());
        typeResponse.setUpdateFram(ledgerHistory.getUpdateFram());
        typeResponse.setUpdateFramDocNo(ledgerHistory.getUpdateFramDocNo());
        typeResponse.setRemark(ledgerHistory.getRemark());
        typeResponse.setUpdateBalance(ledgerHistory.getUpdateBalance());
        typeResponse.setTransType(ledgerHistory.getTransType());
        typeResponse.setCreditAmount(ledgerHistory.getCreditAmount());
        typeResponse.setDebitAmount(ledgerHistory.getDebitAmount());
        typeResponse.setCreditColumnName(ledgerHistory.getCreditColumnName());
        typeResponse.setDebitColumnName(ledgerHistory.getDebitColumnName());
        typeResponse.setId(ledgerHistory.getId());
        typeResponse.setCreatedBy(ledgerHistory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerHistory.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerHistory.getModifiedBy());
        typeResponse.setIsDeleted(ledgerHistory.getIsDeleted());

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