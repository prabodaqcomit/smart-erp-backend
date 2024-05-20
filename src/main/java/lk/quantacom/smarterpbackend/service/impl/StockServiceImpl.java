package lk.quantacom.smarterpbackend.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.StockService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private PriceChangeLogRepository priceChangeLogRepository;

    @Autowired
    private StockTransferLogRepository stockTransferLogRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FitRepository fitRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private OpeningBalanceRepository openingBalanceRepository;

    private static StockResponse convert(Stock stock) {

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
        typeResponse.setItemId(stock.getStockPK().getItem().getId());
        typeResponse.setItemCode(stock.getStockPK().getItem().getItemCode());
        typeResponse.setItemName(stock.getStockPK().getItem().getItemName());
        typeResponse.setColorId(stock.getStockPK().getColor() == null ? 0 : stock.getStockPK().getColor());
        //typeResponse.setColorDesc(stock.getStockPK().getColor()==null? "":stock.getStockPK().getColor().getColorDesc());
        typeResponse.setSizeId(stock.getStockPK().getSize() == null ? 0 : stock.getStockPK().getSize());
        //typeResponse.setSizeDesc(stock.getStockPK().getSize()==null? "":stock.getStockPK().getSize().getSizeDesc());
        typeResponse.setFitId(stock.getStockPK().getFit() == null ? 0 : stock.getStockPK().getFit());
        //typeResponse.setFitDesc(stock.getStockPK().getFit()==null? "":stock.getStockPK().getFit().getFitDesc());
        typeResponse.setBranchId(stock.getStockPK().getBranch().getId());
        typeResponse.setBatchNo(stock.getStockPK().getBatchNo());
        //typeResponse.setId(stock.getId());
        typeResponse.setCreatedBy(stock.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stock.getCreatedDateTime()));
        typeResponse.setModifiedBy(stock.getModifiedBy());
        typeResponse.setIsDeleted(stock.getIsDeleted());

        return typeResponse;
    }

    private static StockFullResponse convert2(Stock stock) {

        StockFullResponse typeResponse = new StockFullResponse();
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
        typeResponse.setItemId(stock.getStockPK().getItem().getId());
        typeResponse.setItemCode(stock.getStockPK().getItem().getItemCode());
        typeResponse.setItemName(stock.getStockPK().getItem().getItemName());
        typeResponse.setItem(convertItem(stock.getStockPK().getItem()));

        typeResponse.setSupplier(convertSupplier(stock.getSupplier()));

        typeResponse.setColorId(stock.getStockPK().getColor() == null ? 0 : stock.getStockPK().getColor());
        // typeResponse.setColorDesc(stock.getStockPK().getColor()==null? "":stock.getStockPK().getColor().getColorDesc());
        typeResponse.setSizeId(stock.getStockPK().getSize() == null ? 0 : stock.getStockPK().getSize());
        // typeResponse.setSizeDesc(stock.getStockPK().getSize()==null? "":stock.getStockPK().getSize().getSizeDesc());
        typeResponse.setFitId(stock.getStockPK().getFit() == null ? 0 : stock.getStockPK().getFit());
        // typeResponse.setFitDesc(stock.getStockPK().getFit()==null? "":stock.getStockPK().getFit().getFitDesc());
        typeResponse.setBranchId(stock.getStockPK().getBranch().getId());
        typeResponse.setBatchNo(stock.getStockPK().getBatchNo());
        //typeResponse.setId(stock.getId());
        typeResponse.setCreatedBy(stock.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stock.getCreatedDateTime()));
        typeResponse.setModifiedBy(stock.getModifiedBy());
        typeResponse.setIsDeleted(stock.getIsDeleted());

        return typeResponse;
    }

    private static ItemMasterResponse convertItem(ItemMaster itemMaster) {

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
        typeResponse.setId(itemMaster.getId());
        typeResponse.setCreatedBy(itemMaster.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(itemMaster.getCreatedDateTime()));
        typeResponse.setModifiedBy(itemMaster.getModifiedBy());
        typeResponse.setIsDeleted(itemMaster.getIsDeleted());

        if (itemMaster.getDepartment1() != null) {
            typeResponse.setDepartment1Id(itemMaster.getDepartment1().getId());
            typeResponse.setDepartment1Name(itemMaster.getDepartment1().getDepartmentName());
        }
        if (itemMaster.getDepartment2() != null) {
            typeResponse.setDepartment2Id(itemMaster.getDepartment2().getId());
            typeResponse.setDepartment2Name(itemMaster.getDepartment2().getDepartmentName());
        }
        if (itemMaster.getDepartment3() != null) {
            typeResponse.setDepartment3Id(itemMaster.getDepartment3().getId());
            typeResponse.setDepartment3Name(itemMaster.getDepartment3().getDepartmentName());
        }
        if (itemMaster.getDepartment4() != null) {
            typeResponse.setDepartment4Id(itemMaster.getDepartment4().getId());
            typeResponse.setDepartment4Name(itemMaster.getDepartment4().getDepartmentName());
        }

        return typeResponse;
    }

    private static SupplierResponse convertSupplier(Supplier supplier) {

        SupplierResponse typeResponse = new SupplierResponse();
        typeResponse.setName(supplier.getName());
        typeResponse.setAddress(supplier.getAddress());
        typeResponse.setTHome(supplier.getTHome());
        typeResponse.setTMobile(supplier.getTMobile());
        typeResponse.setTOffice(supplier.getTOffice());
        typeResponse.setFax(supplier.getFax());
        typeResponse.setEmail(supplier.getEmail());
        typeResponse.setSalesPerson(supplier.getSalesPerson());
        typeResponse.setVat(supplier.getVat());
        typeResponse.setCreditLimit(supplier.getCreditLimit());
        typeResponse.setAvCreditLimit(supplier.getAvCreditLimit());
        typeResponse.setManufacture(supplier.getManufacture());
        typeResponse.setDivision(supplier.getDivision());
        typeResponse.setAgencyName(supplier.getAgencyName());
        typeResponse.setWebsite(supplier.getWebsite());
        typeResponse.setBrands(supplier.getBrands());
        typeResponse.setSupImage(supplier.getSupImage());
        typeResponse.setSupBankName(supplier.getSupBankName());
        typeResponse.setSupBankBranch(supplier.getSupBankBranch());
        typeResponse.setSupBankAccType(supplier.getSupBankAccType());
        typeResponse.setSupBankAccNo(supplier.getSupBankAccNo());
        typeResponse.setSupBankAccName(supplier.getSupBankAccName());
        typeResponse.setSupplierLedgerId(supplier.getSupplierLedgerId());
        typeResponse.setBranchId(supplier.getBranchId());
        typeResponse.setId(supplier.getId());
        typeResponse.setCreatedBy(supplier.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(supplier.getCreatedDateTime()));
        typeResponse.setModifiedBy(supplier.getModifiedBy());
        typeResponse.setIsDeleted(supplier.getIsDeleted());

        typeResponse.setSupplierAgencyResponse(supplier.getSupplierAgency() != null ?
                convert(supplier.getSupplierAgency()) : null);
        typeResponse.setSupplierSalesRepResponse(supplier.getSupplierSalesRep() != null ?
                convert(supplier.getSupplierSalesRep()) : null);

        return typeResponse;
    }

    private static SupplierAgencyResponse convert(SupplierAgency supplierAgency) {

        SupplierAgencyResponse typeResponse = new SupplierAgencyResponse();
        typeResponse.setSupplierId(supplierAgency.getSupplier().getId());
        typeResponse.setSupAgencyName(supplierAgency.getSupAgencyName());
        typeResponse.setSupAgencyAddress(supplierAgency.getSupAgencyAddress());
        typeResponse.setSupAEmail(supplierAgency.getSupAEmail());
        typeResponse.setSupAMobileNo(supplierAgency.getSupAMobileNo());
        typeResponse.setSupAHomeNo(supplierAgency.getSupAHomeNo());
        typeResponse.setSupAFaxNo(supplierAgency.getSupAFaxNo());
        typeResponse.setSupAWebSite(supplierAgency.getSupAWebSite());
        typeResponse.setId(supplierAgency.getId());
        typeResponse.setCreatedBy(supplierAgency.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(supplierAgency.getCreatedDateTime()));
        typeResponse.setModifiedBy(supplierAgency.getModifiedBy());
        typeResponse.setIsDeleted(supplierAgency.getIsDeleted());

        return typeResponse;
    }

    private static SupplierSalesRepResponse convert(SupplierSalesRep supplierSalesRep) {

        SupplierSalesRepResponse typeResponse = new SupplierSalesRepResponse();
        typeResponse.setSupplierId(supplierSalesRep.getSupplier().getId());
        typeResponse.setSalesrepName(supplierSalesRep.getSalesrepName());
        typeResponse.setRepNicNo(supplierSalesRep.getRepNicNo());
        typeResponse.setRepVehicleNo(supplierSalesRep.getRepVehicleNo());
        typeResponse.setRepEmail(supplierSalesRep.getRepEmail());
        typeResponse.setRepMobileNo(supplierSalesRep.getRepMobileNo());
        typeResponse.setRepHomeNo(supplierSalesRep.getRepHomeNo());
        typeResponse.setRepFaxNo(supplierSalesRep.getRepFaxNo());
        typeResponse.setId(supplierSalesRep.getId());
        typeResponse.setCreatedBy(supplierSalesRep.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(supplierSalesRep.getCreatedDateTime()));
        typeResponse.setModifiedBy(supplierSalesRep.getModifiedBy());
        typeResponse.setIsDeleted(supplierSalesRep.getIsDeleted());

        return typeResponse;
    }

    private static StockResponse convert4(Stock stock) {

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
        typeResponse.setItemId(stock.getStockPK().getItem().getId());
        typeResponse.setItemCode(stock.getStockPK().getItem().getItemCode());
        typeResponse.setItemName(stock.getStockPK().getItem().getItemName());
        typeResponse.setColorId(stock.getStockPK().getColor() == null ? 0 : stock.getStockPK().getColor());
//        typeResponse.setColorDesc(stock.getStockPK().getColor()==null? "":stock.getStockPK().getColor().getColorDesc());
        typeResponse.setSizeId(stock.getStockPK().getSize() == null ? 0 : stock.getStockPK().getSize());//;;;;
        //typeResponse.setSizeDesc(stock.getStockPK().getSize()==null? "":stock.getStockPK().getSize().getSizeDesc());
        typeResponse.setFitId(stock.getStockPK().getFit() == null ? 0 : stock.getStockPK().getFit());
        //typeResponse.setFitDesc(stock.getStockPK().getFit()==null? "":stock.getStockPK().getFit().getFitDesc());
        typeResponse.setBranchId(stock.getStockPK().getBranch().getId());
        typeResponse.setBatchNo(stock.getStockPK().getBatchNo());
        //typeResponse.setId(stock.getId());
        typeResponse.setCreatedBy(stock.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stock.getCreatedDateTime()));
        typeResponse.setModifiedBy(stock.getModifiedBy());
        typeResponse.setIsDeleted(stock.getIsDeleted());

        return typeResponse;
    }

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional
    public StockResponse save(StockRequest request) {

        Stock stock = new Stock();
        stock.setStkQty(request.getStkQty());
        stock.setDamgQty(request.getDamgQty());
        stock.setAvailabQty(request.getAvailabQty());
        stock.setAvrgPrice(request.getAvrgPrice());
        stock.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        stock.setStkCashPrice(request.getStkCashPrice());
        stock.setStkCreditPrice(request.getStkCreditPrice());
        stock.setStoresQty(request.getStoresQty());
        stock.setTotalQty(request.getTotalQty());
        stock.setBarcodeNo(request.getBarcodeNo());
        stock.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
        stock.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());

        Supplier supplier = new Supplier();
        supplier.setId(request.getSupplierId());
        stock.setSupplier(supplier);

