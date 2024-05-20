package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegRequest;
import lk.quantacom.smarterpbackend.dto.request.LedgerAssetRegUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.LedgerAssetRegResponse;
import lk.quantacom.smarterpbackend.entity.LedgerAssetReg;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.LedgerAssetRegRepository;
import lk.quantacom.smarterpbackend.service.LedgerAssetRegService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LedgerAssetRegServiceImpl implements LedgerAssetRegService {

    @Autowired
    private LedgerAssetRegRepository ledgerAssetRegRepository;


    @Override
    @Transactional
    public LedgerAssetRegResponse save(LedgerAssetRegRequest request) {

        LedgerAssetReg ledgerAssetReg = new LedgerAssetReg();
        ledgerAssetReg.setAssetCode(request.getAssetCode());
        ledgerAssetReg.setAssetName(request.getAssetName());
        ledgerAssetReg.setCategoryId(request.getCategoryId());
        ledgerAssetReg.setAssertType(request.getAssertType());
        ledgerAssetReg.setSupplierId(request.getSupplierId());
        ledgerAssetReg.setManufacture(request.getManufacture());
        ledgerAssetReg.setBrand(request.getBrand());
        ledgerAssetReg.setSerialNo(request.getSerialNo());
        ledgerAssetReg.setModelNo(request.getModelNo());
        ledgerAssetReg.setBarcodeNo(request.getBarcodeNo());
        ledgerAssetReg.setItemConditionUse(request.getItemConditionUse());
        ledgerAssetReg.setPurchaseDate(request.getPurchaseDate() == null ? null : ConvertUtils.convertStrToDate(request.getPurchaseDate()));
        ledgerAssetReg.setPoNo(request.getPoNo());
        ledgerAssetReg.setPurchaseValue(request.getPurchaseValue());
        ledgerAssetReg.setInvoiceDate(request.getInvoiceDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvoiceDate()));
        ledgerAssetReg.setCurrentMarketValue(request.getCurrentMarketValue());
        ledgerAssetReg.setDepreciation(request.getDepreciation());
        ledgerAssetReg.setIssues(request.getIssues());
        ledgerAssetReg.setDepartmentId(request.getDepartmentId());
        ledgerAssetReg.setAssignPerson(request.getAssignPerson());
        ledgerAssetReg.setServiceTimePeriod(request.getServiceTimePeriod());
        ledgerAssetReg.setServiceTimePeriodType(request.getServiceTimePeriodType());
        ledgerAssetReg.setNextServiceDate(request.getNextServiceDate() == null ? null : ConvertUtils.convertStrToDate(request.getNextServiceDate()));
        ledgerAssetReg.setLifeTimeYears(request.getLifeTimeYears());
        ledgerAssetReg.setLifeTimeMonths(request.getLifeTimeMonths());
        ledgerAssetReg.setSalvageAmount(request.getSalvageAmount());
        ledgerAssetReg.setBusinessUse(request.getBusinessUse());
        ledgerAssetReg.setAcquiredDate(request.getAcquiredDate() == null ? null : ConvertUtils.convertStrToDate(request.getAcquiredDate()));
        ledgerAssetReg.setSoldDate(request.getSoldDate() == null ? null : ConvertUtils.convertStrToDate(request.getSoldDate()));
        ledgerAssetReg.setSoldPerson(request.getSoldPerson());
        ledgerAssetReg.setRemarks(request.getRemarks());
        ledgerAssetReg.setAssertImage(request.getAssertImage());
        ledgerAssetReg.setBranchId(request.getBranchId());
        ledgerAssetReg.setIsDeleted(Deleted.NO);
        LedgerAssetReg save = ledgerAssetRegRepository.save(ledgerAssetReg);

        return convert(save);
    }

    @Override
    @Transactional
    public LedgerAssetRegResponse update(LedgerAssetRegUpdateRequest request) {

        LedgerAssetReg ledgerAssetReg = ledgerAssetRegRepository.findById(request.getId()).orElse(null);
        if (ledgerAssetReg == null) {
            return null;
        }

        ledgerAssetReg.setId(request.getId());
        ledgerAssetReg.setAssetCode(request.getAssetCode());
        ledgerAssetReg.setAssetName(request.getAssetName());
        ledgerAssetReg.setCategoryId(request.getCategoryId());
        ledgerAssetReg.setAssertType(request.getAssertType());
        ledgerAssetReg.setSupplierId(request.getSupplierId());
        ledgerAssetReg.setManufacture(request.getManufacture());
        ledgerAssetReg.setBrand(request.getBrand());
        ledgerAssetReg.setSerialNo(request.getSerialNo());
        ledgerAssetReg.setModelNo(request.getModelNo());
        ledgerAssetReg.setBarcodeNo(request.getBarcodeNo());
        ledgerAssetReg.setItemConditionUse(request.getItemConditionUse());
        ledgerAssetReg.setPurchaseDate(request.getPurchaseDate() == null ? null : ConvertUtils.convertStrToDate(request.getPurchaseDate()));
        ledgerAssetReg.setPoNo(request.getPoNo());
        ledgerAssetReg.setPurchaseValue(request.getPurchaseValue());
        ledgerAssetReg.setInvoiceDate(request.getInvoiceDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvoiceDate()));
        ledgerAssetReg.setCurrentMarketValue(request.getCurrentMarketValue());
        ledgerAssetReg.setDepreciation(request.getDepreciation());
        ledgerAssetReg.setIssues(request.getIssues());
        ledgerAssetReg.setDepartmentId(request.getDepartmentId());
        ledgerAssetReg.setAssignPerson(request.getAssignPerson());
        ledgerAssetReg.setServiceTimePeriod(request.getServiceTimePeriod());
        ledgerAssetReg.setServiceTimePeriodType(request.getServiceTimePeriodType());
        ledgerAssetReg.setNextServiceDate(request.getNextServiceDate() == null ? null : ConvertUtils.convertStrToDate(request.getNextServiceDate()));
        ledgerAssetReg.setLifeTimeYears(request.getLifeTimeYears());
        ledgerAssetReg.setLifeTimeMonths(request.getLifeTimeMonths());
        ledgerAssetReg.setSalvageAmount(request.getSalvageAmount());
        ledgerAssetReg.setBusinessUse(request.getBusinessUse());
        ledgerAssetReg.setAcquiredDate(request.getAcquiredDate() == null ? null : ConvertUtils.convertStrToDate(request.getAcquiredDate()));
        ledgerAssetReg.setSoldDate(request.getSoldDate() == null ? null : ConvertUtils.convertStrToDate(request.getSoldDate()));
        ledgerAssetReg.setSoldPerson(request.getSoldPerson());
        ledgerAssetReg.setRemarks(request.getRemarks());
        ledgerAssetReg.setAssertImage(request.getAssertImage());
        ledgerAssetReg.setBranchId(request.getBranchId());
        LedgerAssetReg updated = ledgerAssetRegRepository.save(ledgerAssetReg);

        return (convert(updated));
    }

    @Override
    public LedgerAssetRegResponse getById(Long id) {

        return ledgerAssetRegRepository.findById(id).map(LedgerAssetRegServiceImpl::convert).orElse(null);
    }

    @Override
    public List<LedgerAssetRegResponse> getAll() {

        return ledgerAssetRegRepository.findByIsDeleted(Deleted.NO)
                .stream().map(LedgerAssetRegServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        LedgerAssetReg got = ledgerAssetRegRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        ledgerAssetRegRepository.save(got);

        return 1;
    }

    private static LedgerAssetRegResponse convert(LedgerAssetReg ledgerAssetReg) {

        LedgerAssetRegResponse typeResponse = new LedgerAssetRegResponse();
        typeResponse.setAssetCode(ledgerAssetReg.getAssetCode());
        typeResponse.setAssetName(ledgerAssetReg.getAssetName());
        typeResponse.setCategoryId(ledgerAssetReg.getCategoryId());
        typeResponse.setAssertType(ledgerAssetReg.getAssertType());
        typeResponse.setSupplierId(ledgerAssetReg.getSupplierId());
        typeResponse.setManufacture(ledgerAssetReg.getManufacture());
        typeResponse.setBrand(ledgerAssetReg.getBrand());
        typeResponse.setSerialNo(ledgerAssetReg.getSerialNo());
        typeResponse.setModelNo(ledgerAssetReg.getModelNo());
        typeResponse.setBarcodeNo(ledgerAssetReg.getBarcodeNo());
        typeResponse.setItemConditionUse(ledgerAssetReg.getItemConditionUse());
        typeResponse.setPurchaseDate(ledgerAssetReg.getPurchaseDate());
        typeResponse.setPoNo(ledgerAssetReg.getPoNo());
        typeResponse.setPurchaseValue(ledgerAssetReg.getPurchaseValue());
        typeResponse.setInvoiceDate(ledgerAssetReg.getInvoiceDate());
        typeResponse.setCurrentMarketValue(ledgerAssetReg.getCurrentMarketValue());
        typeResponse.setDepreciation(ledgerAssetReg.getDepreciation());
        typeResponse.setIssues(ledgerAssetReg.getIssues());
        typeResponse.setDepartmentId(ledgerAssetReg.getDepartmentId());
        typeResponse.setAssignPerson(ledgerAssetReg.getAssignPerson());
        typeResponse.setServiceTimePeriod(ledgerAssetReg.getServiceTimePeriod());
        typeResponse.setServiceTimePeriodType(ledgerAssetReg.getServiceTimePeriodType());
        typeResponse.setNextServiceDate(ledgerAssetReg.getNextServiceDate());
        typeResponse.setLifeTimeYears(ledgerAssetReg.getLifeTimeYears());
        typeResponse.setLifeTimeMonths(ledgerAssetReg.getLifeTimeMonths());
        typeResponse.setSalvageAmount(ledgerAssetReg.getSalvageAmount());
        typeResponse.setBusinessUse(ledgerAssetReg.getBusinessUse());
        typeResponse.setAcquiredDate(ledgerAssetReg.getAcquiredDate());
        typeResponse.setSoldDate(ledgerAssetReg.getSoldDate());
        typeResponse.setSoldPerson(ledgerAssetReg.getSoldPerson());
        typeResponse.setRemarks(ledgerAssetReg.getRemarks());
        typeResponse.setAssertImage(ledgerAssetReg.getAssertImage());
        typeResponse.setBranchId(ledgerAssetReg.getBranchId());
        typeResponse.setId(ledgerAssetReg.getId());
        typeResponse.setCreatedBy(ledgerAssetReg.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(ledgerAssetReg.getCreatedDateTime()));
        typeResponse.setModifiedBy(ledgerAssetReg.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(ledgerAssetReg.getModifiedDateTime()));
        typeResponse.setIsDeleted(ledgerAssetReg.getIsDeleted());

        return typeResponse;
    }
}