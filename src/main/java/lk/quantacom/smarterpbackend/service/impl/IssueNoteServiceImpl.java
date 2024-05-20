package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.IssueNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.IssueNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.StockRequest;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteResponse;
import lk.quantacom.smarterpbackend.dto.response.IssueNoteTempResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.IssueNoteService;
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
public class IssueNoteServiceImpl implements IssueNoteService {

    @Autowired
    private IssueNoteRepository issueNoteRepository;

    @Autowired
    private IssueNoteTempRepository issueNoteTempRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private IssueNoteTempServiceImpl issueNoteTempService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private FitRepository fitRepository;

    @Autowired
    private SizeRepository sizeRepository;


    private static IssueNoteResponse convert(IssueNote issueNote) {

        IssueNoteResponse typeResponse = new IssueNoteResponse();
        typeResponse.setItemCode(issueNote.getItem().getItemCode());
        typeResponse.setItemId(issueNote.getItem().getId());
        typeResponse.setBatchNo(issueNote.getBatchNo());
        typeResponse.setBranchId(issueNote.getBranch().getId());
        typeResponse.setIssueDate(ConvertUtils.convertDateToStr(issueNote.getIssueDate()));
        typeResponse.setTransferMethod(issueNote.getTransferMethod());
        typeResponse.setTrFrom(issueNote.getTrFrom());
        typeResponse.setTrTo(issueNote.getTrTo());
        typeResponse.setUnitPrice(issueNote.getUnitPrice());
        typeResponse.setItemValue(issueNote.getItemValue());
        typeResponse.setIssueQty(issueNote.getIssueQty());
        typeResponse.setInHandQty(issueNote.getInHandQty());
        typeResponse.setCurrentBalance(issueNote.getCurrentBalance());
        typeResponse.setGrandTotal(issueNote.getGrandTotal());
        typeResponse.setIssueTotalQty(issueNote.getIssueTotalQty());
        typeResponse.setItemCreditValue(issueNote.getItemCreditValue());
        typeResponse.setItemCashValue(issueNote.getItemCashValue());
        typeResponse.setTotalCreditAmount(issueNote.getTotalCreditAmount());
        typeResponse.setTotalCashAmount(issueNote.getTotalCashAmount());
        typeResponse.setCashPrice(issueNote.getCashPrice());
        typeResponse.setCreditPrice(issueNote.getCreditPrice());
        typeResponse.setStoresInhand(issueNote.getStoresInhand());
        typeResponse.setStoresNew(issueNote.getStoresNew());
        typeResponse.setTransferBatchNo(issueNote.getTransferBatchNo());
        typeResponse.setTrFromBranchId(issueNote.getTrFromBranchId());
        typeResponse.setTrToBranchId(issueNote.getTrToBranchId());
//        typeResponse.setColorId(issueNote.getColor().getId());
//        typeResponse.setSizeId(issueNote.getSize().getId());
//        typeResponse.setFitId(issueNote.getFit().getId());
        typeResponse.setId(issueNote.getId());
        typeResponse.setCreatedBy(issueNote.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(issueNote.getCreatedDateTime()));
        typeResponse.setModifiedBy(issueNote.getModifiedBy());
        typeResponse.setIsDeleted(issueNote.getIsDeleted());
        typeResponse.setIssueId(issueNote.getIssueId());

        return typeResponse;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<IssueNoteResponse> save(List<IssueNoteRequest> requestList) {

        Long max = issueNoteRepository.getMaxId();
        if (max == null) {
            max = 1L;
        } else {
            max = max + 1;
        }

        Long max1 = issueNoteTempRepository.getMaxId();
        if (max1 == null) {
            max = 1L;
        } else {
            max1 = max1 + 1;
        }


        List<IssueNoteResponse> responses = new ArrayList<>();

        for (IssueNoteRequest request : requestList) {

            if (request.getTransferMethod().equals("MAIN STORES - BRANCH")) {

                IssueNoteTemp issueNoteTemp = new IssueNoteTemp();
                ItemMaster item = itemMasterRepository.getById(request.getItemId());
                //item.setId(request.getItemId());

                issueNoteTemp.setItem(item);
                issueNoteTemp.setBatchNo(request.getBatchNo());

                BranchNetwork branch = new BranchNetwork();
                branch.setId(request.getBranchId());

                issueNoteTemp.setBranch(branch);
                issueNoteTemp.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
                issueNoteTemp.setTransferMethod(request.getTransferMethod());
                issueNoteTemp.setTrFrom(request.getTrFrom());
                issueNoteTemp.setTrTo(request.getTrTo());
                issueNoteTemp.setUnitPrice(request.getUnitPrice());
                issueNoteTemp.setItemValue(request.getItemValue());
                issueNoteTemp.setIssueQty(request.getIssueQty());
                issueNoteTemp.setInHandQty(request.getInHandQty());
                issueNoteTemp.setCurrentBalance(request.getCurrentBalance());
                issueNoteTemp.setGrandTotal(request.getGrandTotal());
                issueNoteTemp.setIssueTotalQty(request.getIssueTotalQty());
                issueNoteTemp.setItemCreditValue(request.getItemCreditValue());
                issueNoteTemp.setItemCashValue(request.getItemCashValue());
                issueNoteTemp.setTotalCreditAmount(request.getTotalCreditAmount());
                issueNoteTemp.setTotalCashAmount(request.getTotalCashAmount());
                issueNoteTemp.setCashPrice(request.getCashPrice());
                issueNoteTemp.setCreditPrice(request.getCreditPrice());
                issueNoteTemp.setStoresInhand(request.getStoresInhand());
                issueNoteTemp.setStoresNew(request.getStoresNew());
                issueNoteTemp.setTransferBatchNo(request.getTransferBatchNo());
                issueNoteTemp.setTrFromBranchId(request.getTrFromBranchId());
                issueNoteTemp.setTrToBranchId(request.getTrToBranchId());
                issueNoteTemp.setColor(request.getColorId() == null ||request.getColorId() == 0 ? null : colorRepository.getById(request.getColorId()));
                issueNoteTemp.setSize(request.getSizeId() == null||request.getSizeId() == 0 ? null : sizeRepository.getById(request.getSizeId()));
                issueNoteTemp.setFit(request.getFitId() == null||request.getFitId()==0 ? null : fitRepository.getById(request.getFitId()));
                issueNoteTemp.setIsDeleted(Deleted.NO);
                issueNoteTemp.setId(max1);
                issueNoteTemp.setStatus("PENDING APPROVE");
                ItemMaster itemMaster = itemMasterRepository.findByItemCode(request.getItemCode());
                issueNoteTemp.setItemName(itemMaster.getItemName());
                issueNoteTemp.setIssueNoteSavedId("0");
                issueNoteTempRepository.save(issueNoteTemp);

            } else {

                IssueNote issueNote = new IssueNote();
                ItemMaster item = itemMasterRepository.getById(request.getItemId());
                //item.setId(request.getItemId());
                issueNote.setItem(item);
                issueNote.setBatchNo(request.getBatchNo());
                BranchNetwork branch = new BranchNetwork();
                branch.setId(request.getBranchId());

                issueNote.setBranch(branch);
                issueNote.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
                issueNote.setTransferMethod(request.getTransferMethod());
                issueNote.setTrFrom(request.getTrFrom());
                issueNote.setTrTo(request.getTrTo());
                issueNote.setUnitPrice(request.getUnitPrice());
                issueNote.setItemValue(request.getItemValue());
                issueNote.setIssueQty(request.getIssueQty());
                issueNote.setInHandQty(request.getInHandQty());
                issueNote.setCurrentBalance(request.getCurrentBalance());
                issueNote.setGrandTotal(request.getGrandTotal());
                issueNote.setIssueTotalQty(request.getIssueTotalQty());
                issueNote.setItemCreditValue(request.getItemCreditValue());
                issueNote.setItemCashValue(request.getItemCashValue());
                issueNote.setTotalCreditAmount(request.getTotalCreditAmount());
                issueNote.setTotalCashAmount(request.getTotalCashAmount());
                issueNote.setCashPrice(request.getCashPrice());
                issueNote.setCreditPrice(request.getCreditPrice());
                issueNote.setStoresInhand(request.getStoresInhand());
                issueNote.setStoresNew(request.getStoresNew());
                issueNote.setTransferBatchNo(request.getTransferBatchNo());
                issueNote.setTrFromBranchId(request.getTrFromBranchId());
                issueNote.setTrToBranchId(request.getTrToBranchId());
                issueNote.setColor(request.getColorId() == null ||request.getColorId() == 0 ? null : colorRepository.getById(request.getColorId()));
                issueNote.setSize(request.getSizeId() == null||request.getSizeId() == 0 ? null : sizeRepository.getById(request.getSizeId()));
                issueNote.setFit(request.getFitId() == null||request.getFitId()==0 ? null : fitRepository.getById(request.getFitId()));
                issueNote.setIsDeleted(Deleted.NO);
                issueNote.setIssueId(max);


                if (request.getTransferMethod().equals("MAIN STOCK - STORES")) {

                    IssueNote save = issueNoteRepository.save(issueNote);
                    responses.add(convert(save));

                    List<Stock> stockList = stockRepository.getByBatchNoAndItemAndBranchAndIsDeleted(request.getBatchNo(), item.getItemCode(), branch.getId());
                    for (Stock stock : stockList) {
                        double New_available_qty = (stock.getAvailabQty() - request.getIssueQty());
                        double New_stores_qty = (stock.getStoresQty() + request.getIssueQty());
                        double total_qty = New_available_qty + New_stores_qty;
                        stock.setStoresQty(New_stores_qty);
                        stock.setAvailabQty(New_available_qty);
                        stock.setTotalQty(total_qty);
                        stockRepository.save(stock);

                        BinCard binCard = new BinCard();
                        binCard.setItem(item);
                        binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getIssueDate()));
                        binCard.setDocType("STOCK TRANSFER NOTE");
                        binCard.setDocNo(max.toString());
                        binCard.setRecQty(0.00);
                        binCard.setIsueQty(request.getIssueQty());
                        binCard.setBalanceQty(New_available_qty);
                        binCard.setBatchNo(request.getBatchNo());
                        binCard.setBranch(branch);
                        binCardRepository.save(binCard);

                    }
                } else if (request.getTransferMethod().equals("STORES - MAIN STOCK")) {

                    IssueNote save = issueNoteRepository.save(issueNote);
                    responses.add(convert(save));

                    List<Stock> stockList = stockRepository.getByBatchNoAndItemAndBranchAndIsDeleted(request.getBatchNo(), item.getItemCode(), branch.getId());
                    for (Stock stock : stockList) {

                        double New_available_qty = (stock.getAvailabQty() + request.getIssueQty());
                        double New_stores_qty = (stock.getStoresQty() - request.getIssueQty());
                        double total_qty = New_available_qty + New_stores_qty;

                        stock.setStoresQty(New_stores_qty);
                        stock.setAvailabQty(New_available_qty);
                        stock.setTotalQty(total_qty);
                        stockRepository.save(stock);

                        BinCard binCard = new BinCard();
                        binCard.setItem(item);
                        binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getIssueDate()));
                        binCard.setDocType("STOCK TRANSFER NOTE");
                        binCard.setDocNo(max.toString());
                        binCard.setRecQty(0.00);
                        binCard.setIsueQty(request.getIssueQty());
                        binCard.setBalanceQty(New_available_qty);
                        binCard.setBatchNo(request.getBatchNo());
                        binCard.setBranch(branch);
                        binCardRepository.save(binCard);

                    }
                } else if (request.getTransferMethod().equals("BRANCH - BRANCH")) {

                    IssueNote save = issueNoteRepository.save(issueNote);
                    responses.add(convert(save));
                    BranchNetwork fromBranch = new BranchNetwork();
                    fromBranch.setId(request.getTrFromBranchId());
                    Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(), item.getItemCode(), fromBranch.getId(), request.getColorId() == 0 ? null : request.getColorId(), request.getSizeId() == 0 ? null : request.getSizeId(), request.getFitId() == 0 ? null : request.getFitId());
                    //for (Stock stock : stockList) {

