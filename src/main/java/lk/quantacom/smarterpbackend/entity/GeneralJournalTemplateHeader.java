package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "GENERAL_JOURNAL_TEMPLATE_HEADER")
@Data
public class GeneralJournalTemplateHeader extends BaseEntity {

    @Column(name = "TEMPLATE_NAME", length = 100, nullable = false)
    private String templateName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(length = 50, nullable = false)
    private String remark;

    @Column(name = "LAST_USED_DATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsedDateTime;

    @Column(nullable = false)
    private Double totalDebit;

    @Column(nullable = false)
    private Double totalCredit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "generalJournalTemplateHeader")
    private List<GeneralJournalTemplateDetail> generalJournalTemplateDetails;
}