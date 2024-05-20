package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.BankCardTypeDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeDetailResponse;
import lk.quantacom.smarterpbackend.dto.response.BankCardTypeResponse;
import lk.quantacom.smarterpbackend.dto.response.BankResponse;
import lk.quantacom.smarterpbackend.entity.Bank;
import lk.quantacom.smarterpbackend.entity.BankCardType;
import lk.quantacom.smarterpbackend.entity.BankCardTypeDetail;
import lk.quantacom.smarterpbackend.repository.BankCardTypeDetailRepository;
import lk.quantacom.smarterpbackend.repository.BankCardTypeRepository;
import lk.quantacom.smarterpbackend.repository.BankRepository;
import lk.quantacom.smarterpbackend.service.BankCardTypeDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankCardTypeDetailServiceImpl implements BankCardTypeDetailService {

    @Autowired
    private BankCardTypeDetailRepository bankCardTypeDetailRepository;

    @Autowired
    private BankCardTypeRepository bankCardTypeRepository;

    @Autowired
    private BankRepository bankRepository;

    @Override
    @Transactional
    public BankCardTypeDetailResponse save(BankCardTypeDetailRequest request) {

        BankCardTypeDetail bankCardTypeDetail = new BankCardTypeDetail();
        BankCardType bankCardType = bankCardTypeRepository.getById( request.getBankCardTypeId() );
        bankCardTypeDetail.setBankCardType(bankCardType);

        bankCardTypeDetail.setDescription(request.getDescription());

        Bank bank = bankRepository.getById(request.getBankId());
        bankCardTypeDetail.setBank(bank);

        bankCardTypeDetail.setServiceCharge(request.getServiceCharge());
        bankCardTypeDetail.setServiceChargePercentage(request.getServiceChargePercentage());
        bankCardTypeDetail.setIsDefault(request.getIsDefault());
        bankCardTypeDetail.setIsDeleted(Deleted.NO);
        BankCardTypeDetail save = bankCardTypeDetailRepository.save(bankCardTypeDetail);

        return convert(save);
    }

    @Override
    @Transactional
    public BankCardTypeDetailResponse update(BankCardTypeDetailUpdateRequest request) {

        BankCardTypeDetail bankCardTypeDetail = bankCardTypeDetailRepository.findById(request.getId()).orElse(null);
        if (bankCardTypeDetail == null) {
            return null;
        }

        bankCardTypeDetail.setId(request.getId());

        BankCardType bankCardType = bankCardTypeRepository.getById( request.getBankCardTypeId() );
        bankCardTypeDetail.setBankCardType(bankCardType);

        bankCardTypeDetail.setDescription(request.getDescription());

        Bank bank = bankRepository.getById(request.getBankId());
        bankCardTypeDetail.setBank(bank);

        bankCardTypeDetail.setServiceCharge(request.getServiceCharge());
        bankCardTypeDetail.setServiceChargePercentage(request.getServiceChargePercentage());
        bankCardTypeDetail.setIsDefault(request.getIsDefault());
        BankCardTypeDetail updated = bankCardTypeDetailRepository.save(bankCardTypeDetail);

        return (convert(updated));
    }

    @Override
    public BankCardTypeDetailResponse getById(Long id) {

        return bankCardTypeDetailRepository.findById(id).map(BankCardTypeDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BankCardTypeDetailResponse> getAll() {

        return bankCardTypeDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BankCardTypeDetailServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        BankCardTypeDetail got = bankCardTypeDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bankCardTypeDetailRepository.save(got);

        return 1;
    }

    public static BankCardTypeDetailResponse convert(BankCardTypeDetail bankCardTypeDetail) {

        BankCardTypeDetailResponse typeResponse = new BankCardTypeDetailResponse();

        BankCardTypeResponse cardTypeResponse = BankCardTypeServiceImpl.convert( bankCardTypeDetail.getBankCardType() );
        typeResponse.setCardType(cardTypeResponse );

        typeResponse.setDescription(bankCardTypeDetail.getDescription());

        BankResponse bankResponse = BankServiceImpl.convert( bankCardTypeDetail.getBank() );
        typeResponse.setBank( bankResponse );

        typeResponse.setServiceCharge(bankCardTypeDetail.getServiceCharge());
        typeResponse.setServiceChargePercentage(bankCardTypeDetail.getServiceChargePercentage());
        typeResponse.setIsDefault(bankCardTypeDetail.getIsDefault());
        typeResponse.setId(bankCardTypeDetail.getId());
        typeResponse.setCreatedBy(bankCardTypeDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bankCardTypeDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(bankCardTypeDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bankCardTypeDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(bankCardTypeDetail.getIsDeleted());

        return typeResponse;
    }
}