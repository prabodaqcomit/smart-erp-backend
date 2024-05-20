package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_CASHBOOK_NOTES")
@Data
public class LedgerCashbookNotes extends BaseEntity {

    @Column(nullable = false)
    private Double amount = 0.0;

    @Column(name = "AMOUNT_WORD", length = 65)
    private String amountWord;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "LEDGER_CASHBOOK_NOTES_ID", nullable = false)
    private Integer ledgerCashbookNotesId;

    @Column(name = "LEDGER_ID_CREDIT")
    private Integer ledgerIdCredit;

    @Column(name = "LEDGER_ID_DEBIT")
    private Integer ledgerIdDebit;

    @Column(name = "PAYEE_NAME", length = 45)
    private String payeeName;

    @Column(length = 85)
    private String reason;

    @Column(length = 85)
    private String remarks;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

}