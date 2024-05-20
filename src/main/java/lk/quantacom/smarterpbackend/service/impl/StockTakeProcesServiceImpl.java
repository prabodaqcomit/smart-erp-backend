package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesRequest;
import lk.quantacom.smarterpbackend.dto.request.StockTakeProcesUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockTakeProcesResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.BinCardRepository;
import lk.quantacom.smarterpbackend.repository.ItemMasterRepository;
import lk.quantacom.smarterpbackend.repository.StockRepository;
import lk.quantacom.smarterpbackend.repository.StockTakeProcesRepository;
import lk.quantacom.smarterpbackend.service.StockTakeProcesService;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockTakeProcesServiceImpl implements StockTakeProcesService {

    @Autowired
    private StockTakeProcesRepository stockTakeProcesRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private StockRepository stockRepository;

    private static StockTakeProcesResponse convert(StockTakeProces stockTakeProces) {

        StockTakeProcesResponse typeResponse = new StockTakeProcesResponse();
        typeResponse.setAvailabQty(stockTakeProces.getAvailabQty());
        typeResponse.setAvrgPrice(stockTakeProces.getAvrgPrice());
        typeResponse.setBarcodeNo(stockTakeProces.getBarcodeNo());
        typeResponse.setBatchNo(stockTakeProces.getBatchNo());
        typeResponse.setBranchId(stockTakeProces.getBranchId());
        typeResponse.setCashDisValue(stockTakeProces.getCashDisValue());
        typeResponse.setColorId(stockTakeProces.getColorId());
        typeResponse.setCreatedBy(stockTakeProces.getCreatedBy());
        typeResponse.setCreatedDateTime(stockTakeProces.getCreatedDateTime());
        typeResponse.setCreditDisValue(stockTakeProces.getCreditDisValue());
        typeResponse.setDamgQty(stockTakeProces.getDamgQty());
        typeResponse.setExpireDate(stockTakeProces.getExpireDate());
        typeResponse.setFitId(stockTakeProces.getFitId());
        typeResponse.setIsDeleted(stockTakeProces.getIsDeleted());
        typeResponse.setItemCode(stockTakeProces.getItemCode());
        typeResponse.setMaterialWidth(stockTakeProces.getMaterialWidth());
        typeResponse.setModifiedBy(stockTakeProces.getModifiedBy());
        typeResponse.setModifiedDateTime(stockTakeProces.getModifiedDateTime());
        typeResponse.setObStock(stockTakeProces.getObStock());
        typeResponse.setSalesDiscoPresentage(stockTakeProces.getSalesDiscoPresentage());
        typeResponse.setSalesPerson(stockTakeProces.getSalesPerson());
        typeResponse.setSizeId(stockTakeProces.getSizeId());
        typeResponse.setStkCashPrice(stockTakeProces.getStkCashPrice());
        typeResponse.setStkCreditPrice(stockTakeProces.getStkCreditPrice());
        typeResponse.setStkQty(stockTakeProces.getStkQty());
        typeResponse.setStockUnitPriceRetail(stockTakeProces.getStockUnitPriceRetail());
        typeResponse.setStockUnitPriceWholesale(stockTakeProces.getStockUnitPriceWholesale());
        typeResponse.setStoresQty(stockTakeProces.getStoresQty());
        typeResponse.setSupplierId(stockTakeProces.getSupplierId());
        typeResponse.setTotalQty(stockTakeProces.getTotalQty());
        typeResponse.setStockProces(stockTakeProces.getStockProces());
        typeResponse.setId(stockTakeProces.getId());
        typeResponse.setId(stockTakeProces.getId());
        typeResponse.setPhysicalQty(stockTakeProces.getPhysicalQty());
        typeResponse.setVarianceQty(stockTakeProces.getVarianceQty());
        typeResponse.setPendingProcessId(stockTakeProces.getPendingProcessId());
        typeResponse.setItemName(stockTakeProces.getItemName());
        typeResponse.setSizeDesc(stockTakeProces.getSizeDesc());
        typeResponse.setColorDesc(stockTakeProces.getColorDesc());
        typeResponse.setFitDesc(stockTakeProces.getFitDesc());
        typeResponse.setBranchName(stockTakeProces.getBranchName());

        return typeResponse;
    }

    @Override
    @Transactional
    public StockTakeProcesResponse save(StockTakeProcesRequest request) {

        StockTakeProces stockTakeProces = new StockTakeProces();
        stockTakeProces.setAvailabQty(request.getAvailabQty());
        stockTakeProces.setAvrgPrice(request.getAvrgPrice());
        stockTakeProces.setBarcodeNo(request.getBarcodeNo());
        stockTakeProces.setBatchNo(request.getBatchNo());
        stockTakeProces.setBranchId(request.getBranchId());
        stockTakeProces.setCashDisValue(request.getCashDisValue());
        stockTakeProces.setColorId(request.getColorId());
        stockTakeProces.setCreatedBy(request.getCreatedBy());
        stockTakeProces.setCreatedDateTime(new Date());
        stockTakeProces.setCreditDisValue(request.getCreditDisValue());
        stockTakeProces.setDamgQty(request.getDamgQty());
        stockTakeProces.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        stockTakeProces.setFitId(request.getFitId());
        stockTakeProces.setIsDeleted(Deleted.NO);
        stockTakeProces.setItemCode(request.getItemCode());
        stockTakeProces.setMaterialWidth(request.getMaterialWidth());
        stockTakeProces.setModifiedBy(request.getCreatedBy());
        stockTakeProces.setModifiedDateTime(new Date());
        stockTakeProces.setObStock(request.getObStock());
        stockTakeProces.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
        stockTakeProces.setSalesPerson(request.getSalesPerson());
        stockTakeProces.setSizeId(request.getSizeId());
        stockTakeProces.setStkCashPrice(request.getStkCashPrice());
        stockTakeProces.setStkCreditPrice(request.getStkCreditPrice());
        stockTakeProces.setStkQty(request.getStkQty());
        stockTakeProces.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
        stockTakeProces.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
        stockTakeProces.setStoresQty(request.getStoresQty());
        stockTakeProces.setSupplierId(request.getSupplierId());
        stockTakeProces.setTotalQty(request.getTotalQty());
        stockTakeProces.setStockProces(request.getStockProces());
        StockTakeProces save = stockTakeProcesRepository.save(stockTakeProces);

        return convert(save);
    }

    @Override
    @Transactional
    public StockTakeProcesResponse update(StockTakeProcesUpdateRequest request) {

        StockTakeProces stockTakeProces = stockTakeProcesRepository.findById(request.getId()).orElse(null);
        if (stockTakeProces == null) {
            return null;
        }

        stockTakeProces.setId(request.getId());
        stockTakeProces.setAvailabQty(request.getAvailabQty());
        stockTakeProces.setAvrgPrice(request.getAvrgPrice());
        stockTakeProces.setBarcodeNo(request.getBarcodeNo());
        stockTakeProces.setBatchNo(request.getBatchNo());
        stockTakeProces.setBranchId(request.getBranchId());
        stockTakeProces.setCashDisValue(request.getCashDisValue());
        stockTakeProces.setColorId(request.getColorId());
        stockTakeProces.setCreatedDateTime(new Date());
        stockTakeProces.setCreditDisValue(request.getCreditDisValue());
        stockTakeProces.setDamgQty(request.getDamgQty());
        stockTakeProces.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
        stockTakeProces.setFitId(request.getFitId());
        stockTakeProces.setIsDeleted(Deleted.NO);
        stockTakeProces.setItemCode(request.getItemCode());
        stockTakeProces.setMaterialWidth(request.getMaterialWidth());
        stockTakeProces.setModifiedDateTime(new Date());
        stockTakeProces.setObStock(request.getObStock());
        stockTakeProces.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
        stockTakeProces.setSalesPerson(request.getSalesPerson());
        stockTakeProces.setSizeId(request.getSizeId());
        stockTakeProces.setStkCashPrice(request.getStkCashPrice());
        stockTakeProces.setStkCreditPrice(request.getStkCreditPrice());
        stockTakeProces.setStkQty(request.getStkQty());
        stockTakeProces.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
        stockTakeProces.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
        stockTakeProces.setStoresQty(request.getStoresQty());
        stockTakeProces.setSupplierId(request.getSupplierId());
        stockTakeProces.setTotalQty(request.getTotalQty());
        stockTakeProces.setStockProces(request.getStockProces());
        stockTakeProces.setId(request.getId());
        StockTakeProces updated = stockTakeProcesRepository.save(stockTakeProces);

        return (convert(updated));
    }

    @Override
    public StockTakeProcesResponse getById(Integer id) {

        return stockTakeProcesRepository.findById(id).map(StockTakeProcesServiceImpl::convert).orElse(null);
    }

    @Override
    public List<StockTakeProcesResponse> getAll() {
        return stockTakeProcesRepository.findByIsDeleted(Deleted.NO)
                .stream().map(StockTakeProcesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        StockTakeProces got = stockTakeProcesRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        stockTakeProcesRepository.save(got);

        return 1;
    }

    @Override
    @Transactional
    public List<StockTakeProcesResponse> saveBulk(List<StockTakeProcesRequest> requests) {

        List<StockTakeProcesResponse> list = new ArrayList<>();

        Integer processId = stockTakeProcesRepository.getMaxId();
        if (processId == null) {
            processId = 1;
        } else {
            processId++;
        }

        for (StockTakeProcesRequest request : requests) {

            if (request.getId() != null) {

                StockTakeProces proces = stockTakeProcesRepository.getById(request.getId());
                proces.setIsDeleted(Deleted.YES);
                proces.setStockProces(true);
                stockTakeProcesRepository.save(proces);

                StockTakeProces stockTakeProces = new StockTakeProces();
                stockTakeProces.setAvailabQty(request.getAvailabQty());
                stockTakeProces.setAvrgPrice(request.getAvrgPrice());
                stockTakeProces.setBarcodeNo(request.getBarcodeNo());
                stockTakeProces.setBatchNo(request.getBatchNo());
                stockTakeProces.setBranchId(request.getBranchId());
                stockTakeProces.setCashDisValue(request.getCashDisValue());
                stockTakeProces.setColorId(request.getColorId());
                stockTakeProces.setCreatedBy(request.getCreatedBy());
                stockTakeProces.setCreatedDateTime(new Date());
                stockTakeProces.setCreditDisValue(request.getCreditDisValue());
                stockTakeProces.setDamgQty(request.getDamgQty());
                stockTakeProces.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
                stockTakeProces.setFitId(request.getFitId());
                stockTakeProces.setIsDeleted(Deleted.NO);
                stockTakeProces.setItemCode(request.getItemCode());
                stockTakeProces.setMaterialWidth(request.getMaterialWidth());
                stockTakeProces.setModifiedBy(request.getCreatedBy());
                stockTakeProces.setModifiedDateTime(new Date());
                stockTakeProces.setObStock(request.getObStock());
                stockTakeProces.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
                stockTakeProces.setSalesPerson(request.getSalesPerson());
                stockTakeProces.setSizeId(request.getSizeId());
                stockTakeProces.setStkCashPrice(request.getStkCashPrice());
                stockTakeProces.setStkCreditPrice(request.getStkCreditPrice());
                stockTakeProces.setStkQty(request.getStkQty());
                stockTakeProces.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
                stockTakeProces.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
                stockTakeProces.setStoresQty(request.getStoresQty());
                stockTakeProces.setSupplierId(request.getSupplierId());
                stockTakeProces.setTotalQty(request.getTotalQty());
                stockTakeProces.setStockProces(false);
                stockTakeProces.setPhysicalQty(request.getPhysicalQty());
                Double result = Math.abs(request.getStoresQty() - request.getPhysicalQty());
                stockTakeProces.setVarianceQty(result);
                stockTakeProces.setPendingProcessId(processId);
                stockTakeProces.setItemName(request.getItemName());
                stockTakeProces.setSizeDesc(request.getSizeDesc());
                stockTakeProces.setColorDesc(request.getColorDesc());
                stockTakeProces.setFitDesc(request.getFitDesc());
                stockTakeProces.setBranchName(request.getBranchName());

                stockTakeProcesRepository.save(stockTakeProces);

            } else {

                StockTakeProces stockTakeProces = new StockTakeProces();
                stockTakeProces.setAvailabQty(request.getAvailabQty());
                stockTakeProces.setAvrgPrice(request.getAvrgPrice());
                stockTakeProces.setBarcodeNo(request.getBarcodeNo());
                stockTakeProces.setBatchNo(request.getBatchNo());
                stockTakeProces.setBranchId(request.getBranchId());
                stockTakeProces.setCashDisValue(request.getCashDisValue());
                stockTakeProces.setColorId(request.getColorId());
                stockTakeProces.setCreatedBy(request.getCreatedBy());
                stockTakeProces.setCreatedDateTime(new Date());
                stockTakeProces.setCreditDisValue(request.getCreditDisValue());
                stockTakeProces.setDamgQty(request.getDamgQty());
                stockTakeProces.setExpireDate(request.getExpireDate() == null ? null : ConvertUtils.convertStrToDate(request.getExpireDate()));
                stockTakeProces.setFitId(request.getFitId());
                stockTakeProces.setIsDeleted(Deleted.NO);
                stockTakeProces.setItemCode(request.getItemCode());
                stockTakeProces.setMaterialWidth(request.getMaterialWidth());
                stockTakeProces.setModifiedBy(request.getCreatedBy());
                stockTakeProces.setModifiedDateTime(new Date());
                stockTakeProces.setObStock(request.getObStock());
                stockTakeProces.setSalesDiscoPresentage(request.getSalesDiscoPresentage());
                stockTakeProces.setSalesPerson(request.getSalesPerson());
                stockTakeProces.setSizeId(request.getSizeId());
                stockTakeProces.setStkCashPrice(request.getStkCashPrice());
                stockTakeProces.setStkCreditPrice(request.getStkCreditPrice());
                stockTakeProces.setStkQty(request.getStkQty());
                stockTakeProces.setStockUnitPriceRetail(request.getStockUnitPriceRetail());
                stockTakeProces.setStockUnitPriceWholesale(request.getStockUnitPriceWholesale());
                stockTakeProces.setStoresQty(request.getStoresQty());
                stockTakeProces.setSupplierId(request.getSupplierId());
                stockTakeProces.setTotalQty(request.getTotalQty());
                stockTakeProces.setStockProces(false);
                stockTakeProces.setPhysicalQty(request.getPhysicalQty());
                Double result = Math.abs(request.getStoresQty() - request.getPhysicalQty());
                stockTakeProces.setVarianceQty(result);
                stockTakeProces.setPendingProcessId(processId);
                stockTakeProces.setItemName(request.getItemName());
                stockTakeProces.setSizeDesc(request.getSizeDesc());
                stockTakeProces.setColorDesc(request.getColorDesc());
                stockTakeProces.setFitDesc(request.getFitDesc());
                stockTakeProces.setBranchName(request.getBranchName());

                StockTakeProces save = stockTakeProcesRepository.save(stockTakeProces);
                list.add(convert(save));
            }


        }
        return list;
    }

    @Override
    public List<StockTakeProcesResponse> getProcesList() {
        return stockTakeProcesRepository.findByStockProcesAndIsDeleted(false, Deleted.NO)
                .stream().map(StockTakeProcesServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<StockTakeProcesResponse> updateBulk(List<StockTakeProcesRequest> requests) {

        List<StockTakeProcesResponse> list = new ArrayList<>();

        if (requests.get(0).getIsZeroProcess()) {

            for (StockTakeProcesRequest request : requests) {

                StockTakeProces stockTakeProces = stockTakeProcesRepository.findById(request.getId()).orElse(null);

                if (stockTakeProces == null) {
                    return null;
                }

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(),
                        request.getItemCode(), request.getBranchId(), request.getColorId(), request.getSizeId(), request.getFitId());
                stock.setStoresQty(request.getPhysicalQty());
                stock.setTotalQty(request.getPhysicalQty());
                stock.setModifiedDateTime(new Date());
                stockRepository.save(stock);

                stockTakeProces.setStockProces(true);
                stockTakeProces.setPhysicalQty(request.getPhysicalQty());
                StockTakeProces updated = stockTakeProcesRepository.save(stockTakeProces);

                stockTakeProcesRepository.updateStock();
                stockTakeProcesRepository.updateStockByCat();

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(request.getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("STOCK TAKE");
                binCard.setDocNo("");
                binCard.setRecQty(request.getPhysicalQty());
                binCard.setIsueQty(0.00);
                binCard.setBalanceQty(request.getPhysicalQty());
                binCard.setBatchNo(stock.getStockPK().getBatchNo());
                binCard.setBranch(stock.getStockPK().getBranch());
                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(stock.getStockPK().getFit());
                binCard.setColor(stock.getStockPK().getColor());
                binCard.setSize(stock.getStockPK().getSize());
                binCardRepository.save(binCard);

                list.add(convert(updated));
            }
        } else {

            for (StockTakeProcesRequest request : requests) {

                StockTakeProces stockTakeProces = stockTakeProcesRepository.findById(request.getId()).orElse(null);
                if (stockTakeProces == null) {
                    return null;
                }

                Stock stock ;
                if (request.getBatchNo()==null){
                     stock = stockRepository.getByItemAndBranchAndColorAndSizeAndFit(request.getItemCode(),
                            request.getBranchId(), request.getColorId(), request.getSizeId(), request.getFitId());
                }else {
                    stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(),
                            request.getItemCode(), request.getBranchId(), request.getColorId(), request.getSizeId(), request.getFitId());
                }

                if (stock!=null){
                    stock.setStoresQty(request.getPhysicalQty());
                    stock.setTotalQty(request.getPhysicalQty());
                    stock.setModifiedDateTime(new Date());
                    stockRepository.save(stock);
                }

                stockTakeProces.setStockProces(true);
                stockTakeProces.setPhysicalQty(request.getPhysicalQty());
                StockTakeProces updated = stockTakeProcesRepository.save(stockTakeProces);

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(request.getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("STOCK TAKE");
                binCard.setDocNo("");
                binCard.setRecQty(request.getPhysicalQty());
                binCard.setIsueQty(0.00);
                binCard.setBalanceQty(request.getPhysicalQty());
                binCard.setBatchNo(request.getBatchNo());
                System.out.println("branch id :- "+request.getBranchId());
                BranchNetwork branchNetwork = new BranchNetwork();
                branchNetwork.setId(request.getBranchId());
                binCard.setBranch(branchNetwork);

                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(request.getFitId());
                binCard.setColor(request.getColorId());
                binCard.setSize(request.getSizeId());
                binCardRepository.save(binCard);

                list.add(convert(updated));
            }
        }

        return list;
    }

    @Override
    public File printVarianceReport(String types) {

        File out = null;
        Connection co = null;
        try {

            File file = new File("JRXML/report/itemVarienceReport.jrxml");
            Map<String, Object> map = new HashMap<>();

            map.put("strqty", stockTakeProcesRepository.getStoresPlusQty()==null?0.00:stockTakeProcesRepository.getStoresPlusQty());
            map.put("phyqty", stockTakeProcesRepository.getPhysicalQty()==null?0.00:stockTakeProcesRepository.getPhysicalQty());
            map.put("minqty", stockTakeProcesRepository.getStoresMinusQty()==null?0.00:stockTakeProcesRepository.getStoresMinusQty());

            co = JDBC.con();
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getPath());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, map, co);

            String filepath = "";
            if (types.equals("pdf")) {
                filepath = "TMP/" + System.currentTimeMillis() + ".pdf";
                JasperExportManager.exportReportToPdfFile(print, filepath);
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

}