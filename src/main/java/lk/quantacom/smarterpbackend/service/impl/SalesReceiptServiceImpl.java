package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.SalesReceiptRequest;
import lk.quantacom.smarterpbackend.dto.request.SalesReceiptUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SalesReceiptResponse;
import lk.quantacom.smarterpbackend.entity.SalesReceipt;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.SalesReceiptRepository;
import lk.quantacom.smarterpbackend.service.SalesReceiptService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesReceiptServiceImpl implements SalesReceiptService {

    @Autowired
    private SalesReceiptRepository salesReceiptRepository;

    private static SalesReceiptResponse convert(SalesReceipt salesReceipt) {

        SalesReceiptResponse typeResponse = new SalesReceiptResponse();
        typeResponse.setBranchId(salesReceipt.getBranchId());
        typeResponse.setChangesOverPaid(salesReceipt.getChangesOverPaid());
        typeResponse.setChequeDate(salesReceipt.getChequeDate());
        typeResponse.setChequeNo(salesReceipt.getChequeNo());
        typeResponse.setChequePaymentStatus(salesReceipt.getChequePaymentStatus());
        typeResponse.setCustomerId(salesReceipt.getCustomerId());
        typeResponse.setDepositAmount(salesReceipt.getDepositAmount());
        typeResponse.setDisPrese(salesReceipt.getDisPrese());
        typeResponse.setDueBalance(salesReceipt.getDueBalance());
        typeResponse.setGrandTotal(salesReceipt.getGrandTotal());
        typeResponse.setIdsalesReceipt(salesReceipt.getIdsalesReceipt());
        typeResponse.setInPayDate(salesReceipt.getInPayDate());
        typeResponse.setInvoiceDisc(salesReceipt.getInvoiceDisc());
        typeResponse.setInvoiceId(salesReceipt.getInvoiceId());
        typeResponse.setLineNo(salesReceipt.getLineNo());
        typeResponse.setNetAmount(salesReceipt.getNetAmount());
        typeResponse.setPaidAdvanced(salesReceipt.getPaidAdvanced());
        typeResponse.setPaymentMethod(salesReceipt.getPaymentMethod());
        typeResponse.setRemark(salesReceipt.getRemark());
        typeResponse.setReturnAmount(salesReceipt.getReturnAmount());
        typeResponse.setSubTotal(salesReceipt.getSubTotal());
        typeResponse.setTime(salesReceipt.getTime());
        typeResponse.setId(salesReceipt.getId());
        typeResponse.setCreatedBy(salesReceipt.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(salesReceipt.getCreatedDateTime()));
        typeResponse.setModifiedBy(salesReceipt.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(salesReceipt.getModifiedDateTime()));
        typeResponse.setIsDeleted(salesReceipt.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public SalesReceiptResponse save(SalesReceiptRequest request) {

        SalesReceipt salesReceipt = new SalesReceipt();
        salesReceipt.setBranchId(request.getBranchId());
        salesReceipt.setChangesOverPaid(request.getChangesOverPaid());
        salesReceipt.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        salesReceipt.setChequeNo(request.getChequeNo());
        salesReceipt.setChequePaymentStatus(request.getChequePaymentStatus());
        salesReceipt.setCustomerId(request.getCustomerId());
        salesReceipt.setDepositAmount(request.getDepositAmount());
        salesReceipt.setDisPrese(request.getDisPrese());
        salesReceipt.setDueBalance(request.getDueBalance());
        salesReceipt.setGrandTotal(request.getGrandTotal());
        salesReceipt.setIdsalesReceipt(request.getIdsalesReceipt());
        salesReceipt.setInPayDate(request.getInPayDate() == null ? null : ConvertUtils.convertStrToDate(request.getInPayDate()));
        salesReceipt.setInvoiceDisc(request.getInvoiceDisc());
        salesReceipt.setInvoiceId(request.getInvoiceId());
        salesReceipt.setLineNo(request.getLineNo());
        salesReceipt.setNetAmount(request.getNetAmount());
        salesReceipt.setPaidAdvanced(request.getPaidAdvanced());
        salesReceipt.setPaymentMethod(request.getPaymentMethod());
        salesReceipt.setRemark(request.getRemark());
        salesReceipt.setReturnAmount(request.getReturnAmount());
        salesReceipt.setSubTotal(request.getSubTotal());
        salesReceipt.setTime(request.getTime());
        salesReceipt.setIsDeleted(Deleted.NO);
        SalesReceipt save = salesReceiptRepository.save(salesReceipt);

        return convert(save);
    }

    @Override
    @Transactional
    public SalesReceiptResponse update(SalesReceiptUpdateRequest request) {

        SalesReceipt salesReceipt = salesReceiptRepository.findById(request.getId()).orElse(null);
        if (salesReceipt == null) {
            return null;
        }

        salesReceipt.setId(request.getId());
        salesReceipt.setBranchId(request.getBranchId());
        salesReceipt.setChangesOverPaid(request.getChangesOverPaid());
        salesReceipt.setChequeDate(request.getChequeDate() == null ? null : ConvertUtils.convertStrToDate(request.getChequeDate()));
        salesReceipt.setChequeNo(request.getChequeNo());
        salesReceipt.setChequePaymentStatus(request.getChequePaymentStatus());
        salesReceipt.setCustomerId(request.getCustomerId());
        salesReceipt.setDepositAmount(request.getDepositAmount());
        salesReceipt.setDisPrese(request.getDisPrese());
        salesReceipt.setDueBalance(request.getDueBalance());
        salesReceipt.setGrandTotal(request.getGrandTotal());
        salesReceipt.setIdsalesReceipt(request.getIdsalesReceipt());
        salesReceipt.setInPayDate(request.getInPayDate() == null ? null : ConvertUtils.convertStrToDate(request.getInPayDate()));
        salesReceipt.setInvoiceDisc(request.getInvoiceDisc());
        salesReceipt.setInvoiceId(request.getInvoiceId());
        salesReceipt.setLineNo(request.getLineNo());
        salesReceipt.setNetAmount(request.getNetAmount());
        salesReceipt.setPaidAdvanced(request.getPaidAdvanced());
        salesReceipt.setPaymentMethod(request.getPaymentMethod());
        salesReceipt.setRemark(request.getRemark());
        salesReceipt.setReturnAmount(request.getReturnAmount());
        salesReceipt.setSubTotal(request.getSubTotal());
        salesReceipt.setTime(request.getTime());
        SalesReceipt updated = salesReceiptRepository.save(salesReceipt);

        return (convert(updated));
    }

    @Override
    public SalesReceiptResponse getById(Long id) {

        return salesReceiptRepository.findById(id).map(SalesReceiptServiceImpl::convert).orElse(null);
    }

    @Override
    public List<SalesReceiptResponse> getAll() {

        return salesReceiptRepository.findByIsDeleted(Deleted.NO)
                .stream().map(SalesReceiptServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        SalesReceipt got = salesReceiptRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        salesReceiptRepository.save(got);

        return 1;
    }
}