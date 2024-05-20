package lk.quantacom.smarterpbackend.service;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.Stock;
import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public interface StockService {

    StockResponse save(StockRequest request);

    StockResponse update(StockUpdateRequest request);

    StockResponse getById(Long id);

    List<StockResponse> getAll();

    List<StockFullResponse> searchStock(StockSearchRequest request);

    List<StockFullResponse> searchStockExp(StockSearchRequest request);

    File printStockRpt(StockSearchRequest request,String which) throws Exception;

    Integer delete(Long id);

    List<StockResponse> getPriceChangesList(StockPriceChangesRequest request);

    List<StockResponse> updatePriceChange(List<StockPriceChangeUpdateRequest> requests);

    List<StockResponse> stockTransfer(StockTransferRequest request);

    List<StockResponse> getByBranchAndIsDeleted(Long branchId);

    List<StockResponse> getByItemCodeAndBranch(String itemCode, Long branchId);

    List<stockLocationResponse> getByStockLocation();

    List<Double> getSumQtyOfLoc(String itemCode,Long catId);

    Double getTotStock(String itemCode,Long catId);

    List<BinCardStockResponse> binCardStock(BinCardItemStockRequest request);

    List<getAllStockByItemResponse> getItemCode();

    StockResponse getByItemCode(String itemCode);
}