package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GrnDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnDetailsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnDetailsResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.GrnDetails;
import lk.quantacom.smarterpbackend.entity.GrnHeader;
import lk.quantacom.smarterpbackend.repository.GrnDetailsRepository;
import lk.quantacom.smarterpbackend.service.GrnDetailsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrnDetailsServiceImpl implements GrnDetailsService {

    @Autowired
    private GrnDetailsRepository grnDetailsRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Override
    @Transactional
    public GrnDetailsResponse save(GrnDetailsRequest request) {

        GrnDetails grnDetails = new GrnDetails();
        //grnDetails.setItemCode(request.getItemCode());
        grnDetails.setUnitPrice(request.getUnitPrice());
        grnDetails.setPoNumber(request.getPoNumber());
        grnDetails.setQty(request.getQty());
        grnDetails.setItemPrice(request.getItemPrice());
        grnDetails.setItemDisc(request.getItemDisc());
        grnDetails.setItemDicPrice(request.getItemDicPrice());
        grnDetails.setItemVat(request.getItemVat());
        grnDetails.setItemVatPrice(request.getItemVatPrice());
        grnDetails.setItemValue(request.getItemValue());
        grnDetails.setItemTotal(request.getItemTotal());
        grnDetails.setGrnUnit(request.getGrnUnit());
        grnDetails.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        grnDetails.setGrnCashPrice(request.getGrnCashPrice());
        grnDetails.setGrnCreditPrice(request.getGrnCreditPrice());
        grnDetails.setBarcodeNo(request.getBarcodeNo());
        grnDetails.setItemProfitMargin(request.getItemProfitMargin());
        grnDetails.setItemProfitValue(request.getItemProfitValue());
        grnDetails.setUnitPriceRetail(request.getUnitPriceRetail());
        grnDetails.setUnitPriceWholesale(request.getUnitPriceWholesale());
        GrnHeader grn=new GrnHeader();
        grn.setId(request.getGrnId());
        grnDetails.setGrn(grn);
        grnDetails.setBatchNo(request.getBatchNo());
        grnDetails.setLineNo(request.getLineNo());
        grnDetails.setColorId(request.getColorId());
        grnDetails.setSizeId(request.getSizeId());
        grnDetails.setFitId(request.getFitId());
        BranchNetwork branch=new BranchNetwork();
        branch.setId(request.getBranchId());
        grnDetails.setBranch(branch);
        grnDetails.setIsDeleted(Deleted.NO);
        GrnDetails save = grnDetailsRepository.save(grnDetails);

        saveLog("GrnDetails", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public GrnDetailsResponse update(GrnDetailsUpdateRequest request) {

        GrnDetails grnDetails = grnDetailsRepository.findById(request.getId()).orElse(null);
        if (grnDetails == null) {
            return null;
        }

        grnDetails.setId(request.getId());
        //grnDetails.setItemCode(request.getItemCode());
        grnDetails.setUnitPrice(request.getUnitPrice());
        grnDetails.setQty(request.getQty());
        grnDetails.setItemPrice(request.getItemPrice());
        grnDetails.setItemDisc(request.getItemDisc());
        grnDetails.setItemDicPrice(request.getItemDicPrice());
        grnDetails.setItemVat(request.getItemVat());
        grnDetails.setItemVatPrice(request.getItemVatPrice());
        grnDetails.setItemValue(request.getItemValue());
        grnDetails.setItemTotal(request.getItemTotal());
        grnDetails.setGrnUnit(request.getGrnUnit());
        grnDetails.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        grnDetails.setGrnCashPrice(request.getGrnCashPrice());
        grnDetails.setGrnCreditPrice(request.getGrnCreditPrice());
        grnDetails.setBarcodeNo(request.getBarcodeNo());
        grnDetails.setItemProfitMargin(request.getItemProfitMargin());
        grnDetails.setItemProfitValue(request.getItemProfitValue());
        grnDetails.setUnitPriceRetail(request.getUnitPriceRetail());
        grnDetails.setUnitPriceWholesale(request.getUnitPriceWholesale());
        GrnHeader grn=new GrnHeader();
        grn.setId(request.getGrnId());
        grnDetails.setGrn(grn);
        grnDetails.setBatchNo(request.getBatchNo());
        grnDetails.setLineNo(request.getLineNo());
        grnDetails.setColorId(request.getColorId());
        grnDetails.setSizeId(request.getSizeId());
        grnDetails.setFitId(request.getFitId());
        BranchNetwork branch=new BranchNetwork();
        branch.setId(request.getBranchId());
        grnDetails.setBranch(branch);
        GrnDetails updated = grnDetailsRepository.save(grnDetails);

        saveLog("GrnDetails", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public GrnDetailsResponse getById(Long id) {

        return grnDetailsRepository.findById(id).map(GrnDetailsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GrnDetailsResponse> getAll() {

        return grnDetailsRepository.findAll()
                .stream().map(GrnDetailsServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GrnDetails got = grnDetailsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        grnDetailsRepository.save(got);

        saveLog("GrnDetails", "Data Deleted - " + id);

        return 1;
    }

    private static GrnDetailsResponse convert(GrnDetails grnDetails) {

        GrnDetailsResponse typeResponse = new GrnDetailsResponse();
        //typeResponse.setItemCode(grnDetails.getItemCode());
        typeResponse.setUnitPrice(grnDetails.getUnitPrice());
        typeResponse.setQty(grnDetails.getQty());
        typeResponse.setItemPrice(grnDetails.getItemPrice());
        typeResponse.setItemDisc(grnDetails.getItemDisc());
        typeResponse.setItemDicPrice(grnDetails.getItemDicPrice());
        typeResponse.setItemVat(grnDetails.getItemVat());
        typeResponse.setItemVatPrice(grnDetails.getItemVatPrice());
        typeResponse.setItemValue(grnDetails.getItemValue());
        typeResponse.setItemTotal(grnDetails.getItemTotal());
        typeResponse.setGrnUnit(grnDetails.getGrnUnit());
        typeResponse.setExpireDate(ConvertUtils.convertDateToStr(grnDetails.getExpireDate()));
        typeResponse.setGrnCashPrice(grnDetails.getGrnCashPrice());
        typeResponse.setGrnCreditPrice(grnDetails.getGrnCreditPrice());
        typeResponse.setBarcodeNo(grnDetails.getBarcodeNo());
        typeResponse.setItemProfitMargin(grnDetails.getItemProfitMargin());
        typeResponse.setItemProfitValue(grnDetails.getItemProfitValue());
        typeResponse.setUnitPriceRetail(grnDetails.getUnitPriceRetail());
        typeResponse.setUnitPriceWholesale(grnDetails.getUnitPriceWholesale());
        typeResponse.setGrnId(grnDetails.getGrn().getId());
        typeResponse.setBatchNo(grnDetails.getBatchNo());
        typeResponse.setLineNo(grnDetails.getLineNo());
        typeResponse.setColorId(grnDetails.getColorId());
        typeResponse.setSizeId(grnDetails.getSizeId());
        typeResponse.setFitId(grnDetails.getFitId());
        typeResponse.setBranchId(grnDetails.getBranch().getId());
        typeResponse.setId(grnDetails.getId());
        typeResponse.setCreatedBy(grnDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnDetails.getModifiedBy());
        typeResponse.setIsDeleted(grnDetails.getIsDeleted());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }
}