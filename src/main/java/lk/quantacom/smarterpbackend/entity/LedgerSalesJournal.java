package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_SALES_JOURNAL")
@Data
public class LedgerSalesJournal extends BaseEntity {


    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CASH_AMOUNT", nullable = false)
    private Double cashAmount = 0.0;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(length = 150)
    private String description;

    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @Column(name = "INVOICE_NO", length = 25)
    private String invoiceNo;

    @Column(name = "LEDGER_SALES_JOURNAL_ID", nullable = false)
    private Integer ledgerSalesJournalId;

    @Column(name = "PR_NO", length = 25)
    private String prNo;

    @Column
    private Date time;

}