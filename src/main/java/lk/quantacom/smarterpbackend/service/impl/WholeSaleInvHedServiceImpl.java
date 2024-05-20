package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.*;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.*;
import lk.quantacom.smarterpbackend.service.UserHeadService;
import lk.quantacom.smarterpbackend.service.WholeSaleInvHedService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import lk.quantacom.smarterpbackend.utils.JDBC1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WholeSaleInvHedServiceImpl implements WholeSaleInvHedService {

    @Autowired
    private WholeSaleInvHedRepository wholeSaleInvHedRepository;

    @Autowired
    private WholeSaleInvDtlRepository wholeSaleInvDtlRepository;

    @Autowired
    private TblinvhedRepository tblinvhedRepository;

    @Autowired
    private TblinvdtlRepository tblinvdtlRepository;

    @Autowired
    private TblpaydtlRepository tblpaydtlRepository;

    @Autowired
    private UserHeadRepository userHeadRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BinCardRepository binCardRepository;

    @Autowired
    private ItemMasterRepository itemMasterRepository;

    @Autowired
    private BranchNetworkRepository branchNetworkRepository;

    @Autowired
    private TaxRepository taxRepository;

    private static WholeSaleInvHedResponse convert(WholeSaleInvHed wholeSaleInvHed) {

        WholeSaleInvHedResponse typeResponse = new WholeSaleInvHedResponse();
        typeResponse.setInvno(wholeSaleInvHed.getInvno());
        typeResponse.setInvDate(wholeSaleInvHed.getInvDate());
        typeResponse.setLocation(wholeSaleInvHed.getLocation());
        typeResponse.setSalesBy(wholeSaleInvHed.getSalesBy());
        typeResponse.setCustomer(wholeSaleInvHed.getCustomer());
        typeResponse.setTotalQty(wholeSaleInvHed.getTotalQty());
        typeResponse.setNumOfItem(wholeSaleInvHed.getNumOfItem());
        typeResponse.setGrossAmount(wholeSaleInvHed.getGrossAmount());
        typeResponse.setBillDisPrecentage(wholeSaleInvHed.getBillDisPrecentage());
        typeResponse.setBillDisAmount(wholeSaleInvHed.getBillDisAmount());
        typeResponse.setNetAmount(wholeSaleInvHed.getNetAmount());
        typeResponse.setPayType(wholeSaleInvHed.getPayType());
        typeResponse.setIsCancel(wholeSaleInvHed.getIsCancel());
        typeResponse.setPayTypeInfo(wholeSaleInvHed.getPayTypeInfo());
        typeResponse.setCreatedBy(wholeSaleInvHed.getCreatedBy());
        typeResponse.setCreatedDateTime(ConvertUtils.convertDateToStr(wholeSaleInvHed.getCreatedDateTime()));
        typeResponse.setModifiedBy(wholeSaleInvHed.getModifiedBy());
        typeResponse.setModifiedDateTime(ConvertUtils.convertDateToStr(wholeSaleInvHed.getModifiedDateTime()));
        typeResponse.setSalesById(wholeSaleInvHed.getSalesById());
        typeResponse.setLocationId(wholeSaleInvHed.getLocationId());
        typeResponse.setIsDeleted(wholeSaleInvHed.getIsDeleted());

        return typeResponse;
    }

    @Override
    @Transactional
    public WholeSaleInvHedResponse save(WholeSaleInvHedRequest request) {

        WholeSaleInvHed wholeSaleInvHed = new WholeSaleInvHed();
        wholeSaleInvHed.setInvno(request.getInvno());
        wholeSaleInvHed.setInvDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        wholeSaleInvHed.setLocation(request.getLocation());
        wholeSaleInvHed.setSalesBy(request.getSalesBy());
        wholeSaleInvHed.setCustomer(request.getCustomer());
        wholeSaleInvHed.setTotalQty(request.getTotalQty());
        wholeSaleInvHed.setNumOfItem(request.getNumOfItem());
        wholeSaleInvHed.setGrossAmount(request.getGrossAmount());
        wholeSaleInvHed.setBillDisPrecentage(request.getBillDisPrecentage());
        wholeSaleInvHed.setBillDisAmount(request.getBillDisAmount());
        wholeSaleInvHed.setNetAmount(request.getNetAmount());
        wholeSaleInvHed.setPayType(request.getPayType());
        wholeSaleInvHed.setIsCancel(request.getIsCancel());
        wholeSaleInvHed.setPayTypeInfo(request.getPayTypeInfo());
        wholeSaleInvHed.setSalesById(request.getSalesById());
        wholeSaleInvHed.setLocationId(request.getLocationId());
        wholeSaleInvHed.setIsDeleted(Deleted.NO);
        WholeSaleInvHed save = wholeSaleInvHedRepository.save(wholeSaleInvHed);

        return convert(save);
    }

    @Override
    @Transactional
    public WholeSaleInvHedResponse update(WholeSaleInvHedUpdateRequest request) {

        WholeSaleInvHed wholeSaleInvHed = wholeSaleInvHedRepository.findById(request.getInvno()).orElse(null);
        if (wholeSaleInvHed == null) {
            return null;
        }

        wholeSaleInvHed.setInvno(request.getInvno());
        wholeSaleInvHed.setInvDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        wholeSaleInvHed.setLocation(request.getLocation());
        wholeSaleInvHed.setSalesBy(request.getSalesBy());
        wholeSaleInvHed.setCustomer(request.getCustomer());
        wholeSaleInvHed.setTotalQty(request.getTotalQty());
        wholeSaleInvHed.setNumOfItem(request.getNumOfItem());
        wholeSaleInvHed.setGrossAmount(request.getGrossAmount());
        wholeSaleInvHed.setBillDisPrecentage(request.getBillDisPrecentage());
        wholeSaleInvHed.setBillDisAmount(request.getBillDisAmount());
        wholeSaleInvHed.setNetAmount(request.getNetAmount());
        wholeSaleInvHed.setPayType(request.getPayType());
        wholeSaleInvHed.setIsCancel(request.getIsCancel());
        wholeSaleInvHed.setPayTypeInfo(request.getPayTypeInfo());
        wholeSaleInvHed.setSalesById(request.getSalesById());
        wholeSaleInvHed.setLocationId(request.getLocationId());
        WholeSaleInvHed updated = wholeSaleInvHedRepository.save(wholeSaleInvHed);

        return (convert(updated));
    }

    @Override
    public WholeSaleInvHedResponse getById(Integer id) {

        return wholeSaleInvHedRepository.findById(id).map(WholeSaleInvHedServiceImpl::convert).orElse(null);
    }

    @Override
    public List<WholeSaleInvHedResponse> getAll() {

        return wholeSaleInvHedRepository.findByIsDeletedOrderByInvnoDesc(Deleted.NO)
                .stream().map(WholeSaleInvHedServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Integer id) {

        WholeSaleInvHed got = wholeSaleInvHedRepository.findById(id).orElse(null);
        if (got == null) {
            return 0;
        }
        got.setIsDeleted(Deleted.YES);
        wholeSaleInvHedRepository.save(got);

        return 1;
    }

    @Override
    @Transactional
    public WholeSaleInvHedResponse saveInvoice(WholeSaleInvRequest request) {

        Integer invNo = getWhInvNo();

        WholeSaleInvHed wholeSaleInvHed = new WholeSaleInvHed();
        wholeSaleInvHed.setInvno(invNo);
        wholeSaleInvHed.setInvDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        wholeSaleInvHed.setLocation(request.getLocation());
        wholeSaleInvHed.setSalesBy(request.getSalesBy());
        wholeSaleInvHed.setCustomer(request.getCustomer());
        wholeSaleInvHed.setTotalQty(request.getTotalQty());
        wholeSaleInvHed.setNumOfItem(request.getNumOfItem());
        wholeSaleInvHed.setGrossAmount(request.getGrossAmount());
        wholeSaleInvHed.setBillDisPrecentage(request.getBillDisPrecentage());
        wholeSaleInvHed.setBillDisAmount(request.getBillDisAmount());
        wholeSaleInvHed.setNetAmount(request.getNetAmount());
        wholeSaleInvHed.setPayType(request.getPayType());
        wholeSaleInvHed.setIsCancel(request.getIsCancel());
        wholeSaleInvHed.setPayTypeInfo(request.getPayTypeInfo());
        wholeSaleInvHed.setIsDeleted(Deleted.NO);
        wholeSaleInvHed.setCreditCardNumber(request.getCreditCardNumber());
        wholeSaleInvHed.setCustomerId(request.getCustomerId());
        wholeSaleInvHed.setSalesById(request.getSalesById());
        wholeSaleInvHed.setLocationId(request.getLocationId());
        System.out.println("dsafdsf"+request.getRemarks());
        wholeSaleInvHed.setRemarks(request.getRemarks());
        WholeSaleInvHed save = wholeSaleInvHedRepository.save(wholeSaleInvHed);

        Tblinvhed tblinvhed = new Tblinvhed();
        tblinvhed.setRemarks(request.getRemarks());
        tblinvhed.setBatchid("INV_W");
        tblinvhed.setBlnconfirmed(false);
        tblinvhed.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldAccessupdate(0.00);
        tblinvhed.setFldBadDeptAmount(0.00);
        tblinvhed.setFldBadDeptDate(null);
        tblinvhed.setFldBadDeptTime(null);
        tblinvhed.setFldCancel(request.getIsCancel());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uu = auth.getName();
        tblinvhed.setFldCashierid(uu);
        tblinvhed.setFldChange(0.00);
        tblinvhed.setFldChangebynexuspoints(0.00);

        if (request.getPayTypeInfo().equals("CASH")) {
            tblinvhed.setFldCash(0.00);
            tblinvhed.setFldCashsale(request.getNetAmount());
        } else if (request.getPayTypeInfo().equals("CREDIT")) {
            tblinvhed.setFldCreditcust(request.getNetAmount());
        }

        tblinvhed.setFldCheque(0.00);
        tblinvhed.setFldChequeno("");
        tblinvhed.setFldCreditcard(0.00);
        tblinvhed.setFldClosesales("");
        tblinvhed.setFldCoupon(0.00);
        tblinvhed.setFldCpacker("M");
        tblinvhed.setFldDatatransfer(false);
        tblinvhed.setFldDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldDiscount(request.getBillDisAmount());
        tblinvhed.setFldForigncustomer(false);
        tblinvhed.setFldFrcurency(0.00);
        tblinvhed.setFldFrcurencysale(0.00);
        tblinvhed.setFldGiftvoucher(0.00);
        tblinvhed.setFldGrossamount(request.getGrossAmount());
        tblinvhed.setFldGvsaleinv(false);
        tblinvhed.setFldHappyStatus(false);
        tblinvhed.setFldHappyStatus2(0);
        tblinvhed.setFldInvno(invNo.toString());
        tblinvhed.setFldItemwisedis(request.getBillDisAmount() / request.getTotalQty());
        tblinvhed.setFldLocalcustomer(request.getCustomerId().doubleValue());
        tblinvhed.setFldLocation(request.getLocationId().toString());
        tblinvhed.setFldMember("1");
        tblinvhed.setFldMiddlewarestatus(0);
        tblinvhed.setFldMiddlewarestatusBw(0);
        tblinvhed.setFldMiddlewareuuid("");
        tblinvhed.setFldMiddlewareuuidBw("");
        tblinvhed.setFldNetamount(request.getNetAmount());
        tblinvhed.setFldNetamountwithouttax(request.getGrossAmount());
        System.out.println("fld net amount =" + request.getGrossAmount().floatValue());
        tblinvhed.setFldOther(0.00);
        tblinvhed.setFldPromodisc(0.00);
        tblinvhed.setFldSapUpdated(false);
        tblinvhed.setFldScratchCards(0.00);
        tblinvhed.setFldShiftno(true);
        tblinvhed.setFldStarttime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldStationid(request.getLocationId().toString());
        tblinvhed.setFldTime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldTmpcashid("");
        tblinvhed.setFldVegaactive(false);
        tblinvhed.setFldWebd(0.00);
        tblinvhed.setNexusmobile("");
        tblinvhed.setSyncstatus(false);
        tblinvhed.setFldTrxtype(request.getTaxName());
        if (request.getTaxName().equals("TAX")){
            tblinvhed.setInvoiceNumber(("TX"+invNo).trim());
        }else {
            tblinvhed.setInvoiceNumber(invNo.toString());
        }
        tblinvhed.setFldTaxamount(request.getTaxRate());
        Tax tax = taxRepository.findByTaxNameAndIsDeleted(request.getTaxName(),Deleted.NO);
        if (tax!=null){
            tblinvhed.setFldTaxPer(tax.getTaxRate());
        }else {
            tblinvhed.setFldTaxPer(0.00);
        }

        tblinvhedRepository.save(tblinvhed);

        int lineNo = 1;

        for (WholeSaleInvDtlRequest dtlRequest : request.getInvDtlRequests()) {

            if (dtlRequest.getSizeQty() != 0) {

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(dtlRequest.getBatchNo(), dtlRequest.getItemCode(),
                        request.getLocationId().longValue(), dtlRequest.getColor(), dtlRequest.getSizeId().longValue(), dtlRequest.getFit());

                if (stock != null) {
                    stock.setStoresQty(stock.getStoresQty() - dtlRequest.getSizeQty());
                    stock.setTotalQty(stock.getTotalQty() - dtlRequest.getSizeQty());
                    stockRepository.save(stock);
                }

                WholeSaleInvDtl wholeSaleInvDtl = new WholeSaleInvDtl();
                wholeSaleInvDtl.setItemCode(dtlRequest.getItemCode());
                wholeSaleInvDtl.setInvno(invNo);
                wholeSaleInvDtl.setItemName(dtlRequest.getItemName());
                wholeSaleInvDtl.setSizeId(dtlRequest.getSizeId());
                wholeSaleInvDtl.setSizeName(dtlRequest.getSizeName());
                wholeSaleInvDtl.setQtyByitem(dtlRequest.getQtyByitem());
                wholeSaleInvDtl.setSizeQty(dtlRequest.getSizeQty());
                wholeSaleInvDtl.setMrp(dtlRequest.getMrp());
                wholeSaleInvDtl.setDisPrecentage(dtlRequest.getDisPrecentage());
                wholeSaleInvDtl.setDisAmount(dtlRequest.getDisAmount());
                wholeSaleInvDtl.setAmount(dtlRequest.getAmount());
                wholeSaleInvDtl.setIsDeleted(Deleted.NO);
                wholeSaleInvDtl.setColor(dtlRequest.getColor());
                wholeSaleInvDtl.setFit(dtlRequest.getFit());
                wholeSaleInvDtl.setBatchNo(dtlRequest.getBatchNo());
                wholeSaleInvDtl.setItemCost(dtlRequest.getItemCost());
                wholeSaleInvDtlRepository.save(wholeSaleInvDtl);

                Tblinvdtl tblinvdtl = new Tblinvdtl();
                tblinvdtl.setBlnconfirmed(false);
                tblinvdtl.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldAccessupdate(0.00);

//                tblinvdtl.setFldAmount((dtlRequest.getMrp() * dtlRequest.getQtyByitem()) - dtlRequest.getAmount());
                tblinvdtl.setFldAmount(dtlRequest.getAmount());

                tblinvdtl.setFldAvgcost(dtlRequest.getItemCost());
                tblinvdtl.setFldBarcodereadstatus("");
                tblinvdtl.setFldBarcodeused("");
                tblinvdtl.setFldBilldisper(request.getBillDisPrecentage());
                tblinvdtl.setFldBillpromodisper(0.00);
                tblinvdtl.setFldCancel(false);
                tblinvdtl.setFldCashierid(uu);
                tblinvdtl.setFldClosesales("f");
                assert stock != null;
                tblinvdtl.setFldCostprice(stock.getStkCashPrice()==null?0.00:stock.getStkCashPrice());
                tblinvdtl.setFldDatatransfer(false);
                tblinvdtl.setFldDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldDoublerefoldinvno("");
                tblinvdtl.setFldDoublerefpromoamt(0.00);
                tblinvdtl.setFldExtranumericfield1(0.00);
                tblinvdtl.setFldExtranumericfield2(0.00);
                tblinvdtl.setFldExtranumericfield3(0.00);
                tblinvdtl.setFldExtranumericfield4(0.00);
                tblinvdtl.setFldExtranumericfield5(0.00);
                tblinvdtl.setFldExtratextfield1("");
                tblinvdtl.setFldExtratextfield2("");
                tblinvdtl.setFldExtratextfield3("");
                tblinvdtl.setFldExtratextfield4("");
                tblinvdtl.setFldExtratextfield5("");
                tblinvdtl.setFldExtratextfield6("");
                tblinvdtl.setFldExtratextfield7("");
                tblinvdtl.setFldExtratextfield8("");
                tblinvdtl.setFldExtratextfield9("");
                tblinvdtl.setFldIntcolorid(dtlRequest.getColor().intValue());
                tblinvdtl.setFldIntfitid(dtlRequest.getFit().intValue());
                tblinvdtl.setFldIntsizeid(dtlRequest.getSizeId());
                tblinvdtl.setFldInvno(invNo.toString());
                tblinvdtl.setFldItemcode(dtlRequest.getItemCode());
                tblinvdtl.setFldItemdescription(dtlRequest.getItemName());

                System.out.println("test "+dtlRequest.getSizeQty());
                System.out.println("test "+dtlRequest.getMrp());
                System.out.println("test "+tblinvdtl.getFldLinedisper());
//                tblinvdtl.setFldLinedisamt(dtlRequest.getAmount());
//                tblinvdtl.setFldLinedisamt(dtlRequest.getDisAmount()==null?0.00:dtlRequest.getDisAmount());
                tblinvdtl.setFldLinedisamt(dtlRequest.getSizeQty()*(dtlRequest.getMrp()*(request.getBillDisPrecentage()/100)));

                if (request.getTaxName().equals("TAX")){
                    tblinvdtl.setInvoiceNumber(("TX"+invNo).trim());
                }else {
                    tblinvdtl.setInvoiceNumber(invNo.toString());
                }

                tblinvdtl.setFldLinediscountauthorizedby("");
                tblinvdtl.setFldLinediscountreasoncode(0);
                tblinvdtl.setFldLinedisper(dtlRequest.getDisPrecentage()==null?0.00: dtlRequest.getDisPrecentage());
                tblinvdtl.setFldLinedisval(dtlRequest.getDisAmount());
                tblinvdtl.setFldLineno(lineNo);
                tblinvdtl.setFldLocation(request.getLocationId().toString());
                tblinvdtl.setFldMiddlewarestatus(0);
                tblinvdtl.setFldMiddlewarestatusBw(0);
                tblinvdtl.setFldMiddlewareuuid("");
                tblinvdtl.setFldMiddlewareuuidBw("");
                tblinvdtl.setFldOrgSelling(dtlRequest.getMrp());
                tblinvdtl.setFldOudrivenpromoamt(0.00);
                tblinvdtl.setFldPrice(dtlRequest.getMrp());
                tblinvdtl.setFldPromocode("");
                tblinvdtl.setFldPromodisamt(0.00);
                tblinvdtl.setFldPromodisper(0.00);
                tblinvdtl.setFldPromodisval(0.00);
                tblinvdtl.setFldPromomode("");
                tblinvdtl.setFldPromotype("");
                tblinvdtl.setFldQty(dtlRequest.getSizeQty());
                tblinvdtl.setFldRefundauthorizedby("");
                tblinvdtl.setFldRefundreasoncode(0);
                tblinvdtl.setFldSapUpdated(false);
                tblinvdtl.setFldSapVendor("");
                tblinvdtl.setFldScanmode("");
                tblinvdtl.setFldSplittedbilldisamt((request.getBillDisAmount() / request.getTotalQty()) * tblinvdtl.getFldQty());
                tblinvdtl.setFldStockcode(dtlRequest.getBatchNo());

                if (request.getTaxRate()==0.00){
                    tblinvdtl.setFldTaxamount((request.getTaxRate()/request.getTotalQty())*(dtlRequest.getSizeQty()));
                }else {
                    tblinvdtl.setFldTaxamount(0.00);
                }

                tblinvdtl.setFldTaxclass("");
                tblinvdtl.setFldTime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldTrxtype("");
                tblinvdtl.setFldVoid(false);
                tblinvdtl.setFldTrxtype(request.getTaxName());
                tblinvdtlRepository.save(tblinvdtl);

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(dtlRequest.getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("WHOLE SALE");
                binCard.setDocNo(invNo.toString());
                binCard.setRecQty(0.00);
                binCard.setIsueQty(dtlRequest.getSizeQty());
                binCard.setBalanceQty(stock.getStoresQty());
                binCard.setBatchNo(dtlRequest.getBatchNo());
                BranchNetwork branchNetwork = branchNetworkRepository.getById(request.getLocationId().longValue());
                binCard.setBranch(branchNetwork);
                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(dtlRequest.getFit());
                binCard.setColor(dtlRequest.getColor());
                binCard.setSize(dtlRequest.getSizeId().longValue());
                binCardRepository.save(binCard);

                lineNo++;
            }

        }

        Tblpaydtl tblpaydtl = new Tblpaydtl();

        TblpaydtlPK tblpaydtlPK = new TblpaydtlPK(ConvertUtils.convertStrToDate(request.getInvDate()), request.getLocationId().toString(), invNo.toString());
        tblpaydtl.setTblpaydtlPK(tblpaydtlPK);
        tblpaydtl.setFldPaymode(request.getPayType().toString());
        tblpaydtl.setFldPaytype(request.getPayTypeInfo());
        tblpaydtl.setFldPaytypecode(request.getCustomerId().toString());
        tblpaydtl.setFldCrdcardno(request.getCreditCardNumber());
        tblpaydtl.setFldFcuramt(0.00);
        tblpaydtl.setFldPaytypeamt(request.getNetAmount());
        tblpaydtl.setFldExchngrate(0.00);
        tblpaydtl.setFldDatatransfer(false);
        tblpaydtl.setFldGvdisno("");
        tblpaydtl.setFldCreditno("");
        tblpaydtl.setFldComno("");
        tblpaydtl.setFldCashierid(uu);
        tblpaydtl.setFldEncrkey("");
        tblpaydtl.setBlnconfirmed(false);
        tblpaydtl.setBlnmodechange(false);

        if (request.getTaxName().equals("TAX")){
            tblpaydtl.setInvoiceNumber(("TX"+invNo).trim());
        }else {
            tblpaydtl.setInvoiceNumber(invNo.toString());
        }

        tblpaydtl.setFldCancel(false);
        tblpaydtl.setFldAccessupdate(0.00);
        tblpaydtl.setFldMiddlewareuuid("");
        tblpaydtl.setFldMiddlewarestatus(0);
        tblpaydtl.setFldMiddlewareuuidCreditcust("");
        tblpaydtl.setFldMiddlewarestatusCreditcust(0);
        tblpaydtl.setFldMiddlewareuuidBw("");
        tblpaydtl.setFldMiddlewarestatusBw(0);
        tblpaydtl.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblpaydtlRepository.save(tblpaydtl);

        return convert(save);

    }

    @Override
    public Integer getWhInvNo() {

        String sql = " select max(fld_InvNo) as fld_InvNo from tblinvhed ";
        String invnum = "";
        Integer inv = null;

        Connection co;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                invnum = rs.getString("fld_InvNo");
            }
            inv = Integer.parseInt(invnum) + 1;
            co.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inv;
    }

    @Override
    public List<getWholeSaleInvDtlResponse> getWholeSaleInvDtl() {
        List<getWholeSaleInvDtlResponse> list = wholeSaleInvHedRepository.getWholeSaleInvDtl();
        return list;
    }

    @Override
    @Transactional
    public Integer updateCancel(String invNo) {

        Tblinvhed tblinvhed = tblinvhedRepository.findByFldInvno(invNo);

        if (tblinvhed != null) {
            tblinvhed.setFldCancel(true);
            tblinvhedRepository.save(tblinvhed);
            tblpaydtlRepository.updateFldCancel(invNo);

            List<Tblinvdtl> list = tblinvdtlRepository.findByFldInvno(invNo);
            if (list != null) {
                for (Tblinvdtl tblinvdtl : list) {
                    Tblinvdtl tblinvdtl1 = tblinvdtlRepository.findByFldInvnoAndFldLineno(invNo, tblinvdtl.getFldLineno());
                    tblinvdtl1.setFldCancel(true);
                    tblinvdtlRepository.save(tblinvdtl1);
                }
            }
        }

        List<getTblInvDtlResponse> list = wholeSaleInvHedRepository.getTblInvDtl(invNo);
        if (list != null) {
            for (getTblInvDtlResponse response : list) {

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(response.getFLD_STOCKCODE(), response.getFLD_ITEMCODE(),
                        Long.parseLong(response.getFLD_LOCATION()), response.getFLD_INTCOLORID().longValue(), response.getFLD_INTSIZEID().longValue(), response.getFLD_INTFITID().longValue());

                if (stock != null) {
                    stock.setStoresQty(stock.getStoresQty() + response.getFLD_QTY());
                    stock.setTotalQty(stock.getTotalQty() + response.getFLD_QTY());
                    stockRepository.save(stock);

                    BinCard binCard = new BinCard();
                    ItemMaster items = itemMasterRepository.getById(response.getFLD_ITEMCODE());
                    binCard.setItem(items);
                    binCard.setBinCardDate(new Date());
                    binCard.setDocType("WSI CANCEL");
                    binCard.setDocNo(invNo);
                    binCard.setRecQty(response.getFLD_QTY());
                    binCard.setIsueQty(0.00);
                    binCard.setBalanceQty(stock.getStoresQty());
                    binCard.setBatchNo(response.getFLD_STOCKCODE());
                    BranchNetwork branchNetwork = branchNetworkRepository.getById(Long.parseLong(response.getFLD_LOCATION()));
                    binCard.setBranch(branchNetwork);
                    binCard.setIsDeleted(Deleted.NO);
                    binCard.setFit(response.getFLD_INTFITID().longValue());
                    binCard.setColor(response.getFLD_INTCOLORID().longValue());
                    binCard.setSize(response.getFLD_INTSIZEID().longValue());
                    binCardRepository.save(binCard);

                }
            }
        }

        return 1;
    }

    @Override
    @Transactional
    public WholeSaleInvHedResponse returnWSInvoice(WholeSaleInvRequest request) {

        Integer invNo = getWhInvNo();

        WholeSaleInvHed wholeSaleInvHed = new WholeSaleInvHed();
        wholeSaleInvHed.setInvno(invNo);
        wholeSaleInvHed.setInvDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        wholeSaleInvHed.setLocation(request.getLocation());
        wholeSaleInvHed.setSalesBy(request.getSalesBy());
        wholeSaleInvHed.setCustomer(request.getCustomer());
        wholeSaleInvHed.setTotalQty(-request.getTotalQty());
        wholeSaleInvHed.setNumOfItem(-request.getNumOfItem());
        wholeSaleInvHed.setGrossAmount(-request.getGrossAmount());
        wholeSaleInvHed.setBillDisPrecentage(request.getBillDisPrecentage());
        wholeSaleInvHed.setBillDisAmount(-request.getBillDisAmount());
        wholeSaleInvHed.setNetAmount(-request.getNetAmount());
        wholeSaleInvHed.setPayType(0);
        wholeSaleInvHed.setIsCancel(request.getIsCancel());
        wholeSaleInvHed.setPayTypeInfo("RETURN");
        wholeSaleInvHed.setIsDeleted(Deleted.NO);
        wholeSaleInvHed.setCreditCardNumber(request.getCreditCardNumber());
        wholeSaleInvHed.setCustomerId(request.getCustomerId());
        wholeSaleInvHed.setSalesById(request.getSalesById());
        wholeSaleInvHed.setLocationId(request.getLocationId());
        WholeSaleInvHed save = wholeSaleInvHedRepository.save(wholeSaleInvHed);

        Tblinvhed tblinvhed = new Tblinvhed();
        tblinvhed.setBatchid("INV_W_RTN");
        tblinvhed.setBlnconfirmed(false);
        tblinvhed.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldAccessupdate(0.00);
        tblinvhed.setFldBadDeptAmount(0.00);
        tblinvhed.setFldBadDeptDate(null);
        tblinvhed.setFldBadDeptTime(null);
        tblinvhed.setFldCancel(request.getIsCancel());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uu = auth.getName();
        tblinvhed.setFldCashierid(uu);
        tblinvhed.setFldChange(0.00);
        tblinvhed.setFldChangebynexuspoints(0.00);

//        if (request.getPayTypeInfo().equals("CASH")) {
//            tblinvhed.setFldCash(0.00);
//            tblinvhed.setFldCashsale(-request.getNetAmount());
//        } else if (request.getPayTypeInfo().equals("CREDIT")) {
//            tblinvhed.setFldCreditcust(-request.getNetAmount());
//        }

        tblinvhed.setFldCheque(0.00);
        tblinvhed.setFldChequeno("");
        tblinvhed.setFldCreditcard(0.00);
        tblinvhed.setFldClosesales("");
        tblinvhed.setFldCoupon(0.00);
        tblinvhed.setFldCpacker("M");
        tblinvhed.setFldDatatransfer(false);
        tblinvhed.setFldDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldDiscount(-request.getBillDisAmount());
        tblinvhed.setFldForigncustomer(false);
        tblinvhed.setFldFrcurency(0.00);
        tblinvhed.setFldFrcurencysale(0.00);
        tblinvhed.setFldGiftvoucher(0.00);
        tblinvhed.setFldGrossamount(-request.getGrossAmount());
        tblinvhed.setFldGvsaleinv(false);
        tblinvhed.setFldHappyStatus(false);
        tblinvhed.setFldHappyStatus2(0);
        tblinvhed.setFldInvno(invNo.toString());
        tblinvhed.setFldItemwisedis(request.getBillDisAmount() / -request.getTotalQty());
        tblinvhed.setFldLocalcustomer(request.getCustomerId().doubleValue());
        tblinvhed.setFldLocation(request.getLocationId().toString());
        tblinvhed.setFldMember("1");
        tblinvhed.setFldMiddlewarestatus(0);
        tblinvhed.setFldMiddlewarestatusBw(0);
        tblinvhed.setFldMiddlewareuuid("");
        tblinvhed.setFldMiddlewareuuidBw("");
        tblinvhed.setFldNetamount(-request.getNetAmount());
        tblinvhed.setFldNetamountwithouttax(-request.getGrossAmount());
        System.out.println("fld net amount =" + -request.getGrossAmount().floatValue());
        tblinvhed.setFldOther(0.00);
        tblinvhed.setFldPromodisc(0.00);
        tblinvhed.setFldSapUpdated(false);
        tblinvhed.setFldScratchCards(0.00);
        tblinvhed.setFldShiftno(true);
        tblinvhed.setFldStarttime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldStationid(request.getLocationId().toString());
        tblinvhed.setFldTime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblinvhed.setFldTmpcashid("");
        tblinvhed.setFldVegaactive(false);
        tblinvhed.setFldWebd(0.00);
        tblinvhed.setNexusmobile("");
        tblinvhed.setSyncstatus(false);
        tblinvhed.setFldTrxtype(request.getTaxName());
        tblinvhed.setFldTaxamount(-request.getTaxRate());
        Tax tax = taxRepository.findByTaxNameAndIsDeleted(request.getTaxName(),Deleted.NO);
        if (tax!=null){
            tblinvhed.setFldTaxPer(tax.getTaxRate());
        }else {
            tblinvhed.setFldTaxPer(0.00);
        }

        tblinvhedRepository.save(tblinvhed);

        int lineNo = 1;

        for (WholeSaleInvDtlRequest dtlRequest : request.getInvDtlRequests()) {

            if (dtlRequest.getSizeQty() != 0) {

                Stock stock = stockRepository.getByBatchNoAndItemAndBranchAndColorAndSizeAndFit(dtlRequest.getBatchNo(), dtlRequest.getItemCode(),
                        request.getLocationId().longValue(), dtlRequest.getColor(), dtlRequest.getSizeId().longValue(), dtlRequest.getFit());

                if (stock != null) {
                    stock.setStoresQty(stock.getStoresQty() + dtlRequest.getSizeQty());
                    stock.setTotalQty(stock.getTotalQty() + dtlRequest.getSizeQty());
                }
//                else {
//                    stock.setStoresQty(dtlRequest.getSizeQty());
//                    stock.setTotalQty(dtlRequest.getSizeQty());
//                }
                assert stock != null;
                stockRepository.save(stock);

                WholeSaleInvDtl wholeSaleInvDtl = new WholeSaleInvDtl();
                wholeSaleInvDtl.setItemCode(dtlRequest.getItemCode());
                wholeSaleInvDtl.setInvno(invNo);
                wholeSaleInvDtl.setItemName(dtlRequest.getItemName());
                wholeSaleInvDtl.setSizeId(dtlRequest.getSizeId());
                wholeSaleInvDtl.setSizeName(dtlRequest.getSizeName());
                wholeSaleInvDtl.setQtyByitem(-dtlRequest.getQtyByitem());
                wholeSaleInvDtl.setSizeQty(-dtlRequest.getSizeQty());
                wholeSaleInvDtl.setMrp(dtlRequest.getMrp());
                wholeSaleInvDtl.setDisPrecentage(dtlRequest.getDisPrecentage());
                wholeSaleInvDtl.setDisAmount(dtlRequest.getDisAmount());
                wholeSaleInvDtl.setAmount(dtlRequest.getAmount());
                wholeSaleInvDtl.setIsDeleted(Deleted.NO);
                wholeSaleInvDtl.setColor(dtlRequest.getColor());
                wholeSaleInvDtl.setFit(dtlRequest.getFit());
                wholeSaleInvDtl.setBatchNo(dtlRequest.getBatchNo());
                wholeSaleInvDtl.setItemCost(dtlRequest.getItemCost());
                wholeSaleInvDtlRepository.save(wholeSaleInvDtl);

                Tblinvdtl tblinvdtl = new Tblinvdtl();
                tblinvdtl.setBlnconfirmed(false);
                tblinvdtl.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldAccessupdate(0.00);
                tblinvdtl.setFldAmount(((dtlRequest.getMrp() * dtlRequest.getQtyByitem()) - dtlRequest.getAmount()));
                tblinvdtl.setFldAvgcost(-dtlRequest.getItemCost());
                tblinvdtl.setFldBarcodereadstatus("");
                tblinvdtl.setFldBarcodeused("");
                tblinvdtl.setFldBilldisper(request.getBillDisPrecentage()==null ? 0.00 : dtlRequest.getDisPrecentage());
                tblinvdtl.setFldBillpromodisper(0.00);
                tblinvdtl.setFldCancel(false);
                tblinvdtl.setFldCashierid(uu);
                tblinvdtl.setFldClosesales("f");

//                tblinvdtl.setFldCostprice(-dtlRequest.getMrp());
                tblinvdtl.setFldCostprice(stock.getStkCashPrice()==null?0.00:stock.getStkCashPrice());

                tblinvdtl.setFldDatatransfer(false);
                tblinvdtl.setFldDate(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldDoublerefoldinvno("");
                tblinvdtl.setFldDoublerefpromoamt(0.00);
                tblinvdtl.setFldExtranumericfield1(0.00);
                tblinvdtl.setFldExtranumericfield2(0.00);
                tblinvdtl.setFldExtranumericfield3(0.00);
                tblinvdtl.setFldExtranumericfield4(0.00);
                tblinvdtl.setFldExtranumericfield5(0.00);
                tblinvdtl.setFldExtratextfield1("");
                tblinvdtl.setFldExtratextfield2("");
                tblinvdtl.setFldExtratextfield3("");
                tblinvdtl.setFldExtratextfield4("");
                tblinvdtl.setFldExtratextfield5("");
                tblinvdtl.setFldExtratextfield6("");
                tblinvdtl.setFldExtratextfield7("");
                tblinvdtl.setFldExtratextfield8("");
                tblinvdtl.setFldExtratextfield9("");
                tblinvdtl.setFldIntcolorid(dtlRequest.getColor().intValue());
                tblinvdtl.setFldIntfitid(dtlRequest.getFit().intValue());
                tblinvdtl.setFldIntsizeid(dtlRequest.getSizeId());
                tblinvdtl.setFldInvno(invNo.toString());
                tblinvdtl.setFldItemcode(dtlRequest.getItemCode());
                tblinvdtl.setFldItemdescription(dtlRequest.getItemName());

//                tblinvdtl.setFldLinedisamt(dtlRequest.getSizeQty()*(dtlRequest.getMrp()*(tblinvdtl.getFldLinedisper()/100)));

                tblinvdtl.setFldLinedisamt(dtlRequest.getSizeQty()*(dtlRequest.getMrp()*(request.getBillDisPrecentage()/100)));
//                tblinvdtl.setFldLinedisamt(dtlRequest.getAmount());


                tblinvdtl.setFldLinediscountauthorizedby("");
                tblinvdtl.setFldLinediscountreasoncode(0);
                tblinvdtl.setFldLinedisper(dtlRequest.getDisPrecentage()==null ? 0.00: dtlRequest.getDisPrecentage());
                tblinvdtl.setFldLinedisval(dtlRequest.getDisAmount());
                tblinvdtl.setFldLineno(lineNo);
                tblinvdtl.setFldLocation(request.getLocationId().toString());
                tblinvdtl.setFldMiddlewarestatus(0);
                tblinvdtl.setFldMiddlewarestatusBw(0);
                tblinvdtl.setFldMiddlewareuuid("");
                tblinvdtl.setFldMiddlewareuuidBw("");
                tblinvdtl.setFldOrgSelling(dtlRequest.getMrp());
                tblinvdtl.setFldOudrivenpromoamt(0.00);
                tblinvdtl.setFldPrice(dtlRequest.getMrp());
                tblinvdtl.setFldPromocode("");
                tblinvdtl.setFldPromodisamt(0.00);
                tblinvdtl.setFldPromodisper(0.00);
                tblinvdtl.setFldPromodisval(0.00);
                tblinvdtl.setFldPromomode("");
                tblinvdtl.setFldPromotype("");
                tblinvdtl.setFldQty(-dtlRequest.getSizeQty());
                tblinvdtl.setFldRefundauthorizedby("");
                tblinvdtl.setFldRefundreasoncode(0);
                tblinvdtl.setFldSapUpdated(false);
                tblinvdtl.setFldSapVendor("");
                tblinvdtl.setFldScanmode("");
                tblinvdtl.setFldSplittedbilldisamt((request.getBillDisAmount() / request.getTotalQty()) * tblinvdtl.getFldQty());
                tblinvdtl.setFldStockcode(dtlRequest.getBatchNo());

                if (request.getTaxRate()==0.00){
                    tblinvdtl.setFldTaxamount((request.getTaxRate()/request.getTotalQty())*(dtlRequest.getSizeQty()));
                }else {
                    tblinvdtl.setFldTaxamount(0.00);
                }

                tblinvdtl.setFldTaxclass("");
                tblinvdtl.setFldTime(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
                tblinvdtl.setFldTrxtype("");
                tblinvdtl.setFldVoid(false);
                tblinvdtl.setFldTrxtype(request.getTaxName());
                tblinvdtlRepository.save(tblinvdtl);

                BinCard binCard = new BinCard();
                ItemMaster items = itemMasterRepository.getById(dtlRequest.getItemCode());
                binCard.setItem(items);
                binCard.setBinCardDate(new Date());
                binCard.setDocType("WHOLE SALE RETURN");
                binCard.setDocNo(invNo.toString());
                binCard.setRecQty(dtlRequest.getSizeQty());
                binCard.setIsueQty(0.00);
                binCard.setBalanceQty(stock.getStoresQty());
                binCard.setBatchNo(dtlRequest.getBatchNo());
                BranchNetwork branchNetwork = branchNetworkRepository.getById(request.getLocationId().longValue());
                binCard.setBranch(branchNetwork);
                binCard.setIsDeleted(Deleted.NO);
                binCard.setFit(dtlRequest.getFit());
                binCard.setColor(dtlRequest.getColor());
                binCard.setSize(dtlRequest.getSizeId().longValue());
                binCardRepository.save(binCard);

                lineNo++;
            }

        }

        Tblpaydtl tblpaydtl = new Tblpaydtl();

        TblpaydtlPK tblpaydtlPK = new TblpaydtlPK(ConvertUtils.convertStrToDate(request.getInvDate()), request.getLocationId().toString(), invNo.toString());
        tblpaydtl.setTblpaydtlPK(tblpaydtlPK);
//        tblpaydtl.setFldPaymode("");
        tblpaydtl.setFldPaytype(request.getPayTypeInfo());
        tblpaydtl.setFldPaytypecode(request.getCustomerId().toString());
        tblpaydtl.setFldCrdcardno(request.getCreditCardNumber());
        tblpaydtl.setFldFcuramt(0.00);
        tblpaydtl.setFldPaytypeamt(request.getNetAmount());
        tblpaydtl.setFldExchngrate(0.00);
        tblpaydtl.setFldDatatransfer(false);
        tblpaydtl.setFldGvdisno("");
        tblpaydtl.setFldCreditno("");
        tblpaydtl.setFldComno("");
        tblpaydtl.setFldCashierid(uu);
        tblpaydtl.setFldEncrkey("");
        tblpaydtl.setBlnconfirmed(false);
        tblpaydtl.setBlnmodechange(false);
        tblpaydtl.setFldCancel(false);
        tblpaydtl.setFldAccessupdate(0.00);
        tblpaydtl.setFldMiddlewareuuid("");
        tblpaydtl.setFldMiddlewarestatus(0);
        tblpaydtl.setFldMiddlewareuuidCreditcust("");
        tblpaydtl.setFldMiddlewarestatusCreditcust(0);
        tblpaydtl.setFldMiddlewareuuidBw("");
        tblpaydtl.setFldMiddlewarestatusBw(0);
        tblpaydtl.setCreatedon(request.getInvDate() == null ? null : ConvertUtils.convertStrToDate(request.getInvDate()));
        tblpaydtlRepository.save(tblpaydtl);

        return convert(save);
    }

    @Override
    public String getWhTXInvNo() {
        String sql = " select max(fld_InvNo) as fld_InvNo from tblinvhed ";
        String invnum = "";
        Integer inv = null;

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                invnum = rs.getString("fld_InvNo");
            }

            inv = Integer.parseInt(invnum) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }

        String invNum = "TX"+inv;

        return invNum;
    }

    @Override
    public String getWhRInvNo() {
        String sql = " select max(fld_InvNo) as fld_InvNo from tblinvhed ";
        String invnum = "";
        Integer inv = null;

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                invnum = rs.getString("fld_InvNo");
            }

            inv = Integer.parseInt(invnum) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {

                }
            }
        }

        String invNum = "R"+inv;

        return invNum;
    }


}