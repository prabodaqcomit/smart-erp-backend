package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;
import org.springframework.security.access.method.P;

import java.util.Date;
import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> , JpaSpecificationExecutor {

    @Query(value = "select max(idpurches_order) from purchase_order",nativeQuery = true)
    Long getMaxId();

    List<PurchaseOrder> findByIsDeleted(Deleted deleted);

    List<PurchaseOrder> findByPoIdAndItemCodeAndIsDeleted(Long poId,String item,Deleted deleted);

    List<PurchaseOrder> findByPoIdAndIsDeleted(Long poId,Deleted deleted);

//    @Query(value = "select * from purchase_order a where is_deleted='0' group by idpurches_order",nativeQuery = true)
//    List<PurchaseOrder> getHeaderList();

    @Query(value = " select * from purchase_order where is_deleted=0  and grn_complete=0 and status='APPROVED' ",nativeQuery = true)
    List<PurchaseOrder> getHeaderListForGRN();

//    @Query(value = "select * from purchase_order where is_deleted=0  and grn_complete=0 " +
//            " group by idpurches_order",nativeQuery = true)
//    List<PurchaseOrder> getHeaderListForGRN();

    @Query(value = "select * from purchase_order a where is_deleted=0 and grand_total <= ?1 " +
            "  group by idpurches_order",nativeQuery = true)
    List<PurchaseOrder> getHeaderListAll(Double grandTotal);

    @Query(value = "select * from purchase_order a where is_deleted=0 and grand_total <= ?1 " +
            "and (authorized=1 )  and status='APPROVE' group by idpurches_order",nativeQuery = true)
    List<PurchaseOrder> getHeaderListAuthorized(Double grandTotal);

    @Query(value = "select * from purchase_order a where is_deleted=0 and grand_total <= ?1 " +
            "and (authorized=0 or authorized is null )  and status<> 'APPROVE' group by idpurches_order",nativeQuery = true)
    List<PurchaseOrder> getHeaderListNotAuthorized(Double grandTotal);

    @Modifying
    @Query(value = "update purchase_order set status='APPROVED',authorized=1,authorized_by=?1 " +
            " where idpurches_order=?2 ",nativeQuery = true)
    Integer UpdateByPoId(String authorizedBy,Long PoId);
 }