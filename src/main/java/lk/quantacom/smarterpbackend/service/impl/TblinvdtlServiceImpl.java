package lk.quantacom.smarterpbackend.service.impl;

import lk.quantacom.smarterpbackend.dto.request.*;
import lk.quantacom.smarterpbackend.dto.response.*;
import lk.quantacom.smarterpbackend.entity.Tblinvdtl;
import lk.quantacom.smarterpbackend.repository.TblinvdtlRepository;
import lk.quantacom.smarterpbackend.service.TblinvdtlService;
import lk.quantacom.smarterpbackend.utils.ConvertUtils;
import lk.quantacom.smarterpbackend.utils.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TblinvdtlServiceImpl implements TblinvdtlService {

    @Autowired
    private TblinvdtlRepository tblinvdtlRepository;

    private static TblinvdtlResponse convert(Tblinvdtl tblinvdtl) {

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

    @Override
    @Transactional
    public TblinvdtlResponse save(TblinvdtlRequest request) {

        Tblinvdtl tblinvdtl = new Tblinvdtl();
        tblinvdtl.setBlnconfirmed(request.getBlnconfirmed());
        tblinvdtl.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        tblinvdtl.setFldAccessupdate(request.getFldAccessupdate());
        tblinvdtl.setFldAmount(request.getFldAmount());
        tblinvdtl.setFldAvgcost(request.getFldAvgcost());
        tblinvdtl.setFldBarcodereadstatus(request.getFldBarcodereadstatus());
        tblinvdtl.setFldBarcodeused(request.getFldBarcodeused());
        tblinvdtl.setFldBilldisper(request.getFldBilldisper());
        tblinvdtl.setFldBillpromodisper(request.getFldBillpromodisper());
        tblinvdtl.setFldCancel(request.getFldCancel());
        tblinvdtl.setFldCashierid(request.getFldCashierid());
        tblinvdtl.setFldClosesales(request.getFldClosesales());
        tblinvdtl.setFldCostprice(request.getFldCostprice());
        tblinvdtl.setFldDatatransfer(request.getFldDatatransfer());
        tblinvdtl.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        tblinvdtl.setFldDoublerefoldinvno(request.getFldDoublerefoldinvno());
        tblinvdtl.setFldDoublerefpromoamt(request.getFldDoublerefpromoamt());
        tblinvdtl.setFldExtranumericfield1(request.getFldExtranumericfield1());
        tblinvdtl.setFldExtranumericfield2(request.getFldExtranumericfield2());
        tblinvdtl.setFldExtranumericfield3(request.getFldExtranumericfield3());
        tblinvdtl.setFldExtranumericfield4(request.getFldExtranumericfield4());
        tblinvdtl.setFldExtranumericfield5(request.getFldExtranumericfield5());
        tblinvdtl.setFldExtratextfield1(request.getFldExtratextfield1());
        tblinvdtl.setFldExtratextfield2(request.getFldExtratextfield2());
        tblinvdtl.setFldExtratextfield3(request.getFldExtratextfield3());
        tblinvdtl.setFldExtratextfield4(request.getFldExtratextfield4());
        tblinvdtl.setFldExtratextfield5(request.getFldExtratextfield5());
        tblinvdtl.setFldExtratextfield6(request.getFldExtratextfield6());
        tblinvdtl.setFldExtratextfield7(request.getFldExtratextfield7());
        tblinvdtl.setFldExtratextfield8(request.getFldExtratextfield8());
        tblinvdtl.setFldExtratextfield9(request.getFldExtratextfield9());
        tblinvdtl.setFldIntcolorid(request.getFldIntcolorid());
        tblinvdtl.setFldIntfitid(request.getFldIntfitid());
        tblinvdtl.setFldIntsizeid(request.getFldIntsizeid());
        tblinvdtl.setFldInvno(request.getFldInvno());
        tblinvdtl.setFldItemcode(request.getFldItemcode());
        tblinvdtl.setFldItemdescription(request.getFldItemdescription());
        tblinvdtl.setFldLinedisamt(request.getFldLinedisamt());
        tblinvdtl.setFldLinediscountauthorizedby(request.getFldLinediscountauthorizedby());
        tblinvdtl.setFldLinediscountreasoncode(request.getFldLinediscountreasoncode());
        tblinvdtl.setFldLinedisper(request.getFldLinedisper());
        tblinvdtl.setFldLinedisval(request.getFldLinedisval());
        tblinvdtl.setFldLineno(request.getFldLineno());
        tblinvdtl.setFldLocation(request.getFldLocation());
        tblinvdtl.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblinvdtl.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblinvdtl.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblinvdtl.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblinvdtl.setFldOrgSelling(request.getFldOrgSelling());
        tblinvdtl.setFldOudrivenpromoamt(request.getFldOudrivenpromoamt());
        tblinvdtl.setFldPrice(request.getFldPrice());
        tblinvdtl.setFldPromocode(request.getFldPromocode());
        tblinvdtl.setFldPromodisamt(request.getFldPromodisamt());
        tblinvdtl.setFldPromodisper(request.getFldPromodisper());
        tblinvdtl.setFldPromodisval(request.getFldPromodisval());
        tblinvdtl.setFldPromomode(request.getFldPromomode());
        tblinvdtl.setFldPromotype(request.getFldPromotype());
        tblinvdtl.setFldQty(request.getFldQty());
        tblinvdtl.setFldRefundauthorizedby(request.getFldRefundauthorizedby());
        tblinvdtl.setFldRefundreasoncode(request.getFldRefundreasoncode());
        tblinvdtl.setFldSapUpdated(request.getFldSapUpdated());
        tblinvdtl.setFldSapVendor(request.getFldSapVendor());
        tblinvdtl.setFldScanmode(request.getFldScanmode());
        tblinvdtl.setFldSplittedbilldisamt(request.getFldSplittedbilldisamt());
        tblinvdtl.setFldStockcode(request.getFldStockcode());
        tblinvdtl.setFldTaxamount(request.getFldTaxamount());
        tblinvdtl.setFldTaxclass(request.getFldTaxclass());
        tblinvdtl.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        tblinvdtl.setFldTrxtype(request.getFldTrxtype());
        tblinvdtl.setFldVoid(request.getFldVoid());
        Tblinvdtl save = tblinvdtlRepository.save(tblinvdtl);

        return convert(save);
    }

    @Override
    @Transactional
    public TblinvdtlResponse update(TblinvdtlUpdateRequest request) {

        Tblinvdtl tblinvdtl = tblinvdtlRepository.findById(request.getId()).orElse(null);
        if (tblinvdtl == null) {
            return null;
        }

        tblinvdtl.setFldInvno(request.getFldInvno());
        tblinvdtl.setBlnconfirmed(request.getBlnconfirmed());
        tblinvdtl.setCreatedon(request.getCreatedon() == null ? null : ConvertUtils.convertStrToDate(request.getCreatedon()));
        tblinvdtl.setFldAccessupdate(request.getFldAccessupdate());
        tblinvdtl.setFldAmount(request.getFldAmount());
        tblinvdtl.setFldAvgcost(request.getFldAvgcost());
        tblinvdtl.setFldBarcodereadstatus(request.getFldBarcodereadstatus());
        tblinvdtl.setFldBarcodeused(request.getFldBarcodeused());
        tblinvdtl.setFldBilldisper(request.getFldBilldisper());
        tblinvdtl.setFldBillpromodisper(request.getFldBillpromodisper());
        tblinvdtl.setFldCancel(request.getFldCancel());
        tblinvdtl.setFldCashierid(request.getFldCashierid());
        tblinvdtl.setFldClosesales(request.getFldClosesales());
        tblinvdtl.setFldCostprice(request.getFldCostprice());
        tblinvdtl.setFldDatatransfer(request.getFldDatatransfer());
        tblinvdtl.setFldDate(request.getFldDate() == null ? null : ConvertUtils.convertStrToDate(request.getFldDate()));
        tblinvdtl.setFldDoublerefoldinvno(request.getFldDoublerefoldinvno());
        tblinvdtl.setFldDoublerefpromoamt(request.getFldDoublerefpromoamt());
        tblinvdtl.setFldExtranumericfield1(request.getFldExtranumericfield1());
        tblinvdtl.setFldExtranumericfield2(request.getFldExtranumericfield2());
        tblinvdtl.setFldExtranumericfield3(request.getFldExtranumericfield3());
        tblinvdtl.setFldExtranumericfield4(request.getFldExtranumericfield4());
        tblinvdtl.setFldExtranumericfield5(request.getFldExtranumericfield5());
        tblinvdtl.setFldExtratextfield1(request.getFldExtratextfield1());
        tblinvdtl.setFldExtratextfield2(request.getFldExtratextfield2());
        tblinvdtl.setFldExtratextfield3(request.getFldExtratextfield3());
        tblinvdtl.setFldExtratextfield4(request.getFldExtratextfield4());
        tblinvdtl.setFldExtratextfield5(request.getFldExtratextfield5());
        tblinvdtl.setFldExtratextfield6(request.getFldExtratextfield6());
        tblinvdtl.setFldExtratextfield7(request.getFldExtratextfield7());
        tblinvdtl.setFldExtratextfield8(request.getFldExtratextfield8());
        tblinvdtl.setFldExtratextfield9(request.getFldExtratextfield9());
        tblinvdtl.setFldIntcolorid(request.getFldIntcolorid());
        tblinvdtl.setFldIntfitid(request.getFldIntfitid());
        tblinvdtl.setFldIntsizeid(request.getFldIntsizeid());
        tblinvdtl.setFldInvno(request.getFldInvno());
        tblinvdtl.setFldItemcode(request.getFldItemcode());
        tblinvdtl.setFldItemdescription(request.getFldItemdescription());
        tblinvdtl.setFldLinedisamt(request.getFldLinedisamt());
        tblinvdtl.setFldLinediscountauthorizedby(request.getFldLinediscountauthorizedby());
        tblinvdtl.setFldLinediscountreasoncode(request.getFldLinediscountreasoncode());
        tblinvdtl.setFldLinedisper(request.getFldLinedisper());
        tblinvdtl.setFldLinedisval(request.getFldLinedisval());
        tblinvdtl.setFldLineno(request.getFldLineno());
        tblinvdtl.setFldLocation(request.getFldLocation());
        tblinvdtl.setFldMiddlewarestatus(request.getFldMiddlewarestatus());
        tblinvdtl.setFldMiddlewarestatusBw(request.getFldMiddlewarestatusBw());
        tblinvdtl.setFldMiddlewareuuid(request.getFldMiddlewareuuid());
        tblinvdtl.setFldMiddlewareuuidBw(request.getFldMiddlewareuuidBw());
        tblinvdtl.setFldOrgSelling(request.getFldOrgSelling());
        tblinvdtl.setFldOudrivenpromoamt(request.getFldOudrivenpromoamt());
        tblinvdtl.setFldPrice(request.getFldPrice());
        tblinvdtl.setFldPromocode(request.getFldPromocode());
        tblinvdtl.setFldPromodisamt(request.getFldPromodisamt());
        tblinvdtl.setFldPromodisper(request.getFldPromodisper());
        tblinvdtl.setFldPromodisval(request.getFldPromodisval());
        tblinvdtl.setFldPromomode(request.getFldPromomode());
        tblinvdtl.setFldPromotype(request.getFldPromotype());
        tblinvdtl.setFldQty(request.getFldQty());
        tblinvdtl.setFldRefundauthorizedby(request.getFldRefundauthorizedby());
        tblinvdtl.setFldRefundreasoncode(request.getFldRefundreasoncode());
        tblinvdtl.setFldSapUpdated(request.getFldSapUpdated());
        tblinvdtl.setFldSapVendor(request.getFldSapVendor());
        tblinvdtl.setFldScanmode(request.getFldScanmode());
        tblinvdtl.setFldSplittedbilldisamt(request.getFldSplittedbilldisamt());
        tblinvdtl.setFldStockcode(request.getFldStockcode());
        tblinvdtl.setFldTaxamount(request.getFldTaxamount());
        tblinvdtl.setFldTaxclass(request.getFldTaxclass());
        tblinvdtl.setFldTime(request.getFldTime() == null ? null : ConvertUtils.convertStrToDate(request.getFldTime()));
        tblinvdtl.setFldTrxtype(request.getFldTrxtype());
        tblinvdtl.setFldVoid(request.getFldVoid());
        Tblinvdtl updated = tblinvdtlRepository.save(tblinvdtl);

        return (convert(updated));
    }

    @Override
    public TblinvdtlResponse getById(Long id) {

        return tblinvdtlRepository.findById(id).map(TblinvdtlServiceImpl::convert).orElse(null);
    }

    @Override
    public List<TblinvdtlResponse> getAll() {

        return tblinvdtlRepository.findAll().stream().map(TblinvdtlServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public Integer delete(Long id) {

        Tblinvdtl got = tblinvdtlRepository.findById(id).orElse(null);
        tblinvdtlRepository.save(got);
        return 1;
    }


    @Override
    public List<TblinvdtlResponse> getAllByInvno(String fldInvno) {
        return tblinvdtlRepository.findByFldInvno(fldInvno).stream().map(TblinvdtlServiceImpl::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TblinvdtlResponse saveWholeSaleInv(WholeSaleTblInvdtlRequest request) {
        return null;
    }


//    @Override
//    public List<getMonthlyInvDtlRequestResponse> getMonthlyInv(getMontlyInvRequest request) {
//
//        List<getMonthlyInvDtlRequestResponse> resp = new ArrayList<>();
//
//        String sql = " select  t.fld_InvNo,t.fld_Price,t.fld_CostPrice from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo " +
//                " inner join item_master i on s.item_code=i.item_code and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId " +
//                " and s.branch_id=t.fld_Location and t.fld_Void=0 and p.fld_Cancel=0 where t.fld_Qty>0 ";
//
//        if (request.getFromDate() != null && request.getToDate() != null) {
//            sql = sql + " and t.fld_Date between '"+request.getFromDate()+"' and '"+request.getToDate()+"'  ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getItemCode() != null) {
//            sql = sql + " and t.fld_ItemCode='"+request.getItemCode()+"' ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getSupId() != null) {
//            sql = sql + " and s.supplier_id="+request.getSupId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getCusId() != null) {
//            sql = sql + " and p.fld_PayTypeCode="+request.getCusId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getCatId() != null) {
//            sql = sql + " and i.category_id="+request.getCatId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        sql = sql + " group by t.fld_InvNo,t.fld_Price,t.fld_CostPrice ";
//
//        System.out.println(sql);
//
//        Connection co = null;
//        try {
//            co = JDBC.con();
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//
//                getMonthlyInvDtlRequestResponse typeResponse = new getMonthlyInvDtlRequestResponse();
//                typeResponse.setFld_InvNo(rs.getString("t.fld_InvNo"));
//                typeResponse.setFld_Price(rs.getDouble("t.fld_Price"));
//                typeResponse.setFld_CostPrice(rs.getDouble("t.fld_CostPrice"));
//
//                resp.add(typeResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (co != null) {
//                    co.close();
//                }
//            } catch (Exception ex) {
//            }
//        }
//
//        return resp;
//
//    }

//    @Override
//    public List<getMonthlyInvDtlsResponse> getMonthlyInvDtl(getMontlyInvRequest request) {
//
//        List<getMonthlyInvDtlsResponse> resp = new ArrayList<>();
//
//        List<getMonthlyInvDtlRequestResponse> list = getMonthlyInv(request);
//
//        for (getMonthlyInvDtlRequestResponse response:list){
//
//            List<getMonthlyInvDtlResponse> dtlResponses = tblinvdtlRepository.getMonthlyInvDtlsList(response.getFld_InvNo(),response.getFld_Price(), response.getFld_CostPrice());
//
//            if (!dtlResponses.isEmpty()){
//
//                getMonthlyInvDtlsResponse typeResponse = new getMonthlyInvDtlsResponse();
//
//                typeResponse.setItem_code(dtlResponses.get(0).getITEM_CODE());
//                typeResponse.setBranch_id(dtlResponses.get(0).getBRANCH_ID());
//                typeResponse.setFld_InvNo(dtlResponses.get(0).getFLD_INVNO());
//                typeResponse.setInvDate(dtlResponses.get(0).getINVDATE());
//                typeResponse.setFld_Price(dtlResponses.get(0).getFLD_PRICE());
//                if (dtlResponses.get(0).getFLD_LINEDISPER()!=0){
//                    typeResponse.setFld_CostPrice(dtlResponses.get(0).getFLD_PRICE()*(100-dtlResponses.get(0).getFLD_LINEDISPER())/100);
//                }else{
//                    typeResponse.setFld_CostPrice(dtlResponses.get(0).getFLD_PRICE());
//                }
//                typeResponse.setFld_ItemDescription(dtlResponses.get(0).getFLD_ITEMDESCRIPTION());
//                typeResponse.setFld_PayTypeCode(dtlResponses.get(0).getFLD_PAYTYPECODE());
//                typeResponse.setName(dtlResponses.get(0).getNAME());
//                typeResponse.setTot(dtlResponses.get(0).getTOT());
//                typeResponse.setCostvalue(dtlResponses.get(0).getCOSTVALUE());
//
//                List<getMonthlyInvDtlsListResponse> resList = new ArrayList<>();
//
//                for (getMonthlyInvDtlResponse res : dtlResponses){
//                    getMonthlyInvDtlsListResponse listResponse = new getMonthlyInvDtlsListResponse();
//                    listResponse.setBatch_no(res.getBATCH_NO());
//                    listResponse.setColor_id(res.getCOLOR_ID());
//                    listResponse.setFit_id(res.getFIT_ID());
//                    listResponse.setSize_id(res.getSIZE_ID());
//                    listResponse.setFld_Qty(res.getFLD_QTY());
//
//                    resList.add(listResponse);
//
//                }
//                typeResponse.setResponseList(resList);
//
//                resp.add(typeResponse);
//            }
//
//        }
//
//        return resp;
//
//    }

//    @Override
//    public List<getMonthlyInvDtlRequestResponse> getMonthlyInvReturn(getMontlyInvReturnRequest request) {
//
//        List<getMonthlyInvDtlRequestResponse> resp = new ArrayList<>();
//
//        String sql = " select  t.fld_InvNo,t.fld_Price,t.fld_CostPrice from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo " +
//                " inner join item_master i on s.item_code=i.item_code and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId " +
//                " and s.branch_id=t.fld_Location and t.fld_Void=0 and p.fld_Cancel=0  and i.category_id where t.fld_Qty<0 ";
//
//        if (request.getFromDate() != null && request.getToDate() != null) {
//            sql = sql + " and t.fld_Date between '"+request.getFromDate()+"' and '"+request.getToDate()+"'  ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getItemCode() != null) {
//            sql = sql + " and t.fld_ItemCode='"+request.getItemCode()+"' ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getSupId() != null) {
//            sql = sql + " and s.supplier_id="+request.getSupId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getCusId() != null) {
//            sql = sql + " and p.fld_PayTypeCode="+request.getCusId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getCatId() != null) {
//            sql = sql + " and i.category_id="+request.getCatId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        sql = sql + " group by t.fld_InvNo,t.fld_Price,t.fld_CostPrice ";
//
//        System.out.println(sql);
//
//        Connection co = null;
//        try {
//            co = JDBC.con();
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//
//                getMonthlyInvDtlRequestResponse typeResponse = new getMonthlyInvDtlRequestResponse();
//
//                typeResponse.setFld_InvNo(rs.getString("t.fld_InvNo"));
//                typeResponse.setFld_Price(rs.getDouble("t.fld_Price"));
//                typeResponse.setFld_CostPrice(rs.getDouble("t.fld_CostPrice"));
//
//                resp.add(typeResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (co != null) {
//                    co.close();
//                }
//            } catch (Exception ex) {
//            }
//        }
//
//        return resp;
//    }
//
//
//    @Override
//    public List<getMonthlyInvDtlsResponse> getMonthlyInvDtlReturn(getMontlyInvReturnRequest request) {
//
//        List<getMonthlyInvDtlsResponse> resp = new ArrayList<>();
//
//        List<getMonthlyInvDtlRequestResponse> list = getMonthlyInvReturn(request);
//
//        for (getMonthlyInvDtlRequestResponse response:list){
//
//            List<getMonthlyInvDtlResponse> dtlResponses = tblinvdtlRepository.getMonthlyInvDtlsListReturn(response.getFld_InvNo(),response.getFld_Price(), response.getFld_CostPrice());
//
//            if (!dtlResponses.isEmpty()){
//
//                getMonthlyInvDtlsResponse typeResponse = new getMonthlyInvDtlsResponse();
//
//                typeResponse.setItem_code(dtlResponses.get(0).getITEM_CODE());
//                typeResponse.setBranch_id(dtlResponses.get(0).getBRANCH_ID());
//                typeResponse.setFld_InvNo(dtlResponses.get(0).getFLD_INVNO());
//                typeResponse.setInvDate(dtlResponses.get(0).getINVDATE());
//                typeResponse.setFld_Price(dtlResponses.get(0).getFLD_PRICE());
//                typeResponse.setFld_CostPrice(dtlResponses.get(0).getFLD_COSTPRICE());
//                typeResponse.setFld_ItemDescription(dtlResponses.get(0).getFLD_ITEMDESCRIPTION());
//                typeResponse.setFld_PayTypeCode(dtlResponses.get(0).getFLD_PAYTYPECODE());
//                typeResponse.setName(dtlResponses.get(0).getNAME());
//                typeResponse.setTot(dtlResponses.get(0).getTOT());
//                typeResponse.setCostvalue(dtlResponses.get(0).getCOSTVALUE());
//
//                List<getMonthlyInvDtlsListResponse> resList = new ArrayList<>();
//
//                for (getMonthlyInvDtlResponse res : dtlResponses){
//                    getMonthlyInvDtlsListResponse listResponse = new getMonthlyInvDtlsListResponse();
//                    listResponse.setBatch_no(res.getBATCH_NO());
//                    listResponse.setColor_id(res.getCOLOR_ID());
//                    listResponse.setFit_id(res.getFIT_ID());
//                    listResponse.setSize_id(res.getSIZE_ID());
//                    listResponse.setFld_Qty(res.getFLD_QTY());
//
//                    resList.add(listResponse);
//
//                }
//                typeResponse.setResponseList(resList);
//
//                resp.add(typeResponse);
//            }
//
//        }
//
//        return resp;
//    }
//
//    @Override
//    public List<getMonthlyDtlsByAllResponse> getMonthlyInvDtls(getMontlyInvRequest request) {
//
////        List<getMonthlyDtlsListAllResponse> list = tblinvdtlRepository.getMonthlyInvDtlsList(request.getFromDate(), request.getToDate());
////        return list;
//
//        List<getMonthlyDtlsByAllResponse> resp = new ArrayList<>();
//
//        String sql = " select a.fld_invno,(select name from customer where id = (select fld_paytypecode from tblpaydtl where fld_invno = a.fld_invno and fld_location = a.fld_location group by fld_invno)) as customer, " +
//                " b.item_code,ifnull(sum(case when fld_intsizeid = '0' then fld_qty else null end), '') as xs,ifnull(sum(case when fld_intsizeid = '1' then fld_qty else null end), '') as s, " +
//                " ifnull(sum(case when fld_intsizeid = '2' then fld_qty else null end), '') as m,ifnull(sum(case when fld_intsizeid = '3' then fld_qty else null end), '') as l, " +
//                " ifnull(sum(case when fld_intsizeid = '4' then fld_qty else null end), '') as xl,ifnull(sum(case when fld_intsizeid = '5' then fld_qty else null end), '') as 2xl, " +
//                " ifnull(sum(case when fld_intsizeid = '6' then fld_qty else null end), '') as 3xl,ifnull(sum(case when fld_intsizeid = '7' then fld_qty else null end), '') as 4xl, " +
//                " ifnull(sum(case when fld_intsizeid = '8' then fld_qty else null end), '') as 5xl,ifnull(sum(case when fld_intsizeid = '9' then fld_qty else null end), '') as 6xl, " +
//                " sum(a.fld_qty) as qty,a.fld_price, ((a.fld_Price * (100-a.fld_LineDisPer)/100)/((100+c.fld_taxper)/100)) as cost, " +
//                " SUM(((a.fld_Price * (100-a.fld_LineDisPer)/100)/((100+c.fld_taxper)/100)) * a.fld_Qty) as value  " +
//                " from tblinvdtl a inner join item_master b on b.item_code = a.fld_itemcode inner join tblinvhed c on c.fld_invno = a.fld_invno and c.fld_location = a.fld_location " +
//                " where  a.fld_cancel = 0 and a.fld_void = 0 ";
//
//        if (request.getFromDate() != null && request.getToDate() != null) {
//            sql = sql + " and a.fld_date between '"+request.getFromDate()+"' and '"+request.getToDate()+"'  ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getItemCode() != null) {
//            sql = sql + " and a.fld_ItemCode='"+request.getItemCode()+"' ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getLocationId() != null) {
//            sql = sql + " and a.fld_location ='"+request.getLocationId()+"' ";
//        } else {
//            sql = sql + "  ";
//        }
//
////        if (request.getSupId() != null) {
////            sql = sql + " and s.supplier_id="+request.getSupId()+" ";
////        } else {
////            sql = sql + "  ";
////        }
////
////        if (request.getCusId() != null) {
////            sql = sql + " and p.fld_PayTypeCode="+request.getCusId()+" ";
////        } else {
////            sql = sql + "  ";
////        }
//
//        if (request.getCatId() != null) {
//            sql = sql + " and b.category_id="+request.getCatId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        sql = sql + " group by c.fld_invno, b.item_code, a.fld_price order by c.fld_invno ";
//
//        System.out.println(sql);
//
//        Connection co = null;
//        try {
//            co = JDBC.con();
//            Statement st = co.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//
//                getMonthlyDtlsByAllResponse typeResponse = new getMonthlyDtlsByAllResponse();
//                typeResponse.setFLD_INVNO(rs.getString("a.fld_invno"));
//                typeResponse.setCUSTOMER(rs.getString("customer"));
//                typeResponse.setFLD_ITEMCODE(rs.getString("b.item_code"));
//                typeResponse.setGetXS(rs.getDouble("xs"));
//                typeResponse.setGetS(rs.getDouble("s"));
//                typeResponse.setGetM(rs.getDouble("m"));
//                typeResponse.setGetL(rs.getDouble("l"));
//                typeResponse.setGetXL(rs.getDouble("xl"));
//                typeResponse.setGet2XL(rs.getDouble("2xl"));
//                typeResponse.setGet3XL(rs.getDouble("3xl"));
//                typeResponse.setGet4XL(rs.getDouble("4xl"));
//                typeResponse.setGet5XL(rs.getDouble("5xl"));
//                typeResponse.setGet6XL(rs.getDouble("6xl"));
//                typeResponse.setQTY(rs.getDouble("qty"));
//                typeResponse.setFLD_PRICE(rs.getDouble("a.fld_price"));
//                typeResponse.setCOST(rs.getDouble("cost"));
//                typeResponse.setVALUE(rs.getDouble("value"));
//
//                resp.add(typeResponse);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (co != null) {
//                    co.close();
//                }
//            } catch (Exception ex) {
//            }
//        }
//
//        return resp;
//    }

    @Override
    public List<getMonthlyDtlsByAllResponse> getMonthlyInvDtlsReturn(getMontlyInvReturnRequest request) {

        List<getMonthlyDtlsByAllResponse> resp = new ArrayList<>();

        String sql = " SELECT C.fld_InvNo,(SELECT NAME FROM customer WHERE id = (SELECT fld_paytypecode FROM tblpaydtl " +
                " WHERE fld_invno = C.fld_InvNo AND fld_location = C.fld_Location)) AS customer,A.fld_ItemCode, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_StockCode = A.fld_StockCode AND fld_IntSizeId = '0'), '') AS XS, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '1'), '') AS S, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '2'), '') AS M, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '3'), '') AS L, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '4'), '') AS XL, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '5'), '') AS 2XL, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '6'), '') AS 3XL, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '7'), '') AS 4XL, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_Void = 0 AND fld_IntSizeId = '8'), '') AS 5XL, " +
                " IFNULL((SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price AND fld_IntSizeId = '9'), '') AS 6XL, " +
                " (SELECT SUM(fld_Qty) FROM TBLINVDTL WHERE fld_InvNo = A.fld_InvNo AND fld_Location = A.fld_Location AND " +
                " fld_ItemCode = A.fld_ItemCode AND fld_Price = A.fld_Price) AS qty, " +
                " A.fld_Price,(A.fld_Price * (100-A.fld_LineDisPer)/100) AS cost,  " +
                " SUM((A.fld_Price - (A.fld_LineDisAmt / A.fld_Qty)) * A.fld_Qty) AS value  " +
                " FROM TBLINVDTL A INNER JOIN ITEM_MASTER B ON B.item_code = A.fld_ItemCode " +
                " INNER JOIN TBLINVHED C ON C.fld_InvNo = A.fld_InvNo AND C.fld_Location = A.fld_Location " +
                " WHERE A.fld_cancel = 0 AND A.fld_void = 0 AND A.fld_Qty<0  ";

        if (request.getFromDate() != null && request.getToDate() != null) {
            sql = sql + " and a.fld_date between '"+request.getFromDate()+"' and '"+request.getToDate()+"'  ";
        } else {
            sql = sql + "  ";
        }

        if (request.getItemCode() != null) {
            sql = sql + " and a.fld_ItemCode='"+request.getItemCode()+"' ";
        } else {
            sql = sql + "  ";
        }

//        if (request.getSupId() != null) {
//            sql = sql + " and s.supplier_id="+request.getSupId()+" ";
//        } else {
//            sql = sql + "  ";
//        }
//
//        if (request.getCusId() != null) {
//            sql = sql + " and p.fld_PayTypeCode="+request.getCusId()+" ";
//        } else {
//            sql = sql + "  ";
//        }

        if (request.getCatId() != null) {
            sql = sql + " and B.category_id="+request.getCatId()+" ";
        } else {
            sql = sql + "  ";
        }

        sql = sql + " group by A.fld_Price,A.fld_CostPrice,A.fld_InvNo order by A.fld_InvNo desc ";

        System.out.println(sql);

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                getMonthlyDtlsByAllResponse typeResponse = new getMonthlyDtlsByAllResponse();
                typeResponse.setFLD_INVNO(rs.getString("C.fld_InvNo"));
                typeResponse.setCUSTOMER(rs.getString("customer"));
                typeResponse.setFLD_ITEMCODE(rs.getString("A.fld_ItemCode"));
                typeResponse.setGetXS(rs.getDouble("XS"));
                typeResponse.setGetS(rs.getDouble("S"));
                typeResponse.setGetM(rs.getDouble("M"));
                typeResponse.setGetL(rs.getDouble("L"));
                typeResponse.setGetXL(rs.getDouble("XL"));
                typeResponse.setGet2XL(rs.getDouble("2XL"));
                typeResponse.setGet3XL(rs.getDouble("3XL"));
                typeResponse.setGet4XL(rs.getDouble("4XL"));
                typeResponse.setGet5XL(rs.getDouble("5XL"));
                typeResponse.setGet6XL(rs.getDouble("6XL"));
                typeResponse.setQTY(rs.getDouble("qty"));
                typeResponse.setFLD_PRICE(rs.getDouble("A.fld_Price"));
                typeResponse.setCOST(rs.getDouble("cost"));
                typeResponse.setVALUE(rs.getDouble("value"));

                resp.add(typeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }

        return resp;
    }

    @Override
    public List<getMonthlyDtlsByAllResponse> getMonthlyInvDtls(getMontlyInvRequest request) {

        List<getMonthlyDtlsByAllResponse> resp = new ArrayList<>();

        String sql = " select a.fld_invno, a.fld_date, cust.name as customer, cust.id as custid, b.item_code, ifnull(sum(case when a.fld_intsizeid = '0' then a.fld_qty else null end), '') as xs, " +
                " ifnull(sum(case when a.fld_intsizeid = '1' then a.fld_qty else null end), '') as s, ifnull(sum(case when a.fld_intsizeid = '2' then a.fld_qty else null end), '') as m, " +
                " ifnull(sum(case when a.fld_intsizeid = '3' then a.fld_qty else null end), '') as l, ifnull(sum(case when a.fld_intsizeid = '4' then a.fld_qty else null end), '') as xl, " +
                " ifnull(sum(case when a.fld_intsizeid = '5' then a.fld_qty else null end), '') as 2xl, ifnull(sum(case when a.fld_intsizeid = '6' then a.fld_qty else null end), '') as 3xl, " +
                " ifnull(sum(case when a.fld_intsizeid = '7' then a.fld_qty else null end), '') as 4xl, ifnull(sum(case when a.fld_intsizeid = '8' then a.fld_qty else null end), '') as 5xl, " +
                " ifnull(sum(case when a.fld_intsizeid = '9' then a.fld_qty else null end), '') as 6xl, sum(a.fld_qty) as qty, round(a.fld_price, 2) as fld_price,  " +
                " round(((a.fld_price * (100-a.fld_linedisper)/100)/((100+c.fld_taxper)/100)), 2) as cost, round(sum(((a.fld_price * (100-a.fld_linedisper)/100)/((100+c.fld_taxper)/100)) * a.fld_qty), 2) as value  " +
                " from  tblinvdtl a  inner join  item_master b on b.item_code = a.fld_itemcode inner join  tblinvhed c on c.fld_invno = a.fld_invno and c.fld_location = a.fld_location " +
                " inner join  tblpaydtl pay on pay.fld_invno = a.fld_invno and pay.fld_location = a.fld_location inner join  customer cust on cust.id = pay.fld_paytypecode " +
                " where a.fld_cancel = 0 and a.fld_void = 0 and a.fld_qty>0   ";


        if (request.getLocationId() != null) {
            sql = sql + " and a.fld_location = '"+request.getLocationId()+"' ";
        } else {
            sql = sql + "  ";
        }

        if (request.getItemCode() != null) {
            sql = sql + " and a.fld_itemcode ='"+request.getItemCode()+"' ";
        } else {
            sql = sql + "  ";
        }

        if (request.getFromDate() != null && request.getToDate() != null) {
            sql = sql + " and date(a.fld_date) >=  '"+request.getFromDate()+"' and date(a.fld_date) <= '"+request.getToDate()+"'";
        } else {
            sql = sql + "  ";
        }

        if (request.getCusId() != null) {
            sql = sql + " and cust.id ="+request.getCusId()+" ";
        } else {
            sql = sql + "  ";
        }

        if (request.getCatId() != null) {
            sql = sql + " and b.category_id="+request.getCatId()+" ";
        } else {
            sql = sql + "  ";
        }

        sql = sql + " group by  c.fld_invno, b.item_code, a.fld_price, cust.name order by c.fld_invno ";

        System.out.println(sql);

        Connection co = null;
        try {
            co = JDBC.con();
            Statement st = co.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                getMonthlyDtlsByAllResponse typeResponse = new getMonthlyDtlsByAllResponse();
                typeResponse.setFLD_INVNO(rs.getString("a.fld_invno"));
                typeResponse.setCUSTOMER(rs.getString("customer"));
                typeResponse.setFLD_ITEMCODE(rs.getString("b.item_code"));
                typeResponse.setGetXS(rs.getDouble("xs"));
                typeResponse.setGetS(rs.getDouble("s"));
                typeResponse.setGetM(rs.getDouble("m"));
                typeResponse.setGetL(rs.getDouble("l"));
                typeResponse.setGetXL(rs.getDouble("xl"));
                typeResponse.setGet2XL(rs.getDouble("2xl"));
                typeResponse.setGet3XL(rs.getDouble("3xl"));
                typeResponse.setGet4XL(rs.getDouble("4xl"));
                typeResponse.setGet5XL(rs.getDouble("5xl"));
                typeResponse.setGet6XL(rs.getDouble("6xl"));
                typeResponse.setQTY(rs.getDouble("qty"));
                typeResponse.setFLD_PRICE(rs.getDouble("fld_price"));
                typeResponse.setCOST(rs.getDouble("cost"));
                typeResponse.setVALUE(rs.getDouble("value"));

                resp.add(typeResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (co != null) {
                    co.close();
                }
            } catch (Exception ex) {
            }
        }

        return resp;
    }

}