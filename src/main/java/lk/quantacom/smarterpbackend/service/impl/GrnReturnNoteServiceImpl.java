package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteRequest;
import lk.quantacom.smarterpbackend.dto.request.GrnReturnNoteUpdateRequest;
import lk.quantacom.smarterpbackend.dto.response.GrnReturnNoteResponse;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.GrnReturnNoteService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.Settings;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GrnReturnNoteServiceImpl implements GrnReturnNoteService {

    @Autowired
    private GrnReturnNoteRepository grnReturnNoteRepository;

    @Autowired
    private UserLoggerRepository userLoggerRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private GrnDetailsRepository grnDetailsRepository;

    @Autowired
    private GrnPaymentsRepository grnPaymentsRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private LedgerHistoryRepository ledgerHistoryRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<GrnReturnNoteResponse> save(List<GrnReturnNoteRequest> requestList) {

        List<GrnReturnNoteResponse> responses = new ArrayList<>();
        Supplier supplier = null;

        Long ledgerDebitId=0L;
        Long ledgerCreditId=requestList.get(0).getLedgerCreditId();

        Long returnId=1L;
        Long max=grnReturnNoteRepository.getMax();
        if(max!=null){
            returnId=max+1;
        }

        for (GrnReturnNoteRequest request : requestList) {

            GrnReturnNote grnReturnNote = new GrnReturnNote();
            grnReturnNote.setReturnDate(request.getReturnDate() == null ? null : ConvertUtils.convertStrToDate(request.getReturnDate()));
            GrnHeader grn = new GrnHeader();
            grn.setId(request.getGrnId());
            grnReturnNote.setGrn(grn);
            grnReturnNote.setReturnId(returnId);
            grnReturnNote.setInHandQty(request.getInHandQty());
            grnReturnNote.setReturnQty(request.getReturnQty());
            grnReturnNote.setUnitPrice(request.getUnitPrice());
            grnReturnNote.setItemValue(request.getItemValue());
            grnReturnNote.setGrandTotal(request.getGrandTotal());
            grnReturnNote.setRemarks(request.getRemarks());
            grnReturnNote.setBatchNo(request.getBatchNo());
            ItemMaster item = new ItemMaster();
            item.setId(request.getItemId());
            grnReturnNote.setItem(item);
            BranchNetwork branch = new BranchNetwork();
            branch.setId(request.getBranchId());
            grnReturnNote.setBranch(branch);
            grnReturnNote.setIsDeleted(Deleted.NO);
            GrnReturnNote save = grnReturnNoteRepository.save(grnReturnNote);

            List<Stock> stockList = stockRepository.getByBatchNoAndItemAndBranch(request.getBatchNo(), item.getItemCode(), branch.getId());
            if(stockList!=null){
                for(Stock stock:stockList ){
                    stock.setAvailabQty(request.getNewStockQty());
                    stockRepository.save(stock);
                }
            }

            BinCard binCard = new BinCard();
            binCard.setItem(item);
            binCard.setBinCardDate(ConvertUtils.convertStrToDate(request.getReturnDate()));
            binCard.setDocType("GRN RETURN NOTE");
            binCard.setDocNo(returnId + "");
            binCard.setRecQty(0.0);
            binCard.setIsueQty(request.getReturnQty());
            binCard.setBalanceQty(request.getNewStockQty());
            binCard.setBatchNo(request.getBatchNo());
            binCard.setBranch(branch);
            binCard.setIsDeleted(Deleted.NO);
            binCardRepository.save(binCard);

            GrnDetails details = grnDetailsRepository.findByGrnAndItemAndBatchNo(grn, item, request.getBatchNo());

            supplier = supplierRepository.getById(details.getGrn().getSupplierId());

            double NEWINVOICE_QTY, GRN_UNITPRICE, ITEM_PRICE, ITEM_DISC_PRICE, DICS_PRESENTAGE, ITEM_TOTAL_VALUE, ITEM_VAT;

            NEWINVOICE_QTY = request.getNewStockQty();
            GRN_UNITPRICE = request.getUnitPrice();

            ITEM_PRICE = NEWINVOICE_QTY * GRN_UNITPRICE;

            DICS_PRESENTAGE = details.getItemDisc();
            ITEM_DISC_PRICE = (ITEM_PRICE / 100) * DICS_PRESENTAGE;

            ITEM_VAT = details.getItemVatPrice();
            ITEM_TOTAL_VALUE = (ITEM_PRICE - ITEM_DISC_PRICE) + ITEM_VAT;

            double Gross, Net, Due, NEWGross, NEWNet, NEWDue, NEW_grn_overpayment, tot_qty_vaue, grn_overpayment;

            tot_qty_vaue = request.getReturnQtyValue();

            GrnPayments grnPay = details.getGrn().getGrnPayments().get(0);

            Gross = grnPay.getGrossAmount();
            Net = grnPay.getNetAmount();
            Due = grnPay.getDueAmount();
            grn_overpayment = grnPay.getGrnOverpaid();

            //  NEWtot_qty = tot_qty - rtrn_qty;
            NEWGross = Gross - tot_qty_vaue;
            NEWNet = Net - tot_qty_vaue;

            if (Due == 0.0) {
                NEWDue = 0.00;
            } else {
                NEWDue = Due - tot_qty_vaue;
            }

            if (Due == 0.0 && grn_overpayment == 0.0) {
                NEW_grn_overpayment = grn_overpayment - tot_qty_vaue;
            } else {
                NEW_grn_overpayment = 0.00;
            }

            // update grn details
            details.setItemDicPrice(ITEM_DISC_PRICE);
            details.setItemPrice(ITEM_PRICE);
            details.setItemValue(ITEM_TOTAL_VALUE);
            details.setQty(request.getNewStockQty());
            details.setItemTotal(ITEM_TOTAL_VALUE);
            grnDetailsRepository.save(details);

            if (grnPay.getDueAmount() == 0.0 && grnPay.getGrnOverpaid() == 0.0) {
                grnPay.setGrnOverpaid(NEW_grn_overpayment);
                grnPaymentsRepository.save(grnPay);
            } else {
                grnPay.setGrossAmount(NEWGross);
                grnPay.setNetAmount(NEWNet);
                grnPay.setDueAmount(NEWDue);
                grnPaymentsRepository.save(grnPay);
            }

            double NewAvai_Ctedit = (request.getCreditLimitAvlbl() + NEWNet);
            supplier.setAvCreditLimit(NewAvai_Ctedit);
            supplierRepository.save(supplier);

            ledgerDebitId=supplier.getSupplierLedgerId();

            responses.add(convert(save));
        }

        // DEBIT ACCOUNT SAVING TO LEDGER HISTORY
//        LedgerHistory ledgerHistoryDebit=new LedgerHistory();
//        ledgerHistoryDebit.setLadgerAccountId(ledgerDebitId);
//        ledgerHistoryDebit.setUpdateFram("GRN RETURN NOTE");
//        ledgerHistoryDebit.setUpdateFramDocNo(returnId+"");
//        ledgerHistoryDebit.setRemark(" GRN Item return on " + returnId);
//        ledgerHistoryDebit.setUpdateBalance(requestList.get(0).getGrandTotal());
//        ledgerHistoryDebit.setCreditColumnName(requestList.get(0).getCreditAccName());
//        ledgerHistoryDebit.setDebitColumnName("-");
//        ledgerHistoryDebit.setBranchId(requestList.get(0).getBranchId());
//        ledgerHistoryRepository.save(ledgerHistoryDebit);
//
//        // CREDIT ACCOUNT SAVING TO LEDGER HISTORY
//        LedgerHistory ledgerHistoryCredit=new LedgerHistory();
//        ledgerHistoryCredit.setLadgerAccountId(ledgerDebitId);
//        ledgerHistoryCredit.setUpdateFram("GRN RETURN NOTE");
//        ledgerHistoryCredit.setUpdateFramDocNo(returnId+"");
//        ledgerHistoryCredit.setRemark(" GRN Item return on " + returnId);
//        ledgerHistoryCredit.setUpdateBalance(requestList.get(0).getGrandTotal());
//        ledgerHistoryCredit.setCreditColumnName("-");
//        ledgerHistoryCredit.setDebitColumnName(requestList.get(0).getDebitAccName());
//        ledgerHistoryCredit.setBranchId(requestList.get(0).getBranchId());
//        ledgerHistoryCredit.setCreditAmount();
//        ledgerHistoryRepository.save(ledgerHistoryCredit);

        saveLog("GrnReturnNote", "Data Saved - "+returnId);

        return responses;
    }

    @Override
    @Transactional
    public GrnReturnNoteResponse update(GrnReturnNoteUpdateRequest request) {

        GrnReturnNote grnReturnNote = grnReturnNoteRepository.findById(request.getId()).orElse(null);
        if (grnReturnNote == null) {
            return null;
        }

        grnReturnNote.setId(request.getId());
        grnReturnNote.setReturnDate(request.getReturnDate() == null ? null : ConvertUtils.convertStrToDate(request.getReturnDate()));
        GrnHeader grn = new GrnHeader();
        grn.setId(request.getGrnId());
        grnReturnNote.setGrn(grn);
        grnReturnNote.setInHandQty(request.getInHandQty());
        grnReturnNote.setReturnQty(request.getReturnQty());
        grnReturnNote.setUnitPrice(request.getUnitPrice());
        grnReturnNote.setItemValue(request.getItemValue());
        grnReturnNote.setGrandTotal(request.getGrandTotal());
        grnReturnNote.setRemarks(request.getRemarks());
        grnReturnNote.setBatchNo(request.getBatchNo());
        ItemMaster item = new ItemMaster();
        item.setId(request.getItemId());
        grnReturnNote.setItem(item);
        BranchNetwork branch = new BranchNetwork();
        branch.setId(request.getBranchId());
        grnReturnNote.setBranch(branch);
        GrnReturnNote updated = grnReturnNoteRepository.save(grnReturnNote);

        saveLog("GrnReturnNote", "Data Updated - " + updated.getId());

        return (convert(updated));
    }

    @Override
    public GrnReturnNoteResponse getById(Long id) {

        return grnReturnNoteRepository.findById(id).map(GrnReturnNoteServiceImpl::convert).orElse(null);
    }

    @Override
    public List<GrnReturnNoteResponse> getAll() {

        return grnReturnNoteRepository.findByIsDeleted(Deleted.NO)
                .stream().map(GrnReturnNoteServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public File print(Long returnId) {

        File pdf=null;
        Connection co=null;
        try {
            co= JDBC.con();

            String report = Settings.readSettings("REPORT_PATH") + "Reports/Inventory/GRN_Return_Note.jrxml";
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("COMPANY_NAME", Settings.readSettings("COMPANY_NAME"));
            params.put("COMPANY_ADDRESS_ONE", Settings.readSettings("COMPANY_ADDRESS_ONE"));
            params.put("COMPANY_ADDRESS_TWO", Settings.readSettings("COMPANY_ADDRESS_TWO"));
            params.put("MOBILE_NO", Settings.readSettings("MOBILE_NO"));
            params.put("EMAIL", Settings.readSettings("EMAIL"));
            params.put("LOGO_PATH", Settings.readSettings("LOGO_PATH"));
            params.put("ReturnId", returnId+"");

            JasperReport jasprereport = JasperCompileManager.compileReport(report);
            JasperPrint jasperprint = JasperFillManager.fillReport(jasprereport, params, co);
            File perentFol = new File(System.getProperty("user.home") + "/smart_erp_reports");
            if (!perentFol.exists()) {
                perentFol.mkdirs();
            }
            pdf = new File(perentFol, System.currentTimeMillis() + ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperprint, new FileOutputStream(pdf));


        } catch (Exception e) {
          e.printStackTrace();
        }finally {
            try {
                co.close();
            }catch (Exception ex){}
        }

        return pdf;
    }


    @Override
    @Transactional
    public Integer delete(Long id) {

        GrnReturnNote got = grnReturnNoteRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        grnReturnNoteRepository.save(got);

        saveLog("GrnReturnNote", "Data Deleted - " + id);

        return 1;
    }

    private static GrnReturnNoteResponse convert(GrnReturnNote grnReturnNote) {

        GrnReturnNoteResponse typeResponse = new GrnReturnNoteResponse();
        typeResponse.setReturnDate(ConvertUtils.convertDateToStr(grnReturnNote.getReturnDate()));
        typeResponse.setGrnId(grnReturnNote.getGrn().getId());
        typeResponse.setInHandQty(grnReturnNote.getInHandQty());
        typeResponse.setReturnQty(grnReturnNote.getReturnQty());
        typeResponse.setUnitPrice(grnReturnNote.getUnitPrice());
        typeResponse.setItemValue(grnReturnNote.getItemValue());
        typeResponse.setGrandTotal(grnReturnNote.getGrandTotal());
        typeResponse.setRemarks(grnReturnNote.getRemarks());
        typeResponse.setBatchNo(grnReturnNote.getBatchNo());
        typeResponse.setItemId(grnReturnNote.getItem().getId());
        typeResponse.setBranchId(grnReturnNote.getBranch().getId());
        typeResponse.setId(grnReturnNote.getId());
        typeResponse.setCreatedBy(grnReturnNote.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(grnReturnNote.getCreatedDateTime()));
        typeResponse.setModifiedBy(grnReturnNote.getModifiedBy());
        typeResponse.setIsDeleted(grnReturnNote.getIsDeleted());
        typeResponse.setReturnId(grnReturnNote.getReturnId());

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