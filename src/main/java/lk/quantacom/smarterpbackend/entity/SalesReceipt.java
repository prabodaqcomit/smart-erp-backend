package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "SALES_RECEIPT")
@Data
public class SalesReceipt extends BaseEntity {


    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CHANGES_OVER_PAID", nullable = false)
    private Double changesOverPaid = 0.0;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chequeDate;

    @Column(name = "CHEQUE_NO", length = 45, nullable = false)
    private String chequeNo;

    @Column(name = "CHEQUE_PAYMENT_STATUS", length = 45)
    private String chequePaymentStatus;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column(name = "DEPOSIT_AMOUNT", nullable = false)
    private Double depositAmount;

    @Column(name = "DIS_PRESE", nullable = false)
    private Double disPrese;

    @Column(name = "DUE_BALANCE", nullable = false)
    private Double dueBalance;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "IDSALES_RECEIPT", nullable = false)
    private Integer idsalesReceipt;

    @Column(name = "IN_PAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inPayDate;

    @Column(name = "INVOICE_DISC", nullable = false)
    private Double invoiceDisc;

    @Column(name = "INVOICE_ID", length = 18, nullable = false)
    private String invoiceId;

    @Column(name = "LINE_NO", nullable = false)
    private Integer lineNo;

    @Column(name = "NET_AMOUNT", nullable = false)
    private Double netAmount;

    @Column(name = "PAID_ADVANCED", nullable = false)
    private Double paidAdvanced;

    @Column(name = "PAYMENT_METHOD", length = 45)
    private String paymentMethod;

    @Column(length = 255)
    private String remark;

    @Column(name = "RETURN_AMOUNT", nullable = false)
    private Double returnAmount;

    @Column(name = "SUB_TOTAL", nullable = false)
    private Double subTotal;

    @Column
    private Date time;

}