package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RECEIPT_DETAIL")
@Data
public class ReceiptDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIPT_HEADER_ID")
    private ReceiptHeader receiptHeader;

    @Column(name = "RECEIPT_NUMBER", length = 50, nullable = false)
    private String receiptNumber;

    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;

    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "ACCOUNT_CODE", length = 50)
    private String accountCode;

    @Column(name = "ACCOUNT_NAME", length = 250)
    private String accountName;

    @Column(name = "INVOICE_BRANCH_ID")
    private Long invoiceBranchId;

    @Column(name = "INVOICE_NUMBER", length = 50)
    private String invoiceNumber;

    @Column(name = "INVOICE_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date InvoiceDateTime;

    @Column(name = "INVOICE_AMOUNT")
    private Double invoiceAmount;

    @Column(name = "INVOICE_PENDING_AMOUNT")
    private Double invoicePendingAmount;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private Double paidAmount;

    @Column(name = "WITHHOLDING_TAX_AMOUNT", nullable = false)
    private Double withholdingTaxAmount;

    @Column(name = "STAMP_DUTY_AMOUNT", nullable = false)
    private Double stampDutyAmount;

    @Column(name = "NET_AMOUNT", nullable = false)
    private Double netAmount;

}