                    //double douAvbQty=stock.getAvailabQty();
                    double douStoresQty = stock.getStoresQty();
                    //double douTotalQty = (douStoresQty + request.getIssueQty());
                    stock.setStoresQty(douStoresQty);
                    stock.setTotalQty(request.getInHandQty());
                    stock.setAvailabQty(request.getInHandQty());
                    stockRepository.save(stock);

                    BinCard binCard = new BinCard();
                    binCard.setItem(item);
                    binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getIssueDate()));
                    binCard.setDocType("STOCK TRANSFER NOTE");
                    binCard.setDocNo(max.toString());
                    binCard.setRecQty(0.00);
                    binCard.setIsueQty(request.getIssueQty());
//                    binCard.setBalanceQty(----------);
                    binCard.setBatchNo(request.getBatchNo());
                    binCard.setBranch(branch);
                    binCardRepository.save(binCard);

                    Stock stock2 = new Stock();

                    stock2.setStkQty(request.getIssueQty());
                    stock2.setDamgQty(0.00);
                    stock2.setAvailabQty(request.getIssueQty());
                    stock2.setAvrgPrice(stock.getAvrgPrice());
                    stock2.setExpireDate(stock.getExpireDate());
                    stock2.setStkCashPrice(stock.getStkCashPrice());
                    stock2.setStkCreditPrice(stock.getStkCreditPrice());
                    stock2.setStoresQty(stock.getStoresQty());
                    stock2.setTotalQty(request.getIssueQty());
                    stock2.setBarcodeNo(stock.getBarcodeNo());
                    stock2.setStockUnitPriceRetail(stock.getStockUnitPriceRetail());
                    stock2.setStockUnitPriceWholesale(stock.getStockUnitPriceWholesale());
                    stock2.setSupplier(stock.getSupplier());
                    stock2.setObStock(0.00);
                    stock2.setCashDisValue(stock.getCashDisValue());
                    stock2.setCreditDisValue(stock.getCreditDisValue());
                    stock2.setSalesDiscoPresentage(stock.getSalesDiscoPresentage());
                    stock2.setMaterialWidth(stock.getMaterialWidth());

                    BranchNetwork toBranch = new BranchNetwork();
                    toBranch.setId(request.getTrToBranchId());

                    StockPK stockPK = new StockPK(stock.getStockPK().getItem(), stock.getStockPK().getColor(), stock.getStockPK().getSize(), stock.getStockPK().getFit(), toBranch, stock.getStockPK().getBatchNo());
                    stock2.setStockPK(stockPK);

                    stock2.setIsDeleted(Deleted.NO);
                    stockRepository.save(stock2);

                    //  }
                } else if (request.getTransferMethod().equals("BATCH - BATCH(STORES")) {

                    IssueNote save = issueNoteRepository.save(issueNote);
                    responses.add(convert(save));

                    Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getTransferBatchNo(), item.getItemCode(), request.getBranchId(), request.getColorId() == 0 ? null : request.getColorId(), request.getSizeId() == 0 ? null : request.getSizeId(), request.getFitId() == 0 ? null : request.getFitId());
                    //for (Stock stock : stockList) {
                    double douStkQty = stock.getStkQty() + request.getIssueQty();
                    double douStoresQty = stock.getStoresQty() + request.getIssueQty();
                    double douTotalQty = stock.getTotalQty() + request.getIssueQty();

                    BinCard binCard = new BinCard();
                    binCard.setItem(item);
                    binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getIssueDate()));
                    binCard.setDocType("STOCK TRANSFER NOTE");
                    binCard.setDocNo(max.toString());
                    binCard.setRecQty(0.00);
                    binCard.setIsueQty(request.getIssueQty());
