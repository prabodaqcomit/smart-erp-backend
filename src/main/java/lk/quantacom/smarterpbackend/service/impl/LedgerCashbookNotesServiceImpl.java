package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookNotesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookNotesResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCashbookNotes;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerCashbookNotesRepository;
import lk.quantacom.smarterpbackend.service.LedgerCashbookNotesService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerCashbookNotesServiceImpl implements LedgerCashbookNotesService {

    @Autowired
    private LedgerCashbookNotesRepository ledgerCashbookNotesRepository;

    private static LedgerCashbookNotesResponse convert(LedgerCashbookNotes ledgerCashbookNotes) {

        LedgerCashbookNotesResponse typeResponse = new LedgerCashbookNotesResponse();
        typeResponse.setAmount(ledgerCashbookNotes.getAmount());
        typeResponse.setAmountWord(ledgerCashbookNotes.getAmountWord());
        typeResponse.setBranchId(ledgerCashbookNotes.getBranchId());
        typeResponse.setDate(ledgerCashbookNotes.getDate());
        typeResponse.setLedgerCashbookNotesId(ledgerCashbookNotes.getLedgerCashbookNotesId());
        typeResponse.setLedgerIdCredit(ledgerCashbookNotes.getLedgerIdCredit());
        typeResponse.setLedgerIdDebit(ledgerCashbookNotes.getLedgerIdDebit());
        typeResponse.setPayeeName(ledgerCashbookNotes.getPayeeName());
        typeResponse.setReason(ledgerCashbookNotes.getReason());
        typeResponse.setRemarks(ledgerCashbookNotes.getRemarks());
        typeResponse.setTime(ConvertUtils.convertDateToStr(ledgerCashbookNotes.getTime()) );
        typeResponse.setLedgerCashbookNotesId(ledgerCashbookNotes.getLedgerCashbookNotesId());
        typeResponse.setCreatedBy(ledgerCashbookNotes.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerCashbookNotes.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerCashbookNotes.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerCashbookNotes.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerCashbookNotes.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public LedgerCashbookNotesResponse save(LedgerCashbookNotesRequest request) {

        Integer max = ledgerCashbookNotesRepository.getMaxId();
        if (max == null) {
            max = 1;
        } else {
            max = max + 1;
        }

        LedgerCashbookNotes ledgerCashbookNotes = new LedgerCashbookNotes();
        ledgerCashbookNotes.setLedgerCashbookNotesId(max);
        ledgerCashbookNotes.setAmount(request.getAmount());
        ledgerCashbookNotes.setAmountWord(request.getAmountWord());
        ledgerCashbookNotes.setBranchId(request.getBranchId());
        ledgerCashbookNotes.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        ledgerCashbookNotes.setLedgerIdCredit(request.getLedgerIdCredit());
        ledgerCashbookNotes.setLedgerIdDebit(request.getLedgerIdDebit());
        ledgerCashbookNotes.setPayeeName(request.getPayeeName());
        ledgerCashbookNotes.setReason(request.getReason());
        ledgerCashbookNotes.setRemarks(request.getRemarks());
        ledgerCashbookNotes.setTime(request.getTime() == null ? null : ConvertUtils.convertStrToDate(request.getTime()));
        ledgerCashbookNotes.setIsDeleted(Deleted.NO);
        LedgerCashbookNotes save = ledgerCashbookNotesRepository.save(ledgerCashbookNotes);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerCashbookNotesResponse update(LedgerCashbookNotesUpdateRequest request) {

        LedgerCashbookNotes ledgerCashbookNotes = ledgerCashbookNotesRepository.findById(request.getLedgerCashbookNotesId()).orElse(null);
        if (ledgerCashbookNotes == null) {
            return null;
        }

        ledgerCashbookNotes.setLedgerCashbookNotesId(request.getLedgerCashbookNotesId());
        ledgerCashbookNotes.setAmount(request.getAmount());
        ledgerCashbookNotes.setAmountWord(request.getAmountWord());
        ledgerCashbookNotes.setBranchId(request.getBranchId());
        ledgerCashbookNotes.setDate(request.getDate() == null ? null : ConvertUtils.convertStrToDate(request.getDate()));
        ledgerCashbookNotes.setLedgerCashbookNotesId(request.getLedgerCashbookNotesId());
        ledgerCashbookNotes.setLedgerIdCredit(request.getLedgerIdCredit());
        ledgerCashbookNotes.setLedgerIdDebit(request.getLedgerIdDebit());
        ledgerCashbookNotes.setPayeeName(request.getPayeeName());
        ledgerCashbookNotes.setReason(request.getReason());
        ledgerCashbookNotes.setRemarks(request.getRemarks());
        ledgerCashbookNotes.setTime(request.getTime() == null ? null : ConvertUtils.convertStrToDate(request.getTime()));
        LedgerCashbookNotes updated = ledgerCashbookNotesRepository.save(ledgerCashbookNotes);

        return (convert(updated));
    }

    @Override
    public LedgerCashbookNotesResponse getById(Integer id) {

        return ledgerCashbookNotesRepository.findById(id).map(LedgerCashbookNotesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerCashbookNotesResponse> getAll() {

        return ledgerCashbookNotesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerCashbookNotesServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        LedgerCashbookNotes got = ledgerCashbookNotesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerCashbookNotesRepository.save(got);

        return 1;
    }

    @Override
    public List<LedgerCashbookNotesResponse> getByPayeeName(String payeeName) {
        return ledgerCashbookNotesRepository.findByPayeeName(payeeName)
                .stream().map(LedgerCashbookNotesServiceImpl::convert).collect(Collectors.toList());
    }


}