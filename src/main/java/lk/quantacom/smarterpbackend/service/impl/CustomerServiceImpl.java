package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.CustomerRequest;
import lk.quantacom.smarterpbackend.dto.request.CustomerUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerCommonSelectionsRequest;
import lk.quantacom.smarterpbackend.dto.response.CustomerResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.CustomerRepository;
import lk.quantacom.smarterpbackend.repository.LadgerAccountRepository;
import lk.quantacom.smarterpbackend.repository.LedgerTypesRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.CustomerService;
import lk.quantacom.smarterpbackend.service.LedgerCommonSelectionsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

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
    public CustomerResponse save(CustomerRequest request) {

        Customer customer = new Customer();
        customer.setType(request.getType());
        customer.setVat(request.getVat());
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setGender(request.getGender());
        customer.setTHome(request.getTHome());
        customer.setTMobile(request.getTMobile());
        customer.setTOffice(request.getTOffice());
        customer.setFax(request.getFax());
        customer.setEmail(request.getEmail());
        customer.setCreditLimit(request.getCreditLimit());
        customer.setTPartyName(request.getTPartyName());
        customer.setTPartyMobile(request.getTPartyMobile());
        customer.setTPartyEmail(request.getTPartyEmail());

        customer.setBillDiscLimit(request.getBillDiscLimit());
        customer.setLineDiscLimit(request.getLineDiscLimit());

        String imageName = "";
        if (!request.getImage().isEmpty()) {
            try {

                File fol = new File(Settings.CUSTOMER_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }

                String[] fArray=request.getImage().split(";");

                String fileEx=fArray[0];
                String base64=fArray[1];

                byte[] data = Base64.decodeBase64(base64);

                imageName=System.currentTimeMillis() + "."+fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.CUSTOMER_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        customer.setImage(imageName);

        customer.setAvlbCreditLimit(request.getAvlbCreditLimit());
        customer.setNicNumbr(request.getNicNumbr());

        String crdAccNo=Settings.readSettings("CREIDT_CUSTOMER_ACC")+request.getCreditAccNo();

        customer.setCreditAccNo(crdAccNo);
        customer.setBranchId(request.getBranchId());
        customer.setIsDeleted(Deleted.NO);
        Customer save = customerRepository.save(customer);

//        LedgerTypes clsLedgerAccType = ledgerTypesRepository.findById(Long.parseLong(Settings.readSettings("LEDGER_ACC_TYPE_ID"))).orElse(null);
//
//        LadgerAccount clsLedgerAccount = new LadgerAccount();
//
//        if(clsLedgerAccType!=null){
//            clsLedgerAccount.setAccountCategory(clsLedgerAccType.getAccCategory());
//            clsLedgerAccount.setAccType(clsLedgerAccType.getMainAccType());
//            clsLedgerAccount.setSubAccType(clsLedgerAccType.getSubAccType());
//        }
//        clsLedgerAccount.setAccNo(request.getCreditAccNo());
//        clsLedgerAccount.setAccName("Debtor-" + request.getName().split(" ")[0]);
//        clsLedgerAccount.setObBalance(0.0);
//        clsLedgerAccount.setCurrentBalance(0.0);
//        clsLedgerAccount.setGeneratedNo(Settings.readSettings("CREIDT_CUSTOMER_ACC"));
//        clsLedgerAccount.setOwnNo(request.getCreditAccNo());
//        BranchNetwork branchNetwork = new BranchNetwork();
//        branchNetwork.setId(request.getBranchId());
//        clsLedgerAccount.setBranch(branchNetwork);
//        clsLedgerAccount.setIsDefault(false);
//        clsLedgerAccount.setIsDeleted(Deleted.NO);
//        LadgerAccount savedLa= ladgerAccountRepository.save(clsLedgerAccount);
//
//        LedgerRecords ledgerRecords=new LedgerRecords();
//
//        for (LedgerCommonSelectionsRequest selections : ledgerRecords.getCommonLedgerList(savedLa.getId()+"",
//                savedLa.getAccNo(),"customer")) {
//
//            ledgerCommonSelectionsService.save(selections);
//        }

        saveLog("Customer", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public CustomerResponse update(CustomerUpdateRequest request) {

        Customer customer = customerRepository.findById(request.getId()).orElse(null);
        if (customer == null) {
            return null;
        }

        customer.setId(request.getId());
        customer.setType(request.getType());
        customer.setVat(request.getVat());
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        customer.setGender(request.getGender());
        customer.setTHome(request.getTHome());
        customer.setTMobile(request.getTMobile());
        customer.setTOffice(request.getTOffice());
        customer.setFax(request.getFax());
        customer.setEmail(request.getEmail());
        customer.setCreditLimit(request.getCreditLimit());
        customer.setTPartyName(request.getTPartyName());
        customer.setTPartyMobile(request.getTPartyMobile());
        customer.setTPartyEmail(request.getTPartyEmail());

        customer.setBillDiscLimit(request.getBillDiscLimit());
        customer.setLineDiscLimit(request.getLineDiscLimit());


        String imageName = "";
        if (!request.getImage().isEmpty()) {
            try {

                File fol = new File(Settings.CUSTOMER_IMAGES);
                if (!fol.exists()) {
                    fol.mkdirs();
                }
                String fileEx="jpg";
                String base64=request.getImage();

                if(request.getImage().contains(";")){
                    String[] fArray=request.getImage().split(";");

                    fileEx=fArray[0];
                    base64=fArray[1];
                }

                byte[] data = Base64.decodeBase64(base64);

                imageName=System.currentTimeMillis() + "."+fileEx;

                OutputStream stream =
                        new FileOutputStream(
                                Settings.CUSTOMER_IMAGES + imageName);
                stream.write(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        customer.setImage(imageName);
        customer.setAvlbCreditLimit(request.getAvlbCreditLimit());
        customer.setNicNumbr(request.getNicNumbr());
        customer.setCreditAccNo(request.getCreditAccNo());
        customer.setBranchId(request.getBranchId());
        customer.setIsDeleted(request.getIsDeleted());
        Customer updated = customerRepository.save(customer);

        saveLog("Customer", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public CustomerResponse getById(Long id) {

        return customerRepository.findById(id).map(CustomerServiceImpl::convertSingle).orElse(null);
    }

    @Override
    public List<CustomerResponse> getAll() {

//        return customerRepository.findAll()
//                .stream().map(CustomerServiceImpl::convert).collect(Collectors.toList());

        return customerRepository.findAll()
                .stream().map(CustomerServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public String getMaxCreditAccNo() {
        String ownNo=ladgerAccountRepository.getMaxOwnNo();
        if(ownNo==null){
            ownNo="1";
        }else{
            ownNo=(Integer.parseInt(ownNo)+1)+"";
        }
        return ownNo;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Customer got = customerRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        customerRepository.save(got);

        saveLog("Customer", "Data Deleted - " + id);

        return 1;
    }

    @Override
    public File printProfile(Long id) {

        try {

            String report = Settings.readSettings("REPORT_PATH") + "Reports/Registration/Customer.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("CustId", id);
            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
            params.put("EMAIL", Settings.readSettings("EMAIL"));
            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));
            params.put("IMAGE_PATH", Settings.CUSTOMER_IMAGES);

            JasperReport jasprereport = JasperCompileManager.compileReport(report);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasprereport, params, JDBC.con());
            //JasperViewer.viewReport(jasperprint, false);
            File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
            if (!perentFol.exists()) {
                perentFol.mkdirs();
            }
            File pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperprint, new FileOutputStream(pdf));

            return pdf;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<CustomerResponse> getByName(String name) {
        return customerRepository.findByName(name)
                .stream().map(CustomerServiceImpl::convert).collect(Collectors.toList());
    }

    public static CustomerResponse convert(Customer customer) {

        CustomerResponse typeResponse = new CustomerResponse();
        typeResponse.setType(customer.getType());
        typeResponse.setVat(customer.getVat());
        typeResponse.setName(customer.getName());
        typeResponse.setAddress(customer.getAddress());
        typeResponse.setGender(customer.getGender());
        typeResponse.setTHome(customer.getTHome());
        typeResponse.setTMobile(customer.getTMobile());
        typeResponse.setTOffice(customer.getTOffice());
        typeResponse.setFax(customer.getFax());
        typeResponse.setEmail(customer.getEmail());
        typeResponse.setCreditLimit(customer.getCreditLimit());
        typeResponse.setTPartyName(customer.getTPartyName());
        typeResponse.setTPartyMobile(customer.getTPartyMobile());
        typeResponse.setTPartyEmail(customer.getTPartyEmail());
        typeResponse.setImage(customer.getImage());
        typeResponse.setAvlbCreditLimit(customer.getAvlbCreditLimit());
        typeResponse.setNicNumbr(customer.getNicNumbr());
        typeResponse.setCreditAccNo(customer.getCreditAccNo());
        typeResponse.setBranchId(customer.getBranchId());
        typeResponse.setId(customer.getId());
        typeResponse.setCreatedBy(customer.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(customer.getCreatedDateTime()));
        typeResponse.setModifiedBy(customer.getModifiedBy());
        typeResponse.setIsDeleted(customer.getIsDeleted());

        typeResponse.setBillDiscLimit(customer.getBillDiscLimit());
        typeResponse.setLineDiscLimit(customer.getLineDiscLimit());


        return typeResponse;
    }

    private static CustomerResponse convertSingle(Customer customer) {

        CustomerResponse typeResponse = new CustomerResponse();
        typeResponse.setType(customer.getType());
        typeResponse.setVat(customer.getVat());
        typeResponse.setName(customer.getName());
        typeResponse.setAddress(customer.getAddress());
        typeResponse.setGender(customer.getGender());
        typeResponse.setTHome(customer.getTHome());
        typeResponse.setTMobile(customer.getTMobile());
        typeResponse.setTOffice(customer.getTOffice());
        typeResponse.setFax(customer.getFax());
        typeResponse.setEmail(customer.getEmail());
        typeResponse.setCreditLimit(customer.getCreditLimit());
        typeResponse.setTPartyName(customer.getTPartyName());
        typeResponse.setTPartyMobile(customer.getTPartyMobile());
        typeResponse.setTPartyEmail(customer.getTPartyEmail());
        String base64="";
        if(!customer.getImage().isEmpty()){
            File img=new File(Settings.CUSTOMER_IMAGES+customer.getImage());
            if(img.exists()){
                base64=img.getName().substring(img.getName().lastIndexOf(".")+1)+
                        ";"+encodeFileToBase64Binary(img);
            }
        }
        typeResponse.setImage(base64);
        typeResponse.setAvlbCreditLimit(customer.getAvlbCreditLimit());
        typeResponse.setNicNumbr(customer.getNicNumbr());
        typeResponse.setCreditAccNo(customer.getCreditAccNo());
        typeResponse.setBranchId(customer.getBranchId());
        typeResponse.setId(customer.getId());
        typeResponse.setCreatedBy(customer.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(customer.getCreatedDateTime()));
        typeResponse.setModifiedBy(customer.getModifiedBy());
        typeResponse.setIsDeleted(customer.getIsDeleted());
        typeResponse.setBillDiscLimit(customer.getBillDiscLimit());
        typeResponse.setLineDiscLimit(customer.getLineDiscLimit());


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