//      stock.setSalesPerson(request.getSalesPerson());
//      stock.setAgencyName(request.getAgencyName());

        stock.setObStock(request.getObStock());
        stock.setCashDisValue(request.getCashDisValue());
        stock.setCreditDisValue(request.getCreditDisValue());
        stock.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
        stock.setMaterialWidth(request.getMaterialWidth());
//
//        ItemMaster item = new ItemMaster();
//        item.setId(request.getItemId());
//        stock.setItem(item);
//
//        Color color = new Color();
//        color.setId(request.getColorId());
//        stock.setColor(color);
//
//        Size size = new Size();
//        size.setId(request.getSizeId());
//        stock.setSize(size);
//
//        Fit fit = new Fit();
//        fit.setId(request.getFitId());
//        stock.setFit(fit);
//
//        BranchNetwork branchNetwork = new BranchNetwork();
//        branchNetwork.setId(request.getBranchId());
//        stock.setBranch(branchNetwork);
//
//        stock.setBatchNo(request.getBatchNo());
//        stock.setIsDeleted(Deleted.NO);
        Stock save = stockRepository.save(stock);
//
//        saveLog("Stock", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public StockResponse update(StockUpdateRequest request) {

//        Stock stock = stockRepository.findById(request.getId()).orElse(null);
//        if (stock == null) {
//            return null;
//        }
//
//        stock.setStkQty(request.getStkQty());
//        stock.setDamgQty(request.getDamgQty());
//        stock.setAvailabQty(request.getAvailabQty());
//        stock.setAvrgPrice(request.getAvrgPrice());
//        stock.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
//        stock.setStkCashPrice(request.getStkCashPrice());
//        stock.setStkCreditPrice(request.getStkCreditPrice());
//        stock.setStoresQty(request.getStoresQty());
//        stock.setTotalQty(request.getTotalQty());
//        stock.setBarcodeNo(request.getBarcodeNo());
//        stock.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
//        stock.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
//
//        Supplier supplier = new Supplier();
//        supplier.setId(request.getSupplierId());
//        stock.setSupplier(supplier);
//
////        stock.setSalesPerson(request.getSalesPerson());
////        stock.setAgencyName(request.getAgencyName());
//
//        stock.setObStock(request.getObStock());
//        stock.setCashDisValue(request.getCashDisValue());
//        stock.setCreditDisValue(request.getCreditDisValue());
//        stock.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
//        stock.setMaterialWidth(request.getMaterialWidth());
//
//        ItemMaster item = new ItemMaster();
//        item.setId(request.getItemId());
//        stock.setItem(item);
//
//        Color color = new Color();
//        color.setId(request.getColorId());
//        stock.setColor(color);
//
//        Size size = new Size();
//        size.setId(request.getSizeId());
//        stock.setSize(size);
//
//        Fit fit = new Fit();
//        fit.setId(request.getFitId());
//        stock.setFit(fit);
//
//        BranchNetwork branchNetwork = new BranchNetwork();
//        branchNetwork.setId(request.getBranchId());
//        stock.setBranch(branchNetwork);
//
//        stock.setBatchNo(request.getBatchNo());
//        Stock updated = stockRepository.save(stock);
//
//        saveLog("Stock", "Data Updated - " + updated.getId());

        return null;
    }

    @Override
    public StockResponse getById(Long id) {

        //    return stockRepository.findById(id).map(StockServiceImpl::convert).orElse(null);
        return null;
    }

    @Override
    public List<StockResponse> getAll() {

        return stockRepository.findAll()
                .stream().map(StockServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<StockFullResponse> searchStock(StockSearchRequest request) {

        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        List<Stock> stockList = stockRepository.getByBranchAndIsDeleted(request.getBranchId());

        List<Stock> response = new ArrayList<>();

        if (request.getSearchType().equals("ITEM CODE")) {
            for (Stock stk : stockList) {
                if (Objects.equals(stk.getStockPK().getItem().getItemCode(), request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("ITEM NAME")) {
            for (Stock stk : stockList) {
                if (stk.getStockPK().getItem().getItemName().toLowerCase().contains(request.getSearchText().toLowerCase())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("BRAND NAME")) {
            for (Stock stk : stockList) {
                if (stk.getStockPK().getItem().getBrand().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("CATEGORY NAME")) {
            for (Stock stk : stockList) {
                if (stk.getStockPK().getItem().getCategory().getCategoryName().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("SUPPLIER NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getName().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("AGENCY NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getAgencyName().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("SALES PERSON NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getSalesPerson().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("BARCODE NO")) {
            for (Stock stk : stockList) {
                if (stk.getBarcodeNo().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("RACK NO")) {
            for (Stock stk : stockList) {
                if (stk.getStockPK().getItem().getRackNo().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("TOTAL STOCK - ACTIVE BATCHES")) {
            for (Stock stk : stockList) {
                if (stk.getTotalQty() != 0) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("TOTAL STOCK - WITH ACTIVE & EMPTY BATCHES")) {
            response = stockList;
        }

        return response.stream().map(StockServiceImpl::convert2).collect(Collectors.toList());
    }

    @Override
    public List<StockFullResponse> searchStockExp(StockSearchRequest request) {
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        List<Stock> stockList = stockRepository.getByBranchAndIsDeleted(request.getBranchId());
        List<Stock> response = new ArrayList<>();
        //System.out.println(request.getSearchType());

        if (request.getSearchType().equals("DEFAULT")) {
            response = stockList;
            System.out.println("def");
        } else if (request.getSearchType().equals("SUPPLIER NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getName().toLowerCase().contains(request.getSearchText().toLowerCase())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("AGENCY NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getAgencyName().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        } else if (request.getSearchType().equals("SALES PERSON NAME")) {
            for (Stock stk : stockList) {
                if (stk.getSupplier().getSalesPerson().equalsIgnoreCase(request.getSearchText())) {
                    response.add(stk);
                }
            }
        }

        return response.stream().map(StockServiceImpl::convert2).collect(Collectors.toList());
    }

    @Override
    public File printStockRpt(StockSearchRequest request, String which) throws Exception {

        System.out.println(request.getJasonList());

        File pdf = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> params = new HashMap<String, Object>();
        String reportsource = null;
        if (which.equals("stock")) {
            reportsource = Settings.readSettings("REPORT_PATH") + "Docs/Inventory/CURRENT_STOCK_CATOGERY_WISE.jrxml";
            if (request.getSearchType().equals("ITEM CODE") || request.getSearchType().equals("ITEM NAME")) {
                reportsource = Settings.readSettings("REPORT_PATH") + "Docs/Inventory/CURRENT_STOCK_ITEM_WISE.jrxml";
            } else if (request.getSearchType().equals("RACK NO")) {
                reportsource = Settings.readSettings("REPORT_PATH") + "Docs/Inventory/CURRENT_STOCK_RACK_WISE.jrxml";
                params.put("rack", request.getSearchText());
            } else if (request.getSearchType().startsWith("TOTAL STOCK")) {
                reportsource = Settings.readSettings("REPORT_PATH") + "Docs/Inventory/CURRENT_STOCK_ALL.jrxml";
                params.put("rack", request.getSearchText());
            } else {
                params.put("category", request.getSearchText());
                params.put("Selection", request.getSearchType());
            }

            params.put("lbTotValue", request.getTotItemValue());
            params.put("Stores_Qty", request.getTotStoresQty());
            params.put("Shop_Qty", request.getTotShopQty());
            params.put("Total_Qty", request.getTotalQty());
            params.put("Current_Date", sdf.format(new Date()));

        } else if (which.equals("exp")) {

            reportsource = Settings.readSettings("REPORT_PATH") + "Docs/Inventory/CURRENT_STOCK_EXPIRED.jrxml";
            params.put("lbTotValue", request.getTotItemValue());
            params.put("Stores_Qty", request.getTotStoresQty());
            params.put("Shop_Qty", request.getTotShopQty());
            params.put("Total_Qty", request.getTotalQty());
            params.put("Current_Date", sdf.format(new Date()));
            params.put("category", request.getSearchText());
            params.put("Selection", request.getSearchType());
        }

        Gson gson = new Gson();
        System.out.println(request.getJasonList());
        List<HashMap<String, Object>> from = gson.fromJson(request.getJasonList(), new TypeToken<List<HashMap<String, Object>>>() {}.getType());
        JasperReport jasperReport = JasperCompileManager.compileReport(reportsource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(from));
        File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
        if (!perentFol.exists()) {
            perentFol.mkdirs();
        }
        pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));

        return pdf;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

//        Stock got = stockRepository.findById(id).orElse(null);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        stockRepository.save(got);
//
//        saveLog("Stock", "Data Deleted - " + id);

        return 0;
    }

    @Override
    public List<StockResponse> getPriceChangesList(StockPriceChangesRequest request) {

        List<StockResponse> resp = new ArrayList<>();

        String sql = " select * from stock where item_code='" + request.getItemId() + "'";

        if (request.getBatchNo() == null) {
            sql = sql + "";
        } else {
            sql = sql + " and batch_no='" + request.getBatchNo() + "'";
        }
        if (request.getColorId() == null) {
            sql = sql + "";
        } else {
            sql = sql + " and color_id=" + request.getColorId() + "";
        }
        if (request.getFitId() == null) {
            sql = sql + "";
        } else {
            sql = sql + " and fit_id=" + request.getFitId() + "";
        }
        if (request.getSizeId() == null) {
            sql = sql + "";
        } else {
            sql = sql + " and size_id=" + request.getSizeId() + "";
        }
        if (request.getBranchId() == null) {
            sql = sql + "";
        } else {
            sql = sql + " and branch_id=" + request.getBranchId() + "";
        }

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("check out correct");
                StockResponse typeResponse = new StockResponse();
                typeResponse.setStkQty(rs.getDouble("stk_qty"));
                typeResponse.setDamgQty(rs.getDouble("damg_qty"));
                typeResponse.setAvailabQty(rs.getDouble("availab_qty"));
                typeResponse.setAvrgPrice(rs.getDouble("avrg_price"));
                typeResponse.setExpireDate(rs.getString("expire_date"));
                typeResponse.setStkCashPrice(rs.getDouble("stk_cash_price"));
                typeResponse.setStkCreditPrice(rs.getDouble("stk_credit_price"));
                typeResponse.setStoresQty(rs.getDouble("stores_qty"));
                typeResponse.setTotalQty(rs.getDouble("total_qty"));
                typeResponse.setBarcodeNo(rs.getString("barcode_no"));
                typeResponse.setStockUnitPriceRetail(rs.getDouble("stock_unit_price_retail"));
                typeResponse.setStockUnitPriceWholesale(rs.getDouble("stock_unit_price_wholesale"));
                typeResponse.setSupplierId(rs.getLong("supplier_id"));
                typeResponse.setSalesPerson(rs.getString("sales_person"));
                typeResponse.setObStock(rs.getDouble("ob_stock"));
                typeResponse.setCashDisValue(rs.getDouble("cash_dis_value"));
                typeResponse.setCreditDisValue(rs.getDouble("credit_dis_value"));
                typeResponse.setSalesDiscoPresentage(rs.getDouble("sales_disco_presentage"));
                typeResponse.setMaterialWidth(rs.getDouble("material_width"));
                typeResponse.setItemCode(rs.getString("item_code"));
                ItemMaster itemMaster = itemMasterRepository.getById(rs.getString("item_code"));
                typeResponse.setItemName(itemMaster.getItemName());
                if (rs.getLong("color_id") != 0) {
                    Color color = colorRepository.getById(rs.getLong("color_id"));
                    typeResponse.setColorDesc(color.getColorDesc());
                } else {
                    typeResponse.setColorDesc("");
                }
                typeResponse.setColorId(rs.getLong("color_id"));
                if (rs.getLong("size_id") != 0) {
                    Size size = sizeRepository.getById(rs.getLong("size_id"));
                    typeResponse.setSizeDesc(size.getSizeDesc());
                } else {
                    typeResponse.setSizeDesc("");
                }
                typeResponse.setSizeId(rs.getLong("size_id"));
                typeResponse.setFitId(rs.getLong("fit_id"));
                if (rs.getLong("fit_id") != 0) {
                    Fit fit = fitRepository.getById(rs.getLong("fit_id"));
                    typeResponse.setFitDesc(fit.getFitDesc());
                } else {
                    typeResponse.setFitDesc("");
                }
                BranchNetwork branchNetwork = branchNetworkRepository.getById(rs.getLong("branch_id"));
                typeResponse.setBranchName(branchNetwork.getBranchName());
                typeResponse.setBranchId(rs.getLong("branch_id"));
                typeResponse.setBatchNo(rs.getString("batch_no"));
                resp.add(typeResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {

            }
        }
        return resp;
    }

    @Override
    @Transactional
    public List<StockResponse> updatePriceChange(List<StockPriceChangeUpdateRequest> requests) {

        List<StockResponse> list = new ArrayList<>();

        for (StockPriceChangeUpdateRequest request : requests) {

            Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(), request.getItemCode(), request.getBranchId(),
                    request.getColorId(), request.getSizeId(), request.getFitId());

            PriceChangeLog priceChangeLog = new PriceChangeLog();
            priceChangeLog.setPrevCostPrice(stock.getAvrgPrice());
            priceChangeLog.setPrevUnitPrice(stock.getStockUnitPriceRetail());

            stock.setAvrgPrice(request.getCostPrice());
            stock.setStkCashPrice(request.getCostPrice());
            stock.setStkCreditPrice(request.getCostPrice());
            stock.setStockUnitPriceRetail(request.getUnitPrice());
            stock.setStockUnitPriceWholesale(request.getUnitPrice());
            Stock stock1 = stockRepository.save(stock);

            ItemMaster item = itemMasterRepository.getById(request.getItemCode());
            item.setBuyingPrice(request.getCostPrice());
            item.setRetailSellPrice(request.getCostPrice());
            item.setWholesaleSellPrice(request.getCostPrice());
            item.setUnitPrice(request.getUnitPrice());
            item.setUnitPriceRetail(request.getUnitPrice());
            item.setUnitPriceWholesale(request.getUnitPrice());
            itemMasterRepository.save(item);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();
            priceChangeLog.setUser(uu);
            priceChangeLog.setItemCode(stock.getStockPK().getItem().getItemCode());
            priceChangeLog.setItemName(stock.getStockPK().getItem().getItemName());
            priceChangeLog.setDate(new Date());
            priceChangeLog.setColor(stock.getStockPK().getColor());
            priceChangeLog.setSize(stock.getStockPK().getSize());
            priceChangeLog.setFit(stock.getStockPK().getFit());
            priceChangeLog.setBranch(stock.getStockPK().getBranch().getId());
            priceChangeLog.setBatchNo(stock.getStockPK().getBatchNo());
            priceChangeLog.setStkCashPrice(stock.getStkCashPrice());
            priceChangeLog.setStkCreditPrice(stock.getStkCreditPrice());
            priceChangeLog.setStockUnitPriceRetail(stock.getStockUnitPriceRetail());
            priceChangeLog.setStockUnitPriceWholesale(stock.getStockUnitPriceWholesale());
            priceChangeLog.setCashDisValue(stock.getCashDisValue());
            priceChangeLog.setCreditDisValue(stock.getCreditDisValue());
            priceChangeLog.setSalesDiscoPresentage(stock.getSalesDiscoPresentage());
            priceChangeLog.setNewCostPrice(stock.getAvrgPrice());
            priceChangeLog.setNewUnitPrice(stock.getStockUnitPriceRetail());
            priceChangeLog.setIsDeleted(Deleted.NO);
            PriceChangeLog price = priceChangeLogRepository.save(priceChangeLog);

            BinCard binCard = new BinCard();
            ItemMaster items = itemMasterRepository.getById(stock.getStockPK().getItem().getItemCode());
            binCard.setItem(items);
            binCard.setBinCardDate(new Date());
            binCard.setDocType("PRICE CHANGE");
            binCard.setDocNo(price.getId() + "");
            binCard.setRecQty(0.0);
            binCard.setIsueQty(0.00);
            binCard.setBalanceQty(stock.getTotalQty());
            binCard.setBatchNo(stock.getStockPK().getBatchNo());
            binCard.setBranch(stock.getStockPK().getBranch());
            binCard.setIsDeleted(Deleted.NO);
            binCard.setFit(stock.getStockPK().getFit());
            binCard.setColor(stock.getStockPK().getFit());
            binCard.setSize(stock.getStockPK().getFit());
            binCardRepository.save(binCard);

            list.add(convert(stock1));

        }

        return list;
    }

    @Override
    @Transactional
    public List<StockResponse> stockTransfer(StockTransferRequest request) {

        List<StockResponse> list = new ArrayList<>();
        Integer issueNum = stockTransferLogRepository.getMaxId();
        if (issueNum!=null){
            issueNum=issueNum+1;
        }else {
            issueNum=1;
        }

        for (StockTransferUpdateRequest stockUpdateRequest : request.getUpdateRequestList()) {

            if ((request.getIsHo() == 1) && (request.getFromBranchId() == request.getToBranchId())) {

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                        stockUpdateRequest.getItemId(), request.getFromBranchId(),
                        stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                StockTransferLog stockTransferLog = new StockTransferLog();
                stockTransferLog.setPrevQtyfromBranch(stock.getTotalQty());
                stockTransferLog.setPrevQtytobranch(stock.getAvailabQty());

                stock.setStoresQty(stock.getStoresQty() - stockUpdateRequest.getTransferStock());
                stock.setTotalQty(stock.getTotalQty() - stockUpdateRequest.getTransferStock());
                stock.setAvailabQty(stock.getAvailabQty() + stockUpdateRequest.getTransferStock());
                stockRepository.save(stock);

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(stock.getStockPK().getItem().getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("STOCK TRANSFER HO TO SHOP");
                binCard.setDocNo(issueNum.toString());
                binCard.setRecQty(0.0);
                binCard.setIsueQty(stockUpdateRequest.getTransferStock());
                binCard.setBalanceQty(stock.getTotalQty());
                binCard.setBatchNo(stock.getStockPK().getBatchNo());
                binCard.setBranch(stock.getStockPK().getBranch());
                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(stock.getStockPK().getFit());
                binCard.setColor(stock.getStockPK().getColor());
                binCard.setSize(stock.getStockPK().getSize());
                binCardRepository.save(binCard);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String uu = auth.getName();

                stockTransferLog.setNewQtytoBranch(stockUpdateRequest.getTransferStock());
                stockTransferLog.setNewQtyFromBranch(stock.getStoresQty());
                stockTransferLog.setUser(uu);
                stockTransferLog.setItemCode(stock.getStockPK().getItem().getItemCode());
                stockTransferLog.setItemName(stock.getStockPK().getItem().getItemName());
                stockTransferLog.setDate(new Date());
                stockTransferLog.setColor(stock.getStockPK().getColor());
                stockTransferLog.setSize(stock.getStockPK().getSize());
                stockTransferLog.setFit(stock.getStockPK().getFit());
                stockTransferLog.setFromBranch(stock.getStockPK().getBranch().getId());
                stockTransferLog.setToBranch(stock.getStockPK().getBranch().getId());
                stockTransferLog.setIsDeleted(Deleted.NO);
                stockTransferLog.setIssueNumber(issueNum);
                stockTransferLog.setUnitPrice(stock.getAvrgPrice());
//
//                StockTransferLog transferLog = stockTransferLogRepository.findByUnitPriceAndSizeAndItemCodeAndIssueNumberAndIsDeleted(re);
//                if ()
                stockTransferLogRepository.save(stockTransferLog);

                list.add(convert(stock));

            }
            else if ((request.getIsHo() == 2) && (request.getFromBranchId() == request.getToBranchId())) {

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                        stockUpdateRequest.getItemId(), request.getFromBranchId(),
                        stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                StockTransferLog stockTransferLog = new StockTransferLog();
                stockTransferLog.setPrevQtyfromBranch(stock.getTotalQty());
                stockTransferLog.setPrevQtytobranch(stock.getAvailabQty());

                stock.setStoresQty(stock.getStoresQty() + stockUpdateRequest.getTransferStock());
                stock.setTotalQty(stock.getTotalQty() + stockUpdateRequest.getTransferStock());
                stock.setAvailabQty(stock.getAvailabQty() - stockUpdateRequest.getTransferStock());
                stockRepository.save(stock);

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(stock.getStockPK().getItem().getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("STOCK TRANSFER SHOP TO HO ");
                binCard.setDocNo(issueNum.toString());
                binCard.setRecQty(0.0);
                binCard.setIsueQty(stockUpdateRequest.getTransferStock());
                binCard.setBalanceQty(stock.getTotalQty());
                binCard.setBatchNo(stock.getStockPK().getBatchNo());
                binCard.setBranch(stock.getStockPK().getBranch());
                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(stock.getStockPK().getFit());
                binCard.setColor(stock.getStockPK().getColor());
                binCard.setSize(stock.getStockPK().getSize());
                binCardRepository.save(binCard);

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String uu = auth.getName();

                stockTransferLog.setNewQtytoBranch(stockUpdateRequest.getTransferStock());
                stockTransferLog.setNewQtyFromBranch(stock.getStoresQty());
                stockTransferLog.setUser(uu);
                stockTransferLog.setItemCode(stock.getStockPK().getItem().getItemCode());
                stockTransferLog.setItemName(stock.getStockPK().getItem().getItemName());
                stockTransferLog.setDate(new Date());
                stockTransferLog.setColor(stock.getStockPK().getColor());
                stockTransferLog.setSize(stock.getStockPK().getSize());
                stockTransferLog.setFit(stock.getStockPK().getFit());
                stockTransferLog.setFromBranch(stock.getStockPK().getBranch().getId());
                stockTransferLog.setToBranch(stock.getStockPK().getBranch().getId());
                stockTransferLog.setIsDeleted(Deleted.NO);
                stockTransferLog.setIssueNumber(issueNum);
                stockTransferLog.setUnitPrice(stock.getAvrgPrice());
                stockTransferLogRepository.save(stockTransferLog);

                list.add(convert(stock));

            }
            else {

                Stock stockCheck = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                        stockUpdateRequest.getItemId(), request.getToBranchId(),
                        stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                if (stockCheck != null) {

                    Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                            stockUpdateRequest.getItemId(), request.getFromBranchId(),
                            stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                    StockTransferLog stockTransferLog = new StockTransferLog();
                    stockTransferLog.setPrevQtyfromBranch(stock.getTotalQty());

                    stock.setTotalQty(stock.getTotalQty() - stockUpdateRequest.getTransferStock());
                    stock.setStoresQty(stock.getStoresQty() - stockUpdateRequest.getTransferStock());
                    stockRepository.save(stock);

                    stockTransferLog.setNewQtyFromBranch(stock.getTotalQty());

                    Stock stock1 = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                            stockUpdateRequest.getItemId(), request.getToBranchId(),
                            stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                    stockTransferLog.setPrevQtytobranch(stock1.getTotalQty());

                    stock1.setTotalQty(stock1.getTotalQty() + stockUpdateRequest.getTransferStock());
                    stock1.setStoresQty(stock1.getStoresQty() + stockUpdateRequest.getTransferStock());
                    stockRepository.save(stock1);

                    BinCard binCard = new BinCard();
                    ItemMaster items = itemMasterRepository.getById(stock.getStockPK().getItem().getItemCode());
                    binCard.setItem(items);
                    binCard.setBinCardDate(new Date());
                    binCard.setDocType("STOCK TRANSFER");
                    binCard.setDocNo(issueNum.toString());
                    binCard.setRecQty(0.0);
                    binCard.setIsueQty(stockUpdateRequest.getTransferStock());
                    binCard.setBalanceQty(stock1.getTotalQty());
                    binCard.setBatchNo(stock1.getStockPK().getBatchNo());
                    binCard.setBranch(stock1.getStockPK().getBranch());
                    binCard.setIsDeleted(Deleted.NO);
                    binCard.setFit(stock1.getStockPK().getFit());
                    binCard.setColor(stock1.getStockPK().getColor());
                    binCard.setSize(stock1.getStockPK().getSize());
                    binCardRepository.save(binCard);

                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String uu = auth.getName();
                    stockTransferLog.setUser(uu);
                    stockTransferLog.setItemCode(stock1.getStockPK().getItem().getItemCode());
                    stockTransferLog.setItemName(stock1.getStockPK().getItem().getItemName());
                    stockTransferLog.setDate(new Date());
                    stockTransferLog.setColor(stock1.getStockPK().getColor());
                    stockTransferLog.setSize(stock1.getStockPK().getSize());
                    stockTransferLog.setFit(stock1.getStockPK().getFit());
                    stockTransferLog.setFromBranch(stock.getStockPK().getBranch().getId());
                    stockTransferLog.setToBranch(stock1.getStockPK().getBranch().getId());
                    stockTransferLog.setNewQtytoBranch(stockUpdateRequest.getTransferStock());
                    stockTransferLog.setIsDeleted(Deleted.NO);
                    stockTransferLog.setIssueNumber(issueNum);
                    stockTransferLog.setUnitPrice(stock.getAvrgPrice());
                    stockTransferLogRepository.save(stockTransferLog);

                    list.add(convert(stock));
                }
                else {

                    Stock stockget = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(stockUpdateRequest.getBatchNo(),
                            stockUpdateRequest.getItemId(), request.getFromBranchId(),
                            stockUpdateRequest.getColorId(), stockUpdateRequest.getSizeId(), stockUpdateRequest.getFitId());

                    StockTransferLog stockTransferLog = new StockTransferLog();
                    stockTransferLog.setPrevQtyfromBranch(stockget.getTotalQty());

                    stockget.setTotalQty(stockget.getTotalQty() - stockUpdateRequest.getTransferStock());
                    stockget.setStoresQty(stockget.getStoresQty() - stockUpdateRequest.getTransferStock());
                    stockRepository.save(stockget);

                    //save stock
                    Stock stock = new Stock();

                    BranchNetwork branchNetwork = new BranchNetwork();
                    branchNetwork.setId(request.getToBranchId());

                    StockPK stockPK = new StockPK(stockget.getStockPK().getItem(), stockget.getStockPK().getColor(), stockget.getStockPK().getSize(),
                            stockget.getStockPK().getFit(), branchNetwork, stockget.getStockPK().getBatchNo());

                    stock.setStockPK(stockPK);
                    stock.setStkQty(stockUpdateRequest.getTransferStock());
                    stock.setDamgQty(stockget.getDamgQty());
                    stock.setAvailabQty(stockget.getAvailabQty());
                    stock.setAvrgPrice(stockget.getAvrgPrice());
                    stock.setExpireDate(stockget.getExpireDate());
                    stock.setStkCashPrice(stockget.getStkCashPrice());
                    stock.setStkCreditPrice(stockget.getStkCreditPrice());
                    stock.setStoresQty(stockUpdateRequest.getTransferStock());
                    stock.setTotalQty(stockUpdateRequest.getTransferStock());
                    stock.setBarcodeNo(stockget.getBarcodeNo());
                    stock.setStockUnitPriceRetail(stockget.getStockUnitPriceRetail());
                    stock.setStockUnitPriceWholesale(stockget.getStockUnitPriceWholesale());
                    stock.setSupplier(stockget.getSupplier());
                    stock.setObStock(stockget.getObStock());
                    stock.setCashDisValue(stockget.getCashDisValue());
                    stock.setCreditDisValue(stockget.getCreditDisValue());
                    stock.setSalesDiscoPresentage(stockget.getSalesDiscoPresentage());
                    stock.setMaterialWidth(stockget.getMaterialWidth());
                    stock.setIsDeleted(Deleted.NO);
                    Stock stock1 = stockRepository.save(stock);

                    BinCard binCard1 = new BinCard();
                    ItemMaster items = itemMasterRepository.getById(stock.getStockPK().getItem().getItemCode());
                    binCard1.setItem(items);
                    binCard1.setBinCardDate(new Date());
                    binCard1.setDocType("STOCK TRANSFER");
                    binCard1.setDocNo(issueNum.toString());
                    binCard1.setRecQty(0.0);
                    binCard1.setIsueQty(stockUpdateRequest.getTransferStock());
                    binCard1.setBalanceQty(stockget.getTotalQty());
                    binCard1.setBatchNo(stockget.getStockPK().getBatchNo());
                    binCard1.setBranch(stockget.getStockPK().getBranch());
                    binCard1.setIsDeleted(Deleted.NO);
                    binCard1.setFit(stockget.getStockPK().getFit());
                    binCard1.setColor(stockget.getStockPK().getColor());
                    binCard1.setSize(stockget.getStockPK().getSize());
                    binCardRepository.save(binCard1);

                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    String uu = auth.getName();
                    stockTransferLog.setUser(uu);
                    stockTransferLog.setItemCode(stock1.getStockPK().getItem().getItemCode());
                    stockTransferLog.setItemName(stock1.getStockPK().getItem().getItemName());
                    stockTransferLog.setDate(new Date());
                    stockTransferLog.setColor(stock1.getStockPK().getColor());
                    stockTransferLog.setSize(stock1.getStockPK().getSize());
                    stockTransferLog.setFit(stock1.getStockPK().getFit());
                    stockTransferLog.setFromBranch(stockget.getStockPK().getBranch().getId());
                    stockTransferLog.setToBranch(stock1.getStockPK().getBranch().getId());
                    stockTransferLog.setNewQtytoBranch(stockUpdateRequest.getTransferStock());
                    stockTransferLog.setPrevQtytobranch(0.00);
                    stockTransferLog.setNewQtyFromBranch(stock1.getTotalQty());
                    stockTransferLog.setIsDeleted(Deleted.NO);
                    stockTransferLog.setIssueNumber(issueNum);
                    stockTransferLog.setUnitPrice(stockget.getAvrgPrice());
                    stockTransferLogRepository.save(stockTransferLog);

                    list.add(convert(stock1));
                }
            }


        }

        return list;
    }

    @Override
    public List<StockResponse> getByBranchAndIsDeleted(Long branchId) {
        return stockRepository.getByBranchAndIsDeleted(branchId)
                .stream().map(StockServiceImpl::convert4).collect(Collectors.toList());
    }

    @Override
    public List<StockResponse> getByItemCodeAndBranch(String itemCode, Long branchId) {

        List<StockResponse> resp = new ArrayList<>();

        String sql = " select * from stock where item_code='" + itemCode + "' and branch_id=" + branchId + " and is_deleted=0";

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("check out correct");
                StockResponse typeResponse = new StockResponse();
                typeResponse.setStkQty(rs.getDouble("stk_qty"));
                typeResponse.setDamgQty(rs.getDouble("damg_qty"));
                typeResponse.setAvailabQty(rs.getDouble("availab_qty"));
                typeResponse.setAvrgPrice(rs.getDouble("avrg_price"));
                typeResponse.setExpireDate(rs.getString("expire_date"));
                typeResponse.setStkCashPrice(rs.getDouble("stk_cash_price"));
                typeResponse.setStkCreditPrice(rs.getDouble("stk_credit_price"));
                typeResponse.setStoresQty(rs.getDouble("stores_qty"));
                typeResponse.setTotalQty(rs.getDouble("total_qty"));
                typeResponse.setBarcodeNo(rs.getString("barcode_no"));
                typeResponse.setStockUnitPriceRetail(rs.getDouble("stock_unit_price_retail"));
                typeResponse.setStockUnitPriceWholesale(rs.getDouble("stock_unit_price_wholesale"));
                typeResponse.setSupplierId(rs.getLong("supplier_id"));
                typeResponse.setSalesPerson(rs.getString("sales_person"));
                typeResponse.setObStock(rs.getDouble("ob_stock"));
                typeResponse.setCashDisValue(rs.getDouble("cash_dis_value"));
                typeResponse.setCreditDisValue(rs.getDouble("credit_dis_value"));
                typeResponse.setSalesDiscoPresentage(rs.getDouble("sales_disco_presentage"));
                typeResponse.setMaterialWidth(rs.getDouble("material_width"));
                typeResponse.setItemCode(rs.getString("item_code"));
                ItemMaster itemMaster = itemMasterRepository.getById(rs.getString("item_code"));
                typeResponse.setItemName(itemMaster.getItemName());
                if (rs.getLong("color_id") != 0) {
                    Color color = colorRepository.getById(rs.getLong("color_id"));
                    typeResponse.setColorDesc(color.getColorDesc());
                } else {
                    typeResponse.setColorDesc("");
                }
                typeResponse.setColorId(rs.getLong("color_id"));
                if (rs.getLong("size_id") != 0) {
                    Size size = sizeRepository.getById(rs.getLong("size_id"));
                    typeResponse.setSizeDesc(size.getSizeDesc());
                } else {
                    typeResponse.setSizeDesc("");
                }
                typeResponse.setSizeId(rs.getLong("size_id"));
                typeResponse.setFitId(rs.getLong("fit_id"));
                if (rs.getLong("fit_id") != 0) {
                    Fit fit = fitRepository.getById(rs.getLong("fit_id"));
                    typeResponse.setFitDesc(fit.getFitDesc());
                } else {
                    typeResponse.setFitDesc("");
                }
                BranchNetwork branchNetwork = branchNetworkRepository.getById(rs.getLong("branch_id"));
                typeResponse.setBranchName(branchNetwork.getBranchName());
                typeResponse.setBranchId(rs.getLong("branch_id"));
                typeResponse.setBatchNo(rs.getString("batch_no"));
                resp.add(typeResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {

            }
        }
        return resp;
    }

    @Override
    public List<stockLocationResponse> getByStockLocation() {
        return stockRepository.getByStockLocation();
    }

    @Override
    public List<Double> getSumQtyOfLoc(String itemCode, Long catId) {

        List<Double> resp = new ArrayList<>();

        List<stockLocationResponse> list = stockRepository.getByStockLocation();

        for (stockLocationResponse response : list) {

            String sql = " select sum(stores_qty) as tot from stock s inner join item_master i on i.item_code=s.item_code where s.branch_id=" + response.getBRANCH_ID() + " ";

            if (!itemCode.equals("null")) {
                sql = sql + " and s.item_code='" + itemCode + "'  ";
            } else {
                sql = sql + "  ";
            }

            if (catId != 0) {
                sql = sql + " and i.category_id=" + catId + " ";
            } else {
                sql = sql + "";
            }

            sql = sql + " ";

            Connection co = null;
            try {
                co = JDBC.con();
                Statement st = co.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Double sum = rs.getDouble("tot");
                    resp.add(sum);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (co != null) {
                        co.close();
                    }
                } catch (Exception ex) {
                }
            }

        }

        return resp;
    }

    @Override
    public Double getTotStock(String itemCode, Long catId) {

        String sql = " select sum(stores_qty) as tot from stock s inner join item_master i on i.item_code=s.item_code ";

        if (!itemCode.equals("null")) {
            sql = sql + " where s.item_code='" + itemCode + "'  ";
        } else {
            sql = sql + "  ";
        }

        if (catId != 0) {
            sql = sql + " where i.category_id=" + catId + " ";
        } else {
            sql = sql + "";
        }

        sql = sql + " ";
        Double sum = 0.00;
        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sum = rs.getDouble("tot");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }

        return sum;
    }

    @Override
    public List<BinCardStockResponse> binCardStock(BinCardItemStockRequest request) {

        List<BinCardStockResponse> resp = new ArrayList<>();

        List<BinCardItemStockResponse> responses = getBinCardItemStock(request);

        for (BinCardItemStockResponse response:responses){

            BinCardStockResponse stockResponse = new BinCardStockResponse();

            stockResponse.setItemCode(response.getItemCode());
            stockResponse.setItemName(response.getItemName());
            stockResponse.setBatchNo(response.getBatchNo());

            List<BinCardSizeStockResponse> res = new ArrayList<>();

            if (request.getBranchId()!=null){
                List<Stock> list = stockRepository.getByBranchAndItemCode(request.getBranchId(), response.getItemCode());
                if (!list.isEmpty()){
                    for (Stock stock:list){
                        BinCardSizeStockResponse sizeStockResponse = new BinCardSizeStockResponse();
                        sizeStockResponse.setSizeId(stock.getStockPK().getSize());
                        sizeStockResponse.setQty(stock.getStoresQty());
                        res.add(sizeStockResponse);
                    }
                }
            }else {
                List<getAllBincardStockBycodeResponse>  list1 = stockRepository.getAllBincardStockBycode(response.getItemCode());
                if (!list1.isEmpty()){
                    for (getAllBincardStockBycodeResponse stock:list1){
                        BinCardSizeStockResponse sizeStockResponse = new BinCardSizeStockResponse();
                        sizeStockResponse.setSizeId(stock.getSIZE_ID());
                        sizeStockResponse.setQty(stock.getQTY());
                        res.add(sizeStockResponse);
                    }
                }
            }

            stockResponse.setResponseList(res);
            resp.add(stockResponse);
        }

        return resp;
    }

    @Override
    public List<getAllStockByItemResponse> getItemCode() {
        List<getAllStockByItemResponse> list = stockRepository.getItemCode();
        return list;
    }

    @Override
    public StockResponse getByItemCode(String itemCode) {
        Stock stock = stockRepository.getByItemCode(itemCode);
        return convert(stock);
    }

    List<BinCardItemStockResponse> getBinCardItemStock(BinCardItemStockRequest request) {

        List<BinCardItemStockResponse> resp = new ArrayList<>();

        String sql = " select s.item_code,i.item_name,s.batch_no from stock s left join item_master i on i.item_code=s.item_code " +
                " where s.is_deleted=0  ";

        if (request.getFromDate()!=null && request.getFromDate()!=null) {
            sql = sql + " and s.modified_date_time between '"+request.getFromDate()+"' and '"+request.getToDate()+"'  ";
        }

        if (request.getBranchId()!=null) {
            sql = sql + " and s.branch_id="+request.getBranchId()+" ";
        }

        if (request.getItemCode()!=null){
            sql = sql + " and s.item_code='"+request.getItemCode()+"' ";
        }

        if (request.getCatId()!=null){
            sql = sql + " and i.category_id="+request.getCatId()+" ";
        }

        sql = sql + "  group by s.item_code  ";

        System.out.println(sql);
        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BinCardItemStockResponse response= new BinCardItemStockResponse();
                response.setItemCode(rs.getString("s.item_code"));
                response.setItemName(rs.getString("i.item_name"));
                response.setBatchNo(rs.getString("s.batch_no"));
                resp.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }
        return resp;
    }

}