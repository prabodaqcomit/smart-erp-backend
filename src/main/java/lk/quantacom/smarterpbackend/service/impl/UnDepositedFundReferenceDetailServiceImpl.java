package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.UnDepositedFundReferenceDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.TblpaymodeResponse;
import lk.quantacom.smarterpbackend.dto.response.UnDepositedFundReferenceDetailResponse;
import lk.quantacom.smarterpbackend.entity.TblpaydtlPK;
import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import lk.quantacom.smarterpbackend.entity.UnDepositedFundReferenceDetail;
import lk.quantacom.smarterpbackend.repository.CurrencyRepository;
import lk.quantacom.smarterpbackend.repository.TblpaymodeRepository;
import lk.quantacom.smarterpbackend.repository.UnDepositedFundReferenceDetailRepository;
import lk.quantacom.smarterpbackend.service.UnDepositedFundReferenceDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnDepositedFundReferenceDetailServiceImpl implements UnDepositedFundReferenceDetailService {

    @Autowired
    private UnDepositedFundReferenceDetailRepository unDepositedFundReferenceDetailRepository;

    @Autowired
    private TblpaymodeRepository tblpaymodeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

//    @Override
//    @Transactional
//    public UnDepositedFundReferenceDetailResponse save(UnDepositedFundReferenceDetailRequest request) {
//
//        UnDepositedFundReferenceDetail unDepositedFundReferenceDetail = new UnDepositedFundReferenceDetail();
//        unDepositedFundReferenceDetail.setUnDepositedFundReferenceHeaderId(request.getUnDepositedFundReferenceHeaderId());
//        unDepositedFundReferenceDetail.setLineNumber(request.getLineNumber());
//        unDepositedFundReferenceDetail.setPaymentModeId(request.getPaymentModeId());
//        unDepositedFundReferenceDetail.setCurrencyTypeId(request.getCurrencyTypeId());
//        unDepositedFundReferenceDetail.setCurrencyTypeExchangeRate(request.getCurrencyTypeExchangeRate());
//        unDepositedFundReferenceDetail.setPaymentModeRemark(request.getPaymentModeRemark());
//        unDepositedFundReferenceDetail.setUnDepositedTotalAmount(request.getUnDepositedTotalAmount());
//        unDepositedFundReferenceDetail.setUnDepositedBalance(request.getUnDepositedBalance());
//        unDepositedFundReferenceDetail.setIsDeleted(Deleted.NO);
//        UnDepositedFundReferenceDetail save = unDepositedFundReferenceDetailRepository.save(unDepositedFundReferenceDetail);
//
//        return convert(save);
//    }

//    @Override
//    @Transactional
//    public UnDepositedFundReferenceDetailResponse update(UnDepositedFundReferenceDetailUpdateRequest request) {
//
//        UnDepositedFundReferenceDetail unDepositedFundReferenceDetail = unDepositedFundReferenceDetailRepository.findById(request.getId()).orElse(null);
//        if (unDepositedFundReferenceDetail == null) {
//            return null;
//        }
//
//        unDepositedFundReferenceDetail.setId(request.getId());
//        unDepositedFundReferenceDetail.setUnDepositedFundReferenceHeaderId(request.getUnDepositedFundReferenceHeaderId());
//        unDepositedFundReferenceDetail.setLineNumber(request.getLineNumber());
//        unDepositedFundReferenceDetail.setPaymentModeId(request.getPaymentModeId());
//        unDepositedFundReferenceDetail.setCurrencyTypeId(request.getCurrencyTypeId());
//        unDepositedFundReferenceDetail.setCurrencyTypeExchangeRate(request.getCurrencyTypeExchangeRate());
//        unDepositedFundReferenceDetail.setPaymentModeRemark(request.getPaymentModeRemark());
//        unDepositedFundReferenceDetail.setUnDepositedTotalAmount(request.getUnDepositedTotalAmount());
//        unDepositedFundReferenceDetail.setUnDepositedBalance(request.getUnDepositedBalance());
//        UnDepositedFundReferenceDetail updated = unDepositedFundReferenceDetailRepository.save(unDepositedFundReferenceDetail);
//
//        return (convert(updated));
//    }

    @Override
    public UnDepositedFundReferenceDetailResponse getById(Long id) {

        return unDepositedFundReferenceDetailRepository.findById(id).map(UnDepositedFundReferenceDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<UnDepositedFundReferenceDetailResponse> getAll() {

        return unDepositedFundReferenceDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(UnDepositedFundReferenceDetailServiceImpl::convert).collect(Collectors.toList());

    }


//    @Override
//    @Transactional
//    public Integer delete(Long id) {
//
//        UnDepositedFundReferenceDetail got = unDepositedFundReferenceDetailRepository.findById(id).orElse(null);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        unDepositedFundReferenceDetailRepository.save(got);
//
//        return 1;
//    }

    public static UnDepositedFundReferenceDetailResponse convert(UnDepositedFundReferenceDetail unDepositedFundReferenceDetail) {

        UnDepositedFundReferenceDetailResponse typeResponse = new UnDepositedFundReferenceDetailResponse();

        typeResponse.setUnDepositedFundReferenceHeaderId(unDepositedFundReferenceDetail.getUnDepositedFundReferenceHeader().getId());
        typeResponse.setLineNumber(unDepositedFundReferenceDetail.getLineNumber());

        typeResponse.setPaymentMode(unDepositedFundReferenceDetail.getPaymentMode() == null ? null : TblpaymodeServiceImpl.convert(unDepositedFundReferenceDetail.getPaymentMode()));
        typeResponse.setCurrencyType(unDepositedFundReferenceDetail.getCurrencyType() == null ? null : CurrencyServiceImpl.convert(unDepositedFundReferenceDetail.getCurrencyType()));
        typeResponse.setCurrencyTypeExchangeRate(unDepositedFundReferenceDetail.getCurrencyTypeExchangeRate());

        typeResponse.setPaymentModeRemark(unDepositedFundReferenceDetail.getPaymentModeRemark());
        typeResponse.setUnDepositedTotalAmount(unDepositedFundReferenceDetail.getPaidAmountValue());
        typeResponse.setUnDepositedBalance(unDepositedFundReferenceDetail.getUnDepositedBalance());
        typeResponse.setId(unDepositedFundReferenceDetail.getId());
        typeResponse.setCreatedBy(unDepositedFundReferenceDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(unDepositedFundReferenceDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(unDepositedFundReferenceDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(unDepositedFundReferenceDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(unDepositedFundReferenceDetail.getIsDeleted());

        return typeResponse;
    }
}