package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderRequest;
import lk.quantacom.smarterpbackend.dto.request.PurchaseOrderUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.PurchaseOrderResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.BranchNetworkRepository;
import lk.quantacom.smarterpbackend.repository.PurchaseOrderRepository;
import lk.quantacom.smarterpbackend.repository.userAuthLimitsRepository;
import lk.quantacom.smarterpbackend.service.PurchaseOrderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private lk.quantacom.smarterpbackend.repository.userAuthLimitsRepository userAuthLimitsRepository;

    @Override
    public File print(String po, Long branchId) {

        File pdf=null;
        try {

            BranchNetwork branch = branchNetworkRepository.getById(branchId);
            Connection connection = JDBC.con();

            try {

                String report = Settings.readSettings("REPORT_PATH") + "Reports/Inventory/PO_copy.jrxml";
                Map<String, Object> params = new HashMap<String, Object>();

                params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
                params.put("BRANCH_NAME", branch.getBranchName());
                params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
                params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
                params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
                params.put("EMAIL", Settings.readSettings("EMAIL"));
                params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));
                params.put("PONo", po);

                JasperReport jasprereport = JasperCompileManager.compileReport(report);
                JasperPrint jasperprint = JasperFillManager.fillReport(jasprereport, params, connection);
                //JasperViewer.viewReport(jasperprint, false);

                File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
                if (!perentFol.exists()) {
                    perentFol.mkdirs();
                }
                pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
                JasperExportManager.exportReportToPdfStream(jasperprint, new FileOutputStream(pdf));


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdf;
    }

    @Override
    @Transactional
    public List<PurchaseOrderResponse> save(List<PurchaseOrderRequest> requestList) {

        List<PurchaseOrderResponse> responses = new ArrayList<>();

        Long max = purchaseOrderRepository.getMaxId();
        Long newMax = 1L;
        if (max != null) {
            newMax = max + 1;
        }

        for (PurchaseOrderRequest request : requestList) {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setPoDate(request.getPoDate() == null ? null : ConvertUtils.convertStrToDate(request.getPoDate()));
            purchaseOrder.setDeliveryDate(request.getDeliveryDate() == null ? null : ConvertUtils.convertStrToDate(request.getDeliveryDate()));
            purchaseOrder.setInhandQty(request.getInhandQty());
            purchaseOrder.setOrderQty(request.getOrderQty());
            purchaseOrder.setUnitPrice(request.getUnitPrice());
            purchaseOrder.setItemValue(request.getItemValue());
            purchaseOrder.setRemarks(request.getRemarks());
            purchaseOrder.setItemTotal(request.getItemTotal());
            purchaseOrder.setDics(request.getDics());
            purchaseOrder.setVat(request.getVat());
            purchaseOrder.setBarcode(request.getBarcode());
            purchaseOrder.setBatchNo(request.getBatchNo());
            purchaseOrder.setFitId(request.getFitId());
            purchaseOrder.setSizeId(request.getSizeId());
            purchaseOrder.setColorId(request.getColorId());
            purchaseOrder.setGrandTotal(request.getGrandTotal());

            Supplier supplier = new Supplier();
            supplier.setId(request.getSupplierId());
            purchaseOrder.setSupplier(supplier);

            purchaseOrder.setPoUnit(request.getPoUnit());
            purchaseOrder.setStatus(request.getStatus());
            purchaseOrder.setAgencyName(request.getAgencyName());
            purchaseOrder.setIsProcess(request.getIsProcess());
            purchaseOrder.setPoId(newMax);

            ItemMaster item = new ItemMaster();
            item.setId(request.getItemId());
            purchaseOrder.setItem(item);
            purchaseOrder.setItemCode(request.getItemCode());

            BranchNetwork branch = new BranchNetwork();
            branch.setId(request.getBranchId());
            purchaseOrder.setBranch(branch);

            purchaseOrder.setGrnCompletedQty(request.getGrnCompletedQty());
            purchaseOrder.setCurrencyId(request.getCurrencyId());
            purchaseOrder.setAuthorizedBy(request.getAuthorizedBy());
            purchaseOrder.setAuthorized(false);
            purchaseOrder.setGrnCompletedQty(request.getGrnCompletedQty());
            purchaseOrder.setGrnComplete(false);


            purchaseOrder.setIsDeleted(Deleted.NO);
            PurchaseOrder save = purchaseOrderRepository.save(purchaseOrder);

            responses.add(convert(save));
        }

        saveLog("PurchaseOrder", "Data Saved - Bulk");

        return responses;
    }

    @Override
    @Transactional
    public PurchaseOrderResponse update(PurchaseOrderUpdateRequest request) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(request.getId()).orElse(null);
        if (purchaseOrder == null) {
            return null;
        }

        purchaseOrder.setId(request.getId());
        purchaseOrder.setPoDate(request.getPoDate() == null ? null : ConvertUtils.convertStrToDate(request.getPoDate()));
        purchaseOrder.setDeliveryDate(request.getDeliveryDate() == null ? null : ConvertUtils.convertStrToDate(request.getDeliveryDate()));
        purchaseOrder.setInhandQty(request.getInhandQty());
        purchaseOrder.setOrderQty(request.getOrderQty());
        purchaseOrder.setUnitPrice(request.getUnitPrice());
        purchaseOrder.setItemValue(request.getItemValue());
        purchaseOrder.setRemarks(request.getRemarks());
        purchaseOrder.setItemTotal(request.getItemTotal());
        purchaseOrder.setDics(request.getDics());
        purchaseOrder.setVat(request.getVat());
        purchaseOrder.setGrandTotal(request.getGrandTotal());
        Supplier supplier = new Supplier();
        supplier.setId(request.getSupplierId());
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setPoUnit(request.getPoUnit());
        purchaseOrder.setStatus(request.getStatus());
        purchaseOrder.setAgencyName(request.getAgencyName());
        purchaseOrder.setIsProcess(request.getIsProcess());
        purchaseOrder.setPoId(request.getPoId());
        ItemMaster item = new ItemMaster();
        item.setId(request.getItemId());
        purchaseOrder.setItem(item);
        purchaseOrder.setItemCode(request.getItemCode());
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        purchaseOrder.setBranch(branch);
//        purchaseOrder.setGrnCompletedQty(request.getGrnCompletedQty());
//        purchaseOrder.setCurrencyId(request.getCurrencyId());
//        purchaseOrder.setAuthorizedBy(request.getAuthorizedBy());
//        purchaseOrder.setAuthorized(false);
//        purchaseOrder.setGrnCompletedQty(request.getGrnCompletedQty());
//        purchaseOrder.setGrnComplete(false);
        PurchaseOrder updated = purchaseOrderRepository.save(purchaseOrder);

        saveLog("PurchaseOrder", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public PurchaseOrderResponse getById(Long id) {

        return purchaseOrderRepository.findById(id).map(PurchaseOrderServiceImpl::convert).orElse(null);
    }

    @Override
    public List<PurchaseOrderResponse> getByPOId(Long id) {
        return purchaseOrderRepository.findByPoIdAndIsDeleted(id,Deleted.NO)
                .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<PurchaseOrderResponse> getAll() {

        return purchaseOrderRepository.findAll()
                .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());

    }

//    @Override
//    public List<PurchaseOrderResponse> getHeadList() {
//        return purchaseOrderRepository.getHeaderList()
//                .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
//    }

    @Override
    public List<PurchaseOrderResponse> getHeadList(String type) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uu = auth.getName();
        System.out.println(uu + "  ");

        userAuthLimits userAuthLimits = userAuthLimitsRepository.findByUsernameAndIsDeleted(uu,Deleted.NO);
        if (userAuthLimits==null){
            return new ArrayList<>();
        }
        if(type.equals("all")){
            return purchaseOrderRepository.getHeaderListAll(userAuthLimits.getPoAuthLimit())
                    .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
        }else if(type.equals("authorized")){
            return purchaseOrderRepository.getHeaderListAuthorized(userAuthLimits.getPoAuthLimit())
                    .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
        }else{
            return purchaseOrderRepository.getHeaderListNotAuthorized(userAuthLimits.getPoAuthLimit())
                    .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
        }

    }

    @Override
    public List<PurchaseOrderResponse> getHeadListForGrn() {
        return purchaseOrderRepository.getHeaderListForGRN()
                .stream().map(PurchaseOrderServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        PurchaseOrder got = purchaseOrderRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        purchaseOrderRepository.save(got);

        saveLog("PurchaseOrder", "Data Deleted - " + id);

        return 1;
    }

    @Override
    @Transactional
    public String bulkApprove(List<Integer> poNo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uu = auth.getName();
        for (Integer pNo : poNo){
            Integer purchaseOrder = purchaseOrderRepository.UpdateByPoId(uu,pNo.longValue());
            if (purchaseOrder==1){
                return "Updated";
            }
        }
        return "Not Updated";
    }

    private static PurchaseOrderResponse convert(PurchaseOrder purchaseOrder) {

        PurchaseOrderResponse typeResponse = new PurchaseOrderResponse();
        typeResponse.setPoDate(ConvertUtils.convertDateToStr(purchaseOrder.getPoDate()));
        typeResponse.setDeliveryDate(ConvertUtils.convertDateToStr(purchaseOrder.getDeliveryDate()));
        typeResponse.setInhandQty(purchaseOrder.getInhandQty());
        typeResponse.setOrderQty(purchaseOrder.getOrderQty());
        typeResponse.setUnitPrice(purchaseOrder.getUnitPrice());
        typeResponse.setItemValue(purchaseOrder.getItemValue());
        typeResponse.setRemarks(purchaseOrder.getRemarks());
        typeResponse.setItemTotal(purchaseOrder.getItemTotal());
        typeResponse.setDics(purchaseOrder.getDics());
        typeResponse.setVat(purchaseOrder.getVat());
        typeResponse.setGrandTotal(purchaseOrder.getGrandTotal());
        typeResponse.setSupplierId(purchaseOrder.getSupplier().getId());
        typeResponse.setPoUnit(purchaseOrder.getPoUnit());
        typeResponse.setStatus(purchaseOrder.getStatus());
        typeResponse.setAgencyName(purchaseOrder.getAgencyName());
        typeResponse.setIsProcess(purchaseOrder.getIsProcess());
        typeResponse.setPoId(purchaseOrder.getPoId());
        typeResponse.setItemId(purchaseOrder.getItem().getId());
        typeResponse.setItemName(purchaseOrder.getItem().getItemName());
        typeResponse.setItemCode(purchaseOrder.getItemCode());
        typeResponse.setBranchId(purchaseOrder.getBranch().getId());
        typeResponse.setId(purchaseOrder.getId());
        typeResponse.setCreatedBy(purchaseOrder.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(purchaseOrder.getCreatedDateTime()));
        typeResponse.setModifiedBy(purchaseOrder.getModifiedBy());
        typeResponse.setIsDeleted(purchaseOrder.getIsDeleted());

        typeResponse.setSupplierName(purchaseOrder.getSupplier().getName());

        typeResponse.setGrnCompletedQty(purchaseOrder.getGrnCompletedQty());
        typeResponse.setCurrencyId(purchaseOrder.getCurrencyId());
        typeResponse.setAuthorizedBy(purchaseOrder.getAuthorizedBy());
        typeResponse.setAuthorized(purchaseOrder.getAuthorized() != null && purchaseOrder.getAuthorized());
        typeResponse.setGrnCompletedQty(purchaseOrder.getGrnCompletedQty());
        typeResponse.setGrnComplete(purchaseOrder.getGrnComplete() != null && purchaseOrder.getGrnComplete());

        typeResponse.setColorId(purchaseOrder.getColorId());
        typeResponse.setFitId(purchaseOrder.getFitId());
        typeResponse.setSizeId(purchaseOrder.getSizeId());
        typeResponse.setBatchNo(purchaseOrder.getBatchNo());
        typeResponse.setBarcode(purchaseOrder.getBarcode());

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