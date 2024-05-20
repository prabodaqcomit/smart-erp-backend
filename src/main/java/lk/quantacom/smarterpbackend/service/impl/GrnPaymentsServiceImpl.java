package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnPaymentsUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.GrnPaymentsResponse;
import lk.quantacom.smarterpbackend.dto.response.supplierPaymentInfoResponse;
import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lk.quantacom.smarterpbackend.entity.GrnHeader;
import lk.quantacom.smarterpbackend.entity.GrnPayments;
import lk.quantacom.smarterpbackend.repository.GrnPaymentsRepository;
import lk.quantacom.smarterpbackend.repository.SupplierRepository;
import lk.quantacom.smarterpbackend.service.GrnPaymentsService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.entity.UserLogger;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrnPaymentsServiceImpl implements GrnPaymentsService {

    @Autowired
    private GrnPaymentsRepository grnPaymentsRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    @Transactional
    public GrnPaymentsResponse save(GrnPaymentsRequest request) {

        GrnPayments grnPayments = new GrnPayments();
        grnPayments.setGrossAmount(request.getGrossAmount());
        grnPayments.setTotalDis(request.getTotalDis());
        grnPayments.setTotalVat(request.getTotalVat());
        grnPayments.setNetAmount(request.getNetAmount());
        grnPayments.setPaidAmount(request.getPaidAmount());
        grnPayments.setDueAmount(request.getDueAmount());
        grnPayments.setPayMode(request.getPayMode());
        grnPayments.setRemarks(request.getRemarks());
        grnPayments.setGrnOverpaid(request.getGrnOverpaid());
        grnPayments.setTotalProfitValue(request.getTotalProfitValue());
        grnPayments.setNetProfitValue(request.getNetProfitValue());
        GrnHeader grn = new GrnHeader();
        grn.setId(request.getGrnId());
        grnPayments.setGrn(grn);
        grnPayments.setLineNo(request.getLineNo());
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        grnPayments.setBranch(branch);
        grnPayments.setIsDeleted(Deleted.NO);
        GrnPayments save = grnPaymentsRepository.save(grnPayments);

        saveLog("GrnPayments", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public GrnPaymentsResponse update(GrnPaymentsUpdateRequest request) {

        GrnPayments grnPayments = grnPaymentsRepository.findById(request.getId()).orElse(null);
        if (grnPayments == null) {
            return null;
        }

        grnPayments.setId(request.getId());
        grnPayments.setGrossAmount(request.getGrossAmount());
        grnPayments.setTotalDis(request.getTotalDis());
        grnPayments.setTotalVat(request.getTotalVat());
        grnPayments.setNetAmount(request.getNetAmount());
        grnPayments.setPaidAmount(request.getPaidAmount());
        grnPayments.setDueAmount(request.getDueAmount());
        grnPayments.setPayMode(request.getPayMode());
        grnPayments.setRemarks(request.getRemarks());
        grnPayments.setGrnOverpaid(request.getGrnOverpaid());
        grnPayments.setTotalProfitValue(request.getTotalProfitValue());
        grnPayments.setNetProfitValue(request.getNetProfitValue());
        GrnHeader grn = new GrnHeader();
        grn.setId(request.getGrnId());
        grnPayments.setGrn(grn);
        grnPayments.setLineNo(request.getLineNo());
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        grnPayments.setBranch(branch);
        GrnPayments updated = grnPaymentsRepository.save(grnPayments);

        saveLog("GrnPayments", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public GrnPaymentsResponse getById(Long id) {

        return grnPaymentsRepository.findById(id).map(GrnPaymentsServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GrnPaymentsResponse> getAll() {
        return grnPaymentsRepository.findAll()
                .stream().map(GrnPaymentsServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    public List<GrnPaymentsResponse> getAllForSupOb() {

        List<GrnPaymentsResponse> resp = new ArrayList<>();

        List<GrnPayments> payments = grnPaymentsRepository.findByPayModeAndIsDeleted("OB DUE", Deleted.NO);
        if (payments != null) {
            for (GrnPayments p : payments) {
                resp.add(convertForOB(p));
            }
        }

        return resp;
    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        GrnPayments got = grnPaymentsRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        grnPaymentsRepository.save(got);

        saveLog("GrnPayments", "Data Deleted - " + id);

        return 1;
    }

    @Override
    public List<supplierPaymentInfoResponse> getAlSup() {
        List<supplierPaymentInfoResponse> payments = grnPaymentsRepository.getAlSup();
        return payments;
    }

    @Override
    public List<supplierPaymentInfoResponse> getAlBySup(Integer supId) {
        List<supplierPaymentInfoResponse> payments = grnPaymentsRepository.getAlBySup(supId);
        return payments;
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

    private GrnPaymentsResponse convertForOB(GrnPayments grnPayments) {

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
        typeResponse.setSupplierId(grnPayments.getGrn().getSupplierId());
        typeResponse.setSupplierName(supplierRepository.getById(grnPayments.getGrn().getSupplierId()).getName());
        typeResponse.setGrnDate(ConvertUtils.convertDateToStr(grnPayments.getGrn().getGrnDate()));
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