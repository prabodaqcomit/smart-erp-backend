package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeResponse;
import lk.quantacom.smarterpbackend.entity.BankCardType;
import lk.quantacom.smarterpbackend.repository.BankCardTypeRepository;
import lk.quantacom.smarterpbackend.service.BankCardTypeService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankCardTypeServiceImpl implements BankCardTypeService {

    @Autowired
    private BankCardTypeRepository bankCardTypeRepository;


    @Override
    @Transactional
    public BankCardTypeResponse save(BankCardTypeRequest request) {

        BankCardType bankCardType = new BankCardType();
        bankCardType.setCardTypeName(request.getCardTypeName());
        bankCardType.setCardNumberLength(request.getCardNumberLength());
        bankCardType.setCardNumberFormat(request.getCardNumberFormat());
        bankCardType.setIsDeleted(Deleted.NO);
        BankCardType save = bankCardTypeRepository.save(bankCardType);

        return convert(save);
    }

    @Override
    @Transactional
    public BankCardTypeResponse update(BankCardTypeUpdateRequest request) {

        BankCardType bankCardType = bankCardTypeRepository.findById(request.getId()).orElse(null);
        if (bankCardType == null) {
            return null;
        }

        bankCardType.setId(request.getId());
        bankCardType.setCardTypeName(request.getCardTypeName());
        bankCardType.setCardNumberLength(request.getCardNumberLength());
        bankCardType.setCardNumberFormat(request.getCardNumberFormat());
        BankCardType updated = bankCardTypeRepository.save(bankCardType);

        return (convert(updated));
    }

    @Override
    public BankCardTypeResponse getById(Long id) {

        return bankCardTypeRepository.findById(id).map(BankCardTypeServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BankCardTypeResponse> getAll() {

        return bankCardTypeRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BankCardTypeServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        BankCardType got = bankCardTypeRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bankCardTypeRepository.save(got);

        return 1;
    }

    public static BankCardTypeResponse convert(BankCardType bankCardType) {

        BankCardTypeResponse typeResponse = new BankCardTypeResponse();
        typeResponse.setCardTypeName(bankCardType.getCardTypeName());
        typeResponse.setCardNumberLength(bankCardType.getCardNumberLength());
        typeResponse.setCardNumberFormat(bankCardType.getCardNumberFormat());
        typeResponse.setId(bankCardType.getId());
        typeResponse.setCreatedBy(bankCardType.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bankCardType.getCreatedDateTime()));
        typeResponse.setModifiedBy(bankCardType.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bankCardType.getModifiedDateTime()));
        typeResponse.setIsDeleted(bankCardType.getIsDeleted());

        return typeResponse;
    }
}