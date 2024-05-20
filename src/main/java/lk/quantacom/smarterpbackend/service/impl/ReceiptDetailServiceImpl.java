package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailRequest;
import lk.quantacom.smarterpbackend.dto.request.ReceiptDetailUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ReceiptDetailResponse;
import lk.quantacom.smarterpbackend.entity.ReceiptDetail;
import lk.quantacom.smarterpbackend.repository.ReceiptDetailRepository;
import lk.quantacom.smarterpbackend.service.ReceiptDetailService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptDetailServiceImpl implements ReceiptDetailService {

    @Autowired
    private ReceiptDetailRepository receiptDetailRepository;


    @Override
    @Transactional
    public ReceiptDetailResponse save(ReceiptDetailRequest request) {

        ReceiptDetail receiptDetail = new ReceiptDetail();
        //receiptDetail.setReceiptHeaderId(request.getReceiptHeaderId());
        //receiptDetail.setReceiptNumber(request.getReceiptNumber());
        receiptDetail.setLineNumber(request.getLineNumber());
        receiptDetail.setAccountId(request.getAccountId());
        //receiptDetail.setAccountCode(request.getAccountCode());
        //receiptDetail.setAccountName(request.getAccountName());
        receiptDetail.setInvoiceBranchId(request.getInvoiceBranchId());
        receiptDetail.setInvoiceNumber(request.getInvoiceNumber());
        //receiptDetail.setInvoiceDateTime(request.getInvoiceDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getInvoiceDateTime()));
        //receiptDetail.setInvoiceAmount(request.getInvoiceAmount());
        //receiptDetail.setInvoicePendingAmount(request.getInvoicePendingAmount());
        receiptDetail.setPaidAmount(request.getPaidAmount());
        receiptDetail.setWithholdingTaxAmount(request.getWithholdingTaxAmount());
        receiptDetail.setStampDutyAmount(request.getStampDutyAmount());
        Double _netAmount = (request.getPaidAmount() - request.getWithholdingTaxAmount()) + request.getStampDutyAmount();
        receiptDetail.setNetAmount( _netAmount );
        receiptDetail.setIsDeleted(Deleted.NO);
        ReceiptDetail save = receiptDetailRepository.save(receiptDetail);

        return convert(save);
    }

    @Override
    @Transactional
    public ReceiptDetailResponse update(ReceiptDetailUpdateRequest request) {

        ReceiptDetail receiptDetail = receiptDetailRepository.findById(request.getId()).orElse(null);
        if (receiptDetail == null) {
            return null;
        }

        receiptDetail.setId(request.getId());
        //receiptDetail.setReceiptHeaderId(request.getReceiptHeaderId());
        //receiptDetail.setReceiptNumber(request.getReceiptNumber());
        receiptDetail.setLineNumber(request.getLineNumber());
        receiptDetail.setAccountId(request.getAccountId());
        //receiptDetail.setAccountCode(request.getAccountCode());
        //receiptDetail.setAccountName(request.getAccountName());
        receiptDetail.setInvoiceBranchId(request.getInvoiceBranchId());
        receiptDetail.setInvoiceNumber(request.getInvoiceNumber());
        //receiptDetail.setInvoiceDateTime(request.getInvoiceDateTime() == null ? null : ConvertUtils.convertStrToDate(request.getInvoiceDateTime()));
        //receiptDetail.setInvoiceAmount(request.getInvoiceAmount());
        //receiptDetail.setInvoicePendingAmount(request.getInvoicePendingAmount());
        receiptDetail.setPaidAmount(request.getPaidAmount());
        receiptDetail.setWithholdingTaxAmount(request.getWithholdingTaxAmount());
        receiptDetail.setStampDutyAmount(request.getStampDutyAmount());
        receiptDetail.setNetAmount(request.getNetAmount());
        ReceiptDetail updated = receiptDetailRepository.save(receiptDetail);

        return (convert(updated));
    }

    @Override
    public ReceiptDetailResponse getById(Long id) {

        return receiptDetailRepository.findById(id).map(ReceiptDetailServiceImpl::convert).orElse(null);
    }

    @Override
    public List<ReceiptDetailResponse> getAll() {

        return receiptDetailRepository.findByIsDeleted(Deleted.NO)
                .stream().map(ReceiptDetailServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ReceiptDetailResponse> getByReceiptHeaderId(Long id){
        return receiptDetailRepository.findByIsDeletedAndReceiptHeaderId(Deleted.NO, id)
                .stream().map(ReceiptDetailServiceImpl::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        ReceiptDetail got = receiptDetailRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        receiptDetailRepository.save(got);

        return 1;
    }

    public static ReceiptDetailResponse convert(ReceiptDetail receiptDetail) {

        ReceiptDetailResponse typeResponse = new ReceiptDetailResponse();
        typeResponse.setReceiptHeaderId(receiptDetail.getReceiptHeader().getId());
        typeResponse.setReceiptNumber(receiptDetail.getReceiptHeader().getReceiptNumber());
        typeResponse.setLineNumber(receiptDetail.getLineNumber());
        typeResponse.setAccountId(receiptDetail.getAccountId());
        typeResponse.setAccountCode(receiptDetail.getAccountCode());
        typeResponse.setAccountName(receiptDetail.getAccountName());
        typeResponse.setInvoiceBranchId(receiptDetail.getInvoiceBranchId());
        typeResponse.setInvoiceNumber(receiptDetail.getInvoiceNumber());
        typeResponse.setInvoiceDateTime(receiptDetail.getInvoiceDateTime());
        typeResponse.setInvoiceAmount(receiptDetail.getInvoiceAmount());
        typeResponse.setInvoicePendingAmount(receiptDetail.getInvoicePendingAmount());
        typeResponse.setPaidAmount(receiptDetail.getPaidAmount());
        typeResponse.setWithholdingTaxAmount(receiptDetail.getWithholdingTaxAmount());
        typeResponse.setStampDutyAmount(receiptDetail.getStampDutyAmount());
        typeResponse.setNetAmount(receiptDetail.getNetAmount());
        typeResponse.setId(receiptDetail.getId());
        typeResponse.setCreatedBy(receiptDetail.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(receiptDetail.getCreatedDateTime()));
        typeResponse.setModifiedBy(receiptDetail.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(receiptDetail.getModifiedDateTime()));
        typeResponse.setIsDeleted(receiptDetail.getIsDeleted());

        return typeResponse;
    }
}