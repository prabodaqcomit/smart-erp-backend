package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankHistoryRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankHistoryUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankHistoryResponse;
import lk.quantacom.smarterpbackend.entity.LedgerBankHistory;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerBankHistoryRepository;
import lk.quantacom.smarterpbackend.service.LedgerBankHistoryService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerBankHistoryServiceImpl implements LedgerBankHistoryService {

    @Autowired
    private LedgerBankHistoryRepository ledgerBankHistoryRepository;

    @Override
    @Transactional
    public LedgerBankHistoryResponse save(LedgerBankHistoryRequest request) {

        LedgerBankHistory ledgerBankHistory = new LedgerBankHistory();
        ledgerBankHistory.setAmount(request.getAmount());
        ledgerBankHistory.setBankAccountId(request.getBankAccountId());
        ledgerBankHistory.setCreditAccAmount(request.getCreditAccAmount());
        ledgerBankHistory.setCreditAccId(request.getCreditAccId());
        ledgerBankHistory.setCurrentBalance(request.getCurrentBalance());
        ledgerBankHistory.setDebitAccAmount(request.getDebitAccAmount());
        ledgerBankHistory.setDebitAccId(request.getDebitAccId());
        ledgerBankHistory.setDepoType(request.getDepoType());
        ledgerBankHistory.setFramDocNo(request.getFramDocNo());
        ledgerBankHistory.setFramName(request.getFramName());
        ledgerBankHistory.setTransaction(request.getTransaction());
        ledgerBankHistory.setIsDeleted(Deleted.NO);
        LedgerBankHistory save = ledgerBankHistoryRepository.save(ledgerBankHistory);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerBankHistoryResponse update(LedgerBankHistoryUpdateRequest request) {

        LedgerBankHistory ledgerBankHistory = ledgerBankHistoryRepository.findById(request.getId()).orElse(null);
        if (ledgerBankHistory == null) {
            return null;
        }

        ledgerBankHistory.setId(request.getId());
        ledgerBankHistory.setAmount(request.getAmount());
        ledgerBankHistory.setBankAccountId(request.getBankAccountId());
        ledgerBankHistory.setCreditAccAmount(request.getCreditAccAmount());
        ledgerBankHistory.setCreditAccId(request.getCreditAccId());
        ledgerBankHistory.setCurrentBalance(request.getCurrentBalance());
        ledgerBankHistory.setDebitAccAmount(request.getDebitAccAmount());
        ledgerBankHistory.setDebitAccId(request.getDebitAccId());
        ledgerBankHistory.setDepoType(request.getDepoType());
        ledgerBankHistory.setFramDocNo(request.getFramDocNo());
        ledgerBankHistory.setFramName(request.getFramName());
        ledgerBankHistory.setTransaction(request.getTransaction());
        LedgerBankHistory updated = ledgerBankHistoryRepository.save(ledgerBankHistory);

        return (convert(updated));
    }

    @Override
    public LedgerBankHistoryResponse getById(Long id) {

        return ledgerBankHistoryRepository.findById(id).map(LedgerBankHistoryServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerBankHistoryResponse> getAll() {

        return ledgerBankHistoryRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerBankHistoryServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerBankHistory got = ledgerBankHistoryRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerBankHistoryRepository.save(got);

        return 1;
    }

    private static LedgerBankHistoryResponse convert(LedgerBankHistory ledgerBankHistory) {

        LedgerBankHistoryResponse typeResponse = new LedgerBankHistoryResponse();
        typeResponse.setAmount(ledgerBankHistory.getAmount());
        typeResponse.setBankAccountId(ledgerBankHistory.getBankAccountId());
        typeResponse.setCreditAccAmount(ledgerBankHistory.getCreditAccAmount());
        typeResponse.setCreditAccId(ledgerBankHistory.getCreditAccId());
        typeResponse.setCurrentBalance(ledgerBankHistory.getCurrentBalance());
        typeResponse.setDebitAccAmount(ledgerBankHistory.getDebitAccAmount());
        typeResponse.setDebitAccId(ledgerBankHistory.getDebitAccId());
        typeResponse.setDepoType(ledgerBankHistory.getDepoType());
        typeResponse.setFramDocNo(ledgerBankHistory.getFramDocNo());
        typeResponse.setFramName(ledgerBankHistory.getFramName());
        typeResponse.setTransaction(ledgerBankHistory.getTransaction());
        typeResponse.setId(ledgerBankHistory.getId());
        typeResponse.setCreatedBy(ledgerBankHistory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerBankHistory.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerBankHistory.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerBankHistory.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerBankHistory.getIsDeleted());

        return typeResponse;
    }
}