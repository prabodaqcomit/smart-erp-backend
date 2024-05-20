package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.ItemMasterRequest;
import lk.quantacom.smarterpbackend.dto.request.ItemMasterUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.ItemMasterResponse;
import lk.quantacom.smarterpbackend.dto.response.StockResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.ItemMasterService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.Settings;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ItemMasterServiceImpl implements ItemMasterService {

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private UserHeadRepository userHeadRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private FitRepository fitRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    static UserHead user = null;

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

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
    public ItemMasterResponse save(ItemMasterRequest request) {

        String itemCode="1";
        if(request.getItemCode().trim().isEmpty()){
            String max=itemMasterRepository.getMaxId();
            if(isInteger(max)){
                int newId=Integer.parseInt(max)+1;
                itemCode=newId+"";
            }else{

                itemCode=max+"-1";
            }
        }else{
            itemCode=request.getItemCode();
        }


        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setItemCode(itemCode);
        itemMaster.setItemName(request.getItemName());
        itemMaster.setGenaricName(request.getGenaricName());
        itemMaster.setPosDescription(request.getPosDescription());
        itemMaster.setBarcode(request.getBarcode());
        itemMaster.setBrand(request.getBrand());
        itemMaster.setStrenth(request.getStrenth());

        Category category = new Category();
        category.setId(request.getCategoryId());
        itemMaster.setCategory(category);

        UnitRef unitRef = new UnitRef();
        unitRef.setId(request.getUnitId());
        itemMaster.setUnit(unitRef);

        itemMaster.setBuyingPrice(request.getBuyingPrice());
        itemMaster.setPackSize(request.getPackSize());
        itemMaster.setWholesaleSellPrice(request.getWholesaleSellPrice());
        itemMaster.setRetailSellPrice(request.getRetailSellPrice());
        itemMaster.setUnitPriceWholesale(request.getUnitPriceWholesale());
        itemMaster.setUnitPriceRetail(request.getUnitPriceRetail());
        itemMaster.setRackNo(request.getRackNo());
        itemMaster.setMinStock(request.getMinStock());
        itemMaster.setMaxStock(request.getMaxStock());
        itemMaster.setServiceItem(request.getServiceItem());

        String imageName = "";
        if (!request.getItemImage().isEmpty()) {
            try {

                File fol = new File(Settings.ITEM_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                String[] fArray = request.getItemImage().split(";");

                String fileEx = fArray[0];
                String base64 = fArray[1];

                byte[] data = Base64.decodeBase64(base64);

                imageName = System.currentTimeMillis() + "." + fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.ITEM_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        itemMaster.setItemImage(imageName);
        itemMaster.setRegistrationCode(request.getRegistrationCode());
        itemMaster.setNoOfUnits(request.getNoOfUnits());
        itemMaster.setUnitPrice(request.getUnitPrice());
        itemMaster.setWastgValue(request.getWastgValue());

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        itemMaster.setBranch(branchNetwork);

        itemMaster.setIsWeightedItem(request.getIsWeightedItem());
        itemMaster.setIsActive(request.getIsActive());
        itemMaster.setIsMaterial(request.getIsMaterial());
        itemMaster.setIsMainMaterial(request.getIsMainMaterial());
        itemMaster.setTaxClass(request.getTaxClass());
        itemMaster.setIsDeleted(Deleted.NO);

        itemMaster.setIsKOT(request.getIsKOT());
        itemMaster.setIsBOT(request.getIsBOT());
        itemMaster.setCurrencyId(request.getCurrencyId());

        if(request.getDepartment1Id()!=null){
            Department1 dep1=new Department1();
            dep1.setId(request.getDepartment1Id());
            itemMaster.setDepartment1(dep1);
        }
        if(request.getDepartment2Id()!=null){
            Department2 dep2=new Department2();
            dep2.setId(request.getDepartment2Id());
            itemMaster.setDepartment2(dep2);
        }
        if(request.getDepartment3Id()!=null){
            Department3 dep3=new Department3();
            dep3.setId(request.getDepartment3Id());
            itemMaster.setDepartment3(dep3);
        }
        if(request.getDepartment4Id()!=null){
            Department4 dep4=new Department4();
            dep4.setId(request.getDepartment4Id());
            itemMaster.setDepartment4(dep4);
        }


        ItemMaster save = itemMasterRepository.save(itemMaster);

        saveLog("ItemMaster", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public ItemMasterResponse update(ItemMasterUpdateRequest request) {

        ItemMaster itemMaster = itemMasterRepository.findById(request.getId()).orElse(null);
        if (itemMaster == null) {
            return null;
        }
        System.out.println(request.getBarcode());
        itemMaster.setId(request.getId());
        itemMaster.setItemCode(request.getItemCode());
        itemMaster.setItemName(request.getItemName());
        itemMaster.setGenaricName(request.getGenaricName());
        itemMaster.setPosDescription(request.getPosDescription());
        itemMaster.setBarcode(request.getBarcode());
        itemMaster.setBrand(request.getBrand());
        itemMaster.setStrenth(request.getStrenth());

        Category category = new Category();
        category.setId(request.getCategoryId());
        itemMaster.setCategory(category);

        UnitRef unitRef = new UnitRef();
        unitRef.setId(request.getUnitId());
        itemMaster.setUnit(unitRef);

        itemMaster.setBuyingPrice(request.getBuyingPrice());
        itemMaster.setPackSize(request.getPackSize());
        itemMaster.setWholesaleSellPrice(request.getWholesaleSellPrice());
        itemMaster.setRetailSellPrice(request.getRetailSellPrice());
        itemMaster.setUnitPriceWholesale(request.getUnitPriceWholesale());
        itemMaster.setUnitPriceRetail(request.getUnitPriceRetail());
        itemMaster.setRackNo(request.getRackNo());
        itemMaster.setMinStock(request.getMinStock());
        itemMaster.setMaxStock(request.getMaxStock());
        itemMaster.setIsKOT(request.getIsKOT());
        itemMaster.setIsBOT(request.getIsBOT());
        itemMaster.setServiceItem(request.getServiceItem());

        String imageName = "";
        if (!request.getItemImage().isEmpty()) {
            try {

                File fol = new File(Settings.ITEM_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                String[] fArray = request.getItemImage().split(";");

                String fileEx = fArray[0];
                String base64 = fArray[1];

                byte[] data = Base64.decodeBase64(base64);

                imageName = System.currentTimeMillis() + "." + fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.ITEM_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        itemMaster.setItemImage(imageName);
        itemMaster.setRegistrationCode(request.getRegistrationCode());
        itemMaster.setNoOfUnits(request.getNoOfUnits());
        itemMaster.setUnitPrice(request.getUnitPrice());
        itemMaster.setWastgValue(request.getWastgValue());

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        itemMaster.setBranch(branchNetwork);

        itemMaster.setIsWeightedItem(request.getIsWeightedItem());
        itemMaster.setIsActive(request.getIsActive());
        itemMaster.setIsMaterial(request.getIsMaterial());
        itemMaster.setIsMainMaterial(request.getIsMainMaterial());
        itemMaster.setTaxClass(request.getTaxClass());

        itemMaster.setCurrencyId(request.getCurrencyId());

        if(request.getDepartment1Id()!=null){
            Department1 dep1=new Department1();
            dep1.setId(request.getDepartment1Id());
            itemMaster.setDepartment1(dep1);
        }else{
            itemMaster.setDepartment1(null);
        }
        if(request.getDepartment2Id()!=null){
            Department2 dep2=new Department2();
            dep2.setId(request.getDepartment2Id());
            itemMaster.setDepartment2(dep2);
        }else{
            itemMaster.setDepartment2(null);
        }
        if(request.getDepartment3Id()!=null){
            Department3 dep3=new Department3();
            dep3.setId(request.getDepartment3Id());
            itemMaster.setDepartment3(dep3);
        }else{
            itemMaster.setDepartment3(null);
        }
        if(request.getDepartment4Id()!=null){
            Department4 dep4=new Department4();
            dep4.setId(request.getDepartment4Id());
            itemMaster.setDepartment4(dep4);
        }else{
            itemMaster.setDepartment4(null);
        }

        ItemMaster updated = itemMasterRepository.save(itemMaster);

        saveLog("ItemMaster", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public ItemMasterResponse getById(String id) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String username = userDetails.getUsername();
//        UserHead user = userHeadRepository.findByFldUserId(username);

        ItemMaster itemMaster=itemMasterRepository.findById(id).orElse(null);
        if(itemMaster!=null){
            return convertSingle(itemMaster);
        }
        return null;
    }

    @Override
    public ItemMasterResponse getByItemCode(String code) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        String username = userDetails.getUsername();
//        UserHead user = userHeadRepository.findByFldUserId(username);

        return convertSingle(itemMasterRepository.getById(code));

    }

    @Override
    public List<ItemMasterResponse> getAll() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        user = userHeadRepository.findByFldUserId(username);

        List<ItemMasterResponse> responses = new ArrayList<>();

        List<ItemMaster> masters= itemMasterRepository.findAll();

        for(ItemMaster master:masters){
            responses.add(convert(master));
        }

        return responses;

    }

    @Override
    public List<ItemMasterResponse> getAllAll() {

        List<ItemMasterResponse> responses = new ArrayList<>();

        List<ItemMaster> masters= itemMasterRepository.findAll();

        for(ItemMaster master:masters){
            responses.add(convertExBranch(master));
        }

        return responses;
    }

    @Override
    @Transactional
    public Integer delete(String id) {

        ItemMaster got = itemMasterRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        itemMasterRepository.save(got);

        saveLog("ItemMaster", "Data Deleted - " + id);

        return 1;
    }

    private ItemMasterResponse convert(ItemMaster itemMaster) {

        ItemMasterResponse typeResponse = new ItemMasterResponse();
        typeResponse.setItemCode(itemMaster.getItemCode());
        typeResponse.setItemName(itemMaster.getItemName());
        typeResponse.setGenaricName(itemMaster.getGenaricName());
        typeResponse.setPosDescription(itemMaster.getPosDescription());
        typeResponse.setBarcode(itemMaster.getBarcode());
        typeResponse.setBrand(itemMaster.getBrand());
        typeResponse.setStrenth(itemMaster.getStrenth());
        typeResponse.setCategoryId(itemMaster.getCategory().getId());
        typeResponse.setCategoryName(itemMaster.getCategory().getCategoryName());
        typeResponse.setUnitId(itemMaster.getUnit().getId());
        typeResponse.setUnitLong(itemMaster.getUnit().getUnitLong());
        typeResponse.setUnitShort(itemMaster.getUnit().getUnitShort());
        typeResponse.setBuyingPrice(itemMaster.getBuyingPrice());
        typeResponse.setPackSize(itemMaster.getPackSize());
        typeResponse.setWholesaleSellPrice(itemMaster.getWholesaleSellPrice());
        typeResponse.setRetailSellPrice(itemMaster.getRetailSellPrice());
        typeResponse.setUnitPriceWholesale(itemMaster.getUnitPriceWholesale());
        typeResponse.setUnitPriceRetail(itemMaster.getUnitPriceRetail());
        typeResponse.setRackNo(itemMaster.getRackNo());
        typeResponse.setMinStock(itemMaster.getMinStock());
        typeResponse.setMaxStock(itemMaster.getMaxStock());
        typeResponse.setItemImage(itemMaster.getItemImage());
        typeResponse.setRegistrationCode(itemMaster.getRegistrationCode());
        typeResponse.setNoOfUnits(itemMaster.getNoOfUnits());
        typeResponse.setUnitPrice(itemMaster.getUnitPrice());
        typeResponse.setWastgValue(itemMaster.getWastgValue());
        typeResponse.setBranchId(itemMaster.getBranch().getId());
        typeResponse.setBranchName(itemMaster.getBranch().getBranchName());
        typeResponse.setIsWeightedItem(itemMaster.getIsWeightedItem());
        typeResponse.setIsActive(itemMaster.getIsActive());
        typeResponse.setIsMaterial(itemMaster.getIsMaterial());
        typeResponse.setIsMainMaterial(itemMaster.getIsMainMaterial());
        typeResponse.setTaxClass(itemMaster.getTaxClass());
        typeResponse.setId(itemMaster.getItemCode());
        typeResponse.setCreatedBy(itemMaster.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(itemMaster.getCreatedDateTime()));
        typeResponse.setModifiedBy(itemMaster.getModifiedBy());
        typeResponse.setIsDeleted(itemMaster.getIsDeleted());
        typeResponse.setIsKOT(itemMaster.getIsKOT());
        typeResponse.setIsBOT(itemMaster.getIsBOT());
        typeResponse.setServiceItem(itemMaster.getServiceItem() != null && itemMaster.getServiceItem());
        typeResponse.setCurrencyId(itemMaster.getCurrencyId()==null? 0:itemMaster.getCurrencyId());

        List<StockResponse> stocks = new ArrayList<>();

        if (itemMaster.getStock() != null) {
            if (!itemMaster.getStock().isEmpty()) {

                for (Stock stock : itemMaster.getStock()) {
                    if (Objects.equals(stock.getStockPK().getBranch().getId(), user.getBranch().getId())) {
                        stocks.add(convertStock2(stock));
                    }
                }
            }
        }

        typeResponse.setStockDetails(stocks);

        if(itemMaster.getDepartment1()!=null){
            typeResponse.setDepartment1Id(itemMaster.getDepartment1().getId());
            typeResponse.setDepartment1Name(itemMaster.getDepartment1().getDepartmentName());
        }
        if(itemMaster.getDepartment2()!=null){
            typeResponse.setDepartment2Id(itemMaster.getDepartment2().getId());
            typeResponse.setDepartment2Name(itemMaster.getDepartment2().getDepartmentName());
        }
        if(itemMaster.getDepartment3()!=null){
            typeResponse.setDepartment3Id(itemMaster.getDepartment3().getId());
            typeResponse.setDepartment3Name(itemMaster.getDepartment3().getDepartmentName());
        }
        if(itemMaster.getDepartment4()!=null){
            typeResponse.setDepartment4Id(itemMaster.getDepartment4().getId());
            typeResponse.setDepartment4Name(itemMaster.getDepartment4().getDepartmentName());
        }


        return typeResponse;
    }

    private ItemMasterResponse convertExBranch(ItemMaster itemMaster) {

        ItemMasterResponse typeResponse = new ItemMasterResponse();
        typeResponse.setItemCode(itemMaster.getItemCode());
        typeResponse.setItemName(itemMaster.getItemName());
        typeResponse.setGenaricName(itemMaster.getGenaricName());
        typeResponse.setPosDescription(itemMaster.getPosDescription());
        typeResponse.setBarcode(itemMaster.getBarcode());
        typeResponse.setBrand(itemMaster.getBrand());
        typeResponse.setStrenth(itemMaster.getStrenth());
        typeResponse.setCategoryId(itemMaster.getCategory().getId());
        typeResponse.setCategoryName(itemMaster.getCategory().getCategoryName());
        typeResponse.setUnitId(itemMaster.getUnit().getId());
        typeResponse.setUnitLong(itemMaster.getUnit().getUnitLong());
        typeResponse.setUnitShort(itemMaster.getUnit().getUnitShort());
        typeResponse.setBuyingPrice(itemMaster.getBuyingPrice());
        typeResponse.setPackSize(itemMaster.getPackSize());
        typeResponse.setWholesaleSellPrice(itemMaster.getWholesaleSellPrice());
        typeResponse.setRetailSellPrice(itemMaster.getRetailSellPrice());
        typeResponse.setUnitPriceWholesale(itemMaster.getUnitPriceWholesale());
        typeResponse.setUnitPriceRetail(itemMaster.getUnitPriceRetail());
        typeResponse.setRackNo(itemMaster.getRackNo());
        typeResponse.setMinStock(itemMaster.getMinStock());
        typeResponse.setMaxStock(itemMaster.getMaxStock());
        typeResponse.setItemImage(itemMaster.getItemImage());
        typeResponse.setRegistrationCode(itemMaster.getRegistrationCode());
        typeResponse.setNoOfUnits(itemMaster.getNoOfUnits());
        typeResponse.setUnitPrice(itemMaster.getUnitPrice());
        typeResponse.setWastgValue(itemMaster.getWastgValue());
        typeResponse.setBranchId(itemMaster.getBranch().getId());
        typeResponse.setBranchName(itemMaster.getBranch().getBranchName());
        typeResponse.setIsWeightedItem(itemMaster.getIsWeightedItem());
        typeResponse.setIsActive(itemMaster.getIsActive());
        typeResponse.setIsMaterial(itemMaster.getIsMaterial());
        typeResponse.setIsMainMaterial(itemMaster.getIsMainMaterial());
        typeResponse.setTaxClass(itemMaster.getTaxClass());
        typeResponse.setId(itemMaster.getItemCode());
        typeResponse.setCreatedBy(itemMaster.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(itemMaster.getCreatedDateTime()));
        typeResponse.setModifiedBy(itemMaster.getModifiedBy());
        typeResponse.setIsDeleted(itemMaster.getIsDeleted());
        typeResponse.setIsKOT(itemMaster.getIsKOT());
        typeResponse.setIsBOT(itemMaster.getIsBOT());
        typeResponse.setServiceItem(itemMaster.getServiceItem() != null && itemMaster.getServiceItem());
        typeResponse.setCurrencyId(itemMaster.getCurrencyId()==null? 0:itemMaster.getCurrencyId());

        List<StockResponse> stocks = new ArrayList<>();

        if (itemMaster.getStock() != null) {
            if (!itemMaster.getStock().isEmpty()) {
                for (Stock stock : itemMaster.getStock()) {
                    //if (stock.getBranch().getId() == user.getBranch().getId()) {
                    stocks.add(convertStock2(stock));
                    // }
                }
            }
        }

        typeResponse.setStockDetails(stocks);

        if(itemMaster.getDepartment1()!=null){
            typeResponse.setDepartment1Id(itemMaster.getDepartment1().getId());
            typeResponse.setDepartment1Name(itemMaster.getDepartment1().getDepartmentName());
        }
        if(itemMaster.getDepartment2()!=null){
            typeResponse.setDepartment2Id(itemMaster.getDepartment2().getId());
            typeResponse.setDepartment2Name(itemMaster.getDepartment2().getDepartmentName());
        }
        if(itemMaster.getDepartment3()!=null){
            typeResponse.setDepartment3Id(itemMaster.getDepartment3().getId());
            typeResponse.setDepartment3Name(itemMaster.getDepartment3().getDepartmentName());
        }
        if(itemMaster.getDepartment4()!=null){
            typeResponse.setDepartment4Id(itemMaster.getDepartment4().getId());
            typeResponse.setDepartment4Name(itemMaster.getDepartment4().getDepartmentName());
        }

        return typeResponse;
    }

    private ItemMasterResponse convertSingle(ItemMaster itemMaster) {

        ItemMasterResponse typeResponse = new ItemMasterResponse();
        typeResponse.setItemCode(itemMaster.getItemCode());
        typeResponse.setItemName(itemMaster.getItemName());
        typeResponse.setGenaricName(itemMaster.getGenaricName());
        typeResponse.setPosDescription(itemMaster.getPosDescription());
        typeResponse.setBarcode(itemMaster.getBarcode());
        typeResponse.setBrand(itemMaster.getBrand());
        typeResponse.setStrenth(itemMaster.getStrenth());
        typeResponse.setCategoryId(itemMaster.getCategory().getId());
        typeResponse.setCategoryName(itemMaster.getCategory().getCategoryName());
        typeResponse.setUnitId(itemMaster.getUnit().getId());
        typeResponse.setUnitLong(itemMaster.getUnit().getUnitLong());
        typeResponse.setUnitShort(itemMaster.getUnit().getUnitShort());
        typeResponse.setBuyingPrice(itemMaster.getBuyingPrice());
        typeResponse.setPackSize(itemMaster.getPackSize());
        typeResponse.setWholesaleSellPrice(itemMaster.getWholesaleSellPrice());
        typeResponse.setRetailSellPrice(itemMaster.getRetailSellPrice());
        typeResponse.setUnitPriceWholesale(itemMaster.getUnitPriceWholesale());
        typeResponse.setUnitPriceRetail(itemMaster.getUnitPriceRetail());
        typeResponse.setRackNo(itemMaster.getRackNo());
        typeResponse.setMinStock(itemMaster.getMinStock());
        typeResponse.setMaxStock(itemMaster.getMaxStock());
        typeResponse.setServiceItem(itemMaster.getServiceItem() != null && itemMaster.getServiceItem());

        String base64 = "";
        if (!itemMaster.getItemImage().isEmpty()) {
            File img = new File(Settings.ITEM_IMAGES + itemMaster.getItemImage());
            if (img.exists()) {
                base64 = encodeFileToBase64Binary(img);
            }
        }
        typeResponse.setItemImage(base64);
        typeResponse.setRegistrationCode(itemMaster.getRegistrationCode());
        typeResponse.setNoOfUnits(itemMaster.getNoOfUnits());
        typeResponse.setUnitPrice(itemMaster.getUnitPrice());
        typeResponse.setWastgValue(itemMaster.getWastgValue());
        typeResponse.setBranchId(itemMaster.getBranch().getId());
        typeResponse.setBranchName(itemMaster.getBranch().getBranchName());
        typeResponse.setIsWeightedItem(itemMaster.getIsWeightedItem());
        typeResponse.setIsActive(itemMaster.getIsActive());
        typeResponse.setIsMaterial(itemMaster.getIsMaterial());
        typeResponse.setIsMainMaterial(itemMaster.getIsMainMaterial());
        typeResponse.setTaxClass(itemMaster.getTaxClass());
        typeResponse.setId(itemMaster.getItemCode());
        typeResponse.setCreatedBy(itemMaster.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(itemMaster.getCreatedDateTime()));
        typeResponse.setModifiedBy(itemMaster.getModifiedBy());
        typeResponse.setIsDeleted(itemMaster.getIsDeleted());
        typeResponse.setCurrencyId(itemMaster.getCurrencyId()==null? 0:itemMaster.getCurrencyId());

        List<StockResponse> stocks = new ArrayList<>();

        if (itemMaster.getStock() != null) {
            if (!itemMaster.getStock().isEmpty()) {
                for (Stock stock : itemMaster.getStock()) {
                    //           if (stock.getBranch().getId() == user.getBranch().getId()) {
                    stocks.add(convertStock2(stock));
                    //          }
                }
            }
        }

        typeResponse.setStockDetails(stocks);
        if(itemMaster.getDepartment1()!=null){
            typeResponse.setDepartment1Id(itemMaster.getDepartment1().getId());
            typeResponse.setDepartment1Name(itemMaster.getDepartment1().getDepartmentName());
        }
        if(itemMaster.getDepartment2()!=null){
            typeResponse.setDepartment2Id(itemMaster.getDepartment2().getId());
            typeResponse.setDepartment2Name(itemMaster.getDepartment2().getDepartmentName());
        }
        if(itemMaster.getDepartment3()!=null){
            typeResponse.setDepartment3Id(itemMaster.getDepartment3().getId());
            typeResponse.setDepartment3Name(itemMaster.getDepartment3().getDepartmentName());
        }
        if(itemMaster.getDepartment4()!=null){
            typeResponse.setDepartment4Id(itemMaster.getDepartment4().getId());
            typeResponse.setDepartment4Name(itemMaster.getDepartment4().getDepartmentName());
        }

        return typeResponse;
    }

//    private static StockResponse convertStock(Stock stock) {
//
//        StockResponse typeResponse = new StockResponse();
//        typeResponse.setStkQty(stock.getStkQty());
//        typeResponse.setDamgQty(stock.getDamgQty());
//        typeResponse.setAvailabQty(stock.getAvailabQty());
//        typeResponse.setAvrgPrice(stock.getAvrgPrice());
//        typeResponse.setExpireDate(ConvertUtils.convertDateToStr(stock.getExpireDate()));
//        typeResponse.setStkCashPrice(stock.getStkCashPrice());
//        typeResponse.setStkCreditPrice(stock.getStkCreditPrice());
//        typeResponse.setStoresQty(stock.getStoresQty());
//        typeResponse.setTotalQty(stock.getTotalQty());
//        typeResponse.setBarcodeNo(stock.getBarcodeNo());
//        typeResponse.setStockUnitPriceRetail(stock.getStockUnitPriceRetail());
//        typeResponse.setStockUnitPriceWholesale(stock.getStockUnitPriceWholesale());
//        typeResponse.setSupplierId(stock.getSupplier().getId());
//        typeResponse.setSalesPerson(stock.getSupplier().getSalesPerson());
//        typeResponse.setAgencyName(stock.getSupplier().getAgencyName());
//        typeResponse.setObStock(stock.getObStock());
//        typeResponse.setCashDisValue(stock.getCashDisValue());
//        typeResponse.setCreditDisValue(stock.getCreditDisValue());
//        typeResponse.setSalesDiscoPresentage(stock.getSalesDiscoPresentage());
//        typeResponse.setMaterialWidth(stock.getMaterialWidth());
//        typeResponse.setItemImage(stock.getStockPK().getItem().getItemImage());
//        typeResponse.setItemId(stock.getStockPK().getItem().getItemCode());
//        typeResponse.setItemCode(stock.getStockPK().getItem().getItemCode());
//        typeResponse.setItemName(stock.getStockPK().getItem().getItemName());
//        typeResponse.setColorId(stock.getStockPK().getColor()==null? 0:stock.getStockPK().getColor());
//        //typeResponse.setColorDesc(stock.getStockPK().getColor()==null? "":stock.getStockPK().getColor().getColorDesc());
//        typeResponse.setSizeId(stock.getStockPK().getSize()==null? 0:stock.getStockPK().getSize());
//        //typeResponse.setSizeDesc(stock.getStockPK().getSize()==null? "":stock.getStockPK().getSize().getSizeDesc());
//        typeResponse.setFitId(stock.getStockPK().getFit()==null? 0:stock.getStockPK().getFit());
//        //typeResponse.setFitDesc(stock.getStockPK().getFit()==null? "":stock.getStockPK().getFit().getFitDesc());
//        typeResponse.setBranchId(stock.getStockPK().getBranch().getId());
//        typeResponse.setBatchNo(stock.getStockPK().getBatchNo());
//        //typeResponse.setId(stock.getId());
//        typeResponse.setCreatedBy(stock.getCreatedBy());
//        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stock.getCreatedDateTime()));
//        typeResponse.setModifiedBy(stock.getModifiedBy());
//        typeResponse.setIsDeleted(stock.getIsDeleted());
//
//        return typeResponse;
//    }
//

    private StockResponse convertStock2(Stock stock) {

        StockResponse typeResponse = new StockResponse();
        typeResponse.setStkQty(stock.getStkQty());
        typeResponse.setDamgQty(stock.getDamgQty());
        typeResponse.setAvailabQty(stock.getAvailabQty());
        typeResponse.setAvrgPrice(stock.getAvrgPrice());
        typeResponse.setExpireDate(ConvertUtils.convertDateToStr(stock.getExpireDate()));
        typeResponse.setStkCashPrice(stock.getStkCashPrice());
        typeResponse.setStkCreditPrice(stock.getStkCreditPrice());
        typeResponse.setStoresQty(stock.getStoresQty());
        typeResponse.setTotalQty(stock.getTotalQty());
        typeResponse.setBarcodeNo(stock.getBarcodeNo());
        typeResponse.setStockUnitPriceRetail(stock.getStockUnitPriceRetail());
        typeResponse.setStockUnitPriceWholesale(stock.getStockUnitPriceWholesale());
        typeResponse.setSupplierId(stock.getSupplier().getId());
        typeResponse.setSalesPerson(stock.getSupplier().getSalesPerson());
        typeResponse.setAgencyName(stock.getSupplier().getAgencyName());
        typeResponse.setObStock(stock.getObStock());
        typeResponse.setCashDisValue(stock.getCashDisValue());
        typeResponse.setCreditDisValue(stock.getCreditDisValue());
        typeResponse.setSalesDiscoPresentage(stock.getSalesDiscoPresentage());
        typeResponse.setMaterialWidth(stock.getMaterialWidth());
        typeResponse.setItemImage(stock.getStockPK().getItem().getItemImage());
        typeResponse.setItemId(stock.getStockPK().getItem().getItemCode());
        typeResponse.setItemCode(stock.getStockPK().getItem().getItemCode());
        typeResponse.setItemName(stock.getStockPK().getItem().getItemName());
        typeResponse.setColorId(stock.getStockPK().getColor()==null? 0:stock.getStockPK().getColor());
        typeResponse.setColorDesc((stock.getStockPK().getColor()==null||stock.getStockPK().getColor()==0)?
                "":colorRepository.getById(stock.getStockPK().getColor()).getColorDesc());
        typeResponse.setSizeId((stock.getStockPK().getSize()==null)? 0:stock.getStockPK().getSize());
        typeResponse.setSizeDesc((stock.getStockPK().getSize()==null||stock.getStockPK().getSize()==0)?
                "":sizeRepository.getById(stock.getStockPK().getSize()).getSizeDesc());
        typeResponse.setFitId(stock.getStockPK().getFit()==null? 0:stock.getStockPK().getFit());
        typeResponse.setFitDesc((stock.getStockPK().getFit()==null || stock.getStockPK().getFit()==0)?
                "":fitRepository.getById(stock.getStockPK().getFit()).getFitDesc());
        typeResponse.setBranchId(stock.getStockPK().getBranch().getId());
        typeResponse.setBatchNo(stock.getStockPK().getBatchNo());
        //typeResponse.setId(stock.getId());
        typeResponse.setCreatedBy(stock.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stock.getCreatedDateTime()));
        typeResponse.setModifiedBy(stock.getModifiedBy());
        typeResponse.setIsDeleted(stock.getIsDeleted());

        return typeResponse;
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }

}