package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.TblbomheaderService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TblbomheaderServiceImpl implements TblbomheaderService {

    @Autowired
    private TblbomheaderRepository tblbomheaderRepository;

    @Autowired
    private TblbommainmaterialRepository tblbommainmaterialRepository;

    @Autowired
    private TblbomfitRepository tblbomfitRepository;

    @Autowired
    private TblbomsizeRepository tblbomsizeRepository;

    @Autowired
    private TblbomaccessoryRepository tblbomaccessoryRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public TblbomheaderResponse save(TblbomheaderRequest request) {

        // delete if update
        if (request.getBomId() != 0) {

            tblbomheaderRepository.deleteHdr(request.getBomId());
            tblbomheaderRepository.deleteAcc(request.getBomId());
            tblbomheaderRepository.deleteSize(request.getBomId());
            tblbomheaderRepository.deleteFit(request.getBomId());
            tblbomheaderRepository.deleteMat(request.getBomId());

        }

        // save/update

        Integer max = tblbomheaderRepository.getMaxId();
        if (request.getBomId() != 0) {
            max = request.getBomId();
        } else {
            if (max == null) {
                max = 1000;
            } else {
                max = max + 1;
            }
        }


        Tblbomheader tblbomheader = new Tblbomheader();
        tblbomheader.setBomId(max);
        tblbomheader.setBomCategoryId(request.getBomCategoryId());
        tblbomheader.setBomCreatedBranchId(request.getBomCreatedBranchId());
        tblbomheader.setBomCreatedDate(request.getBomCreatedDate() == null ? null : ConvertUtils.convertStrToDate(request.getBomCreatedDate()));
        tblbomheader.setBomCreatedUserId(request.getBomCreatedUserId());
        tblbomheader.setBomDescription(request.getBomDescription());
        tblbomheader.setBomIsDelete(request.getBomIsDelete());
        tblbomheader.setBomModifiedBranchId(request.getBomModifiedBranchId());
        tblbomheader.setBomModifiedDate(request.getBomModifiedDate() == null ? null : ConvertUtils.convertStrToDate(request.getBomModifiedDate()));
        tblbomheader.setBomModifiedUserId(request.getBomModifiedUserId());
        tblbomheader.setBomSleeveLong(request.getBomSleeveLong());
        tblbomheader.setBomSleeveShort(request.getBomSleeveShort());
        tblbomheader.setIsDeleted(Deleted.NO);
        Tblbomheader save = tblbomheaderRepository.save(tblbomheader);

        for (TblbommainmaterialRequest tblbommainmaterialRequest : request.getTblbommainmaterialRequests()) {
            Tblbommainmaterial tblbommainmaterial = new Tblbommainmaterial();
            tblbommainmaterial.setBomId(max);
            tblbommainmaterial.setBomMainMaterialBatchId(tblbommainmaterialRequest.getBomMainMaterialBatchId());
            tblbommainmaterial.setBomMainMaterialBranchId(tblbommainmaterialRequest.getBomMainMaterialBranchId());
            tblbommainmaterial.setBomMainMaterialColorId(tblbommainmaterialRequest.getBomMainMaterialColorId());
            tblbommainmaterial.setBomMainMaterialDesc(tblbommainmaterialRequest.getBomMainMaterialDesc());
            tblbommainmaterial.setBomMainMaterialId(tblbommainmaterialRequest.getBomMainMaterialId());

            tblbommainmaterial.setBomMainSizeId(tblbommainmaterialRequest.getBomMainSizeId());
            tblbommainmaterial.setBomMainFitId(tblbommainmaterialRequest.getBomMainFitId());
            tblbommainmaterial.setBomMainItemQty(tblbommainmaterialRequest.getBomMainItemQty());

            tblbommainmaterial.setIsDeleted(Deleted.NO);
            tblbommainmaterialRepository.save(tblbommainmaterial);
        }

        for (TblbomaccessoryRequest tblbomaccessoryRequest : request.getTblbomaccessoryRequests()) {
            Tblbomaccessory tblbomaccessory = new Tblbomaccessory();
            tblbomaccessory.setBdAccessoryBatchId(tblbomaccessoryRequest.getBdAccessoryBatchId());
            tblbomaccessory.setBdAccessoryBranchId(tblbomaccessoryRequest.getBdAccessoryBranchId());
            tblbomaccessory.setBdAccessoryId(tblbomaccessoryRequest.getBdAccessoryId());
            tblbomaccessory.setBdDesc(tblbomaccessoryRequest.getBdDesc());
            tblbomaccessory.setBdId(max);
            tblbomaccessory.setBdQty(tblbomaccessoryRequest.getBdQty());

            tblbomaccessory.setBdAccSizeId(tblbomaccessoryRequest.getBdAccSizeId());
            tblbomaccessory.setBdAccColorId(tblbomaccessoryRequest.getBdAccColorId());
            tblbomaccessory.setBdAccFitId(tblbomaccessoryRequest.getBdAccFitId());

            tblbomaccessory.setIsDeleted(Deleted.NO);
            tblbomaccessoryRepository.save(tblbomaccessory);
        }
        for (TblbomsizeRequest tblbomsizeRequest : request.getTblbomsizeRequests()) {

            Tblbomsize tblbomsize = new Tblbomsize();
            tblbomsize.setBsDesc(tblbomsizeRequest.getBsDesc());
            tblbomsize.setBsId(max);
            tblbomsize.setBsSizeId(tblbomsizeRequest.getBsSizeId());
            tblbomsize.setBsDesc(tblbomsizeRequest.getBsDesc());

            tblbomsize.setIsDeleted(Deleted.NO);



            tblbomsizeRepository.save(tblbomsize);
        }
        for (TblbomfitRequest tblbomfitRequest : request.getTblbomfitRequests()) {
            Tblbomfit tblbomfit = new Tblbomfit();
            tblbomfit.setBfDesc(tblbomfitRequest.getBfDesc());
            tblbomfit.setBfFitId(tblbomfitRequest.getBfFitId());
            tblbomfit.setBfId(max);
            tblbomfit.setIsDeleted(Deleted.NO);
            tblbomfitRepository.save(tblbomfit);
        }

        return convert(save);
    }

    @Override
    @Transactional
    public TblbomheaderResponse update(TblbomheaderUpdateRequest request) {

        Tblbomheader tblbomheader = tblbomheaderRepository.findById(request.getBomId()).orElse(null);
        if (tblbomheader == null) {
            return null;
        }

        tblbomheader.setBomId(request.getBomId());
        tblbomheader.setBomCategoryId(request.getBomCategoryId());
        tblbomheader.setBomCreatedBranchId(request.getBomCreatedBranchId());
        tblbomheader.setBomCreatedDate(request.getBomCreatedDate() == null ? null : ConvertUtils.convertStrToDate(request.getBomCreatedDate()));
        tblbomheader.setBomCreatedUserId(request.getBomCreatedUserId());
        tblbomheader.setBomDescription(request.getBomDescription());
        tblbomheader.setBomId(request.getBomId());
        tblbomheader.setBomIsDelete(request.getBomIsDelete());
        tblbomheader.setBomModifiedBranchId(request.getBomModifiedBranchId());
        tblbomheader.setBomModifiedDate(request.getBomModifiedDate() == null ? null : ConvertUtils.convertStrToDate(request.getBomModifiedDate()));
        tblbomheader.setBomModifiedUserId(request.getBomModifiedUserId());
        tblbomheader.setBomSleeveLong(request.getBomSleeveLong());
        tblbomheader.setBomSleeveShort(request.getBomSleeveShort());
        Tblbomheader updated = tblbomheaderRepository.save(tblbomheader);

        return (convert(updated));
    }

    @Override
    public TblbomheaderResponse getById(Integer id) {
        return convert(tblbomheaderRepository.findByBomId(id));
    }

    @Override
    public List<TblbomheaderResponse> getAll() {

        return tblbomheaderRepository.findByIsDeleted(Deleted.NO)
                .stream().map(TblbomheaderServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer bomId) {

//        Tblbomheader got = tblbomheaderRepository.findByBomId(bomId);
//        if (got == null) {
//            return 0;
//        }
//        got.setIsDeleted(Deleted.YES);
//        tblbomheaderRepository.save(got);

        tblbomheaderRepository.deleteHdr(bomId);
        tblbomheaderRepository.deleteAcc(bomId);
        tblbomheaderRepository.deleteSize(bomId);
        tblbomheaderRepository.deleteFit(bomId);
        tblbomheaderRepository.deleteMat(bomId);

        return 1;
    }

    @Override
    public List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc(String itemName) {
        return tblbomheaderRepository.getAllItemCodeByDesc();
    }

    @Override
    public List<GetForBomAllItemCodeByDescResponse> getAllItemCodeByDesc2(String itemName) {
        return tblbomheaderRepository.getAllItemCodeByDesc2();
    }

    @Override
    public BOMAllResponse getFromAllTables(Integer bomId) {

        BOMAllResponse res = new BOMAllResponse();


        Tblbomheader bomHead = (tblbomheaderRepository.findByBomId(bomId));
        if (bomHead != null) {
            res.setBomHeader(convert(bomHead));
        }

        List<BomMainMaterialResponse> bomMainMaterial = tblbomheaderRepository.getMainMaterial(bomId);
        res.setBomMainMaterial(bomMainMaterial);

        List<Tblbomaccessory> bomAccesory = tblbomaccessoryRepository.findByBdId(bomId);
        List<TblbomaccessoryResponse> bomAccesoryRes = new ArrayList<>();
        if (bomAccesory != null) {
            bomAccesoryRes = bomAccesory.stream().map(TblbomheaderServiceImpl::convert).collect(Collectors.toList());
        }
        res.setBomAccessory(bomAccesoryRes);

        List<Tblbomsize> bomSize = tblbomsizeRepository.findByBsId(bomId);
        List<TblbomsizeResponse> bomSizeResponses = new ArrayList<>();
        if (bomSize != null) {
            bomSizeResponses = bomSize.stream().map(TblbomheaderServiceImpl::convert).collect(Collectors.toList());
        }
        res.setBomSize(bomSizeResponses);

        List<Tblbomfit> bomFit = tblbomfitRepository.findByBfId(bomId);
        List<TblbomfitResponse> bomFitResponses = new ArrayList<>();
        if (bomFit != null) {
            bomFitResponses = bomFit.stream().map(TblbomheaderServiceImpl::convert).collect(Collectors.toList());
        }
        res.setBomFit(bomFitResponses);

        return res;
    }

    @Override
    public List<BomMainMaterialForPOR> getMaterialForPOR() {
        return   tblbomheaderRepository.getMaterialForPOR();
    }

    @Override
    public File printBomHeader(Integer bomId) {
        File pdf = null;
        try {

            Tblbomheader bomHead = (tblbomheaderRepository.findByBomId(bomId));
            if (bomHead ==null) {
                return null;
            }

            Connection co=JDBC.con();
            String reportSource = Settings.readSettings("REPORT_PATH") + "Reports/Production/Style_Sheet.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("BOM_ID", bomId+"");
            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
            params.put("SUBREPORT_DIR", Settings.readSettings("REPORT_PATH")+"Reports/Production/");
            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
            params.put("EMAIL", Settings.readSettings("EMAIL"));
            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, co);
            File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
            if (!perentFol.exists()) {
                perentFol.mkdirs();
            }
            pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, new FileOutputStream(pdf));
            co.close();
            //  JasperViewer.viewReport(jasperPrint,false);
//            JRViewer v = new JRViewer(jasperPrint);
//            v.setZoomRatio((float) 0.60);
//            jTabbedPane1.add(v);

        } catch (Exception e) {
             e.printStackTrace();
        }

        return pdf;
    }

    private static TblbomheaderResponse convert(Tblbomheader tblbomheader) {


        TblbomheaderResponse typeResponse = new TblbomheaderResponse();
        typeResponse.setBomCategoryId(tblbomheader.getBomCategoryId());
        typeResponse.setBomCreatedBranchId(tblbomheader.getBomCreatedBranchId());
        typeResponse.setBomCreatedDate(tblbomheader.getBomCreatedDate());
        typeResponse.setBomCreatedUserId(tblbomheader.getBomCreatedUserId());
        typeResponse.setBomDescription(tblbomheader.getBomDescription());
        typeResponse.setBomId(tblbomheader.getBomId());
        typeResponse.setBomIsDelete(tblbomheader.getBomIsDelete());
        typeResponse.setBomModifiedBranchId(tblbomheader.getBomModifiedBranchId());
        typeResponse.setBomModifiedDate(tblbomheader.getBomModifiedDate());
        typeResponse.setBomModifiedUserId(tblbomheader.getBomModifiedUserId());
        typeResponse.setBomSleeveLong(tblbomheader.getBomSleeveLong());
        typeResponse.setBomSleeveShort(tblbomheader.getBomSleeveShort());
        typeResponse.setBomId(tblbomheader.getBomId());
        typeResponse.setCreatedBy(tblbomheader.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomheader.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomheader.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomheader.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomheader.getIsDeleted());



        return typeResponse;
    }

    private static TblbomaccessoryResponse convert(Tblbomaccessory tblbomaccessory) {

        TblbomaccessoryResponse typeResponse = new TblbomaccessoryResponse();
        typeResponse.setBdAccessoryBatchId(tblbomaccessory.getBdAccessoryBatchId());
        typeResponse.setBdAccessoryBranchId(tblbomaccessory.getBdAccessoryBranchId());
        typeResponse.setBdAccessoryId(tblbomaccessory.getBdAccessoryId());
        typeResponse.setBdDesc(tblbomaccessory.getBdDesc());
        typeResponse.setBdId(tblbomaccessory.getBdId());
        typeResponse.setBdQty(tblbomaccessory.getBdQty());
        typeResponse.setId(tblbomaccessory.getId());
        typeResponse.setCreatedBy(tblbomaccessory.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomaccessory.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomaccessory.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomaccessory.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomaccessory.getIsDeleted());

        typeResponse.setBdAccSizeId(tblbomaccessory.getBdAccSizeId());
        typeResponse.setBdAccColorId(tblbomaccessory.getBdAccColorId());
        typeResponse.setBdAccFitId(tblbomaccessory.getBdAccFitId());

        return typeResponse;
    }

    private static TblbomsizeResponse convert(Tblbomsize tblbomsize) {

        TblbomsizeResponse typeResponse = new TblbomsizeResponse();
        typeResponse.setBsDesc(tblbomsize.getBsDesc());
        typeResponse.setBsId(tblbomsize.getBsId());
        typeResponse.setBsSizeId(tblbomsize.getBsSizeId());
        typeResponse.setId(tblbomsize.getId());
        typeResponse.setCreatedBy(tblbomsize.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomsize.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomsize.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomsize.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomsize.getIsDeleted());

        typeResponse.setBsDesc(tblbomsize.getBsDesc());

        return typeResponse;
    }

    private static TblbomfitResponse convert(Tblbomfit tblbomfit) {

        TblbomfitResponse typeResponse = new TblbomfitResponse();
        typeResponse.setBfDesc(tblbomfit.getBfDesc());
        typeResponse.setBfFitId(tblbomfit.getBfFitId());
        typeResponse.setBfId(tblbomfit.getBfId());
        typeResponse.setId(tblbomfit.getId());
        typeResponse.setCreatedBy(tblbomfit.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(tblbomfit.getCreatedDateTime()));
        typeResponse.setModifiedBy(tblbomfit.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(tblbomfit.getModifiedDateTime()));
        typeResponse.setIsDeleted(tblbomfit.getIsDeleted());

        return typeResponse;
    }
}