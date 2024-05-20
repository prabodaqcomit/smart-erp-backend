package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountCreditRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerBankAccountCreditUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerBankAccountCreditResponse;
import lk.quantacom.smarterpbackend.entity.LedgerBankAccountCredit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerBankAccountCreditRepository;
import lk.quantacom.smarterpbackend.service.LedgerBankAccountCreditService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerBankAccountCreditServiceImpl implements LedgerBankAccountCreditService {

    @Autowired
    private LedgerBankAccountCreditRepository ledgerBankAccountCreditRepository;

    @Override
    @Transactional
    public LedgerBankAccountCreditResponse save(LedgerBankAccountCreditRequest request) {

        LedgerBankAccountCredit ledgerBankAccountCredit = new LedgerBankAccountCredit();
        ledgerBankAccountCredit.setBranchId(request.getBranchId());
        ledgerBankAccountCredit.setCashAmount(request.getCashAmount());
        ledgerBankAccountCredit.setDescription(request.getDescription());
        ledgerBankAccountCredit.setOtherAccNo(request.getOtherAccNo());
        ledgerBankAccountCredit.setOtherAccountName(request.getOtherAccountName());
        ledgerBankAccountCredit.setPrNo(request.getPrNo());
        ledgerBankAccountCredit.setIsDeleted(Deleted.NO);
        LedgerBankAccountCredit save = ledgerBankAccountCreditRepository.save(ledgerBankAccountCredit);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerBankAccountCreditResponse update(LedgerBankAccountCreditUpdateRequest request) {

        LedgerBankAccountCredit ledgerBankAccountCredit = ledgerBankAccountCreditRepository.findById(request.getId()).orElse(null);
        if (ledgerBankAccountCredit == null) {
            return null;
        }

        ledgerBankAccountCredit.setId(request.getId());
        ledgerBankAccountCredit.setBranchId(request.getBranchId());
        ledgerBankAccountCredit.setCashAmount(request.getCashAmount());
        ledgerBankAccountCredit.setDescription(request.getDescription());
        ledgerBankAccountCredit.setOtherAccNo(request.getOtherAccNo());
        ledgerBankAccountCredit.setOtherAccountName(request.getOtherAccountName());
        ledgerBankAccountCredit.setPrNo(request.getPrNo());
        LedgerBankAccountCredit updated = ledgerBankAccountCreditRepository.save(ledgerBankAccountCredit);

        return (convert(updated));
    }

    @Override
    public LedgerBankAccountCreditResponse getById(Long id) {

        return ledgerBankAccountCreditRepository.findById(id).map(LedgerBankAccountCreditServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerBankAccountCreditResponse> getAll() {

        return ledgerBankAccountCreditRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerBankAccountCreditServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerBankAccountCredit got = ledgerBankAccountCreditRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerBankAccountCreditRepository.save(got);

        return 1;
    }

    private static LedgerBankAccountCreditResponse convert(LedgerBankAccountCredit ledgerBankAccountCredit) {

        LedgerBankAccountCreditResponse typeResponse = new LedgerBankAccountCreditResponse();
        typeResponse.setBranchId(ledgerBankAccountCredit.getBranchId());
        typeResponse.setCashAmount(ledgerBankAccountCredit.getCashAmount());
        typeResponse.setDescription(ledgerBankAccountCredit.getDescription());
        typeResponse.setOtherAccNo(ledgerBankAccountCredit.getOtherAccNo());
        typeResponse.setOtherAccountName(ledgerBankAccountCredit.getOtherAccountName());
        typeResponse.setPrNo(ledgerBankAccountCredit.getPrNo());
        typeResponse.setId(ledgerBankAccountCredit.getId());
        typeResponse.setCreatedBy(ledgerBankAccountCredit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerBankAccountCredit.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerBankAccountCredit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerBankAccountCredit.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerBankAccountCredit.getIsDeleted());

        return typeResponse;
    }
}