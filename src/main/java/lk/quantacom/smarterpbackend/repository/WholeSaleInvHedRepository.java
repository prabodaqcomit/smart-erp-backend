package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.getAllBincardStockBycodeResponse;
import lk.quantacom.smarterpbackend.dto.response.getTblInvDtlResponse;
import lk.quantacom.smarterpbackend.dto.response.getWholeSaleInvDtlResponse;
import lk.quantacom.smarterpbackend.entity.WholeSaleInvHed;
import lk.quantacom.smarterpbackend.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

public interface WholeSaleInvHedRepository extends JpaRepository<WholeSaleInvHed,Integer> , JpaSpecificationExecutor {


    List<WholeSaleInvHed> findByIsDeletedOrderByInvnoDesc(Deleted deleted);

    WholeSaleInvHed findByInvnoAndIsDeleted(Integer invno,Deleted deleted);

    @Query(value = " SELECT h.fld_InvNo,Date(h.fld_Date) as date,(select branch_name from branch_network where id = h.fld_Location) as location, " +
            " (SELECT NAME FROM customer WHERE id = h.fld_LocalCustomer) as customer,h.fld_Cancel,h.fld_GrossAmount,h.fld_Location " +
            " FROM tblinvhed h order by h.fld_InvNo Desc ",nativeQuery = true)
    List<getWholeSaleInvDtlResponse> getWholeSaleInvDtl();


    @Query(value = " select h.fld_InvNo,d.fld_ItemCode,d.fld_Location,d.fld_IntColorId,d.fld_IntSizeId,d.fld_IntFitId,d.fld_Qty,d.fld_StockCode,d.fld_LineNo " +
            " from tblinvhed h inner join tblinvdtl d on h.fld_InvNo=d.fld_InvNo where h.fld_InvNo=?1 ",nativeQuery = true)
    List<getTblInvDtlResponse> getTblInvDtl(String invNo);

}