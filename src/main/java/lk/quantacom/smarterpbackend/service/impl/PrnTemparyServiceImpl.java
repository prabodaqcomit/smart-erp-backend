package lk.quantacom.smarterpbackend.service.impl;


import lk.quantacom.smarterpbackend.dto.request.PrnTemparyRequest;
import lk.quantacom.smarterpbackend.dto.request.PrnTemparyUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PrnTemparyResponse;
import lk.quantacom.smarterpbackend.dto.response.PurchaseOrderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.PrnTemparyService;
import lk.quantacom.smarterpbackend.service.PurchaseOrderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrnTemparyServiceImpl implements PrnTemparyService {

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private PrnTemparyRepository prnTemparyRepository;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FitRepository fitRepository;

    @Autowired
    private SizeRepository sizeRepository;

    private static PrnTemparyResponse convert(PrnTempary prnTempary) {

        PrnTemparyResponse typeResponse = new PrnTemparyResponse();
        typeResponse.setPrnTemparyId(prnTempary.getPrnTemparyId());
        typeResponse.setPrnDate(ConvertUtils.convertDateToStr(prnTempary.getPrnDate()));
        typeResponse.setSupInDate(ConvertUtils.convertDateToStr(prnTempary.getSupInDate()));
        typeResponse.setSupInNo(prnTempary.getSupInNo());
        typeResponse.setItemCode(prnTempary.getItem().getItemCode());
        typeResponse.setUnitPrice(prnTempary.getUnitPrice());
        typeResponse.setQty(prnTempary.getQty());
        typeResponse.setItemPrice(prnTempary.getItemPrice());
        typeResponse.setItemDisc(prnTempary.getItemDisc());
        typeResponse.setItemDicPrice(prnTempary.getItemDicPrice());
        typeResponse.setItemVat(prnTempary.getItemVat());
        typeResponse.setItemVatPrice(prnTempary.getItemVatPrice());
        typeResponse.setItemValue(prnTempary.getItemValue());
        typeResponse.setItemTotal(prnTempary.getItemTotal());
        typeResponse.setGrossAmount(prnTempary.getGrossAmount());
        typeResponse.setTotalDis(prnTempary.getTotalDis());
        typeResponse.setTotalVat(prnTempary.getTotalVat());
        typeResponse.setNetAmount(prnTempary.getNetAmount());
        typeResponse.setPayMode(prnTempary.getPayMode());
        typeResponse.setPayingAmount(prnTempary.getPayingAmount());
        typeResponse.setDueAmount(prnTempary.getDueAmount());
        typeResponse.setSupplierId(prnTempary.getSupplierId());
        typeResponse.setItemName(prnTempary.getItemName());
        typeResponse.setRemarks(prnTempary.getRemarks());
        typeResponse.setImage(prnTempary.getImage());
        typeResponse.setPrnUnit(prnTempary.getPrnUnit());
        typeResponse.setBatchNo(prnTempary.getBatchNo());
        typeResponse.setExpireDate(ConvertUtils.convertDateToStr(prnTempary.getExpireDate()));
        typeResponse.setPrnCashPrice(prnTempary.getPrnCashPrice());
        typeResponse.setPrnCreditPrice(prnTempary.getPrnCreditPrice());
        typeResponse.setItemTime(ConvertUtils.convertDateToStr(prnTempary.getItemTime()));
        typeResponse.setPrnOverpaid(prnTempary.getPrnOverpaid());
        typeResponse.setPurchesOrderId(prnTempary.getPurchesOrderId());
        typeResponse.setBarcodeNo(prnTempary.getBarcodeNo());
        typeResponse.setSalesPerson(prnTempary.getSalesPerson());
        typeResponse.setSalesPersonTel(prnTempary.getSalesPersonTel());
        typeResponse.setPrnAgencyName(prnTempary.getPrnAgencyName());
        typeResponse.setBranchId(prnTempary.getBranch().getBranchId());
        typeResponse.setProfitMarginItem(prnTempary.getProfitMarginItem());
        typeResponse.setProfitValueItem(prnTempary.getProfitValueItem());
        typeResponse.setProfitValueTotal(prnTempary.getProfitValueTotal());
        typeResponse.setColorId(prnTempary.getColor()==null? 0:prnTempary.getColor().getId());
        typeResponse.setSizeId(prnTempary.getSize()==null? 0:prnTempary.getSize().getId());
        typeResponse.setFitId(prnTempary.getFit()==null? 0:prnTempary.getFit().getId());
        typeResponse.setWidth(prnTempary.getWidth());
        typeResponse.setIsProcess(prnTempary.getIsProcess());
        typeResponse.setUnitPriceRetail(prnTempary.getUnitPriceRetail());
        typeResponse.setUnitPriceWholesale(prnTempary.getUnitPriceWholesale());
        typeResponse.setPrnTemparyId(prnTempary.getPrnTemparyId());
        typeResponse.setCreatedBy(prnTempary.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(prnTempary.getCreatedDateTime()));
        typeResponse.setModifiedBy(prnTempary.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(prnTempary.getModifiedDateTime()));
        typeResponse.setIsDeleted(prnTempary.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public List<PrnTemparyResponse> save(List<PrnTemparyRequest> requestList) {

        Integer max = prnTemparyRepository.getMaxId();
        if (max == null) {
            max = 1;
        } else {
            max = max + 1;
        }
        List<PrnTemparyResponse> responses = new ArrayList<>();

        for (PrnTemparyRequest request : requestList) {


            PrnTempary prnTempary = new PrnTempary();
            prnTempary.setPrnTemparyId(max);
            prnTempary.setPrnDate(request.getPrnDate() == null ? null : ConvertUtils.convertStrToDate(request.getPrnDate()));
            prnTempary.setSupInDate(request.getSupInDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInDate()));
            prnTempary.setSupInNo(request.getSupInNo());

            ItemMaster item = new ItemMaster();
            item.setId(request.getItemId());
            prnTempary.setItem(item);

            prnTempary.setUnitPrice(request.getUnitPrice());
            prnTempary.setQty(request.getQty());
            prnTempary.setItemPrice(request.getItemPrice());
            prnTempary.setItemDisc(request.getItemDisc());
            prnTempary.setItemDicPrice(request.getItemDicPrice());
            prnTempary.setItemVat(request.getItemVat());
            prnTempary.setItemVatPrice(request.getItemVatPrice());
            prnTempary.setItemValue(request.getItemValue());
            prnTempary.setItemTotal(request.getItemTotal());
            prnTempary.setGrossAmount(request.getGrossAmount());
            prnTempary.setTotalDis(request.getTotalDis());
            prnTempary.setTotalVat(request.getTotalVat());
            prnTempary.setNetAmount(request.getNetAmount());
            prnTempary.setPayMode(request.getPayMode());
            prnTempary.setPayingAmount(request.getPayingAmount());
            prnTempary.setDueAmount(request.getDueAmount());
            prnTempary.setSupplierId(request.getSupplierId());
            prnTempary.setItemName(request.getItemName());
            prnTempary.setRemarks(request.getRemarks());
            prnTempary.setImage(request.getImage());
            prnTempary.setPrnUnit(request.getPrnUnit());
            prnTempary.setBatchNo(request.getBatchNo());
            prnTempary.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
            prnTempary.setPrnCashPrice(request.getPrnCashPrice());
            prnTempary.setPrnCreditPrice(request.getPrnCreditPrice());
            prnTempary.setItemTime(ConvertUtils.convertStrToDate(request.getItemTime()));
            prnTempary.setPrnOverpaid(0.00);
            prnTempary.setPurchesOrderId(request.getPurchesOrderId());
            prnTempary.setBarcodeNo(request.getBarcodeNo());
            prnTempary.setSalesPerson(request.getSalesPerson());
            prnTempary.setSalesPersonTel(request.getSalesPersonTel());
            prnTempary.setPrnAgencyName(request.getPrnAgencyName());

            BranchNetwork branch = new BranchNetwork();
            branch.setId(request.getBranchId());
            prnTempary.setBranch(branch);

            prnTempary.setProfitMarginItem(request.getProfitMarginItem());
            prnTempary.setProfitValueItem(request.getProfitValueItem());
            prnTempary.setProfitValueTotal(request.getProfitValueTotal());

//        prnTempary.setColor(new Color(request.getColorId()));
//        prnTempary.setSize(new Size(request.getSizeId()));
//        prnTempary.setFit(new Fit(request.getFitId()));

            prnTempary.setColor(request.getColorId() == 0 ? null : colorRepository.getById(request.getColorId()));
            prnTempary.setSize(request.getSizeId() == 0 ? null : sizeRepository.getById(request.getSizeId()));
            prnTempary.setFit(request.getFitId() == 0 ? null : fitRepository.getById(request.getFitId()));

            prnTempary.setWidth(request.getWidth());
            prnTempary.setIsProcess(false);
            prnTempary.setUnitPriceRetail(request.getUnitPriceRetail());
            prnTempary.setUnitPriceWholesale(request.getUnitPriceWholesale());
            prnTempary.setIsDeleted(Deleted.NO);

            PrnTempary save = prnTemparyRepository.save(prnTempary);



            responses.add(convert(save));

        }

        if (requestList.get(0).getPurchesOrderId() != null) {
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(requestList.get(0).getPurchesOrderId()).orElse(null);
            purchaseOrder.setIsProcess(true);
            purchaseOrder.setStatus("APPROVED");
            purchaseOrderRepository.save(purchaseOrder);
        }

        saveLog("PrnTempary", "Data Saved - Bulk");

        return responses;
    }

    @Override
    @Transactional
    public PrnTemparyResponse update(PrnTemparyUpdateRequest request) {

        PrnTempary prnTempary = prnTemparyRepository.findById(request.getPrnTemparyId()).orElse(null);
        if (prnTempary == null) {
            return null;
        }

        prnTempary.setPrnTemparyId(request.getPrnTemparyId());
        prnTempary.setPrnTemparyId(request.getPrnTemparyId());
        prnTempary.setPrnDate(request.getPrnDate() == null ? null : ConvertUtils.convertStrToDate(request.getPrnDate()));
        prnTempary.setSupInDate(request.getSupInDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInDate()));
        prnTempary.setSupInNo(request.getSupInNo());

        ItemMaster item = new ItemMaster();
        item.setId(request.getItemId());
        prnTempary.setItem(item);

        prnTempary.setUnitPrice(request.getUnitPrice());
        prnTempary.setQty(request.getQty());
        prnTempary.setItemPrice(request.getItemPrice());
        prnTempary.setItemDisc(request.getItemDisc());
        prnTempary.setItemDicPrice(request.getItemDicPrice());
        prnTempary.setItemVat(request.getItemVat());
        prnTempary.setItemVatPrice(request.getItemVatPrice());
        prnTempary.setItemValue(request.getItemValue());
        prnTempary.setItemTotal(request.getItemTotal());
        prnTempary.setGrossAmount(request.getGrossAmount());
        prnTempary.setTotalDis(request.getTotalDis());
        prnTempary.setTotalVat(request.getTotalVat());
        prnTempary.setNetAmount(request.getNetAmount());
        prnTempary.setPayMode(request.getPayMode());
        prnTempary.setPayingAmount(request.getPayingAmount());
        prnTempary.setDueAmount(request.getDueAmount());
        prnTempary.setSupplierId(request.getSupplierId());
        prnTempary.setItemName(request.getItemName());
        prnTempary.setRemarks(request.getRemarks());
        prnTempary.setImage(request.getImage());
        prnTempary.setPrnUnit(request.getPrnUnit());
        prnTempary.setBatchNo(request.getBatchNo());
        prnTempary.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        prnTempary.setPrnCashPrice(request.getPrnCashPrice());
        prnTempary.setPrnCreditPrice(request.getPrnCreditPrice());
        prnTempary.setItemTime(ConvertUtils.convertStrToDate(request.getItemTime()));
        prnTempary.setPrnOverpaid(request.getPrnOverpaid());
        prnTempary.setPurchesOrderId(request.getPurchesOrderId());
        prnTempary.setBarcodeNo(request.getBarcodeNo());
        prnTempary.setSalesPerson(request.getSalesPerson());
        prnTempary.setSalesPersonTel(request.getSalesPersonTel());
        prnTempary.setPrnAgencyName(request.getPrnAgencyName());

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        prnTempary.setBranch(branch);

        prnTempary.setProfitMarginItem(request.getProfitMarginItem());
        prnTempary.setProfitValueItem(request.getProfitValueItem());
        prnTempary.setProfitValueTotal(request.getProfitValueTotal());
        prnTempary.setColor(new Color(request.getColorId()));
        prnTempary.setSize(new Size(request.getSizeId()));
        prnTempary.setFit(new Fit(request.getFitId()));
        prnTempary.setWidth(request.getWidth());
        prnTempary.setIsProcess(request.getIsProcess());
        prnTempary.setUnitPriceRetail(request.getUnitPriceRetail());
        prnTempary.setUnitPriceWholesale(request.getUnitPriceWholesale());
        PrnTempary updated = prnTemparyRepository.save(prnTempary);

        return (convert(updated));
    }

    @Override
    public PrnTemparyResponse getById(Integer id) {

        return prnTemparyRepository.findById(id).map(PrnTemparyServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PrnTemparyResponse> getAll() {

        return prnTemparyRepository.findByIsDeleted(Deleted.NO)
                .stream().map(PrnTemparyServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        PrnTempary got = prnTemparyRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        prnTemparyRepository.save(got);

        return 1;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}