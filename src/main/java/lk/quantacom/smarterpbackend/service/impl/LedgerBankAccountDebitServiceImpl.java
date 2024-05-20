package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountDebitRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountDebitUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankAccountDebitResponse;
import lk.quantacom.smarterpbackend.entity.LedgerBankAccountDebit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerBankAccountDebitRepository;
import lk.quantacom.smarterpbackend.service.LedgerBankAccountDebitService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerBankAccountDebitServiceImpl implements LedgerBankAccountDebitService {

    @Autowired
    private LedgerBankAccountDebitRepository ledgerBankAccountDebitRepository;

    private static LedgerBankAccountDebitResponse convert(LedgerBankAccountDebit ledgerBankAccountDebit) {

        LedgerBankAccountDebitResponse typeResponse = new LedgerBankAccountDebitResponse();
        typeResponse.setBranchId(ledgerBankAccountDebit.getBranchId());
        typeResponse.setCashAmount(ledgerBankAccountDebit.getCashAmount());
        typeResponse.setDescription(ledgerBankAccountDebit.getDescription());
        typeResponse.setOtherAccNo(ledgerBankAccountDebit.getOtherAccNo());
        typeResponse.setOtherAccountName(ledgerBankAccountDebit.getOtherAccountName());
        typeResponse.setPrNo(ledgerBankAccountDebit.getPrNo());
        typeResponse.setId(ledgerBankAccountDebit.getId());
        typeResponse.setCreatedBy(ledgerBankAccountDebit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerBankAccountDebit.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerBankAccountDebit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerBankAccountDebit.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerBankAccountDebit.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public LedgerBankAccountDebitResponse save(LedgerBankAccountDebitRequest request) {

        LedgerBankAccountDebit ledgerBankAccountDebit = new LedgerBankAccountDebit();
        ledgerBankAccountDebit.setBranchId(request.getBranchId());
        ledgerBankAccountDebit.setCashAmount(request.getCashAmount());
        ledgerBankAccountDebit.setDescription(request.getDescription());
        ledgerBankAccountDebit.setOtherAccNo(request.getOtherAccNo());
        ledgerBankAccountDebit.setOtherAccountName(request.getOtherAccountName());
        ledgerBankAccountDebit.setPrNo(request.getPrNo());
        ledgerBankAccountDebit.setIsDeleted(Deleted.NO);
        LedgerBankAccountDebit save = ledgerBankAccountDebitRepository.save(ledgerBankAccountDebit);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerBankAccountDebitResponse update(LedgerBankAccountDebitUpdateRequest request) {

        LedgerBankAccountDebit ledgerBankAccountDebit = ledgerBankAccountDebitRepository.findById(request.getId()).orElse(null);
        if (ledgerBankAccountDebit == null) {
            return null;
        }

        ledgerBankAccountDebit.setId(request.getId());
        ledgerBankAccountDebit.setBranchId(request.getBranchId());
        ledgerBankAccountDebit.setCashAmount(request.getCashAmount());
        ledgerBankAccountDebit.setDescription(request.getDescription());
        ledgerBankAccountDebit.setOtherAccNo(request.getOtherAccNo());
        ledgerBankAccountDebit.setOtherAccountName(request.getOtherAccountName());
        ledgerBankAccountDebit.setPrNo(request.getPrNo());
        LedgerBankAccountDebit updated = ledgerBankAccountDebitRepository.save(ledgerBankAccountDebit);

        return (convert(updated));
    }

    @Override
    public LedgerBankAccountDebitResponse getById(Long id) {

        return ledgerBankAccountDebitRepository.findById(id).map(LedgerBankAccountDebitServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerBankAccountDebitResponse> getAll() {

        return ledgerBankAccountDebitRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerBankAccountDebitServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerBankAccountDebit got = ledgerBankAccountDebitRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerBankAccountDebitRepository.save(got);

        return 1;
    }
}