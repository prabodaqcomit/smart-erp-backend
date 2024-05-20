package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RECEIPT_SETTLE_INVOICE")
@Data
public class ReceiptSettleInvoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "RECEIPT_ID", nullable = false)
    private Long receiptId;

    @Column(name = "INVOICE_LOCATION", length = 20, nullable = false)
    private String invoiceLocation;

    @Column(name = "INVOICE_NUMBER", length = 50, nullable = false)
    private String invoiceNumber;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @Column(name = "INVOICE_AMOUNT", nullable = false)
    private Double invoiceAmount;

    @Column(name = "SETTLE_AMOUNT")
    private Double settleAmount;

    @Column(name = "BALANCE_AMOUNT")
    private Double balanceAmount;

    @Column(name = "IS_RETURNED_INVOICE", length = 1, nullable = false)
    private Boolean IsReturnedInvoice = false;

}