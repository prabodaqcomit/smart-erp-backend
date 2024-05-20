package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceRequest;
import lk.quantacom.smarterpbackend.dto.request.OpeningBalanceUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.OpeningBalanceResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.OpeningBalanceService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpeningBalanceServiceImpl implements OpeningBalanceService {

    @Autowired
    private OpeningBalanceRepository openingBalanceRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private BinCardRepository binCardRepository;

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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<OpeningBalanceResponse> saveBulk(List<OpeningBalanceRequest> requestList) {

        List<OpeningBalanceResponse> responses = new ArrayList<>();

        int genId = 1;

        Integer max = openingBalanceRepository.getMax();
        if (max != null) {
            genId = max + 1;
        }

        for (OpeningBalanceRequest request : requestList) {
            OpeningBalance openingBalance = new OpeningBalance();
            openingBalance.setObNo(genId);
            ItemMaster itemMaster = new ItemMaster();
            itemMaster.setId(request.getItemId());
            openingBalance.setItem(itemMaster);

            openingBalance.setBatchNo(request.getBatchNo());

            BranchNetwork branchNetwork = new BranchNetwork();
            branchNetwork.setId(request.getBranchId());

            openingBalance.setBranch(branchNetwork);
            openingBalance.setObDate(request.getObDate() == null ? null : ConvertUtils.convertStrToDate(request.getObDate()));
            openingBalance.setObQty(request.getObQty());
            openingBalance.setUnitPrice(request.getUnitPrice());
            openingBalance.setItemValue(request.getItemValue());
            openingBalance.setGrandTotal(request.getGrandTotal());
            openingBalance.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
            openingBalance.setIsDeleted(Deleted.NO);
            OpeningBalance save = openingBalanceRepository.save(openingBalance);

            responses.add(convert(save));


            Stock stk = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getStockRequest().getBatchNo(),request.getStockRequest().getItemId(),
                    request.getStockRequest().getBranchId(),request.getStockRequest().getColorId(),request.getStockRequest().getSizeId(),request.getStockRequest().getFitId());

            if (stk!=null){
                stk.setStoresQty(stk.getStoresQty()+request.getStockRequest().getStoresQty());
                stk.setTotalQty(stk.getTotalQty()+request.getStockRequest().getTotalQty());
                stockRepository.save(stk);
            }else {
                //save stock
                Stock stock = new Stock();
                stock.setStkQty(request.getStockRequest().getStkQty());
                stock.setDamgQty(request.getStockRequest().getDamgQty());
                stock.setAvailabQty(request.getStockRequest().getAvailabQty());
                stock.setAvrgPrice(request.getStockRequest().getAvrgPrice());
                stock.setExpireDate(request.getStockRequest().getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
                stock.setStkCashPrice(request.getStockRequest().getStkCashPrice());
                stock.setStkCreditPrice(request.getStockRequest().getStkCreditPrice());
                stock.setStoresQty(request.getStockRequest().getStoresQty());
                stock.setTotalQty(request.getStockRequest().getTotalQty());
                stock.setBarcodeNo(request.getStockRequest().getBarcodeNo());
                stock.setStockUnitPriceRetail(request.getStockRequest().getStockUnitPriceRetail());
                stock.setStockUnitPriceWholesale(request.getStockRequest().getStockUnitPriceWholesale());

                Supplier supplier = new Supplier();
                supplier.setId(request.getStockRequest().getSupplierId());
                stock.setSupplier(supplier);

//      stock.setSalesPerson(request.getSalesPerson());
//      stock.setAgencyName(request.getAgencyName());

                stock.setObStock(request.getStockRequest().getObStock());
                stock.setCashDisValue(request.getStockRequest().getCashDisValue());
                stock.setCreditDisValue(request.getStockRequest().getCreditDisValue());
                stock.setSalesDiscoPresentage(request.getStockRequest().getSalesDiscoPresentage());
                stock.setMaterialWidth(request.getStockRequest().getMaterialWidth());

                Color color = null;
                Size size = null;
                Fit fit = null;

                if (request.getStockRequest().getColorId() != 0) {
                    color = new Color();
                    color.setId(request.getStockRequest().getColorId());
                }

                if (request.getStockRequest().getSizeId() != 0) {
                    size = new Size();
                    size.setId(request.getStockRequest().getSizeId());
                }

                if (request.getStockRequest().getFitId() != 0) {
                    fit = new Fit();
                    fit.setId(request.getStockRequest().getFitId());
                }

                StockPK stockPK = new StockPK(itemMaster, request.getStockRequest().getColorId(), request.getStockRequest().getSizeId(), request.getStockRequest().getFitId(), branchNetwork, request.getBatchNo());
                stock.setStockPK(stockPK);
                stock.setIsDeleted(Deleted.NO);
                stockRepository.save(stock);

                //update unit price
                ItemMaster itemForUpdate = itemMasterRepository.findById(request.getItemId()).orElse(null);
                if (itemForUpdate != null) {
                    itemForUpdate.setUnitPrice(stock.getAvrgPrice());
                    itemForUpdate.setBarcode(request.getStockRequest().getBarcodeNo());
                    itemMasterRepository.save(itemForUpdate);
                }

            }

            // save bin card
            BinCard binCard = new BinCard();
            binCard.setItem(itemMaster);
            binCard.setBinCardDate(request.getBinCardRequest().getBinCardDate() == null ?
                    null : ConvertUtils.convertStrToDate(request.getBinCardRequest().getBinCardDate()));
            binCard.setDocType(request.getBinCardRequest().getDocType());
            binCard.setDocNo(save.getId() + "");
            binCard.setRecQty(request.getBinCardRequest().getRecQty());
            binCard.setIsueQty(request.getBinCardRequest().getIsueQty());
            binCard.setBalanceQty(request.getBinCardRequest().getBalanceQty());
            binCard.setBatchNo(request.getBinCardRequest().getBatchNo());

            binCard.setColor(request.getStockRequest().getColorId()==null ? null:request.getStockRequest().getColorId());
            binCard.setFit(request.getStockRequest().getFitId()==null ? null:request.getStockRequest().getFitId());
            binCard.setSize(request.getStockRequest().getSizeId()==null ? null:request.getStockRequest().getSizeId());

            //BranchNetwork branchNetwork=new BranchNetwork();
            branchNetwork.setId(request.getBranchId());
            binCard.setBranch(branchNetwork);
            binCard.setIsDeleted(Deleted.NO);
            BinCard card = binCardRepository.save(binCard);
            System.out.println("bin card save "+card.getId());

        }

        saveLog("OpeningBalance", "Data Saved - Bulk");

        return responses;
    }

    @Override
    @Transactional
    public OpeningBalanceResponse save(OpeningBalanceRequest request) {

        OpeningBalance openingBalance = new OpeningBalance();

        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setId(request.getItemId());
        openingBalance.setItem(itemMaster);

        openingBalance.setBatchNo(request.getBatchNo());

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        openingBalance.setBranch(branchNetwork);

        openingBalance.setObDate(request.getObDate() == null ? null : ConvertUtils.convertStrToDate(request.getObDate()));
        openingBalance.setObQty(request.getObQty());
        openingBalance.setUnitPrice(request.getUnitPrice());
        openingBalance.setItemValue(request.getItemValue());
        openingBalance.setGrandTotal(request.getGrandTotal());
        openingBalance.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        openingBalance.setIsDeleted(Deleted.NO);
        OpeningBalance save = openingBalanceRepository.save(openingBalance);

        saveLog("OpeningBalance", "Data Saved - " + save.getId());

        return convert(save);
    }

    @Override
    @Transactional
    public OpeningBalanceResponse update(OpeningBalanceUpdateRequest request) {

        OpeningBalance openingBalance = openingBalanceRepository.findById(request.getId()).orElse(null);
        if (openingBalance == null) {
            return null;
        }

        openingBalance.setId(request.getId());
        ItemMaster itemMaster = new ItemMaster();
        itemMaster.setId(request.getItemId());
        openingBalance.setItem(itemMaster);

        openingBalance.setBatchNo(request.getBatchNo());

        BranchNetwork branchNetwork = new BranchNetwork();
        branchNetwork.setId(request.getBranchId());
        openingBalance.setBranch(branchNetwork);
        openingBalance.setObDate(request.getObDate() == null ? null : ConvertUtils.convertStrToDate(request.getObDate()));
        openingBalance.setObQty(request.getObQty());
        openingBalance.setUnitPrice(request.getUnitPrice());
        openingBalance.setItemValue(request.getItemValue());
        openingBalance.setGrandTotal(request.getGrandTotal());
        openingBalance.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        OpeningBalance updated = openingBalanceRepository.save(openingBalance);

        saveLog("OpeningBalance", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public OpeningBalanceResponse getById(Long id) {

        return openingBalanceRepository.findById(id).map(OpeningBalanceServiceImpl::convert).orElse(null);
    }

    @Override
    public List<OpeningBalanceResponse> getAll() {

        return openingBalanceRepository.findAll()
                .stream().map(OpeningBalanceServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        OpeningBalance got = openingBalanceRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        openingBalanceRepository.save(got);

        saveLog("OpeningBalance", "Data Deleted - " + id);

        return 1;
    }

    private static OpeningBalanceResponse convert(OpeningBalance openingBalance) {

        OpeningBalanceResponse typeResponse = new OpeningBalanceResponse();
        typeResponse.setItemId(openingBalance.getItem().getId());
        typeResponse.setBatchNo(openingBalance.getBatchNo());
        typeResponse.setBranchId(openingBalance.getBranch().getId());
        typeResponse.setObDate(ConvertUtils.convertDateToStr(openingBalance.getObDate()));
        typeResponse.setObQty(openingBalance.getObQty());
        typeResponse.setUnitPrice(openingBalance.getUnitPrice());
        typeResponse.setItemValue(openingBalance.getItemValue());
        typeResponse.setGrandTotal(openingBalance.getGrandTotal());
        typeResponse.setExpireDate(ConvertUtils.convertDateToStr(openingBalance.getExpireDate()));
        typeResponse.setObNo(openingBalance.getObNo());
        typeResponse.setId(openingBalance.getId());
        typeResponse.setCreatedBy(openingBalance.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(openingBalance.getCreatedDateTime()));
        typeResponse.setModifiedBy(openingBalance.getModifiedBy());
        typeResponse.setIsDeleted(openingBalance.getIsDeleted());

        return typeResponse;
    }
}