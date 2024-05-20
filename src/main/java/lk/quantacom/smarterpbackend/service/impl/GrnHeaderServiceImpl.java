package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GrnDetailsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnHeaderRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnHeaderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.GrnHeaderResponse;
import lk.quantacom.smarterpbackend.dto.response.GrnPaymentsResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.GrnHeaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrnHeaderServiceImpl implements GrnHeaderService {

    @Autowired
    private GrnHeaderRepository grnHeaderRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private GrnDetailsRepository grnDetailsRepository;

    @Autowired
    private GrnPaymentsRepository grnPaymentsRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private GrnServiceItemsRepository grnServiceItemsRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GrnHeaderResponse save(GrnHeaderRequest request) {

        GrnHeader grnHeader = new GrnHeader();
        grnHeader.setSupplierId(request.getSupplierId());
        grnHeader.setBranchId(request.getBranchId());
        grnHeader.setGrnDate(request.getGrnDate() == null ? null : ConvertUtils.convertStrToDate(request.getGrnDate()));
        grnHeader.setSupInDate(request.getSupInDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInDate()));
        grnHeader.setSupInNo(request.getSupInNo());
        grnHeader.setSalesPerson(request.getSalesPerson());
        grnHeader.setSalesPersonTel(request.getSalesPersonTel());
        grnHeader.setGrnAgencyName(request.getGrnAgencyName());
        grnHeader.setRemarks(request.getRemarks());
        grnHeader.setIsDeleted(Deleted.NO);
        GrnHeader save = grnHeaderRepository.save(grnHeader);

        for (GrnDetailsRequest detailsRequest : request.getGrnDetails()) {

            if(!detailsRequest.getIsService()){
                GrnDetails grnDetails = new GrnDetails();

                ItemMaster item = new ItemMaster();
                item.setId(detailsRequest.getItemId());
                grnDetails.setItem(item);
                grnDetails.setUnitPrice(detailsRequest.getUnitPrice());
                grnDetails.setQty(detailsRequest.getQty());
                grnDetails.setPoNumber(detailsRequest.getPoNumber());
                grnDetails.setItemPrice(detailsRequest.getItemPrice());
                grnDetails.setItemDisc(detailsRequest.getItemDisc());
                grnDetails.setItemDicPrice(detailsRequest.getItemDicPrice());
                grnDetails.setItemVat(detailsRequest.getItemVat());
                grnDetails.setItemVatPrice(detailsRequest.getItemVatPrice());
                grnDetails.setItemValue(detailsRequest.getItemValue());
                grnDetails.setItemTotal(detailsRequest.getItemTotal());
                grnDetails.setGrnUnit(detailsRequest.getGrnUnit());
                grnDetails.setExpireDate(detailsRequest.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(detailsRequest.getExpireDate()));
                grnDetails.setGrnCashPrice(detailsRequest.getGrnCashPrice());
                grnDetails.setGrnCreditPrice(detailsRequest.getGrnCreditPrice());
                grnDetails.setBarcodeNo(detailsRequest.getBarcodeNo());
                grnDetails.setItemProfitMargin(detailsRequest.getItemProfitMargin());
                grnDetails.setItemProfitValue(detailsRequest.getItemProfitValue());
                grnDetails.setUnitPriceRetail(detailsRequest.getUnitPriceRetail());
                grnDetails.setUnitPriceWholesale(detailsRequest.getUnitPriceWholesale());
                grnDetails.setGrn(save);
                grnDetails.setBatchNo(detailsRequest.getBatchNo());
                grnDetails.setLineNo(detailsRequest.getLineNo());
                grnDetails.setColorId(detailsRequest.getColorId());
                grnDetails.setSizeId(detailsRequest.getSizeId());
                grnDetails.setFitId(detailsRequest.getFitId());
                BranchNetwork branch = new BranchNetwork();
                branch.setId(detailsRequest.getBranchId());
                grnDetails.setBranch(branch);
                grnDetails.setIsDeleted(Deleted.NO);
                grnDetailsRepository.save(grnDetails);

                // Bin Card
                BinCard binCard = new BinCard();
                binCard.setItem(item);
                binCard.setBinCardDate(new Date());
                binCard.setDocNo(save.getId() + "");
                binCard.setDocType("GOOD RECEIVED NOTE");
                binCard.setRecQty(detailsRequest.getQty());
                binCard.setIsueQty(0.0);
                binCard.setBalanceQty(detailsRequest.getQty());
                binCard.setBatchNo(detailsRequest.getBatchNo());
                BranchNetwork branchNetwork = new BranchNetwork();
                branchNetwork.setId(request.getBranchId());
                binCard.setBranch(branchNetwork);
                binCard.setIsDeleted(Deleted.NO);
                binCardRepository.save(binCard);

                if (detailsRequest.getPoNumber() != null && detailsRequest.getPoNumber() != 0) {
                    List<PurchaseOrder> po1 = purchaseOrderRepository.findByPoIdAndItemCodeAndIsDeleted(detailsRequest.getPoNumber(), detailsRequest.getItemId(), Deleted.NO);
                    for (PurchaseOrder po:po1){
                        if (po != null) {
                            double grnQty = po.getGrnCompletedQty() == null ? 0.0 : po.getGrnCompletedQty();
                            double poQty = po.getOrderQty();
                            double updQty = grnQty + detailsRequest.getQty();
//                        if ((poQty - grnQty) >= updQty) {
//                            updQty = poQty;
//                        }
                            po.setGrnCompletedQty(updQty);
                            purchaseOrderRepository.save(po);
                        }
                    }
                }

//            Color color = null;
//            Size size = null;
//            Fit fit = null;
//
//            if (detailsRequest.getColorId() != 0) {
//                color = new Color();
//                color.setId(detailsRequest.getColorId());
//            }
//
//            if (detailsRequest.getSizeId() != 0) {
//                size = new Size();
//                size.setId(detailsRequest.getSizeId());
//            }
//
//            if (detailsRequest.getFitId() != 0) {
//                fit = new Fit();
//                fit.setId(detailsRequest.getFitId());
//            }

                StockPK stockPK = new StockPK(item, detailsRequest.getColorId(), detailsRequest.getSizeId(), detailsRequest.getFitId(), branchNetwork, detailsRequest.getBatchNo());

                Stock stock = stockRepository.findById(stockPK).orElse(null);

                if (stock != null) {
                    double avlblQty = stock.getAvailabQty();
                    double totalQty = stock.getTotalQty();

                    double newStkQty = detailsRequest.getQty() + totalQty;

                    double avrgP = ((detailsRequest.getUnitPrice() * detailsRequest.getQty()) +
                            (stock.getTotalQty() * stock.getAvrgPrice())) / (detailsRequest.getQty() * stock.getTotalQty());

                    stock.setSalesDiscoPresentage(detailsRequest.getSalesDiscPer());
                    stock.setCashDisValue(detailsRequest.getCashDiscValue());
                    stock.setCreditDisValue(detailsRequest.getCreditDiscValue());
                    stock.setStockUnitPriceRetail(detailsRequest.getUnitPriceRetail());
                    stock.setStockUnitPriceWholesale(detailsRequest.getUnitPriceWholesale());
                    stock.setStkCashPrice(detailsRequest.getGrnCashPrice());
                    stock.setStkCreditPrice(detailsRequest.getGrnCreditPrice());
                    stock.setExpireDate(detailsRequest.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(detailsRequest.getExpireDate()));

                    stock.setStkQty(avlblQty + detailsRequest.getQty());
                    stock.setTotalQty(newStkQty);
                    stock.setAvrgPrice(avrgP);

                    if (detailsRequest.getStockOption().equals("store")) {
                        double storeQty = stock.getStoresQty() == null ? 0.0 : stock.getStoresQty();
                        double newStoresQty = storeQty + detailsRequest.getQty();
                        stock.setStoresQty(newStoresQty);
                        stock.setTotalQty(stock.getTotalQty()+newStoresQty);
                    } else {
                        double shopQty = stock.getAvailabQty() == null ? 0.0 : stock.getAvailabQty();
                        double newShopQty = shopQty + detailsRequest.getQty();
                        stock.setAvailabQty(newShopQty);
                        stock.setTotalQty(stock.getTotalQty()+newShopQty);
                    }
//                stock.setSize(size);
//                stock.setColor(color);
//                stock.setFit(fit);
                    stockRepository.save(stock);
                } else {
//

                    stock = new Stock();
                    stock.setStkQty(detailsRequest.getQty());
                    stock.setDamgQty(0.0);
                    stock.setAvrgPrice(detailsRequest.getUnitPrice());
                    stock.setExpireDate(detailsRequest.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(detailsRequest.getExpireDate()));
                    stock.setStkCashPrice(detailsRequest.getGrnCashPrice());
                    stock.setStkCreditPrice(detailsRequest.getGrnCreditPrice());

                    stock.setStoresQty(detailsRequest.getQty());
                    stock.setTotalQty(detailsRequest.getQty());
                    stock.setAvailabQty(0.00);

                    // fixed changes

//                    if (detailsRequest.getStockOption().equals("store")) {
//                        stock.setStoresQty(detailsRequest.getQty());
//                        stock.setTotalQty(detailsRequest.getQty());
//                        stock.setAvailabQty(0.00);
//                    } else {
//                        stock.setStoresQty(0.00);
//                        stock.setAvailabQty(detailsRequest.getQty());
//                        stock.setTotalQty(detailsRequest.getQty());
//                    }

                    stock.setBarcodeNo(detailsRequest.getBarcodeNo());
                    stock.setStockUnitPriceRetail(detailsRequest.getUnitPriceRetail());
                    stock.setStockUnitPriceWholesale(detailsRequest.getUnitPriceWholesale());
                    Supplier supplier = new Supplier();
                    supplier.setId(request.getSupplierId());
                    stock.setSupplier(supplier);
                    stock.setObStock(detailsRequest.getQty());
                    stock.setCashDisValue(detailsRequest.getCashDiscValue());
                    stock.setCreditDisValue(detailsRequest.getCreditDiscValue());
                    stock.setSalesDiscoPresentage(detailsRequest.getSalesDiscPer());
                    stock.setMaterialWidth(detailsRequest.getWidth());
//                stock.setItem(item);
//                stock.setColor(color);
//                stock.setSize(size);
//                stock.setFit(fit);
//                stock.setBranch(branchNetwork);
//                stock.setBatchNo(detailsRequest.getBatchNo());
                    stock.setStockPK(stockPK);
                    stock.setIsDeleted(Deleted.NO);
                    stockRepository.save(stock);

                }


                ItemMaster itemM = itemMasterRepository.findById(item.getId()).orElse(null);
                itemM.setBuyingPrice(detailsRequest.getBuyingPrice());
                itemM.setWholesaleSellPrice(detailsRequest.getGrnCreditPrice());
                itemM.setRetailSellPrice(detailsRequest.getGrnCashPrice());
                itemM.setUnitPriceWholesale(detailsRequest.getUnitPriceWholesale());
                itemM.setUnitPriceRetail(detailsRequest.getUnitPriceRetail());
                itemM.setUnitPrice(detailsRequest.getUnitPrice());

                itemMasterRepository.save(itemM);
            }else{

                GrnServiceItems grnDetails = new GrnServiceItems();

                ItemMaster item = new ItemMaster();
                item.setId(detailsRequest.getItemId());
                grnDetails.setItem(item);
                grnDetails.setUnitPrice(detailsRequest.getUnitPrice());
                grnDetails.setQty(detailsRequest.getQty());
                grnDetails.setPoNumber(detailsRequest.getPoNumber());
                grnDetails.setItemValue(detailsRequest.getItemValue());
                grnDetails.setItemTotal(detailsRequest.getItemTotal());
                grnDetails.setGrnUnit(detailsRequest.getGrnUnit());
                grnDetails.setGrn(save);
                BranchNetwork branch = new BranchNetwork();
                branch.setId(detailsRequest.getBranchId());
                grnDetails.setBranch(branch);
                grnDetails.setIsDeleted(Deleted.NO);
                grnServiceItemsRepository.save(grnDetails);

            }


        }
        for (GrnPaymentsRequest paymentsRequest : request.getGrnPayments()) {

            GrnPayments grnPayments = new GrnPayments();
            grnPayments.setGrossAmount(paymentsRequest.getGrossAmount());
            grnPayments.setTotalDis(paymentsRequest.getTotalDis());
            grnPayments.setTotalVat(paymentsRequest.getTotalVat());
            grnPayments.setNetAmount(paymentsRequest.getNetAmount());
            grnPayments.setPaidAmount(paymentsRequest.getPaidAmount());
            grnPayments.setDueAmount(paymentsRequest.getDueAmount());
            grnPayments.setPayMode(paymentsRequest.getPayMode());
            grnPayments.setRemarks(paymentsRequest.getRemarks());
            grnPayments.setGrnOverpaid(paymentsRequest.getGrnOverpaid());
            grnPayments.setTotalProfitValue(paymentsRequest.getTotalProfitValue());
            grnPayments.setNetProfitValue(paymentsRequest.getNetProfitValue());
            grnPayments.setGrn(save);
            grnPayments.setLineNo(paymentsRequest.getLineNo());
            BranchNetwork branch = new BranchNetwork();
            branch.setId(request.getBranchId());
            grnPayments.setBranch(branch);
            grnPayments.setIsDeleted(Deleted.NO);
            grnPaymentsRepository.save(grnPayments);

        }

        saveLog("GrnHeader/Details/Payment", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public GrnHeaderResponse saveSupOB(GrnHeaderRequest request) {

        GrnHeader grnHeader = new GrnHeader();
        grnHeader.setSupplierId(request.getSupplierId());
        grnHeader.setBranchId(request.getBranchId());
        grnHeader.setGrnDate(request.getGrnDate() == null ? null : ConvertUtils.convertStrToDate(request.getGrnDate()));
        grnHeader.setSupInDate(request.getSupInDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInDate()));
        grnHeader.setSupInNo(request.getSupInNo());
        grnHeader.setSalesPerson(request.getSalesPerson());
        grnHeader.setSalesPersonTel(request.getSalesPersonTel());
        grnHeader.setGrnAgencyName(request.getGrnAgencyName());
        grnHeader.setRemarks(request.getRemarks());
        grnHeader.setIsDeleted(Deleted.NO);
        GrnHeader save = grnHeaderRepository.save(grnHeader);

        for (GrnDetailsRequest detailsRequest : request.getGrnDetails()) {

            GrnDetails grnDetails = new GrnDetails();

//            ItemMaster item = new ItemMaster();
//            item.setId(detailsRequest.getItemId());
//            grnDetails.setItem(item);
            grnDetails.setUnitPrice(detailsRequest.getUnitPrice());
            grnDetails.setQty(detailsRequest.getQty());
            grnDetails.setItemPrice(detailsRequest.getItemPrice());
            grnDetails.setItemDisc(detailsRequest.getItemDisc());
            grnDetails.setItemDicPrice(detailsRequest.getItemDicPrice());
            grnDetails.setItemVat(detailsRequest.getItemVat());
            grnDetails.setItemVatPrice(detailsRequest.getItemVatPrice());
            grnDetails.setItemValue(detailsRequest.getItemValue());
            grnDetails.setItemTotal(detailsRequest.getItemTotal());
            grnDetails.setGrnUnit(detailsRequest.getGrnUnit());
            grnDetails.setExpireDate(detailsRequest.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(detailsRequest.getExpireDate()));
            grnDetails.setGrnCashPrice(detailsRequest.getGrnCashPrice());
            grnDetails.setGrnCreditPrice(detailsRequest.getGrnCreditPrice());
            grnDetails.setBarcodeNo(detailsRequest.getBarcodeNo());
            grnDetails.setItemProfitMargin(detailsRequest.getItemProfitMargin());
            grnDetails.setItemProfitValue(detailsRequest.getItemProfitValue());
            grnDetails.setUnitPriceRetail(detailsRequest.getUnitPriceRetail());
            grnDetails.setUnitPriceWholesale(detailsRequest.getUnitPriceWholesale());
            grnDetails.setGrn(save);
            grnDetails.setBatchNo(detailsRequest.getBatchNo());
            grnDetails.setLineNo(detailsRequest.getLineNo());
            grnDetails.setColorId(detailsRequest.getColorId());
            grnDetails.setSizeId(detailsRequest.getSizeId());
            grnDetails.setFitId(detailsRequest.getFitId());
            BranchNetwork branch = new BranchNetwork();
            branch.setId(detailsRequest.getBranchId());
            grnDetails.setBranch(branch);
            grnDetails.setIsDeleted(Deleted.NO);
            grnDetailsRepository.save(grnDetails);


        }
        for (GrnPaymentsRequest paymentsRequest : request.getGrnPayments()) {

            GrnPayments grnPayments = new GrnPayments();
            grnPayments.setGrossAmount(paymentsRequest.getGrossAmount());
            grnPayments.setTotalDis(paymentsRequest.getTotalDis());
            grnPayments.setTotalVat(paymentsRequest.getTotalVat());
            grnPayments.setNetAmount(paymentsRequest.getNetAmount());
            grnPayments.setPaidAmount(paymentsRequest.getPaidAmount());
            grnPayments.setDueAmount(paymentsRequest.getDueAmount());
            grnPayments.setPayMode(paymentsRequest.getPayMode());
            grnPayments.setRemarks(paymentsRequest.getRemarks());
            grnPayments.setGrnOverpaid(paymentsRequest.getGrnOverpaid());
            grnPayments.setTotalProfitValue(paymentsRequest.getTotalProfitValue());
            grnPayments.setNetProfitValue(paymentsRequest.getNetProfitValue());
            grnPayments.setGrn(save);
            grnPayments.setLineNo(paymentsRequest.getLineNo());
            BranchNetwork branch = new BranchNetwork();
            branch.setId(request.getBranchId());
            grnPayments.setBranch(branch);
            grnPayments.setIsDeleted(Deleted.NO);
            grnPaymentsRepository.save(grnPayments);

        }


        //----- Ledger recordscd


        saveLog("GrnHeader/Details/Payment", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public GrnHeaderResponse update(GrnHeaderUpdateRequest request) {

        GrnHeader grnHeader = grnHeaderRepository.findById(request.getId()).orElse(null);
        if (grnHeader == null) {
            return null;
        }

        grnHeader.setId(request.getId());
        grnHeader.setSupplierId(request.getSupplierId());
        grnHeader.setBranchId(request.getBranchId());
        grnHeader.setGrnDate(request.getGrnDate() == null ? null : ConvertUtils.convertStrToDate(request.getGrnDate()));
        grnHeader.setSupInDate(request.getSupInDate() == null ? null : ConvertUtils.convertStrToDate(request.getSupInDate()));
        grnHeader.setSupInNo(request.getSupInNo());
        grnHeader.setSalesPerson(request.getSalesPerson());
        grnHeader.setSalesPersonTel(request.getSalesPersonTel());
        grnHeader.setGrnAgencyName(request.getGrnAgencyName());
        grnHeader.setRemarks(request.getRemarks());
        GrnHeader updated = grnHeaderRepository.save(grnHeader);

        saveLog("GrnHeader", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public GrnHeaderResponse getById(Long id) {

        return grnHeaderRepository.findById(id).map(GrnHeaderServiceImpl::convert2).orElse(null);
    }

    @Override
    public List<GrnHeaderResponse> getAll() {
        List<GrnHeaderResponse> list = grnHeaderRepository.findAll().stream()
                .sorted(Comparator.comparingLong(GrnHeader::getId).reversed())
                .map(this::convert3)
                .collect(Collectors.toList());
        return list;
//        List<GrnHeaderResponse> list = new ArrayList<>();
//        List<GrnHeader> grnHeader= grnHeaderRepository.findAll().stream()
//                .sorted(Comparator.comparingLong(GrnHeader::getId).reversed())
//                .collect(Collectors.toList());
//        for (GrnHeader header :grnHeader){
//            GrnHeaderResponse response = convert3(header);
//            list.add(response);
//        }
//        return list;
    }

    @Override
    public List<String> getGrnIds() {

        List<String> stringList = new ArrayList<>();

        List<GrnHeader> list = grnHeaderRepository.findByIsDeleted(Deleted.NO);
        for (GrnHeader header : list) {
            stringList.add(header.getId() + "");
        }

        return stringList;
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GrnHeader got = grnHeaderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        grnHeaderRepository.save(got);

        saveLog("GrnHeader", "Data Deleted - " + id);

        return 1;
    }

    @Override
    public File grnReport(Integer grnNo, String type) {

        File out = null;
        Connection co = null;
        try {

            String topic = Settings.readSettings("COMPANY_NAME");
            String address = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String address1 = Settings.readSettings("COMPANY_ADDRESS_ONE");
            String telNo = Settings.readSettings("MOBILE_NO");
            String email = Settings.readSettings("EMAIL");
            String logoPath = Settings.readSettings("LOGO_PATH");

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uu = auth.getName();


            File file = new File("JRXML/report/GRN_NOTE.jrxml");
            Map<String, Object> map = new HashMap<>();
            map.put("GrnNo", grnNo);
            map.put("COMPANY_NAME", topic);
            map.put("COMPANY_ADDRESS_ONE", address);
            map.put("COMPANY_ADDRESS_TWO", address1);
            map.put("MOBILE_NO", telNo);
            map.put("EMAIL", email);
            map.put("LOGO_PATH", logoPath);
            map.put("BRANCH_NAME","HEAD OFFICE");


            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            String filepath = "";
            if (type.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
            } else if (type.equals("docx")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".docx";
                JasperExportManager.exportReportToXml(print);
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(print));
                File exportReportFile = new File(filepath);
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportReportFile));
                exporter.exportReport();
            }


            out = new File(filepath);


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }
        return out;
    }

    private static GrnHeaderResponse convert(GrnHeader grnHeader) {

        GrnHeaderResponse typeResponse = new GrnHeaderResponse();
        typeResponse.setSupplierId(grnHeader.getSupplierId());
        typeResponse.setBranchId(grnHeader.getBranchId());
        typeResponse.setGrnDate(ConvertUtils.convertDateToStr(grnHeader.getGrnDate()));
        typeResponse.setSupInDate(ConvertUtils.convertDateToStr(grnHeader.getSupInDate()));
        typeResponse.setSupInNo(grnHeader.getSupInNo());
        typeResponse.setSalesPerson(grnHeader.getSalesPerson());
        typeResponse.setSalesPersonTel(grnHeader.getSalesPersonTel());
        typeResponse.setGrnAgencyName(grnHeader.getGrnAgencyName());
        typeResponse.setRemarks(grnHeader.getRemarks());
        typeResponse.setId(grnHeader.getId());
        typeResponse.setCreatedBy(grnHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnHeader.getModifiedBy());
        typeResponse.setIsDeleted(grnHeader.getIsDeleted());

        return typeResponse;
    }


    private  GrnHeaderResponse convert3(GrnHeader grnHeader) {

        GrnHeaderResponse typeResponse = new GrnHeaderResponse();
        typeResponse.setSupplierId(grnHeader.getSupplierId());
        if (grnHeader.getSupplierId()!=null){
            Supplier supplier = supplierRepository.getById(grnHeader.getSupplierId());
            typeResponse.setSupplierName(supplier.getName());
        }
        typeResponse.setBranchId(grnHeader.getBranchId());
        if (grnHeader.getBranchId()!=null){
            BranchNetwork branchNetwork = branchNetworkRepository.getById(grnHeader.getBranchId());
            typeResponse.setBranchName(branchNetwork.getBranchName());
        }
        typeResponse.setGrnDate(ConvertUtils.convertDateToStr(grnHeader.getGrnDate()));
        typeResponse.setSupInDate(ConvertUtils.convertDateToStr(grnHeader.getSupInDate()));
        typeResponse.setSupInNo(grnHeader.getSupInNo());
        typeResponse.setSalesPerson(grnHeader.getSalesPerson());
        typeResponse.setSalesPersonTel(grnHeader.getSalesPersonTel());
        typeResponse.setGrnAgencyName(grnHeader.getGrnAgencyName());
        typeResponse.setRemarks(grnHeader.getRemarks());
        typeResponse.setId(grnHeader.getId());
        typeResponse.setCreatedBy(grnHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnHeader.getModifiedBy());
        typeResponse.setIsDeleted(grnHeader.getIsDeleted());

        return typeResponse;
    }

    private static GrnHeaderResponse convert2(GrnHeader grnHeader) {

        GrnHeaderResponse typeResponse = new GrnHeaderResponse();
        typeResponse.setSupplierId(grnHeader.getSupplierId());
        typeResponse.setBranchId(grnHeader.getBranchId());
        typeResponse.setGrnDate(ConvertUtils.convertDateToStr(grnHeader.getGrnDate()));
        typeResponse.setSupInDate(ConvertUtils.convertDateToStr(grnHeader.getSupInDate()));
        typeResponse.setSupInNo(grnHeader.getSupInNo());
        typeResponse.setSalesPerson(grnHeader.getSalesPerson());
        typeResponse.setSalesPersonTel(grnHeader.getSalesPersonTel());
        typeResponse.setGrnAgencyName(grnHeader.getGrnAgencyName());
        typeResponse.setRemarks(grnHeader.getRemarks());
        typeResponse.setId(grnHeader.getId());
        typeResponse.setCreatedBy(grnHeader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnHeader.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnHeader.getModifiedBy());
        typeResponse.setIsDeleted(grnHeader.getIsDeleted());

        typeResponse.setGrnDetails(grnHeader.getGrnDetails()
                .stream().map(GrnHeaderServiceImpl::convert).collect(Collectors.toList()));

        typeResponse.setGrnPayments(grnHeader.getGrnPayments() == null ? null :
                grnHeader.getGrnPayments().stream().map(GrnHeaderServiceImpl::convert).collect(Collectors.toList()));

        return typeResponse;
    }

    private static GrnDetailsResponse convert(GrnDetails grnDetails) {

        GrnDetailsResponse typeResponse = new GrnDetailsResponse();
        typeResponse.setItemId(grnDetails.getItem().getId());
        typeResponse.setItemCode(grnDetails.getItem().getItemCode());
        typeResponse.setItemName(grnDetails.getItem().getItemName());
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

    private static GrnPaymentsResponse convert(GrnPayments grnPayments) {

        GrnPaymentsResponse typeResponse = new GrnPaymentsResponse();
        typeResponse.setGrossAmount(grnPayments.getGrossAmount());
        typeResponse.setTotalDis(grnPayments.getTotalDis());
        typeResponse.setTotalVat(grnPayments.getTotalVat());
        typeResponse.setNetAmount(grnPayments.getNetAmount());
        typeResponse.setPaidAmount(grnPayments.getPaidAmount());
        typeResponse.setDueAmount(grnPayments.getDueAmount());
        typeResponse.setPayMode(grnPayments.getPayMode());
        typeResponse.setRemarks(grnPayments.getRemarks());
        typeResponse.setGrnOverpaid(grnPayments.getGrnOverpaid());
        typeResponse.setTotalProfitValue(grnPayments.getTotalProfitValue());
        typeResponse.setNetProfitValue(grnPayments.getNetProfitValue());
        typeResponse.setGrnId(grnPayments.getGrn().getId());
        typeResponse.setLineNo(grnPayments.getLineNo());
        typeResponse.setBranchId(grnPayments.getBranch().getId());
        typeResponse.setId(grnPayments.getId());
        typeResponse.setCreatedBy(grnPayments.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnPayments.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnPayments.getModifiedBy());
        typeResponse.setIsDeleted(grnPayments.getIsDeleted());

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