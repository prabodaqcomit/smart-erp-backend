package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.TblinvdtlRequest;
import lk.quantacom.smarterpbackend.dto.request.TblinvhedRequest;
import lk.quantacom.smarterpbackend.dto.request.TblinvhedUpdateRequest;
import lk.quantacom.smarterpbackend.dto.request.TblpaydtlRequest;
import lk.quantacom.smarterpbackend.dto.response.TblinvListDtlsResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvdtlResponse;
import lk.quantacom.smarterpbackend.dto.response.TblinvhedResponse;
import lk.quantacom.smarterpbackend.dto.response.TblpaydtlResponse;
import lk.quantacom.smarterpbackend.entity.Tblinvdtl;
import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import lk.quantacom.smarterpbackend.entity.Tblpaydtl;
import lk.quantacom.smarterpbackend.entity.TblpaydtlPK;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.repository.TblinvdtlRepository;
import lk.quantacom.smarterpbackend.repository.TblinvhedRepository;
import lk.quantacom.smarterpbackend.repository.TblpaydtlRepository;
import lk.quantacom.smarterpbackend.service.TblinvdtlService;
import lk.quantacom.smarterpbackend.service.TblinvhedService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblinvhedServiceImpl implements TblinvhedService {

    @Autowired
    private TblinvhedRepository tblinvhedRepository;

    @Autowired
    private TblinvdtlRepository tblinvdtlRepository;

    @Autowired
    private TblpaydtlRepository tblpaydtlRepository;

    public static TblinvhedResponse convert(Tblinvhed tblinvhed) {

        TblinvhedResponse typeResponse = new TblinvhedResponse();
        typeResponse.setBatchid(tblinvhed.getBatchid());
        typeResponse.setBlnconfirmed(tblinvhed.getBlnconfirmed());
        typeResponse.setCreatedon(tblinvhed.getCreatedon());
        typeResponse.setFldAccessupdate(tblinvhed.getFldAccessupdate());
        typeResponse.setFldBadDeptAmount(tblinvhed.getFldBadDeptAmount());
        typeResponse.setFldBadDeptDate(tblinvhed.getFldBadDeptDate());
        typeResponse.setFldBadDeptTime(tblinvhed.getFldBadDeptTime());
        typeResponse.setFldCancel(tblinvhed.getFldCancel());
        typeResponse.setFldCash(tblinvhed.getFldCash());
        typeResponse.setFldCashierid(tblinvhed.getFldCashierid());
        typeResponse.setFldCashsale(tblinvhed.getFldCashsale());
        typeResponse.setFldChange(tblinvhed.getFldChange());
        typeResponse.setFldChangebynexuspoints(tblinvhed.getFldChangebynexuspoints());
        typeResponse.setFldCheque(tblinvhed.getFldCheque());
        typeResponse.setFldChequeno(tblinvhed.getFldChequeno());
        typeResponse.setFldClosesales(tblinvhed.getFldClosesales());
        typeResponse.setFldCoupon(tblinvhed.getFldCoupon());
        typeResponse.setFldCpacker(tblinvhed.getFldCpacker());
        typeResponse.setFldCreditcard(tblinvhed.getFldCreditcard());
        typeResponse.setFldCreditcust(tblinvhed.getFldCreditcust());
        typeResponse.setFldDatatransfer(tblinvhed.getFldDatatransfer());
        typeResponse.setFldDate(tblinvhed.getFldDate());
        typeResponse.setFldDiscount(tblinvhed.getFldDiscount());
        typeResponse.setFldForigncustomer(tblinvhed.getFldForigncustomer());
        typeResponse.setFldFrcurency(tblinvhed.getFldFrcurency());
        typeResponse.setFldFrcurencysale(tblinvhed.getFldFrcurencysale());
        typeResponse.setFldGiftvoucher(tblinvhed.getFldGiftvoucher());
        typeResponse.setFldGrossamount(tblinvhed.getFldGrossamount());
        typeResponse.setFldGvsaleinv(tblinvhed.getFldGvsaleinv());
        typeResponse.setFldHappyStatus(tblinvhed.getFldHappyStatus());
        typeResponse.setFldHappyStatus2(tblinvhed.getFldHappyStatus2());
        typeResponse.setFldInvno(tblinvhed.getFldInvno());
        typeResponse.setFldItemwisedis(tblinvhed.getFldItemwisedis());
        typeResponse.setFldLocalcustomer(tblinvhed.getFldLocalcustomer());
        typeResponse.setFldLocation(tblinvhed.getFldLocation());
        typeResponse.setFldMember(tblinvhed.getFldMember());
        typeResponse.setFldMiddlewarestatus(tblinvhed.getFldMiddlewarestatus());
        typeResponse.setFldMiddlewarestatusBw(tblinvhed.getFldMiddlewarestatusBw());
        typeResponse.setFldMiddlewareuuid(tblinvhed.getFldMiddlewareuuid());
        typeResponse.setFldMiddlewareuuidBw(tblinvhed.getFldMiddlewareuuidBw());
        typeResponse.setFldNetamount(tblinvhed.getFldNetamount());
        typeResponse.setFldNetamountwithouttax(tblinvhed.getFldNetamountwithouttax());
        typeResponse.setFldOther(tblinvhed.getFldOther());
        typeResponse.setFldPromodisc(tblinvhed.getFldPromodisc());
        typeResponse.setFldSapUpdated(tblinvhed.getFldSapUpdated());
        typeResponse.setFldScratchCards(tblinvhed.getFldScratchCards());
        typeResponse.setFldShiftno(tblinvhed.getFldShiftno());
        typeResponse.setFldStarttime(tblinvhed.getFldStarttime());
        typeResponse.setFldStationid(tblinvhed.getFldStationid());
        typeResponse.setFldTaxamount(tblinvhed.getFldTaxamount());
        typeResponse.setFldTime(tblinvhed.getFldTime());
        typeResponse.setFldTmpcashid(tblinvhed.getFldTmpcashid());
        typeResponse.setFldTrxtype(tblinvhed.getFldTrxtype());
        typeResponse.setFldVegaactive(tblinvhed.getFldVegaactive());
        typeResponse.setFldWebd(tblinvhed.getFldWebd());
        typeResponse.setNexusmobile(tblinvhed.getNexusmobile());
        typeResponse.setSyncstatus(tblinvhed.getSyncstatus());
        typeResponse.setFldInvno(tblinvhed.getFldInvno());

        return typeResponse;
    }

    @Override
    @Transactional
    public TblinvhedResponse save(TblinvhedRequest request) {

        Tblinvhed tblinvhed = new Tblinvhed();
        tblinvhed.setBatchid(request.getBatchid());
        tblinvhed.setBlnconfirmed(request.getBlnconfirmed());
        tblinvhed.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        tblinvhed.setFldAccessupdate(request.getFldAccessupdate());
        tblinvhed.setFldBadDeptAmount(request.getFldBadDeptAmount());
        tblinvhed.setFldBadDeptDate(request.getFldBadDeptDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldBadDeptDate()));
        tblinvhed.setFldBadDeptTime(request.getFldBadDeptTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldBadDeptTime()));
        tblinvhed.setFldCancel(request.getFldCancel());
        tblinvhed.setFldCash(request.getFldCash());
        tblinvhed.setFldCashierid(request.getFldCashierid());
        tblinvhed.setFldCashsale(request.getFldCashsale());
        tblinvhed.setFldChange(request.getFldChange());
        tblinvhed.setFldChangebynexuspoints(request.getFldChangebynexuspoints());
        tblinvhed.setFldCheque(request.getFldCheque());
        tblinvhed.setFldChequeno(request.getFldChequeno());
        tblinvhed.setFldClosesales(request.getFldClosesales());
        tblinvhed.setFldCoupon(request.getFldCoupon());
        tblinvhed.setFldCpacker(request.getFldCpacker());
        tblinvhed.setFldCreditcard(request.getFldCreditcard());
        tblinvhed.setFldCreditcust(request.getFldCreditcust());
        tblinvhed.setFldDatatransfer(request.getFldDatatransfer());
        tblinvhed.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        tblinvhed.setFldDiscount(request.getFldDiscount());
        tblinvhed.setFldForigncustomer(request.getFldForigncustomer());
        tblinvhed.setFldFrcurency(request.getFldFrcurency());
        tblinvhed.setFldFrcurencysale(request.getFldFrcurencysale());
        tblinvhed.setFldGiftvoucher(request.getFldGiftvoucher());
        tblinvhed.setFldGrossamount(request.getFldGrossamount());
        tblinvhed.setFldGvsaleinv(request.getFldGvsaleinv());
        tblinvhed.setFldHappyStatus(request.getFldHappyStatus());
        tblinvhed.setFldHappyStatus2(request.getFldHappyStatus2());
        tblinvhed.setFldInvno(request.getFldInvno());
        tblinvhed.setFldItemwisedis(request.getFldItemwisedis());
        tblinvhed.setFldLocalcustomer(request.getFldLocalcustomer());
        tblinvhed.setFldLocation(request.getFldLocation());
        tblinvhed.setFldMember(request.getFldMember());
        tblinvhed.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblinvhed.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblinvhed.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblinvhed.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblinvhed.setFldNetamount(request.getFldNetamount());
        tblinvhed.setFldNetamountwithouttax(request.getFldNetamountwithouttax());
        tblinvhed.setFldOther(request.getFldOther());
        tblinvhed.setFldPromodisc(request.getFldPromodisc());
        tblinvhed.setFldSapUpdated(request.getFldSapUpdated());
        tblinvhed.setFldScratchCards(request.getFldScratchCards());
        tblinvhed.setFldShiftno(request.getFldShiftno());
        tblinvhed.setFldStarttime(request.getFldStarttime() == null ? null : ConvertUtils.convertStrToDate(request.getFldStarttime()));
        tblinvhed.setFldStationid(request.getFldStationid());
        tblinvhed.setFldTaxamount(request.getFldTaxamount());
        tblinvhed.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        tblinvhed.setFldTmpcashid(request.getFldTmpcashid());
        tblinvhed.setFldTrxtype(request.getFldTrxtype());
        tblinvhed.setFldVegaactive(request.getFldVegaactive());
        tblinvhed.setFldWebd(request.getFldWebd());
        tblinvhed.setNexusmobile(request.getNexusmobile());
        tblinvhed.setSyncstatus(request.getSyncstatus());
        Tblinvhed save = tblinvhedRepository.save(tblinvhed);

        String newMax="100000001";
        String max=tblinvhedRepository.getMaxId();
        if(max!=null){
            double exMax=Double.parseDouble(max);
            newMax=new DecimalFormat("100000000").format(exMax+1);
        }

        if(request.getRequestList()!=null){
            for(TblinvdtlRequest request1: request.getRequestList()){

                Tblinvdtl tblinvdtl = new Tblinvdtl();
                tblinvdtl.setBlnconfirmed(request1.getBlnconfirmed());
                tblinvdtl.setCreatedon(request1.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request1.getCreatedon()));
                tblinvdtl.setFldAccessupdate(request1.getFldAccessupdate());
                tblinvdtl.setFldAmount(request1.getFldAmount());
                tblinvdtl.setFldAvgcost(request1.getFldAvgcost());
                tblinvdtl.setFldBarcodereadstatus(request1.getFldBarcodereadstatus());
                tblinvdtl.setFldBarcodeused(request1.getFldBarcodeused());
                tblinvdtl.setFldBilldisper(request1.getFldBilldisper());
                tblinvdtl.setFldBillpromodisper(request1.getFldBillpromodisper());
                tblinvdtl.setFldCancel(request1.getFldCancel());
                tblinvdtl.setFldCashierid(request1.getFldCashierid());
                tblinvdtl.setFldClosesales(request1.getFldClosesales());
                tblinvdtl.setFldCostprice(request1.getFldCostprice());
                tblinvdtl.setFldDatatransfer(request1.getFldDatatransfer());
                tblinvdtl.setFldDate(request1.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request1.getFldDate()));
                tblinvdtl.setFldDoublerefoldinvno(request1.getFldDoublerefoldinvno());
                tblinvdtl.setFldDoublerefpromoamt(request1.getFldDoublerefpromoamt());
                tblinvdtl.setFldExtranumericfield1(request1.getFldExtranumericfield1());
                tblinvdtl.setFldExtranumericfield2(request1.getFldExtranumericfield2());
                tblinvdtl.setFldExtranumericfield3(request1.getFldExtranumericfield3());
                tblinvdtl.setFldExtranumericfield4(request1.getFldExtranumericfield4());
                tblinvdtl.setFldExtranumericfield5(request1.getFldExtranumericfield5());
                tblinvdtl.setFldExtratextfield1(request1.getFldExtratextfield1());
                tblinvdtl.setFldExtratextfield2(request1.getFldExtratextfield2());
                tblinvdtl.setFldExtratextfield3(request1.getFldExtratextfield3());
                tblinvdtl.setFldExtratextfield4(request1.getFldExtratextfield4());
                tblinvdtl.setFldExtratextfield5(request1.getFldExtratextfield5());
                tblinvdtl.setFldExtratextfield6(request1.getFldExtratextfield6());
                tblinvdtl.setFldExtratextfield7(request1.getFldExtratextfield7());
                tblinvdtl.setFldExtratextfield8(request1.getFldExtratextfield8());
                tblinvdtl.setFldExtratextfield9(request1.getFldExtratextfield9());
                tblinvdtl.setFldIntcolorid(request1.getFldIntcolorid());
                tblinvdtl.setFldIntfitid(request1.getFldIntfitid());
                tblinvdtl.setFldIntsizeid(request1.getFldIntsizeid());
                tblinvdtl.setFldInvno(newMax);
                tblinvdtl.setFldItemcode(request1.getFldItemcode());
                tblinvdtl.setFldItemdescription(request1.getFldItemdescription());
                tblinvdtl.setFldLinedisamt(request1.getFldLinedisamt());
                tblinvdtl.setFldLinediscountauthorizedby(request1.getFldLinediscountauthorizedby());
                tblinvdtl.setFldLinediscountreasoncode(request1.getFldLinediscountreasoncode());
                tblinvdtl.setFldLinedisper(request1.getFldLinedisper());
                tblinvdtl.setFldLinedisval(request1.getFldLinedisval());
                tblinvdtl.setFldLineno(request1.getFldLineno());
                tblinvdtl.setFldLocation(request1.getFldLocation());
                tblinvdtl.setFldMiddlewarestatus(request1.getFldMiddlewarestatus());
                tblinvdtl.setFldMiddlewarestatusBw(request1.getFldMiddlewarestatusBw());
                tblinvdtl.setFldMiddlewareuuid(request1.getFldMiddlewareuuid());
                tblinvdtl.setFldMiddlewareuuidBw(request1.getFldMiddlewareuuidBw());
                tblinvdtl.setFldOrgSelling(request1.getFldOrgSelling());
                tblinvdtl.setFldOudrivenpromoamt(request1.getFldOudrivenpromoamt());
                tblinvdtl.setFldPrice(request1.getFldPrice());
                tblinvdtl.setFldPromocode(request1.getFldPromocode());
                tblinvdtl.setFldPromodisamt(request1.getFldPromodisamt());
                tblinvdtl.setFldPromodisper(request1.getFldPromodisper());
                tblinvdtl.setFldPromodisval(request1.getFldPromodisval());
                tblinvdtl.setFldPromomode(request1.getFldPromomode());
                tblinvdtl.setFldPromotype(request1.getFldPromotype());
                tblinvdtl.setFldQty(request1.getFldQty());
                tblinvdtl.setFldRefundauthorizedby(request1.getFldRefundauthorizedby());
                tblinvdtl.setFldRefundreasoncode(request1.getFldRefundreasoncode());
                tblinvdtl.setFldSapUpdated(request1.getFldSapUpdated());
                tblinvdtl.setFldSapVendor(request1.getFldSapVendor());
                tblinvdtl.setFldScanmode(request1.getFldScanmode());
                tblinvdtl.setFldSplittedbilldisamt(request1.getFldSplittedbilldisamt());
                tblinvdtl.setFldStockcode(request1.getFldStockcode());
                tblinvdtl.setFldTaxamount(request1.getFldTaxamount());
                tblinvdtl.setFldTaxclass(request1.getFldTaxclass());
                tblinvdtl.setFldTime(request1.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request1.getFldTime()));
                tblinvdtl.setFldTrxtype(request1.getFldTrxtype());
                tblinvdtl.setFldVoid(request1.getFldVoid());
                tblinvdtlRepository.save(tblinvdtl);
            }
        }

        if(request.getPayList()!=null){
            for (TblpaydtlRequest request1:request.getPayList()){
                Tblpaydtl tblpaydtl = new Tblpaydtl();
                TblpaydtlPK tblpaydtlPK = new TblpaydtlPK(ConvertUtils.convertStrToDate(request.getFldDate()),request.getFldLocation(),newMax);
                tblpaydtl.setTblpaydtlPK(tblpaydtlPK);
                tblpaydtl.setFldPaymode(request1.getFldPaymode());
                tblpaydtl.setFldPaytype(request1.getFldPaytype());
                tblpaydtl.setFldPaytypecode(request1.getFldPaytypecode());
                tblpaydtl.setFldCrdcardno(request1.getFldCrdcardno());
                tblpaydtl.setFldFcuramt(request1.getFldFcuramt());
                tblpaydtl.setFldPaytypeamt(request1.getFldPaytypeamt());
                tblpaydtl.setFldExchngrate(request1.getFldExchngrate());
                tblpaydtl.setFldDatatransfer(request1.getFldDatatransfer());
                tblpaydtl.setFldGvdisno(request1.getFldGvdisno());
                tblpaydtl.setFldCreditno(request1.getFldCreditno());
                tblpaydtl.setFldComno(request1.getFldComno());
                tblpaydtl.setFldCashierid(request1.getFldCashierid());
                tblpaydtl.setFldEncrkey(request1.getFldEncrkey());
                tblpaydtl.setBlnconfirmed(request1.getBlnconfirmed());
                tblpaydtl.setBlnmodechange(request1.getBlnmodechange());
                tblpaydtl.setFldCancel(request1.getFldCancel());
                tblpaydtl.setFldAccessupdate(request1.getFldAccessupdate());
                tblpaydtl.setFldMiddlewareuuid(request1.getFldMiddlewareuuid());
                tblpaydtl.setFldMiddlewarestatus(request1.getFldMiddlewarestatus());
                tblpaydtl.setFldMiddlewareuuidCreditcust(request1.getFldMiddlewareuuidCreditcust());
                tblpaydtl.setFldMiddlewarestatusCreditcust(request1.getFldMiddlewarestatusCreditcust());
                tblpaydtl.setFldMiddlewareuuidBw(request1.getFldMiddlewareuuidBw());
                tblpaydtl.setFldMiddlewarestatusBw(request1.getFldMiddlewarestatusBw());
                tblpaydtl.setCreatedon(request1.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request1.getCreatedon()));
                tblpaydtlRepository.save(tblpaydtl);
            }
        }

        return convert(save);
    }

    @Override
    @Transactional
    public TblinvhedResponse update(TblinvhedUpdateRequest request) {

        Tblinvhed tblinvhed = tblinvhedRepository.findById(request.getFldInvno()).orElse(null);
        if (tblinvhed == null) {
            return null;
        }

        tblinvhed.setFldInvno(request.getFldInvno());
        tblinvhed.setBatchid(request.getBatchid());
        tblinvhed.setBlnconfirmed(request.getBlnconfirmed());
        tblinvhed.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        tblinvhed.setFldAccessupdate(request.getFldAccessupdate());
        tblinvhed.setFldBadDeptAmount(request.getFldBadDeptAmount());
        tblinvhed.setFldBadDeptDate(request.getFldBadDeptDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldBadDeptDate()));
        tblinvhed.setFldBadDeptTime(request.getFldBadDeptTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldBadDeptTime()));
        tblinvhed.setFldCancel(request.getFldCancel());
        tblinvhed.setFldCash(request.getFldCash());
        tblinvhed.setFldCashierid(request.getFldCashierid());
        tblinvhed.setFldCashsale(request.getFldCashsale());
        tblinvhed.setFldChange(request.getFldChange());
        tblinvhed.setFldChangebynexuspoints(request.getFldChangebynexuspoints());
        tblinvhed.setFldCheque(request.getFldCheque());
        tblinvhed.setFldChequeno(request.getFldChequeno());
        tblinvhed.setFldClosesales(request.getFldClosesales());
        tblinvhed.setFldCoupon(request.getFldCoupon());
        tblinvhed.setFldCpacker(request.getFldCpacker());
        tblinvhed.setFldCreditcard(request.getFldCreditcard());
        tblinvhed.setFldCreditcust(request.getFldCreditcust());
        tblinvhed.setFldDatatransfer(request.getFldDatatransfer());
        tblinvhed.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        tblinvhed.setFldDiscount(request.getFldDiscount());
        tblinvhed.setFldForigncustomer(request.getFldForigncustomer());
        tblinvhed.setFldFrcurency(request.getFldFrcurency());
        tblinvhed.setFldFrcurencysale(request.getFldFrcurencysale());
        tblinvhed.setFldGiftvoucher(request.getFldGiftvoucher());
        tblinvhed.setFldGrossamount(request.getFldGrossamount());
        tblinvhed.setFldGvsaleinv(request.getFldGvsaleinv());
        tblinvhed.setFldHappyStatus(request.getFldHappyStatus());
        tblinvhed.setFldHappyStatus2(request.getFldHappyStatus2());
        tblinvhed.setFldInvno(request.getFldInvno());
        tblinvhed.setFldItemwisedis(request.getFldItemwisedis());
        tblinvhed.setFldLocalcustomer(request.getFldLocalcustomer());
        tblinvhed.setFldLocation(request.getFldLocation());
        tblinvhed.setFldMember(request.getFldMember());
        tblinvhed.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblinvhed.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblinvhed.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblinvhed.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblinvhed.setFldNetamount(request.getFldNetamount());
        tblinvhed.setFldNetamountwithouttax(request.getFldNetamountwithouttax());
        tblinvhed.setFldOther(request.getFldOther());
        tblinvhed.setFldPromodisc(request.getFldPromodisc());
        tblinvhed.setFldSapUpdated(request.getFldSapUpdated());
        tblinvhed.setFldScratchCards(request.getFldScratchCards());
        tblinvhed.setFldShiftno(request.getFldShiftno());
        tblinvhed.setFldStarttime(request.getFldStarttime() == null ? null : ConvertUtils.convertStrToDate(request.getFldStarttime()));
        tblinvhed.setFldStationid(request.getFldStationid());
        tblinvhed.setFldTaxamount(request.getFldTaxamount());
        tblinvhed.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        tblinvhed.setFldTmpcashid(request.getFldTmpcashid());
        tblinvhed.setFldTrxtype(request.getFldTrxtype());
        tblinvhed.setFldVegaactive(request.getFldVegaactive());
        tblinvhed.setFldWebd(request.getFldWebd());
        tblinvhed.setNexusmobile(request.getNexusmobile());
        tblinvhed.setSyncstatus(request.getSyncstatus());
        Tblinvhed updated = tblinvhedRepository.save(tblinvhed);

        return (convert(updated));
    }

    @Override
    public TblinvhedResponse getById(String fldInvno) {

        return tblinvhedRepository.findById(fldInvno).map(TblinvhedServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblinvhedResponse> getAll() {

        return tblinvhedRepository.findAll().stream().map(TblinvhedServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(String fldInvno) {

        Tblinvhed got = tblinvhedRepository.findById(fldInvno).orElse(null);
        tblinvhedRepository.delete(got);
        return 1;
    }

    @Override
    public TblinvListDtlsResponse TblinvListDtls(String fldInvno) {

        TblinvListDtlsResponse response = new TblinvListDtlsResponse();

        Tblinvhed tblinvhed = tblinvhedRepository.findById(fldInvno).orElse(null);
        if (tblinvhed!=null){
            response.setTblinvhedResponse(convert(tblinvhed));
        }

        List<Tblinvdtl> tblinvdtlList = tblinvdtlRepository.findByFldInvno(fldInvno);
        List<TblinvdtlResponse> responseList = new ArrayList<>();
        if(!tblinvdtlList.isEmpty()){
            for (Tblinvdtl tblinvdtl:tblinvdtlList){
                TblinvdtlResponse typeResponse = convertTblinvdtl(tblinvdtl);
                responseList.add(typeResponse);
            }
        }
        response.setTblinvdtlResponseList(responseList);

        Tblpaydtl tblpaydtl = tblpaydtlRepository.findById(fldInvno).orElse(null);
        if (tblpaydtl!=null){
            response.setTblpaydtlResponse(convertTblpaydtl(tblpaydtl));
        }

        return response;
    }

    private static TblinvdtlResponse convertTblinvdtl(Tblinvdtl tblinvdtl) {

        TblinvdtlResponse typeResponse = new TblinvdtlResponse();
        typeResponse.setBlnconfirmed(tblinvdtl.getBlnconfirmed());
        typeResponse.setCreatedon(tblinvdtl.getCreatedon());
        typeResponse.setFldAccessupdate(tblinvdtl.getFldAccessupdate());
        typeResponse.setFldAmount(tblinvdtl.getFldAmount());
        typeResponse.setFldAvgcost(tblinvdtl.getFldAvgcost());
        typeResponse.setFldBarcodereadstatus(tblinvdtl.getFldBarcodereadstatus());
        typeResponse.setFldBarcodeused(tblinvdtl.getFldBarcodeused());
        typeResponse.setFldBilldisper(tblinvdtl.getFldBilldisper());
        typeResponse.setFldBillpromodisper(tblinvdtl.getFldBillpromodisper());
        typeResponse.setFldCancel(tblinvdtl.getFldCancel());
        typeResponse.setFldCashierid(tblinvdtl.getFldCashierid());
        typeResponse.setFldClosesales(tblinvdtl.getFldClosesales());
        typeResponse.setFldCostprice(tblinvdtl.getFldCostprice());
        typeResponse.setFldDatatransfer(tblinvdtl.getFldDatatransfer());
        typeResponse.setFldDate(tblinvdtl.getFldDate());
        typeResponse.setFldDoublerefoldinvno(tblinvdtl.getFldDoublerefoldinvno());
        typeResponse.setFldDoublerefpromoamt(tblinvdtl.getFldDoublerefpromoamt());
        typeResponse.setFldExtranumericfield1(tblinvdtl.getFldExtranumericfield1());
        typeResponse.setFldExtranumericfield2(tblinvdtl.getFldExtranumericfield2());
        typeResponse.setFldExtranumericfield3(tblinvdtl.getFldExtranumericfield3());
        typeResponse.setFldExtranumericfield4(tblinvdtl.getFldExtranumericfield4());
        typeResponse.setFldExtranumericfield5(tblinvdtl.getFldExtranumericfield5());
        typeResponse.setFldExtratextfield1(tblinvdtl.getFldExtratextfield1());
        typeResponse.setFldExtratextfield2(tblinvdtl.getFldExtratextfield2());
        typeResponse.setFldExtratextfield3(tblinvdtl.getFldExtratextfield3());
        typeResponse.setFldExtratextfield4(tblinvdtl.getFldExtratextfield4());
        typeResponse.setFldExtratextfield5(tblinvdtl.getFldExtratextfield5());
        typeResponse.setFldExtratextfield6(tblinvdtl.getFldExtratextfield6());
        typeResponse.setFldExtratextfield7(tblinvdtl.getFldExtratextfield7());
        typeResponse.setFldExtratextfield8(tblinvdtl.getFldExtratextfield8());
        typeResponse.setFldExtratextfield9(tblinvdtl.getFldExtratextfield9());
        typeResponse.setFldIntcolorid(tblinvdtl.getFldIntcolorid());
        typeResponse.setFldIntfitid(tblinvdtl.getFldIntfitid());
        typeResponse.setFldIntsizeid(tblinvdtl.getFldIntsizeid());
        typeResponse.setFldInvno(tblinvdtl.getFldInvno());
        typeResponse.setFldItemcode(tblinvdtl.getFldItemcode());
        typeResponse.setFldItemdescription(tblinvdtl.getFldItemdescription());
        typeResponse.setFldLinedisamt(tblinvdtl.getFldLinedisamt());
        typeResponse.setFldLinediscountauthorizedby(tblinvdtl.getFldLinediscountauthorizedby());
        typeResponse.setFldLinediscountreasoncode(tblinvdtl.getFldLinediscountreasoncode());
        typeResponse.setFldLinedisper(tblinvdtl.getFldLinedisper());
        typeResponse.setFldLinedisval(tblinvdtl.getFldLinedisval());
        typeResponse.setFldLineno(tblinvdtl.getFldLineno());
        typeResponse.setFldLocation(tblinvdtl.getFldLocation());
        typeResponse.setFldMiddlewarestatus(tblinvdtl.getFldMiddlewarestatus());
        typeResponse.setFldMiddlewarestatusBw(tblinvdtl.getFldMiddlewarestatusBw());
        typeResponse.setFldMiddlewareuuid(tblinvdtl.getFldMiddlewareuuid());
        typeResponse.setFldMiddlewareuuidBw(tblinvdtl.getFldMiddlewareuuidBw());
        typeResponse.setFldOrgSelling(tblinvdtl.getFldOrgSelling());
        typeResponse.setFldOudrivenpromoamt(tblinvdtl.getFldOudrivenpromoamt());
        typeResponse.setFldPrice(tblinvdtl.getFldPrice());
        typeResponse.setFldPromocode(tblinvdtl.getFldPromocode());
        typeResponse.setFldPromodisamt(tblinvdtl.getFldPromodisamt());
        typeResponse.setFldPromodisper(tblinvdtl.getFldPromodisper());
        typeResponse.setFldPromodisval(tblinvdtl.getFldPromodisval());
        typeResponse.setFldPromomode(tblinvdtl.getFldPromomode());
        typeResponse.setFldPromotype(tblinvdtl.getFldPromotype());
        typeResponse.setFldQty(tblinvdtl.getFldQty());
        typeResponse.setFldRefundauthorizedby(tblinvdtl.getFldRefundauthorizedby());
        typeResponse.setFldRefundreasoncode(tblinvdtl.getFldRefundreasoncode());
        typeResponse.setFldSapUpdated(tblinvdtl.getFldSapUpdated());
        typeResponse.setFldSapVendor(tblinvdtl.getFldSapVendor());
        typeResponse.setFldScanmode(tblinvdtl.getFldScanmode());
        typeResponse.setFldSplittedbilldisamt(tblinvdtl.getFldSplittedbilldisamt());
        typeResponse.setFldStockcode(tblinvdtl.getFldStockcode());
        typeResponse.setFldTaxamount(tblinvdtl.getFldTaxamount());
        typeResponse.setFldTaxclass(tblinvdtl.getFldTaxclass());
        typeResponse.setFldTime(tblinvdtl.getFldTime());
        typeResponse.setFldTrxtype(tblinvdtl.getFldTrxtype());
        typeResponse.setFldVoid(tblinvdtl.getFldVoid());
        typeResponse.setFldInvno(tblinvdtl.getFldInvno());
        typeResponse.setId(tblinvdtl.getId());

        return typeResponse;
    }

    private static TblpaydtlResponse convertTblpaydtl(Tblpaydtl tblpaydtl) {

        TblpaydtlResponse typeResponse = new TblpaydtlResponse();
        typeResponse.setFldInvpaydtlkey(tblpaydtl.getTblpaydtlPK().getFldInvpaydtlkey());
        typeResponse.setFldDate(tblpaydtl.getTblpaydtlPK().getFldDate());
        typeResponse.setFldLocation(tblpaydtl.getTblpaydtlPK().getFldLocation());
        typeResponse.setFldInvno(tblpaydtl.getTblpaydtlPK().getFldInvno());
        typeResponse.setFldPaymode(tblpaydtl.getFldPaymode());
        typeResponse.setFldPaytype(tblpaydtl.getFldPaytype());
        typeResponse.setFldPaytypecode(tblpaydtl.getFldPaytypecode());
        typeResponse.setFldCrdcardno(tblpaydtl.getFldCrdcardno());
        typeResponse.setFldFcuramt(tblpaydtl.getFldFcuramt());
        typeResponse.setFldPaytypeamt(tblpaydtl.getFldPaytypeamt());
        typeResponse.setFldExchngrate(tblpaydtl.getFldExchngrate());
        typeResponse.setFldDatatransfer(tblpaydtl.getFldDatatransfer());
        typeResponse.setFldGvdisno(tblpaydtl.getFldGvdisno());
        typeResponse.setFldCreditno(tblpaydtl.getFldCreditno());
        typeResponse.setFldComno(tblpaydtl.getFldComno());
        typeResponse.setFldCashierid(tblpaydtl.getFldCashierid());
        typeResponse.setFldEncrkey(tblpaydtl.getFldEncrkey());
        typeResponse.setBlnconfirmed(tblpaydtl.getBlnconfirmed());
        typeResponse.setBlnmodechange(tblpaydtl.getBlnmodechange());
        typeResponse.setFldCancel(tblpaydtl.getFldCancel());
        typeResponse.setFldAccessupdate(tblpaydtl.getFldAccessupdate());
        typeResponse.setFldMiddlewareuuid(tblpaydtl.getFldMiddlewareuuid());
        typeResponse.setFldMiddlewarestatus(tblpaydtl.getFldMiddlewarestatus());
        typeResponse.setFldMiddlewareuuidCreditcust(tblpaydtl.getFldMiddlewareuuidCreditcust());
        typeResponse.setFldMiddlewarestatusCreditcust(tblpaydtl.getFldMiddlewarestatusCreditcust());
        typeResponse.setFldMiddlewareuuidBw(tblpaydtl.getFldMiddlewareuuidBw());
        typeResponse.setFldMiddlewarestatusBw(tblpaydtl.getFldMiddlewarestatusBw());
        typeResponse.setCreatedon(tblpaydtl.getCreatedon());
        typeResponse.setFldInvpaydtlkey(tblpaydtl.getTblpaydtlPK().getFldInvpaydtlkey());

        return typeResponse;
    }
}