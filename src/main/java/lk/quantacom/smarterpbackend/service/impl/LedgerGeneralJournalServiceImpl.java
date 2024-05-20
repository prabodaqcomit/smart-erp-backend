package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerGeneralJournalUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerGeneralJournalResponse;
import lk.quantacom.smarterpbackend.entity.LedgerGeneralJournal;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerGeneralJournalRepository;
import lk.quantacom.smarterpbackend.service.LedgerGeneralJournalService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerGeneralJournalServiceImpl implements LedgerGeneralJournalService {

    @Autowired
    private LedgerGeneralJournalRepository ledgerGeneralJournalRepository;


    @Override
    @Transactional
    public LedgerGeneralJournalResponse save(LedgerGeneralJournalRequest request) {

        LedgerGeneralJournal ledgerGeneralJournal = new LedgerGeneralJournal();
        ledgerGeneralJournal.setDescription(request.getDescription());
        ledgerGeneralJournal.setAmount(request.getAmount());
        ledgerGeneralJournal.setPrNo(request.getPrNo());
        ledgerGeneralJournal.setDebitAccId(request.getDebitAccId());
        ledgerGeneralJournal.setCreditAccId(request.getCreditAccId());
        ledgerGeneralJournal.setCreditAmount(request.getCreditAmount());
        ledgerGeneralJournal.setRemarks(request.getRemarks());
        ledgerGeneralJournal.setBranchId(request.getBranchId());
        ledgerGeneralJournal.setIsDeleted(Deleted.NO);
        LedgerGeneralJournal save = ledgerGeneralJournalRepository.save(ledgerGeneralJournal);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerGeneralJournalResponse update(LedgerGeneralJournalUpdateRequest request) {

        LedgerGeneralJournal ledgerGeneralJournal = ledgerGeneralJournalRepository.findById(request.getId()).orElse(null);
        if (ledgerGeneralJournal == null) {
            return null;
        }

        ledgerGeneralJournal.setId(request.getId());
        ledgerGeneralJournal.setDescription(request.getDescription());
        ledgerGeneralJournal.setAmount(request.getAmount());
        ledgerGeneralJournal.setPrNo(request.getPrNo());
        ledgerGeneralJournal.setDebitAccId(request.getDebitAccId());
        ledgerGeneralJournal.setCreditAccId(request.getCreditAccId());
        ledgerGeneralJournal.setCreditAmount(request.getCreditAmount());
        ledgerGeneralJournal.setRemarks(request.getRemarks());
        ledgerGeneralJournal.setBranchId(request.getBranchId());
        LedgerGeneralJournal updated = ledgerGeneralJournalRepository.save(ledgerGeneralJournal);

        return (convert(updated));
    }

    @Override
    public LedgerGeneralJournalResponse getById(Long id) {

        return ledgerGeneralJournalRepository.findById(id).map(LedgerGeneralJournalServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerGeneralJournalResponse> getAll() {

        return ledgerGeneralJournalRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerGeneralJournalServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerGeneralJournal got = ledgerGeneralJournalRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerGeneralJournalRepository.save(got);

        return 1;
    }

    private static LedgerGeneralJournalResponse convert(LedgerGeneralJournal ledgerGeneralJournal) {

        LedgerGeneralJournalResponse typeResponse = new LedgerGeneralJournalResponse();
        typeResponse.setDescription(ledgerGeneralJournal.getDescription());
        typeResponse.setAmount(ledgerGeneralJournal.getAmount());
        typeResponse.setPrNo(ledgerGeneralJournal.getPrNo());
        typeResponse.setDebitAccId(ledgerGeneralJournal.getDebitAccId());
        typeResponse.setCreditAccId(ledgerGeneralJournal.getCreditAccId());
        typeResponse.setCreditAmount(ledgerGeneralJournal.getCreditAmount());
        typeResponse.setRemarks(ledgerGeneralJournal.getRemarks());
        typeResponse.setBranchId(ledgerGeneralJournal.getBranchId());
        typeResponse.setId(ledgerGeneralJournal.getId());
        typeResponse.setCreatedBy(ledgerGeneralJournal.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerGeneralJournal.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerGeneralJournal.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerGeneralJournal.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerGeneralJournal.getIsDeleted());

        return typeResponse;
    }
}