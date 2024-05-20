package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnRecordUpdateUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnRecordUpdateResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.GrnDetailsRepository;
import lk.quantacom.smarterpbackend.repository.GrnRecordUpdateRepository;
import lk.quantacom.smarterpbackend.repository.StockRepository;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import lk.quantacom.smarterpbackend.service.GrnRecordUpdateService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrnRecordUpdateServiceImpl implements GrnRecordUpdateService {

    @Autowired
    private GrnRecordUpdateRepository grnRecordUpdateRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private GrnDetailsRepository grnDetailsRepository;

    @Override
    @Transactional
    public GrnRecordUpdateResponse save(GrnRecordUpdateRequest request) {


        GrnRecordUpdate grnRecordUpdate = new GrnRecordUpdate();
        grnRecordUpdate.setBarcodeNo(request.getBarcodeNo());
        grnRecordUpdate.setBatchNo(request.getBatchNo());
        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        grnRecordUpdate.setBranch(branchNetwork);
        grnRecordUpdate.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        grnRecordUpdate.setGrnIddd(request.getGrnId());
        //grnRecordUpdate.setIdgrnRecordUpdate(request.getIdgrnRecordUpdate());
        ItemMaster item = new ItemMaster();
        item.setId(request.getItmeId());
        grnRecordUpdate.setItem(item);
        grnRecordUpdate.setStkQty(request.getStkQty());
        Supplier supplier = new Supplier();
        supplier.setId(request.getSupplierIddd());
        grnRecordUpdate.setSupplier(supplier);
        grnRecordUpdate.setUnitPrice(request.getUnitPrice());
        grnRecordUpdate.setUpdateDate(request.getUpdateDate() == null ? null : ConvertUtils.convertStrToDate(request.getUpdateDate()));
        grnRecordUpdate.setUpdateTime(request.getUpdateTime() == null ? null : ConvertUtils.convertStrToDate(request.getUpdateTime()));
        grnRecordUpdate.setIsDeleted(Deleted.NO);
        grnRecordUpdate.setColor(request.getColorId());
        grnRecordUpdate.setSize(request.getSizeId());
        grnRecordUpdate.setFit(request.getFitId());
        GrnRecordUpdate save = grnRecordUpdateRepository.save(grnRecordUpdate);

        // Correct stock values

//        StockPK stockPK = new StockPK(item, request.getColorId(), request.getSizeId(), request.getFitId(), branchNetwork, request.getBatchNo());
//
//
//        Stock stock = stockRepository.findById(stockPK).orElse(null);
//        if(stock!=null){
//            stock.setSalesDiscoPresentage(request.getStockRequest().getSalesDiscoPresentage());
//            stock.setCashDisValue(request.getStockRequest().getCashDisValue());
//            stock.setCreditDisValue(request.getStockRequest().getCreditDisValue());
//            stock.setStockUnitPriceRetail(request.getStockRequest().getStockUnitPriceRetail());
//            stock.setStockUnitPriceWholesale(request.getStockRequest().getStockUnitPriceWholesale());
//            stock.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
//            stock.setBarcodeNo(request.getBarcodeNo());
//            stockRepository.save(stock);
//        }

        // grn details table need to be updated

//        List<GrnDetails> grnDetails = grnDetailsRepository.getGrndetailsByItemAndGrnAndSupplier(item.getItemCode().toString(),request.getSupplierIddd().intValue(),supplier.getId().intValue());
//        for (GrnDetails grnDetail : grnDetails){
//            grnDetail.setItem(item);
//            grnDetailsRepository.save(grnDetail);
//        }

        saveLog("GrnRecordUpdate", "Data Saved - " + save.getId());
        return convert(save);
    }

    @Override
    @Transactional
    public GrnRecordUpdateResponse update(GrnRecordUpdateUpdateRequest request) {

        GrnRecordUpdate grnRecordUpdate = grnRecordUpdateRepository.findById(request.getId()).orElse(null);
        if (grnRecordUpdate == null) {
            return null;
        }

        grnRecordUpdate.setId(request.getId());
        grnRecordUpdate.setBarcodeNo(request.getBarcodeNo());
        grnRecordUpdate.setBatchNo(request.getBatchNo());
        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        grnRecordUpdate.setBranch(branchNetwork);
        grnRecordUpdate.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        grnRecordUpdate.setGrnIddd(request.getGrnIddd());
        //grnRecordUpdate.setIdgrnRecordUpdate(request.getIdgrnRecordUpdate());
        ItemMaster item = new ItemMaster();
        item.setId(request.getItmeIddd());
        grnRecordUpdate.setItem(item);
        grnRecordUpdate.setStkQty(request.getStkQty());
        Supplier supplier = new Supplier();
        supplier.setId(request.getSupplierIddd());
        grnRecordUpdate.setSupplier(supplier);
        grnRecordUpdate.setUnitPrice(request.getUnitPrice());
        grnRecordUpdate.setUpdateDate(request.getUpdateDate() == null ? null : ConvertUtils.convertStrToDate(request.getUpdateDate()));
        grnRecordUpdate.setUpdateTime(request.getUpdateTime() == null ? null : ConvertUtils.convertStrToDate(request.getUpdateTime()));
        GrnRecordUpdate updated = grnRecordUpdateRepository.save(grnRecordUpdate);

        return (convert(updated));
    }

    @Override
    public GrnRecordUpdateResponse getById(Integer id) {

        return grnRecordUpdateRepository.findById(id.longValue()).map(GrnRecordUpdateServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GrnRecordUpdateResponse> getAll() {

        return grnRecordUpdateRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GrnRecordUpdateServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        GrnRecordUpdate got = grnRecordUpdateRepository.findById(id.longValue()).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        grnRecordUpdateRepository.save(got);

        return 1;
    }

    private static GrnRecordUpdateResponse convert(GrnRecordUpdate grnRecordUpdate) {

        GrnRecordUpdateResponse typeResponse = new GrnRecordUpdateResponse();
        typeResponse.setBarcodeNo(grnRecordUpdate.getBarcodeNo());
        typeResponse.setBatchNo(grnRecordUpdate.getBatchNo());
        typeResponse.setBranchId(grnRecordUpdate.getBranch().getBranchId());
        typeResponse.setExpireDate(grnRecordUpdate.getExpireDate());
        typeResponse.setGrnIddd(grnRecordUpdate.getGrnIddd());
        //typeResponse.setIdgrnRecordUpdate(grnRecordUpdate.getIdgrnRecordUpdate());
        typeResponse.setItmeIddd(grnRecordUpdate.getItem().getItemName());
        typeResponse.setStkQty(grnRecordUpdate.getStkQty());
        typeResponse.setSupplierIddd(grnRecordUpdate.getSupplier().getName());
        typeResponse.setUnitPrice(grnRecordUpdate.getUnitPrice());
        typeResponse.setUpdateDate(grnRecordUpdate.getUpdateDate());
        typeResponse.setUpdateTime(grnRecordUpdate.getUpdateTime());
        //typeResponse.setIdgrnRecordUpdate(grnRecordUpdate.getIdgrnRecordUpdate());
        typeResponse.setCreatedBy(grnRecordUpdate.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnRecordUpdate.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnRecordUpdate.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(grnRecordUpdate.getModifiedDateTime()));
        typeResponse.setIsDeleted(grnRecordUpdate.getIsDeleted());

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