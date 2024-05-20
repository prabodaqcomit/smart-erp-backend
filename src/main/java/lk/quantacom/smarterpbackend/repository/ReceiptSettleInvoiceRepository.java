package lk.quantacom.smarterpbackend.repository;

import lk.quantacom.smarterpbackend.entity.ReceiptSettleInvoice;
import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import lk.quantacom.smarterpbackend.enums.Deleted;
import java.util.Date;
import java.util.List;

public interface ReceiptSettleInvoiceRepository extends JpaRepository<ReceiptSettleInvoice,Long> , JpaSpecificationExecutor {


    List<ReceiptSettleInvoice> findByIsDeleted(Deleted deleted);

    @Query(
            value = "SELECT \n" +
                    "rsi.* \n" +
                    "FROM \n" +
                    "receipt_settle_invoice rsi \n" +
                    "WHERE \n" +
                    "rsi.invoice_location = ?1 \n" +
                    "AND rsi.invoice_number = ?2 \n" +
                    "ORDER BY receipt_id DESC \n" +
                    "LIMIT 1"
            ,nativeQuery = true
    )
    ReceiptSettleInvoice getLastPaidReceiptByInvoiceLocationAndInvoiceNumber(String invoiceLocation, String invoiceNumber);



}