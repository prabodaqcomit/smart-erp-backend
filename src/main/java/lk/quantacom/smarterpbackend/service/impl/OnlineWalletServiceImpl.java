package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.OnlineWalletRequest;
import lk.quantacom.smarterpbackend.dto.request.OnlineWalletUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankResponse;
import lk.quantacom.smarterpbackend.dto.response.OnlineWalletResponse;
import lk.quantacom.smarterpbackend.entity.Bank;
import lk.quantacom.smarterpbackend.entity.OnlineWallet;
import lk.quantacom.smarterpbackend.repository.BankRepository;
import lk.quantacom.smarterpbackend.repository.OnlineWalletRepository;
import lk.quantacom.smarterpbackend.service.OnlineWalletService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OnlineWalletServiceImpl implements OnlineWalletService {

    @Autowired
    private OnlineWalletRepository onlineWalletRepository;

    @Autowired
    private BankRepository bankRepository;

    @Override
    @Transactional
    public OnlineWalletResponse save(OnlineWalletRequest request) {

        OnlineWallet onlineWallet = new OnlineWallet();

        Bank bank = bankRepository.getById(request.getBankId());
        onlineWallet.setBank(bank);

        onlineWallet.setDescription(request.getDescription());
        onlineWallet.setServiceCharge(request.getServiceCharge());
        onlineWallet.setServiceChargePercentage(request.getServiceChargePercentage());
        onlineWallet.setIsDeleted(Deleted.NO);
        OnlineWallet save = onlineWalletRepository.save(onlineWallet);

        return convert(save);
    }

    @Override
    @Transactional
    public OnlineWalletResponse update(OnlineWalletUpdateRequest request) {

        OnlineWallet onlineWallet = onlineWalletRepository.findById(request.getId()).orElse(null);
        if (onlineWallet == null) {
            return null;
        }

        onlineWallet.setId(request.getId());

        Bank bank = bankRepository.getById(request.getBankId());
        onlineWallet.setBank(bank);

        onlineWallet.setDescription(request.getDescription());
        onlineWallet.setServiceCharge(request.getServiceCharge());
        onlineWallet.setServiceChargePercentage(request.getServiceChargePercentage());
        OnlineWallet updated = onlineWalletRepository.save(onlineWallet);

        return (convert(updated));
    }

    @Override
    public OnlineWalletResponse getById(Long id) {

        return onlineWalletRepository.findById(id).map(OnlineWalletServiceImpl::convert).orElse(null);
    }

    @Override
    public List<OnlineWalletResponse> getAll() {

        return onlineWalletRepository.findByIsDeleted(Deleted.NO)
                .stream().map(OnlineWalletServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        OnlineWallet got = onlineWalletRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        onlineWalletRepository.save(got);

        return 1;
    }

    public static OnlineWalletResponse convert(OnlineWallet onlineWallet) {

        OnlineWalletResponse typeResponse = new OnlineWalletResponse();

        BankResponse bankResponse = BankServiceImpl.convert(onlineWallet.getBank());
        typeResponse.setBank(bankResponse);

        typeResponse.setDescription(onlineWallet.getDescription());
        typeResponse.setServiceCharge(onlineWallet.getServiceCharge());
        typeResponse.setServiceChargePercentage(onlineWallet.getServiceChargePercentage());
        typeResponse.setId(onlineWallet.getId());
        typeResponse.setCreatedBy(onlineWallet.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(onlineWallet.getCreatedDateTime()));
        typeResponse.setModifiedBy(onlineWallet.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(onlineWallet.getModifiedDateTime()));
        typeResponse.setIsDeleted(onlineWallet.getIsDeleted());

        return typeResponse;
    }
}