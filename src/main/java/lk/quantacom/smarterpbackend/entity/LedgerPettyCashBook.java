package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_PETTY_CASH_BOOK")
@Data
public class LedgerPettyCashBook extends BaseEntity {


    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(length = 45)
    private String cartage;

    @Column(name = "CASH_RECEIVED", nullable = false)
    private Double cashReceived;

    @Column(name = "CB_FOLLOW", length = 45)
    private String cbFollow;

    @Column(length = 150, nullable = false)
    private String desctription;

    @Column(nullable = false)
    private Double entartainment;

    @Column(nullable = false)
    private Double foods;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "LEDGER_ACC", nullable = false)
    private Double ledgerAcc;


    @Column(name = "LEDGER_PETTY_CASH_BOOK_ID", nullable = false)
    private Integer ledgerPettyCashBookId;

    @Column(name = "LINE_NO", nullable = false)
    private Integer lineNo;

    @Column(name = "PAYEE_NAME", length = 45, nullable = false)
    private String payeeName;

    @Column(name = "PAYING_AMOUNT", nullable = false)
    private Double payingAmount;

    @Column(name = "PETTY_CASH_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pettyCashDate;

    @Column(nullable = false)
    private Double postage;

    @Column(nullable = false)
    private Double stationary;

    @Column(name = "TIME_TAKE", nullable = false)
    private Date timeTake;

    @Column(nullable = false)
    private Double travel;

    @Column(name = "VOUCHER_NO", length = 45)
    private String voucherNo;

}