package lk.quantacom.smarterpbackend.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherPrint;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherRequest;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherSearch;
import lk.quantacom.smarterpbackend.dto.request.GiftVoucherUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GiftVoucherResponse;
import lk.quantacom.smarterpbackend.entity.GiftVoucher;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.GiftVoucherRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.GiftVoucherService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GiftVoucherServiceImpl implements GiftVoucherService {

    @Autowired
    private GiftVoucherRepository giftVoucherRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }

    @Override
    public String getMaxSRNO() {
        String max = giftVoucherRepository.getMaxSRNO();
        if (max == null) {
            return "1000000";
        } else {
            int newSn = Integer.parseInt(max) + 1;
            return newSn + "";
        }
    }

    @Override
    @Transactional
    public List<GiftVoucherResponse> saveBulk(List<GiftVoucherRequest> bRequest) {

        List<GiftVoucherResponse> responses = new ArrayList<>();

        for (GiftVoucherRequest request : bRequest) {
            GiftVoucher giftVoucher = new GiftVoucher();
            giftVoucher.setBatchID(request.getBatchID());
            giftVoucher.setFldMiddlewareStatus(request.getFldMiddlewareStatus());
            giftVoucher.setFldMiddlewareUUID(request.getFldMiddlewareUUID());
            giftVoucher.setGiftVoucherAmount(request.getGiftVoucherAmount());
            giftVoucher.setGiftVoucherArticleNo(request.getGiftVoucherArticleNo());
            giftVoucher.setGiftVoucherBatchNo(request.getGiftVoucherBatchNo());
            giftVoucher.setGiftVoucherCusName(request.getGiftVoucherCusName());
            giftVoucher.setGiftVoucherDate(request.getGiftVoucherDate() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDate()));
            giftVoucher.setGiftVoucherDateSold(request.getGiftVoucherDateSold() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateSold()));
            giftVoucher.setGiftVoucherDateUsed(request.getGiftVoucherDateUsed() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateUsed()));
            giftVoucher.setGiftVoucherExpiry(request.getGiftVoucherExpiry() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherExpiry()));
            giftVoucher.setGiftVoucherFlag(request.getGiftVoucherFlag());
            giftVoucher.setGiftVoucherInvoiceNo(request.getGiftVoucherInvoiceNo());
            giftVoucher.setGiftVoucherInvoiceNoUsed(request.getGiftVoucherInvoiceNoUsed());
            giftVoucher.setGiftVoucherLocation(request.getGiftVoucherLocation());
            giftVoucher.setGiftVoucherLocationUsed(request.getGiftVoucherLocationUsed());
            giftVoucher.setGiftVoucherNo(request.getGiftVoucherNo());
            giftVoucher.setGiftVoucherSAPUpdated(request.getGiftVoucherSAPUpdated());
            giftVoucher.setGiftVoucherSerialNo(request.getGiftVoucherSerialNo());
            giftVoucher.setSyncStatus(request.getSyncStatus());
            giftVoucher.setIsDeleted(Deleted.NO);
            GiftVoucher save = giftVoucherRepository.save(giftVoucher);
            responses.add(convert(save));
        }

        saveLog("GiftVoucher", "Data Saved - Bulk");


        return responses;
    }

    @Override
    @Transactional
    public GiftVoucherResponse save(GiftVoucherRequest request) {

        GiftVoucher giftVoucher = new GiftVoucher();
        giftVoucher.setBatchID(request.getBatchID());
        giftVoucher.setFldMiddlewareStatus(request.getFldMiddlewareStatus());
        giftVoucher.setFldMiddlewareUUID(request.getFldMiddlewareUUID());
        giftVoucher.setGiftVoucherAmount(request.getGiftVoucherAmount());
        giftVoucher.setGiftVoucherArticleNo(request.getGiftVoucherArticleNo());
        giftVoucher.setGiftVoucherBatchNo(request.getGiftVoucherBatchNo());
        giftVoucher.setGiftVoucherCusName(request.getGiftVoucherCusName());
        giftVoucher.setGiftVoucherDate(request.getGiftVoucherDate() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDate()));
        giftVoucher.setGiftVoucherDateSold(request.getGiftVoucherDateSold() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateSold()));
        giftVoucher.setGiftVoucherDateUsed(request.getGiftVoucherDateUsed() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateUsed()));
        giftVoucher.setGiftVoucherExpiry(request.getGiftVoucherExpiry() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherExpiry()));
        giftVoucher.setGiftVoucherFlag(request.getGiftVoucherFlag());
        giftVoucher.setGiftVoucherInvoiceNo(request.getGiftVoucherInvoiceNo());
        giftVoucher.setGiftVoucherInvoiceNoUsed(request.getGiftVoucherInvoiceNoUsed());
        giftVoucher.setGiftVoucherLocation(request.getGiftVoucherLocation());
        giftVoucher.setGiftVoucherLocationUsed(request.getGiftVoucherLocationUsed());
        giftVoucher.setGiftVoucherNo(request.getGiftVoucherNo());
        giftVoucher.setGiftVoucherSAPUpdated(request.getGiftVoucherSAPUpdated());
        giftVoucher.setGiftVoucherSerialNo(request.getGiftVoucherSerialNo());
        giftVoucher.setSyncStatus(request.getSyncStatus());
        giftVoucher.setIsDeleted(Deleted.NO);
        GiftVoucher save = giftVoucherRepository.save(giftVoucher);

        saveLog("GiftVoucher", "Data Saved - " + save.getId());


        return convert(save);
    }

    @Override
    @Transactional
    public GiftVoucherResponse update(GiftVoucherUpdateRequest request) {

        GiftVoucher giftVoucher = giftVoucherRepository.findById(request.getId()).orElse(null);
        if (giftVoucher == null) {
            return null;
        }

        giftVoucher.setId(request.getId());
        giftVoucher.setBatchID(request.getBatchID());
        giftVoucher.setFldMiddlewareStatus(request.getFldMiddlewareStatus());
        giftVoucher.setFldMiddlewareUUID(request.getFldMiddlewareUUID());
        giftVoucher.setGiftVoucherAmount(request.getGiftVoucherAmount());
        giftVoucher.setGiftVoucherArticleNo(request.getGiftVoucherArticleNo());
        giftVoucher.setGiftVoucherBatchNo(request.getGiftVoucherBatchNo());
        giftVoucher.setGiftVoucherCusName(request.getGiftVoucherCusName());
        giftVoucher.setGiftVoucherDate(request.getGiftVoucherDate() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDate()));
        giftVoucher.setGiftVoucherDateSold(request.getGiftVoucherDateSold() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateSold()));
        giftVoucher.setGiftVoucherDateUsed(request.getGiftVoucherDateUsed() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherDateUsed()));
        giftVoucher.setGiftVoucherExpiry(request.getGiftVoucherExpiry() == null ? null : ConvertUtils.convertStrToDate(request.getGiftVoucherExpiry()));
        giftVoucher.setGiftVoucherFlag(request.getGiftVoucherFlag());
        giftVoucher.setGiftVoucherInvoiceNo(request.getGiftVoucherInvoiceNo());
        giftVoucher.setGiftVoucherInvoiceNoUsed(request.getGiftVoucherInvoiceNoUsed());
        giftVoucher.setGiftVoucherLocation(request.getGiftVoucherLocation());
        giftVoucher.setGiftVoucherLocationUsed(request.getGiftVoucherLocationUsed());
        giftVoucher.setGiftVoucherNo(request.getGiftVoucherNo());
        giftVoucher.setGiftVoucherSAPUpdated(request.getGiftVoucherSAPUpdated());
        giftVoucher.setGiftVoucherSerialNo(request.getGiftVoucherSerialNo());
        giftVoucher.setSyncStatus(request.getSyncStatus());
        GiftVoucher updated = giftVoucherRepository.save(giftVoucher);

        saveLog("GiftVoucher", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public GiftVoucherResponse getById(Long id) {

        return giftVoucherRepository.findById(id).map(GiftVoucherServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GiftVoucherResponse> getAll() {

        return giftVoucherRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<GiftVoucherResponse> searchAll(GiftVoucherSearch request) {

        if (request.getType().equals("used")) {
            if (request.getFromDate().isEmpty()||request.getToDate().isEmpty()) {
                return giftVoucherRepository.findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeleted(Deleted.NO)
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            } else {
                return giftVoucherRepository
                        .findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeletedAndGiftVoucherDateUsedBetween(
                                Deleted.NO,
                                ConvertUtils.convertStrToDate(request.getFromDate()),
                                ConvertUtils.convertStrToDate(request.getToDate()))
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            }
        }else if(request.getType().equals("valid")){
            if (request.getFromDate().isEmpty()||request.getToDate().isEmpty()) {
                return giftVoucherRepository.findByGiftVoucherExpiryAfterAndIsDeleted(new Date(),Deleted.NO)
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            } else {
                return giftVoucherRepository
                        .findByGiftVoucherExpiryAfterAndIsDeletedAndGiftVoucherExpiryBetween(
                                new Date(),
                                Deleted.NO,
                                ConvertUtils.convertStrToDate(request.getFromDate()),
                                ConvertUtils.convertStrToDate(request.getToDate()))
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            }
        }else if(request.getType().equals("sold")){
            if (request.getFromDate().isEmpty()||request.getToDate().isEmpty()) {
                return giftVoucherRepository.findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeleted(Deleted.NO)
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            } else {
                return giftVoucherRepository
                        .findByGiftVoucherInvoiceNoUsedIsNotNullAndIsDeletedAndGiftVoucherDateSoldBetween(
                                Deleted.NO,
                                ConvertUtils.convertStrToDate(request.getFromDate()),
                                ConvertUtils.convertStrToDate(request.getToDate()))
                        .stream().map(GiftVoucherServiceImpl::convert).collect(Collectors.toList());
            }
        }

        return null;
    }

    @Override
    public File print(GiftVoucherPrint request) {

        try {

            String report = Settings.readSettings("REPORT_PATH") + "Reports/Registration/gv_list.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
            params.put("EMAIL", Settings.readSettings("EMAIL"));
            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));
            params.put("TotalValue", request.getTotalValue());

            Gson gson=new Gson();
            List<HashMap<String,Object>> from=gson.fromJson(request.getDataList(), new TypeToken<List<HashMap<String,Object>>>(){}.getType());
            JasperReport jasprereport = JasperCompileManager.compileReport(report);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasprereport, params, new JRBeanCollectionDataSource(from));

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
    @Transactional
    public Integer delete(Long id) {

        GiftVoucher got = giftVoucherRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        giftVoucherRepository.save(got);

        saveLog("GiftVoucher", "Data Deleted - " + id);

        return 1;
    }

    private static GiftVoucherResponse convert(GiftVoucher giftVoucher) {

        GiftVoucherResponse typeResponse = new GiftVoucherResponse();
        typeResponse.setBatchID(giftVoucher.getBatchID());

        typeResponse.setFldMiddlewareStatus(giftVoucher.getFldMiddlewareStatus());

        typeResponse.setFldMiddlewareUUID(giftVoucher.getFldMiddlewareUUID());

        typeResponse.setGiftVoucherAmount(giftVoucher.getGiftVoucherAmount());

        typeResponse.setGiftVoucherArticleNo(giftVoucher.getGiftVoucherArticleNo());

        typeResponse.setGiftVoucherBatchNo(giftVoucher.getGiftVoucherBatchNo());

        typeResponse.setGiftVoucherCusName(giftVoucher.getGiftVoucherCusName());

        typeResponse.setGiftVoucherDate(ConvertUtils.convertDateToStr(giftVoucher.getGiftVoucherDate()));

        typeResponse.setGiftVoucherDateSold(ConvertUtils.convertDateToStr(giftVoucher.getGiftVoucherDateSold()));

        typeResponse.setGiftVoucherDateUsed(ConvertUtils.convertDateToStr(giftVoucher.getGiftVoucherDateUsed()));

        typeResponse.setGiftVoucherExpiry(ConvertUtils.convertDateToStr(giftVoucher.getGiftVoucherExpiry()));

        typeResponse.setGiftVoucherFlag(giftVoucher.getGiftVoucherFlag());

        typeResponse.setGiftVoucherInvoiceNo(giftVoucher.getGiftVoucherInvoiceNo());

        typeResponse.setGiftVoucherInvoiceNoUsed(giftVoucher.getGiftVoucherInvoiceNoUsed());

        typeResponse.setGiftVoucherLocation(giftVoucher.getGiftVoucherLocation());

        typeResponse.setGiftVoucherLocationUsed(giftVoucher.getGiftVoucherLocationUsed());

        typeResponse.setGiftVoucherNo(giftVoucher.getGiftVoucherNo());

        typeResponse.setGiftVoucherSAPUpdated(giftVoucher.getGiftVoucherSAPUpdated());

        typeResponse.setGiftVoucherSerialNo(giftVoucher.getGiftVoucherSerialNo());

        typeResponse.setSyncStatus(giftVoucher.getSyncStatus());

        typeResponse.setId(giftVoucher.getId());
        typeResponse.setCreatedBy(giftVoucher.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(giftVoucher.getCreatedDateTime()));
        typeResponse.setModifiedBy(giftVoucher.getModifiedBy());
        typeResponse.setIsDeleted(giftVoucher.getIsDeleted());

        return typeResponse;
    }
}