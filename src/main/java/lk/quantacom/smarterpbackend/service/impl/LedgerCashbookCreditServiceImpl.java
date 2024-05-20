package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCashbookCreditUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerCashbookCreditResponse;
import lk.quantacom.smarterpbackend.entity.LedgerCashbookCredit;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerCashbookCreditRepository;
import lk.quantacom.smarterpbackend.service.LedgerCashbookCreditService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerCashbookCreditServiceImpl implements LedgerCashbookCreditService {

    @Autowired
    private LedgerCashbookCreditRepository ledgerCashbookCreditRepository;

    private static LedgerCashbookCreditResponse convert(LedgerCashbookCredit ledgerCashbookCredit) {

        LedgerCashbookCreditResponse typeResponse = new LedgerCashbookCreditResponse();
        typeResponse.setBankAmount(ledgerCashbookCredit.getBankAmount());
        typeResponse.setBranchId(ledgerCashbookCredit.getBranchId());
        typeResponse.setCashAmount(ledgerCashbookCredit.getCashAmount());
        typeResponse.setDescription(ledgerCashbookCredit.getDescription());
        typeResponse.setDiscountAllowed(ledgerCashbookCredit.getDiscountAllowed());
        typeResponse.setOtherAccNo(ledgerCashbookCredit.getOtherAccNo());
        typeResponse.setOtherAccountName(ledgerCashbookCredit.getOtherAccountName());
        typeResponse.setPrNo(ledgerCashbookCredit.getPrNo());
        typeResponse.setVcharNo(ledgerCashbookCredit.getVcharNo());
        typeResponse.setId(ledgerCashbookCredit.getId());
        typeResponse.setCreatedBy(ledgerCashbookCredit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerCashbookCredit.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerCashbookCredit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerCashbookCredit.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerCashbookCredit.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public LedgerCashbookCreditResponse save(LedgerCashbookCreditRequest request) {

        LedgerCashbookCredit ledgerCashbookCredit = new LedgerCashbookCredit();
        ledgerCashbookCredit.setBankAmount(request.getBankAmount());
        ledgerCashbookCredit.setBranchId(request.getBranchId());
        ledgerCashbookCredit.setCashAmount(request.getCashAmount());
        ledgerCashbookCredit.setDescription(request.getDescription());
        ledgerCashbookCredit.setDiscountAllowed(request.getDiscountAllowed());
        ledgerCashbookCredit.setOtherAccNo(request.getOtherAccNo());
        ledgerCashbookCredit.setOtherAccountName(request.getOtherAccountName());
        ledgerCashbookCredit.setPrNo(request.getPrNo());
        ledgerCashbookCredit.setVcharNo(request.getVcharNo());
        ledgerCashbookCredit.setIsDeleted(Deleted.NO);
        LedgerCashbookCredit save = ledgerCashbookCreditRepository.save(ledgerCashbookCredit);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerCashbookCreditResponse update(LedgerCashbookCreditUpdateRequest request) {

        LedgerCashbookCredit ledgerCashbookCredit = ledgerCashbookCreditRepository.findById(request.getId()).orElse(null);
        if (ledgerCashbookCredit == null) {
            return null;
        }

        ledgerCashbookCredit.setId(request.getId());
        ledgerCashbookCredit.setBankAmount(request.getBankAmount());
        ledgerCashbookCredit.setBranchId(request.getBranchId());
        ledgerCashbookCredit.setCashAmount(request.getCashAmount());
        ledgerCashbookCredit.setDescription(request.getDescription());
        ledgerCashbookCredit.setDiscountAllowed(request.getDiscountAllowed());
        ledgerCashbookCredit.setOtherAccNo(request.getOtherAccNo());
        ledgerCashbookCredit.setOtherAccountName(request.getOtherAccountName());
        ledgerCashbookCredit.setPrNo(request.getPrNo());
        ledgerCashbookCredit.setVcharNo(request.getVcharNo());
        LedgerCashbookCredit updated = ledgerCashbookCreditRepository.save(ledgerCashbookCredit);

        return (convert(updated));
    }

    @Override
    public LedgerCashbookCreditResponse getById(Long id) {

        return ledgerCashbookCreditRepository.findById(id).map(LedgerCashbookCreditServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerCashbookCreditResponse> getAll() {

        return ledgerCashbookCreditRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerCashbookCreditServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerCashbookCredit got = ledgerCashbookCreditRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerCashbookCreditRepository.save(got);

        return 1;
    }
}