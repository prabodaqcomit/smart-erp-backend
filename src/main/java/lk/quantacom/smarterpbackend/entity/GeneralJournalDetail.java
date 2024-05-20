package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GENERAL_JOURNAL_DETAIL")
@Data
public class GeneralJournalDetail extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "General_Journal_Header_ID")
    private GeneralJournalHeader generalJournalHeader;

    @Column(name = "JOURNAL_NUMBER", nullable = false)
    private String journalNumber;

    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;

    //    @Column(name = "ACCOUNT_CODE", length = 50, nullable = false)
    //    private String accountCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LEDGER_ACCOUNT_ID")
    private LadgerAccount ladgerAccount;

    @Column
    private Double debit;

    @Column
    private Double credit;

}