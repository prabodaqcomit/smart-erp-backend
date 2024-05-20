package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.dto.response.GLPayMethodDetailsResponse;
import lk.quantacom.smarterpbackend.dto.response.supplierPaymentInfoResponse;
import lk.quantacom.smarterpbackend.entity.GrnPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.*;

import java.util.Date;
import java.util.List;

public interface GrnPaymentsRepository extends JpaRepository<GrnPayments,Long> , JpaSpecificationExecutor {

    List<GrnPayments> findByIsDeleted(Deleted deleted);

    List<GrnPayments> findByPayModeAndIsDeleted(String payMode,Deleted deleted);

    @Query(value = " select s.name,s.manufacture,h.id as headerId,s.id as supId,p.total_vat,p.total_profit_value,p.total_dis,p.pay_mode,p.paid_amount,p.net_profit_value,p.net_amount,p.gross_amount, " +
            " p.grn_overpaid,p.due_amount,p.created_date_time,p.modified_date_time,h.sup_in_no,h.branch_id from grn_header h inner join grn_payments p on h.id=p.grn_id  " +
            " inner join supplier s on s.id=h.supplier_id ",nativeQuery = true)
    List<supplierPaymentInfoResponse> getAlSup();

    @Query(value = " select s.name,s.manufacture,h.id as headerId,s.id as supId,p.total_vat,p.total_profit_value,p.total_dis,p.pay_mode,p.paid_amount,p.net_profit_value,p.net_amount,p.gross_amount, " +
            " p.grn_overpaid,p.due_amount,p.created_date_time,p.modified_date_time,h.sup_in_no,h.branch_id  from grn_header h inner join grn_payments p on h.id=p.grn_id  " +
            " inner join supplier s on s.id=h.supplier_id  where h.supplier_id=?1 ",nativeQuery = true)
    List<supplierPaymentInfoResponse> getAlBySup(Integer suppId);


 }
