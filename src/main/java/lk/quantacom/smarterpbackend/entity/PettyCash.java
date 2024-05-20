package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PETTY_CASH")
@Data
public class PettyCash extends BaseEntity {


    @Column(name = "AMOUNT_RECEIVED", nullable = false)
    private Double amountReceived;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(length = 45, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double entertainment;

    @Column(nullable = false)
    private Double foods;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "IDPETTY_CASH", nullable = false)
    private Integer idpettyCash;

    @Column(name = "NO_SEMI_GRAND_TOTAL", nullable = false)
    private Double noSemiGrandTotal;

    @Column(name = "NO_SEMI_PAYING_AMOUNT", nullable = false)
    private Double noSemiPayingAmount;

    @Column(name = "OTHER_EX", nullable = false)
    private Double otherEx;

    @Column(name = "PAYEE_NAME", length = 45, nullable = false)
    private String payeeName;

    @Column(name = "PAYING_AMOUNT", nullable = false)
    private Double payingAmount;

    @Column(name = "PETTY_CASH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pettyCashDate;

    @Column(nullable = false)
    private Double postage;

    @Column(length = 45)
    private String reference;

    @Column(nullable = false)
    private Double stationary;

    @Column(name = "TIME_TAKE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeTake;

    @Column(nullable = false)
    private Double travel;

    @Column(name = "VOUCHER_NO", length = 45)
    private String voucherNo;

}