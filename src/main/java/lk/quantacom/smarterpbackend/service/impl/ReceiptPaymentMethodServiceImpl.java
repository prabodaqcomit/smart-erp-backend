package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.response.ReceiptPaymentMethodResponse;
import lk.quantacom.smarterpbackend.entity.ReceiptPaymentMethod;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.ReceiptPaymentMethodRepository;
import lk.quantacom.smarterpbackend.repository.TblpaymodeRepository;
import lk.quantacom.smarterpbackend.service.ReceiptPaymentMethodService;
import org.bouncycastle.util.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptPaymentMethodServiceImpl implements ReceiptPaymentMethodService {

    @Autowired
    private ReceiptPaymentMethodRepository receiptPaymentMethodRepository;

    @Autowired
    private TblpaymodeRepository tblpaymodeRepository;

    //Save is Handled in Receipt Header Service Impl
//    @Override
//    @Transactional
//    public ReceiptPaymentMethodResponse save(ReceiptPaymentMethodRequest request) {
//
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public ReceiptPaymentMethodResponse update(ReceiptPaymentMethodRequest request) {
//        //Handled in Receipt Header Service Impl
//        return null;
//    }

    @Override
    public ReceiptPaymentMethodResponse getById(Long id) {

        return receiptPaymentMethodRepository.findById(id).map(ReceiptPaymentMethodServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ReceiptPaymentMethodResponse> getAll() {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        return receiptPaymentMethodRepository.findByIsDeleted(Deleted.NO, sort)
                .stream().map(ReceiptPaymentMethodServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ReceiptPaymentMethodResponse> getByReceiptHeaderId(Long id) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC, "id"));

        return receiptPaymentMethodRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, id)
                .stream().map(ReceiptPaymentMethodServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        ReceiptPaymentMethod got = receiptPaymentMethodRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        receiptPaymentMethodRepository.save(got);

        return 1;
    }

    public static ReceiptPaymentMethodResponse convert(ReceiptPaymentMethod receiptPaymentMethod) {

        ReceiptPaymentMethodResponse paymentMethodResponse = new ReceiptPaymentMethodResponse();
        paymentMethodResponse.setId(receiptPaymentMethod.getId());
        paymentMethodResponse.setLineNumber(receiptPaymentMethod.getLineNumber());
        paymentMethodResponse.setPaymentDate(new DateFormatConverter().patternDate(receiptPaymentMethod.getPaymentDate()));
        paymentMethodResponse.setBranch( BranchNetworkServiceImpl.convert(receiptPaymentMethod.getBranch()));
        paymentMethodResponse.setReceiptHeaderId(receiptPaymentMethod.getReceiptHeader().getId());
        paymentMethodResponse.setPaymentMethodId(receiptPaymentMethod.getPaymentMethodId());
        if(receiptPaymentMethod.getCurrencyType() != null){
            paymentMethodResponse.setCurrencyType(CurrencyServiceImpl.convert(receiptPaymentMethod.getCurrencyType()));
        }
        paymentMethodResponse.setCurrencyToLocalCurrencyRate(receiptPaymentMethod.getCurrencyToLocalCurrencyRate());
        if(receiptPaymentMethod.getChequeDate() != null){
            paymentMethodResponse.setChequeDate(new DateFormatConverter().patternDate(receiptPaymentMethod.getChequeDate()));
        }
        paymentMethodResponse.setChequeNumber(receiptPaymentMethod.getReceiptNumber());
        if(receiptPaymentMethod.getFromBank() != null){
            paymentMethodResponse.setFromBank(BankServiceImpl.convert(receiptPaymentMethod.getFromBank()));
        }
        if(receiptPaymentMethod.getToBank() != null){
            paymentMethodResponse.setToBank(BankServiceImpl.convert(receiptPaymentMethod.getToBank()));
        }
        paymentMethodResponse.setFromBankAccountName(receiptPaymentMethod.getFromBankAccountDescription());
        if(receiptPaymentMethod.getToBankAccount() != null){
            paymentMethodResponse.setToBankAccount(LadgerAccountServiceImpl.convert(receiptPaymentMethod.getToBankAccount()));
        }
        if(receiptPaymentMethod.getFromWallet() != null){
            paymentMethodResponse.setFromWallet( OnlineWalletServiceImpl.convert(receiptPaymentMethod.getFromWallet()));
        }
        if(receiptPaymentMethod.getToWallet() != null){
            paymentMethodResponse.setToWallet(LadgerAccountServiceImpl.convert(receiptPaymentMethod.getToWallet()));
        }
        paymentMethodResponse.setFromBankCardNumber(receiptPaymentMethod.getFromBankCardNumber());
        paymentMethodResponse.setPaidAmount(receiptPaymentMethod.getPaidAmount());
        paymentMethodResponse.setReference(receiptPaymentMethod.getReference());

        paymentMethodResponse.setCreatedBy(receiptPaymentMethod.getCreatedBy());
        paymentMethodResponse.setCreatedDateTime(new DateFormatConverter().patternDateTime(receiptPaymentMethod.getCreatedDateTime()));
        paymentMethodResponse.setModifiedBy(receiptPaymentMethod.getModifiedBy());
        paymentMethodResponse.setModifiedDateTime(new DateFormatConverter().patternDateTime(receiptPaymentMethod.getModifiedDateTime()));
        paymentMethodResponse.setIsDeleted(receiptPaymentMethod.getIsDeleted());

        return paymentMethodResponse;
    }
}