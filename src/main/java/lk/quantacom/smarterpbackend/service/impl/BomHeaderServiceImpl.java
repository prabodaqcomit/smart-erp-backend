package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.BomDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.BomHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.BomHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.BomDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.BomHeaderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BomDetailsRepository;
import lk.quantacom.smarterpbackend.repository.BomHeaderRepository;
import lk.quantacom.smarterpbackend.repository.ItemMasterRepository;
import lk.quantacom.smarterpbackend.service.BomHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BomHeaderServiceImpl implements BomHeaderService {

    @Autowired
    private BomHeaderRepository bomHeaderRepository;


    @Autowired
    private BomDetailsRepository bomDetailsRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public BomHeaderResponse save(BomHeaderRequest request) {


        String itemCode="1";
        if(request.getBomItemCode().trim().isEmpty()){
            String max=itemMasterRepository.getMaxId();
            if(isInteger(max)){
                int newId=Integer.parseInt(max)+1;
                itemCode=newId+"";
            }else{

                itemCode=max+"-1";
            }
        }else{
            itemCode=request.getBomItemCode();
        }

        BomHeader bomHeader = new BomHeader();
        bomHeader.setBomItemCode(itemCode);
        bomHeader.setBomDescription(request.getBomDescription());
        bomHeader.setUnit(request.getUnit());
        bomHeader.setMarkup(request.getMarkup());
        bomHeader.setOnCost(request.getOnCost());
        bomHeader.setCostPriceDining(request.getCostPriceDining());
        bomHeader.setCostPriceTakeAway(request.getCostPriceTakeAway());
        bomHeader.setSellPrice(request.getSellPrice());
        bomHeader.setIsDeleted(Deleted.NO);
        bomHeader.setIsBOT(request.getIsBOT());
        bomHeader.setIsKOT(request.getIsKOT());
        bomHeader.setCategoryId(request.getCategoryId());
        bomHeader.setBranchId(request.getBranchId());
        BomHeader save = bomHeaderRepository.save(bomHeader);

        if (request.getBomDetails() != null) {

            for (BomDetailsRequest bomD : request.getBomDetails()) {

                BomDetails bomDetails = new BomDetails();
                bomDetails.setSubItemCode(bomD.getSubItemCode());
                bomDetails.setBatchNo(bomD.getBatchNo());
                bomDetails.setUnitCost(bomD.getUnitCost());
                bomDetails.setUnit(bomD.getUnit());
                bomDetails.setQuantity(bomD.getQuantity());
                bomDetails.setCost(bomD.getCost());
                bomDetails.setInvType(bomD.getInvType());
                bomDetails.setIsDeleted(Deleted.NO);
                bomDetails.setHeaderId(save.getId());
                bomDetailsRepository.save(bomDetails);
            }
        }

        // item

        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setItemCode(itemCode);
        itemMaster.setItemName(request.getBomDescription());
        itemMaster.setGenaricName(request.getBomDescription());
        itemMaster.setPosDescription(request.getBomDescription());
        itemMaster.setBarcode("");
        itemMaster.setBrand("");
        itemMaster.setStrenth("");

        Category category = new Category();
        category.setId(request.getCategoryId());
        itemMaster.setCategory(category);

        UnitRef unitRef = new UnitRef();
        unitRef.setId(Long.parseLong(request.getUnit()));
        itemMaster.setUnit(unitRef);

        itemMaster.setBuyingPrice(request.getCostPriceDining());
        itemMaster.setPackSize(0.0);
        itemMaster.setWholesaleSellPrice(0.0);
        itemMaster.setRetailSellPrice(request.getSellPrice());
        itemMaster.setUnitPriceWholesale(0.0);
        itemMaster.setUnitPriceRetail(request.getSellPrice());
        itemMaster.setRackNo("");
        itemMaster.setMinStock(0.0);
        itemMaster.setMaxStock(0.0);
        itemMaster.setItemImage("");
        itemMaster.setRegistrationCode("");
        itemMaster.setNoOfUnits(0.0);
        itemMaster.setUnitPrice(request.getSellPrice());
        itemMaster.setWastgValue(0.0);
        itemMaster.setIsKOT(request.getIsKOT());
        itemMaster.setIsBOT(request.getIsBOT());

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        itemMaster.setBranch(branchNetwork);

        itemMaster.setIsWeightedItem(false);
        itemMaster.setIsActive(true);
        itemMaster.setIsMaterial(false);
        itemMaster.setIsMainMaterial(false);
        itemMaster.setTaxClass(0.0);
        itemMaster.setIsDeleted(Deleted.NO);
       itemMasterRepository.save(itemMaster);

        return convert(save);
    }

    @Override
    @Transactional
    public BomHeaderResponse update(BomHeaderUpdateRequest request) {

        BomHeader bomHeader = bomHeaderRepository.findById(request.getId()).orElse(null);
        if (bomHeader == null) {
            return null;
        }

        bomHeader.setId(request.getId());
        bomHeader.setBomItemCode(request.getBomItemCode());
        bomHeader.setBomDescription(request.getBomDescription());
        bomHeader.setUnit(request.getUnit());
        bomHeader.setMarkup(request.getMarkup());
        bomHeader.setOnCost(request.getOnCost());
        bomHeader.setCostPriceDining(request.getCostPriceDining());
        bomHeader.setCostPriceTakeAway(request.getCostPriceTakeAway());
        bomHeader.setIsBOT(request.getIsBOT());
        bomHeader.setIsKOT(request.getIsKOT());
        bomHeader.setCategoryId(request.getCategoryId());
        bomHeader.setBranchId(request.getBranchId());
        BomHeader updated = bomHeaderRepository.save(bomHeader);

        return (convert(updated));
    }

    @Override
    public BomHeaderResponse getById(Long id) {

        BomHeader header = bomHeaderRepository.findById(id).orElse(null);
        if (header != null) {
          return  convertAll(header);
        }

        return null;
    }

    @Override
    public List<BomHeaderResponse> getAll() {

        return bomHeaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(BomHeaderServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        BomHeader got = bomHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        bomHeaderRepository.save(got);

        return 1;
    }

    private static BomHeaderResponse convert(BomHeader bomHeader) {

        BomHeaderResponse typeResponse = new BomHeaderResponse();
        typeResponse.setBomItemCode(bomHeader.getBomItemCode());
        typeResponse.setBomDescription(bomHeader.getBomDescription());
        typeResponse.setUnit(bomHeader.getUnit());
        typeResponse.setMarkup(bomHeader.getMarkup());
        typeResponse.setOnCost(bomHeader.getOnCost());
        typeResponse.setCostPriceDining(bomHeader.getCostPriceDining());
        typeResponse.setCostPriceTakeAway(bomHeader.getCostPriceTakeAway());
        typeResponse.setId(bomHeader.getId());
        typeResponse.setCreatedBy(bomHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bomHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(bomHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bomHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(bomHeader.getIsDeleted());
        typeResponse.setIsKOT(bomHeader.getIsKOT());
        typeResponse.setIsBOT(bomHeader.getIsBOT());
        typeResponse.setSellPrice(bomHeader.getSellPrice());

        return typeResponse;
    }

    private BomHeaderResponse convertAll(BomHeader bomHeader) {

        BomHeaderResponse typeResponse = new BomHeaderResponse();
        typeResponse.setBomItemCode(bomHeader.getBomItemCode());
        typeResponse.setBomDescription(bomHeader.getBomDescription());
        typeResponse.setUnit(bomHeader.getUnit());
        typeResponse.setMarkup(bomHeader.getMarkup());
        typeResponse.setOnCost(bomHeader.getOnCost());
        typeResponse.setCostPriceDining(bomHeader.getCostPriceDining());
        typeResponse.setCostPriceTakeAway(bomHeader.getCostPriceTakeAway());
        typeResponse.setId(bomHeader.getId());
        typeResponse.setCreatedBy(bomHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bomHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(bomHeader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bomHeader.getModifiedDateTime()));
        typeResponse.setIsDeleted(bomHeader.getIsDeleted());
        typeResponse.setSellPrice(bomHeader.getSellPrice());
        typeResponse.setCategoryId(bomHeader.getCategoryId());
        typeResponse.setBranchId(bomHeader.getBranchId());
        typeResponse.setIsKOT(bomHeader.getIsKOT());
        typeResponse.setIsBOT(bomHeader.getIsBOT());

        List<BomDetailsResponse> responseList = new ArrayList<>();
        List<BomDetails> details = bomDetailsRepository.findByHeaderIdAndIsDeleted(bomHeader.getId(), Deleted.NO);
        if (details != null) {
            for (BomDetails bomd : details) {
                responseList.add(convertD(bomd));
            }
        }

        typeResponse.setDetailsList(responseList);

        return typeResponse;
    }

    private BomDetailsResponse convertD(BomDetails bomDetails) {

        BomDetailsResponse typeResponse = new BomDetailsResponse();
        typeResponse.setSubItemCode(bomDetails.getSubItemCode());
        typeResponse.setBatchNo(bomDetails.getBatchNo());
        typeResponse.setUnitCost(bomDetails.getUnitCost());
        typeResponse.setUnit(bomDetails.getUnit());
        typeResponse.setQuantity(bomDetails.getQuantity());
        typeResponse.setCost(bomDetails.getCost());
        typeResponse.setInvType(bomDetails.getInvType());
        typeResponse.setId(bomDetails.getId());
        typeResponse.setCreatedBy(bomDetails.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(bomDetails.getCreatedDateTime()));
        typeResponse.setModifiedBy(bomDetails.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(bomDetails.getModifiedDateTime()));
        typeResponse.setIsDeleted(bomDetails.getIsDeleted());

        String temDsc="";
        ItemMaster itemMaster= itemMasterRepository.findById(bomDetails.getSubItemCode()).orElse(null);
        if(itemMaster!=null){
           temDsc=itemMaster.getItemName();
        }
        typeResponse.setItemDesc(temDsc);

        return typeResponse;
    }
}