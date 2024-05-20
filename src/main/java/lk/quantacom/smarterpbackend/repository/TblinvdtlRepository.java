package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.getMonthlyDtlsByAllResponse;
import lk.quantacom.smarterpbackend.dto.response.getMonthlyDtlsListAllResponse;
import lk.quantacom.smarterpbackend.dto.response.getMonthlyInvDtlResponse;
import lk.quantacom.smarterpbackend.dto.response.getMonthlyInvDtlsListResponse;
import lk.quantacom.smarterpbackend.entity.Tblinvdtl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface TblinvdtlRepository extends JpaRepository<Tblinvdtl,Long> , JpaSpecificationExecutor {


    List<Tblinvdtl> findByFldInvno(String fldInvno);

    Tblinvdtl findByFldInvnoAndFldLineno(String fldInvno,Integer fldLineno);

    @Query(value = "select fld_InvNo from tblinvdtl where fld_Qty<0 group by fld_InvNo",nativeQuery = true)
    List<String> getInvNoList();

//    @Query(value = " select s.batch_no,s.color_id,s.fit_id,s.size_id,s.item_code,s.branch_id,t.fld_InvNo,cast(t.fld_Date as Date) as invDate,t.fld_Qty, " +
//            " t.fld_Price,t.fld_CostPrice,t.fld_ItemDescription,p.fld_PayTypeCode,c.name,t.fld_LineDisPer, " +
//            " (select sum(i.fld_Qty) from tblinvdtl i where i.fld_Qty>0 and i.fld_ItemCode=s.item_code and t.fld_InvNo=i.fld_InvNo ) as tot, " +
//            " (select sum(i.fld_Qty) from tblinvdtl i where i.fld_Qty>0 and i.fld_ItemCode=s.item_code and t.fld_InvNo=i.fld_InvNo )*fld_CostPrice as costvalue " +
//            " from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo inner join customer c on p.fld_PayTypeCode=c.id " +
//            " and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId and s.branch_id=t.fld_Location and t.fld_Void=0 and p.fld_Cancel=0 " +
//            " where t.fld_Qty>0 and t.fld_InvNo=?1 and t.fld_Price=?2 and t.fld_CostPrice=?3  ",nativeQuery = true)
//    List<getMonthlyInvDtlResponse> getMonthlyInvDtlsList(String invNo, Double mrp, Double cost);

    @Query(value = " select s.batch_no,s.color_id,s.fit_id,s.size_id,s.item_code,s.branch_id,t.fld_InvNo,cast(t.fld_Date as Date) as invDate,t.fld_Qty, " +
            " t.fld_Price,t.fld_CostPrice,t.fld_ItemDescription,p.fld_PayTypeCode,c.name, " +
            " (select sum(i.fld_Qty) from tblinvdtl i where i.fld_Qty<0 and i.fld_ItemCode=s.item_code and t.fld_InvNo=i.fld_InvNo ) as tot, " +
            " (select sum(i.fld_Qty) from tblinvdtl i where i.fld_Qty<0 and i.fld_ItemCode=s.item_code and t.fld_InvNo=i.fld_InvNo )*fld_CostPrice as costvalue " +
            " from stock s join tblinvdtl t on s.item_code=t.fld_ItemCode inner join tblpaydtl p on t.fld_InvNo=p.fld_InvNo inner join customer c on p.fld_PayTypeCode=c.id " +
            " and s.color_id=t.fld_IntColorId and s.size_id=t.fld_IntSizeId and s.fit_id=t.fld_IntFitId and s.branch_id=t.fld_Location and t.fld_Void=0 and p.fld_Cancel=0 " +
            " where t.fld_Qty<0 and t.fld_InvNo=?1 and t.fld_Price=?2 and t.fld_CostPrice=?3  ",nativeQuery = true)
    List<getMonthlyInvDtlResponse> getMonthlyInvDtlsListReturn(String invNo, Double mrp, Double cost);


    @Query(value = " select a.fld_invno,(select name from customer where id = (select fld_paytypecode from tblpaydtl where fld_invno = a.fld_invno and fld_location = a.fld_location)) as customer, " +
            " b.item_code,ifnull(sum(case when fld_intsizeid = '0' then fld_qty else null end), '') as xs,ifnull(sum(case when fld_intsizeid = '1' then fld_qty else null end), '') as s, " +
            " ifnull(sum(case when fld_intsizeid = '2' then fld_qty else null end), '') as m,ifnull(sum(case when fld_intsizeid = '3' then fld_qty else null end), '') as l, " +
            " ifnull(sum(case when fld_intsizeid = '4' then fld_qty else null end), '') as xl,ifnull(sum(case when fld_intsizeid = '5' then fld_qty else null end), '') as 2xl, " +
            " ifnull(sum(case when fld_intsizeid = '6' then fld_qty else null end), '') as 3xl,ifnull(sum(case when fld_intsizeid = '7' then fld_qty else null end), '') as 4xl, " +
            " ifnull(sum(case when fld_intsizeid = '8' then fld_qty else null end), '') as 5xl,ifnull(sum(case when fld_intsizeid = '9' then fld_qty else null end), '') as 6xl, " +
            " sum(a.fld_qty) as qty,a.fld_price,(a.fld_price - (a.fld_linedisamt / a.fld_qty)) as cost,sum((a.fld_price - (a.fld_linedisamt / a.fld_qty)) * a.fld_qty) as value " +
            " from tblinvdtl a inner join item_master b on b.item_code = a.fld_itemcode inner join tblinvhed c on c.fld_invno = a.fld_invno and c.fld_location = a.fld_location " +
            " where  a.fld_cancel = 0 and a.fld_void = 0 and a.fld_location = 1 and a.fld_date between ?1 and ?2 " +
            " group by c.fld_invno, b.item_code, a.fld_price order by c.fld_invno ",nativeQuery = true)
    List<getMonthlyDtlsListAllResponse> getMonthlyInvDtlsList(String dateFrom, String dateTo);






}