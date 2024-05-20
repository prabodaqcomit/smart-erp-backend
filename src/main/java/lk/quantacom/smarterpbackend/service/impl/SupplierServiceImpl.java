package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierRequest;
import lk.quantacom.smarterpbackend.dto.request.SupplierUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.SupplierAgencyResponse;
import lk.quantacom.smarterpbackend.dto.response.SupplierResponse;
import lk.quantacom.smarterpbackend.dto.response.SupplierSalesRepResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.LedgerCommonSelectionsService;
import lk.quantacom.smarterpbackend.service.SupplierService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierAgencyRepository supplierAgencyRepository;

    @Autowired
    private SupplierSalesRepRepository supplierSalesRepRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private LadgerAccountRepository ladgerAccountRepository;

    @Autowired
    private LedgerTypesRepository ledgerTypesRepository;

    @Autowired
    private LedgerCommonSelectionsService ledgerCommonSelectionsService;

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public SupplierResponse save(SupplierRequest request) {

        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setTHome(request.getTHome());
        supplier.setTMobile(request.getTMobile());
        supplier.setTOffice(request.getTOffice());
        supplier.setFax(request.getFax());
        supplier.setEmail(request.getEmail());
        supplier.setSalesPerson(request.getSalesPerson());
        supplier.setVat(request.getVat());
        supplier.setCreditLimit(request.getCreditLimit());
        supplier.setAvCreditLimit(request.getAvCreditLimit());
        supplier.setManufacture(request.getManufacture());
        supplier.setDivision(request.getDivision());
        supplier.setAgencyName(request.getAgencyName());
        supplier.setWebsite(request.getWebsite());
        supplier.setBrands(request.getBrands());

        String imageName = "";
        if (!request.getSupImage().isEmpty()) {
            try {

                File fol = new File(Settings.SUPPLIER_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                String[] fArray = request.getSupImage().split(";");

                String fileEx = fArray[0];
                String base64 = fArray[1];

                byte[] data = Base64.decodeBase64(base64);

                imageName = System.currentTimeMillis() + "." + fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.SUPPLIER_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        supplier.setSupImage(imageName);
        supplier.setSupBankName(request.getSupBankName());
        supplier.setSupBankBranch(request.getSupBankBranch());
        supplier.setSupBankAccType(request.getSupBankAccType());
        supplier.setSupBankAccNo(request.getSupBankAccNo());
        supplier.setSupBankAccName(request.getSupBankAccName());
        supplier.setSupplierLedgerId(request.getSupplierLedgerId());
        supplier.setBranchId(request.getBranchId());
        supplier.setIsDeleted(Deleted.NO);
        Supplier save = supplierRepository.save(supplier);

        if (request.getSupplierAgencyRequest() != null) {

            SupplierAgency supplierAgency = new SupplierAgency();
            supplierAgency.setSupplier(save);
            supplierAgency.setSupAgencyName(request.getSupplierAgencyRequest().getSupAgencyName());
            supplierAgency.setSupAgencyAddress(request.getSupplierAgencyRequest().getSupAgencyAddress());
            supplierAgency.setSupAEmail(request.getSupplierAgencyRequest().getSupAEmail());
            supplierAgency.setSupAMobileNo(request.getSupplierAgencyRequest().getSupAMobileNo());
            supplierAgency.setSupAHomeNo(request.getSupplierAgencyRequest().getSupAHomeNo());
            supplierAgency.setSupAFaxNo(request.getSupplierAgencyRequest().getSupAFaxNo());
            supplierAgency.setSupAWebSite(request.getSupplierAgencyRequest().getSupAWebSite());
            supplierAgency.setIsDeleted(Deleted.NO);
            supplierAgencyRepository.save(supplierAgency);

        }

        if (request.getSupplierSalesRepRequest() != null) {

            SupplierSalesRep supplierSalesRep = new SupplierSalesRep();
            supplierSalesRep.setSupplier(save);
            supplierSalesRep.setSalesrepName(request.getSupplierSalesRepRequest().getSalesrepName());
            supplierSalesRep.setRepNicNo(request.getSupplierSalesRepRequest().getRepNicNo());
            supplierSalesRep.setRepVehicleNo(request.getSupplierSalesRepRequest().getRepVehicleNo());
            supplierSalesRep.setRepEmail(request.getSupplierSalesRepRequest().getRepEmail());
            supplierSalesRep.setRepMobileNo(request.getSupplierSalesRepRequest().getRepMobileNo());
            supplierSalesRep.setRepHomeNo(request.getSupplierSalesRepRequest().getRepHomeNo());
            supplierSalesRep.setRepFaxNo(request.getSupplierSalesRepRequest().getRepFaxNo());
            supplierSalesRep.setIsDeleted(Deleted.NO);
            supplierSalesRepRepository.save(supplierSalesRep);

        }

        String accNo=Settings.readSettings("CREDIT_SUPPLIER_ACC")+getMaxCreditAccNo();

        LadgerAccount clsLedgerAccount = new LadgerAccount();

        clsLedgerAccount.setAccountCategory("LIABILITY");
        clsLedgerAccount.setAccType("CURRENT LIABILITIES (SHORT-TERM LIABILITIES)");
        clsLedgerAccount.setSubAccType("ACCOUNTS PAYABLES");
        clsLedgerAccount.setAccNo(accNo);
        clsLedgerAccount.setAccName("Creditor-" + request.getName().split(" ")[0]);
        clsLedgerAccount.setObBalance(0.0);
        clsLedgerAccount.setCurrentBalance(0.0);
        clsLedgerAccount.setGeneratedNo(Settings.readSettings("CREDIT_SUPPLIER_ACC"));
        clsLedgerAccount.setOwnNo(getMaxCreditAccNo());
        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        clsLedgerAccount.setBranch(branchNetwork);
        clsLedgerAccount.setIsDefault(false);
        clsLedgerAccount.setIsDeleted(Deleted.NO);
        LadgerAccount savedLa = ladgerAccountRepository.save(clsLedgerAccount);

        LedgerRecords ledgerRecords = new LedgerRecords();

        for (LedgerCommonSelectionsRequest selections : ledgerRecords.getCommonLedgerList(savedLa.getId() + "",
                savedLa.getAccNo(), "supplier")) {

            ledgerCommonSelectionsService.save(selections);
        }

        saveLog("Supplier", "Data Saved - " + save.getId());


        return getById(save.getId());
    }

    @Override
    @Transactional
    public SupplierResponse update(SupplierUpdateRequest request) {

        Supplier supplier = supplierRepository.findById(request.getId()).orElse(null);
        if (supplier == null) {
            return null;
        }

        supplier.setId(request.getId());
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setTHome(request.getTHome());
        supplier.setTMobile(request.getTMobile());
        supplier.setTOffice(request.getTOffice());
        supplier.setFax(request.getFax());
        supplier.setEmail(request.getEmail());
        supplier.setSalesPerson(request.getSalesPerson());
        supplier.setVat(request.getVat());
        supplier.setCreditLimit(request.getCreditLimit());
        supplier.setAvCreditLimit(request.getAvCreditLimit());
        supplier.setManufacture(request.getManufacture());
        supplier.setDivision(request.getDivision());
        supplier.setAgencyName(request.getAgencyName());
        supplier.setWebsite(request.getWebsite());
        supplier.setBrands(request.getBrands());
        String imageName = "";
        if (!request.getSupImage().isEmpty()) {
            try {

                File fol = new File(Settings.SUPPLIER_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                String fileEx = "jpg";
                String base64 = request.getSupImage();

                if (request.getSupImage().contains(";")) {
                    String[] fArray = request.getSupImage().split(";");

                    fileEx = fArray[0];
                    base64 = fArray[1];
                }

                byte[] data = Base64.decodeBase64(base64);

                imageName = System.currentTimeMillis() + "." + fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.SUPPLIER_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        supplier.setSupImage(imageName);
        supplier.setSupBankName(request.getSupBankName());
        supplier.setSupBankBranch(request.getSupBankBranch());
        supplier.setSupBankAccType(request.getSupBankAccType());
        supplier.setSupBankAccNo(request.getSupBankAccNo());
        supplier.setSupBankAccName(request.getSupBankAccName());
        supplier.setSupplierLedgerId(request.getSupplierLedgerId());
        supplier.setBranchId(request.getBranchId());
        supplier.setIsDeleted(request.getIsDeleted());

        Supplier updated = supplierRepository.save(supplier);

        if (request.getSupplierAgencyRequest() != null) {

            SupplierAgency supplierAgency = supplier.getSupplierAgency();
            supplierAgency.setSupAgencyName(request.getSupplierAgencyRequest().getSupAgencyName());
            supplierAgency.setSupAgencyAddress(request.getSupplierAgencyRequest().getSupAgencyAddress());
            supplierAgency.setSupAEmail(request.getSupplierAgencyRequest().getSupAEmail());
            supplierAgency.setSupAMobileNo(request.getSupplierAgencyRequest().getSupAMobileNo());
            supplierAgency.setSupAHomeNo(request.getSupplierAgencyRequest().getSupAHomeNo());
            supplierAgency.setSupAFaxNo(request.getSupplierAgencyRequest().getSupAFaxNo());
            supplierAgency.setSupAWebSite(request.getSupplierAgencyRequest().getSupAWebSite());
            supplierAgency.setIsDeleted(Deleted.NO);
            supplierAgencyRepository.save(supplierAgency);

        }

        if (request.getSupplierSalesRepRequest() != null) {

            SupplierSalesRep supplierSalesRep = supplier.getSupplierSalesRep();
            supplierSalesRep.setSalesrepName(request.getSupplierSalesRepRequest().getSalesrepName());
            supplierSalesRep.setRepNicNo(request.getSupplierSalesRepRequest().getRepNicNo());
            supplierSalesRep.setRepVehicleNo(request.getSupplierSalesRepRequest().getRepVehicleNo());
            supplierSalesRep.setRepEmail(request.getSupplierSalesRepRequest().getRepEmail());
            supplierSalesRep.setRepMobileNo(request.getSupplierSalesRepRequest().getRepMobileNo());
            supplierSalesRep.setRepHomeNo(request.getSupplierSalesRepRequest().getRepHomeNo());
            supplierSalesRep.setRepFaxNo(request.getSupplierSalesRepRequest().getRepFaxNo());
            supplierSalesRep.setIsDeleted(Deleted.NO);
            supplierSalesRepRepository.save(supplierSalesRep);

        }

        saveLog("Supplier", "Data Updated - " + updated.getId());


        return (convert(updated));
    }

    @Override
    public SupplierResponse getById(Long id) {

        return supplierRepository.findById(id).map(SupplierServiceImpl::convertSingle).orElse(null);
    }

    @Override
    public List<SupplierResponse> getAll() {

        return supplierRepository.findAll()
                .stream().map(SupplierServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public String getMaxCreditAccNo() {
        String ownNo = ladgerAccountRepository.getMaxOwnNoSup();
        if (ownNo == null) {
            ownNo = "1";
        }else{
            ownNo=(Integer.parseInt(ownNo)+1)+"";
        }
        return ownNo;
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        Supplier got = supplierRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        supplierRepository.save(got);

        saveLog("Supplier", "Data Delete - " + id);

        return 1;
    }

    @Override
    public File printProfile(Long id) {

        try {

//            String report = Settings.readSettings("REPORT_PATH") + "Reports/Registration/Supplier Information Sheet.jrxml";
//            Map<String, Object> params = new HashMap<String, Object>();
//
//            params.put("CustId", id);
//            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
//            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
//            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
//            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
//            params.put("EMAIL", Settings.readSettings("EMAIL"));
//            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));
//            params.put("IMAGE_PATH", Settings.CUSTOMER_IMAGES);

//            JasperReport jasprereport = JasperCompileManager.compileReport(report);
//            JasperPrint jasperprint = JasperFillManager.fillReport(jasprereport, params, JDBC.con());
//            //JasperViewer.viewReport(jasperprint, false);
//            File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
//            if (!perentFol.exists()) {
//                perentFol.mkdirs();
//            }
            File pdf = new File(Settings.readSettings("REPORT_PATH") + "Reports/Registration/supplier_application_form.pdf");
//            JasperExportManager.exportReportToPdfStream(jasperprint, new FileOutputStream(pdf));

            return pdf;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static SupplierResponse convert(Supplier supplier) {

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

    private static SupplierResponse convertSingle(Supplier supplier) {

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
        String base64 = "";
        if (!supplier.getSupImage().isEmpty()) {
            File img = new File(Settings.SUPPLIER_IMAGES + supplier.getSupImage());
            if (img.exists()) {
                base64 = img.getName().substring(img.getName().lastIndexOf(".") + 1) +
                        ";" + encodeFileToBase64Binary(img);
            }
        }
        typeResponse.setSupImage(base64);
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