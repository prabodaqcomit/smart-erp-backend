package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BankRequest;
import lk.quantacom.smarterpbackend.dto.request.BankUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankResponse;
import lk.quantacom.smarterpbackend.entity.Bank;
import lk.quantacom.smarterpbackend.repository.BankRepository;
import lk.quantacom.smarterpbackend.service.BankService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;


    @Override
    @Transactional
    public BankResponse save(BankRequest request) {

        Bank bank = new Bank();
        bank.setBankName(request.getBankName());
        bank.setBankCode(request.getBankCode());
        bank.setIsDeleted(Deleted.NO);
        Bank save = bankRepository.save(bank);

        return convert(save);
    }

    @Override
    @Transactional
    public BankResponse update(BankUpdateRequest request) {

        Bank bank = bankRepository.findById(request.getId()).orElse(null);
        if (bank == null) {
            return null;
        }

        bank.setId(request.getId());
        bank.setBankName(request.getBankName());
        bank.setBankCode(request.getBankCode());
        Bank updated = bankRepository.save(bank);

        return (convert(updated));
    }

    @Override
    public BankResponse getById(Long id) {

        return bankRepository.findById(id).map(BankServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BankResponse> getAll() {

        return bankRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BankServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Bank got = bankRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bankRepository.save(got);

        return 1;
    }

    public static BankResponse convert(Bank bank) {

        BankResponse typeResponse = new BankResponse();
        typeResponse.setBankName(bank.getBankName());
        typeResponse.setBankCode(bank.getBankCode());
        typeResponse.setId(bank.getId());
        typeResponse.setCreatedBy(bank.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bank.getCreatedDateTime()));
        typeResponse.setModifiedBy(bank.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bank.getModifiedDateTime()));
        typeResponse.setIsDeleted(bank.getIsDeleted());

        return typeResponse;
    }
}