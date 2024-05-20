package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.StockAdjustRequest;
import lk.quantacom.smarterpbackend.dto.request.StockAdjustUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.StockAdjustResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.BinCardRepository;
import lk.quantacom.smarterpbackend.repository.StockAdjustRepository;
import lk.quantacom.smarterpbackend.repository.StockRepository;
import lk.quantacom.smarterpbackend.service.StockAdjustService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import lk.quantacom.smarterpbackend.repository.UserLoggerRepository;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockAdjustServiceImpl implements StockAdjustService {

    @Autowired
    private StockAdjustRepository stockAdjustRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<StockAdjustResponse> save(List<StockAdjustRequest> requests) {

        List<StockAdjustResponse> responses=new ArrayList<>();

        for(StockAdjustRequest request:requests){

            ItemMaster item=new ItemMaster();
            item.setId(request.getItemId());

            BranchNetwork branch=new BranchNetwork();
            branch.setId(request.getBranchId());


            List<Stock> stockList=stockRepository.getByBatchNoAndItemAndBranch(request.getStkAdjBatchNo(),request.getItemId(),request.getBranchId());
            if(stockList!=null){
                for(Stock stock:stockList){
                    StockAdjust stockAdjust = new StockAdjust();
                    stockAdjust.setGrandTotal(request.getGrandTotal());
                    stockAdjust.setInhandQty(request.getInhandQty());
                    stockAdjust.setLatestQty(request.getLatestQty());
                    stockAdjust.setAdjtQty(request.getAdjtQty());
                    stockAdjust.setUnitPrice(request.getUnitPrice());
                    stockAdjust.setItemValue(request.getItemValue());
                    stockAdjust.setItem(item);
                    stockAdjust.setStkAdjBatchNo(request.getStkAdjBatchNo());
                    stockAdjust.setBranch(branch);
                    stockAdjust.setIsDeleted(Deleted.NO);
                    StockAdjust save = stockAdjustRepository.save(stockAdjust);

                    //Stock Update

                    double avlblQty=stock.getAvailabQty();
                    double newQty=(request.getAdjtQty()<0)? (avlblQty-(-request.getAdjtQty())):(avlblQty+request.getAdjtQty());
                    System.out.println(newQty);
                    stock.setAvailabQty(newQty);
                    stockRepository.save(stock);

                    // Bin Card
                    BinCard binCard = new BinCard();
                    binCard.setItem(item);
                    binCard.setBinCardDate(new Date());
                    binCard.setDocNo(save.getId() + "");
                    binCard.setDocType("STOCK ADJUSTMENT NOTE");
                    binCard.setRecQty((request.getAdjtQty()<0)? 0.0:(request.getAdjtQty()));
                    binCard.setIsueQty((request.getAdjtQty()<0)? (request.getAdjtQty()*(-1)):0.0);
                    binCard.setBalanceQty(request.getLatestQty());
                    binCard.setBatchNo(request.getStkAdjBatchNo());
                    BranchNetwork branchNetwork = new BranchNetwork();
                    branchNetwork.setId(request.getBranchId());
                    binCard.setBranch(branchNetwork);
                    binCard.setIsDeleted(Deleted.NO);
                    binCardRepository.save(binCard);

                    responses.add(convert(save));
                }

            }



        }

        if(responses.size()!=0){
            saveLog("StockAdjust", "Data Saved - Bulk");
        }

        return responses;
    }

    @Override
    @Transactional
    public StockAdjustResponse update(StockAdjustUpdateRequest request) {

        StockAdjust stockAdjust = stockAdjustRepository.findById(request.getId()).orElse(null);
        if (stockAdjust == null) {
            return null;
        }

        stockAdjust.setId(request.getId());
        stockAdjust.setGrandTotal(request.getGrandTotal());
        stockAdjust.setInhandQty(request.getInhandQty());
        stockAdjust.setLatestQty(request.getLatestQty());
        stockAdjust.setAdjtQty(request.getAdjtQty());
        stockAdjust.setUnitPrice(request.getUnitPrice());
        stockAdjust.setItemValue(request.getItemValue());
        ItemMaster item=new ItemMaster();
        item.setId(request.getItemId());
        stockAdjust.setItem(item);
        stockAdjust.setStkAdjBatchNo(request.getStkAdjBatchNo());
        BranchNetwork branch=new BranchNetwork();
        branch.setId(request.getBranchId());
        stockAdjust.setBranch(branch);
        StockAdjust updated = stockAdjustRepository.save(stockAdjust);

        saveLog("StockAdjust", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public StockAdjustResponse getById(Long id) {

        return stockAdjustRepository.findById(id).map(StockAdjustServiceImpl::convert).orElse(null);
    }

    @Override
    public List<StockAdjustResponse> getAll() {

        return stockAdjustRepository.findByIsDeleted(Deleted.NO)
                .stream().map(StockAdjustServiceImpl::convert).collect(Collectors.toList());

    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        StockAdjust got = stockAdjustRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        stockAdjustRepository.save(got);

        saveLog("StockAdjust", "Data Deleted - " + id);

        return 1;
    }

    private static StockAdjustResponse convert(StockAdjust stockAdjust) {

        StockAdjustResponse typeResponse = new StockAdjustResponse();
        typeResponse.setGrandTotal(stockAdjust.getGrandTotal());
        typeResponse.setInhandQty(stockAdjust.getInhandQty());
        typeResponse.setLatestQty(stockAdjust.getLatestQty());
        typeResponse.setAdjtQty(stockAdjust.getAdjtQty());
        typeResponse.setUnitPrice(stockAdjust.getUnitPrice());
        typeResponse.setItemValue(stockAdjust.getItemValue());
        typeResponse.setItemId(stockAdjust.getItem().getId());
        typeResponse.setStkAdjBatchNo(stockAdjust.getStkAdjBatchNo());
        typeResponse.setBranchId(stockAdjust.getBranch().getId());
        typeResponse.setId(stockAdjust.getId());
        typeResponse.setCreatedBy(stockAdjust.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(stockAdjust.getCreatedDateTime()));
        typeResponse.setModifiedBy(stockAdjust.getModifiedBy());
        typeResponse.setIsDeleted(stockAdjust.getIsDeleted());

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