//                  binCard.setBalanceQty(----------);
                    binCard.setBatchNo(request.getBatchNo());
                    binCard.setBranch(branch);
                    binCardRepository.save(binCard);

                    //Stock stock2 = stockRepository.findByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getTransferBatchNo(), item, branch, new Color(request.getColorId()), new Size(request.getSizeId()), new Fit(request.getFitId()));
                    stock.setStkQty(douStkQty);
                    stock.setStoresQty(douStoresQty);
                    stock.setTotalQty(douTotalQty);
                    stockRepository.save(stock);

                    Stock stock2 = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(), item.getItemCode(), request.getBranchId(), request.getColorId() == 0 ? null : request.getColorId(), request.getSizeId() == 0 ? null : request.getSizeId(), request.getFitId() == 0 ? null : request.getFitId());
                    stock2.setStoresQty(stock2.getStoresQty()-request.getIssueQty());
                    stock2.setTotalQty(stock2.getTotalQty()-request.getIssueQty());
                    stockRepository.save(stock2);


                    //  }
                } else if (request.getTransferMethod().equals("BATCH - BATCH(SHOPS)")) {

                    IssueNote save = issueNoteRepository.save(issueNote);
                    responses.add(convert(save));


                    Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getTransferBatchNo(), item.getItemCode(), request.getBranchId(), request.getColorId() == 0 ? null : request.getColorId(), request.getSizeId() == 0 ? null : request.getSizeId(), request.getFitId() == 0 ? null : request.getFitId());
                    //for (Stock stock : stockList) {
                    double douStkQty = stock.getStkQty() + request.getIssueQty();
                    double douStoresQty = stock.getStoresQty() + request.getIssueQty();
                    double douTotalQty = stock.getTotalQty() + request.getIssueQty();

                    BinCard binCard = new BinCard();
                    binCard.setItem(item);
                    binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getIssueDate()));
                    binCard.setDocType("STOCK TRANSFER NOTE");
                    binCard.setDocNo(max.toString());
                    binCard.setRecQty(0.00);
                    binCard.setIsueQty(request.getIssueQty());
