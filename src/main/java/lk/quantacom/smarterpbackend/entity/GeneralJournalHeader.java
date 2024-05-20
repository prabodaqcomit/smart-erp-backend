package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GENERAL_JOURNAL_HEADER")
@Data
public class GeneralJournalHeader extends BaseEntity {

    @Column(name = "JOURNAL_NUMBER", nullable = false, unique = true)
    private String journalNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(length = 50, nullable = false)
    private String remark;

    @Column(name = "TRANSACTION_DETAIL", length = 250, nullable = false)
    private String transactionDetail;

    @Column(nullable = false)
    private Double totalDebit;

    @Column(nullable = false)
    private Double totalCredit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "generalJournalHeader")
    private List<GeneralJournalDetail> generalJournalDetails;

}