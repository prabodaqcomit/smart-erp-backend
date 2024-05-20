package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.converters.DateFormatConverter;
import lk.quantacom.smarterpbackend.dto.request.BankDepositDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.BankDepositDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BankDepositDetailResponse;
import lk.quantacom.smarterpbackend.entity.BankDepositDetail;
import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import lk.quantacom.smarterpbackend.repository.BankDepositDetailRepository;
import lk.quantacom.smarterpbackend.service.BankDepositDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankDepositDetailServiceImpl implements BankDepositDetailService {

    @Autowired
    private BankDepositDetailRepository bankDepositDetailRepository;


//    @Override
//    @Transactional
//    public BankDepositDetailResponse save(BankDepositDetailRequest request) {
//
//        BankDepositDetail bankDepositDetail = new BankDepositDetail();
//        bankDepositDetail.setDepositHeaderId(request.getDepositHeaderId());
//        bankDepositDetail.setDepositNumber(request.getDepositNumber());
//        bankDepositDetail.setLineNumber(request.getLineNumber());
//        bankDepositDetail.setUnDepositedFundReferenceId(request.getUnDepositedFundReferenceId());
//        bankDepositDetail.setUnDepositedFundReferenceLineNumber(request.getUnDepositedFundReferenceLineNumber());
//        bankDepositDetail.setTransactionTypeDescription(request.getTransactionTypeDescription());
//        bankDepositDetail.setTransactionIsInvoice(request.getTransactionIisInvoice());
//        bankDepositDetail.setTransactionIsReceipt(request.getTransactionIsReceipt());
//        bankDepositDetail.setTransactionBranchId(request.getTransactionBranchId());
//        bankDepositDetail.setTransactionId(request.getTransactionId());
//        bankDepositDetail.setTransactionDate(request.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransactionDate()));
//        bankDepositDetail.setTransactionAmount(request.getTransactionAmount());
//        bankDepositDetail.setPaymentModeId(request.getPaymentModeId());
//        bankDepositDetail.setCurrencyTypeId(request.getCurrencyTypeId());
//        bankDepositDetail.setCurrencyTypeExchangeRate(request.getCurrencyTypeExchangeRate());
//        bankDepositDetail.setPaymentModeRemark(request.getPaymentModeRemark());
//        bankDepositDetail.setUnDepositAmount(request.getUnDepositAmount());
//        bankDepositDetail.setUnDepositBalance(request.getUnDepositBalance());
//        bankDepositDetail.setIsDeleted(Deleted.NO);
//        BankDepositDetail save = bankDepositDetailRepository.save(bankDepositDetail);
//
//        return convert(save);
//    }

//    @Override
//    @Transactional
//    public BankDepositDetailResponse update(BankDepositDetailUpdateRequest request) {
//
//        BankDepositDetail bankDepositDetail = bankDepositDetailRepository.findById(request.getId()).orElse(null);
//        if (bankDepositDetail == null) {
//            return null;
//        }
//
//        bankDepositDetail.setId(request.getId());
//        bankDepositDetail.setDepositHeaderId(request.getDepositHeaderId());
//        bankDepositDetail.setDepositNumber(request.getDepositNumber());
//        bankDepositDetail.setLineNumber(request.getLineNumber());
//        bankDepositDetail.setUnDepositedFundReferenceId(request.getUndepositedFundReferenceId());
//        bankDepositDetail.setUnDepositedFundReferenceLineNumber(request.getUndepositedFundReferenceLineNumber());
//        bankDepositDetail.setTransactionTypeDescription(request.getTansactionTypeDescription());
//        bankDepositDetail.setTransactionIsInvoice(request.getTransactionIisInvoice());
//        bankDepositDetail.setTransactionIsReceipt(request.getTransactionIsReceipt());
//        bankDepositDetail.setTransactionBranchId(request.getTransactionBranchId());
//        bankDepositDetail.setTransactionId(request.getTransactionId());
//        bankDepositDetail.setTransactionDate(request.getTransactionDate() == null ? null : ConvertUtils.convertStrToDate(request.getTransactionDate()));
//        bankDepositDetail.setTransactionAmount(request.getTransactionAmount());
//        bankDepositDetail.setPaymentModeId(request.getPaymentModeId());
//        bankDepositDetail.setCurrencyTypeId(request.getCurrencyTypeId());
//        bankDepositDetail.setCurrencyTypeExchangeRate(request.getCurrencyTypeExchangeRate());
//        bankDepositDetail.setPaymentModeRemark(request.getPaymentModeRemark());
//        bankDepositDetail.setUnDepositAmount(request.getUndepositedAmount());
//        bankDepositDetail.setUnDepositBalance(request.getUndepositedBalance());
//        BankDepositDetail updated = bankDepositDetailRepository.save(bankDepositDetail);
//
//        return (convert(updated));
//    }

    @Override
    public BankDepositDetailResponse getById(Long id) {

        return bankDepositDetailRepository.findById(id).map(BankDepositDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<BankDepositDetailResponse> getAll() {

        return bankDepositDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BankDepositDetailServiceImpl::convert).collect(Collectors.toList());

    }


//    @Override
//    @Transactional
//    public Integer delete(Long id) {
//
//        BankDepositDetail got = bankDepositDetailRepository.findById(id).orElse(null);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        bankDepositDetailRepository.save(got);
//
//        return 1;
//    }

    public static BankDepositDetailResponse convert(BankDepositDetail bankDepositDetail) {

        BankDepositDetailResponse typeResponse = new BankDepositDetailResponse();
        typeResponse.setDepositHeaderId(bankDepositDetail.getBankDepositHeader().getId());
        typeResponse.setDepositNumber(bankDepositDetail.getDepositNumber());
        typeResponse.setLineNumber(bankDepositDetail.getLineNumber());
        typeResponse.setUnDepositedFundReferenceId(bankDepositDetail.getUnDepositedFundReferenceId());
        typeResponse.setUnDepositedFundReferenceLineNumber(bankDepositDetail.getUnDepositedFundReferenceLineNumber());
        typeResponse.setTransactionTypeDescription(bankDepositDetail.getTransactionTypeDescription());
        typeResponse.setTransactionIsInvoice(bankDepositDetail.getTransactionIsInvoice());
        typeResponse.setTransactionIsReceipt(bankDepositDetail.getTransactionIsReceipt());
        typeResponse.setTransactionBranchId(bankDepositDetail.getTransactionBranchId());
        typeResponse.setTransactionId(bankDepositDetail.getTransactionId());
        typeResponse.setTransactionDate(new DateFormatConverter().patternDate(bankDepositDetail.getTransactionDate()));
        typeResponse.setTransactionAmount(bankDepositDetail.getTransactionAmount());
        typeResponse.setPaymentMode(TblpaymodeServiceImpl.convert(bankDepositDetail.getPaymentMode()));
        typeResponse.setCurrencyType(CurrencyServiceImpl.convert(bankDepositDetail.getCurrencyType()));
        typeResponse.setCurrencyTypeExchangeRate(bankDepositDetail.getCurrencyTypeExchangeRate());
        typeResponse.setPaymentModeRemark(bankDepositDetail.getPaymentModeRemark());
        typeResponse.setUnDepositAmount(bankDepositDetail.getUnDepositAmount());
        typeResponse.setUnDepositBalance(bankDepositDetail.getUnDepositBalance());
        typeResponse.setId(bankDepositDetail.getId());
        typeResponse.setCreatedBy(bankDepositDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bankDepositDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(bankDepositDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bankDepositDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(bankDepositDetail.getIsDeleted());

        return typeResponse;
    }
}