//                  binCard.setBalanceQty(----------);
                    binCard.setBatchNo(request.getBatchNo());
                    binCard.setBranch(branch);
                    binCardRepository.save(binCard);

                    //Stock stock2 = stockRepository.findByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getTransferBatchNo(), item, branch, new Color(request.getColorId()), new Size(request.getSizeId()), new Fit(request.getFitId()));
                    stock.setStkQty(douStkQty);
                    stock.setStoresQty(douStoresQty);
                    stock.setTotalQty(douTotalQty);
                    stockRepository.save(stock);

                    Stock stock2 = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(request.getBatchNo(), item.getItemCode(), request.getBranchId(), request.getColorId() == 0 ? null : request.getColorId(), request.getSizeId() == 0 ? null : request.getSizeId(), request.getFitId() == 0 ? null : request.getFitId());
                    stock2.setStoresQty(stock2.getAvailabQty()-request.getIssueQty());
                    stock2.setTotalQty(stock2.getTotalQty()-request.getIssueQty());
                    stockRepository.save(stock2);

                  //  }
                }
            }
        }

        saveLog("IssueNote", "Data Saved - Bulk");

        return responses;
    }

    @Override
    @Transactional
    public IssueNoteResponse update(IssueNoteUpdateRequest request) {

        IssueNote issueNote = issueNoteRepository.findById(request.getId()).orElse(null);
        if (issueNote == null) {
            return null;
        }

        issueNote.setId(request.getId());
        ItemMaster item = new ItemMaster();
        item.setId(request.getItemId());
        issueNote.setItem(item);
        issueNote.setBatchNo(request.getBatchNo());
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        issueNote.setBranch(branch);
        issueNote.setIssueDate(request.getIssueDate() == null ? null : ConvertUtils.convertStrToDate(request.getIssueDate()));
        issueNote.setTransferMethod(request.getTransferMethod());
        issueNote.setTrFrom(request.getTrFrom());
        issueNote.setTrTo(request.getTrTo());
        issueNote.setUnitPrice(request.getUnitPrice());
        issueNote.setItemValue(request.getItemValue());
        issueNote.setIssueQty(request.getIssueQty());
        issueNote.setInHandQty(request.getInHandQty());
        issueNote.setCurrentBalance(request.getCurrentBalance());
        issueNote.setGrandTotal(request.getGrandTotal());
        issueNote.setIssueTotalQty(request.getIssueTotalQty());
        issueNote.setItemCreditValue(request.getItemCreditValue());
        issueNote.setItemCashValue(request.getItemCashValue());
        issueNote.setTotalCreditAmount(request.getTotalCreditAmount());
        issueNote.setTotalCashAmount(request.getTotalCashAmount());
        issueNote.setCashPrice(request.getCashPrice());
        issueNote.setCreditPrice(request.getCreditPrice());
        issueNote.setStoresInhand(request.getStoresInhand());
        issueNote.setStoresNew(request.getStoresNew());
        issueNote.setTransferBatchNo(request.getTransferBatchNo());
        issueNote.setTrFromBranchId(request.getTrFromBranchId());
        issueNote.setTrToBranchId(request.getTrToBranchId());
        issueNote.setColor(new Color(request.getColorId()));
        issueNote.setSize(new Size(request.getSizeId()));
        issueNote.setFit(new Fit(request.getFitId()));
        IssueNote updated = issueNoteRepository.save(issueNote);

        saveLog("IssueNote", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public IssueNoteResponse getById(Long id) {

        return issueNoteRepository.findById(id).map(IssueNoteServiceImpl::convert).orElse(null);
    }

    @Override
    public List<IssueNoteResponse> getAll() {

        return issueNoteRepository.findAll()
                .stream().map(IssueNoteServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        IssueNote got = issueNoteRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        issueNoteRepository.save(got);

        saveLog("IssueNote", "Data Deleted - " + id);

        return 1;
    }



    private void saveLog(String form, String action) {
        UserLogger userLogger = new UserLogger();
        userLogger.setForm(form);
        userLogger.setAction(action);
        userLogger.setIsDeleted(Deleted.NO);
        userLoggerRepository.save(userLogger);
    }


}