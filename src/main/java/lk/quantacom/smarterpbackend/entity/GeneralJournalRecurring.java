package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GENERAL_JOURNAL_RECURRING")
@Data
public class GeneralJournalRecurring extends BaseEntity {

    @Column(name = "RECURRING_NAME", length = 50, nullable = false)
    private String recurringName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "TEMPLATE_ID", nullable = false)
    private Integer templateId;

    @Column(name = "START_DATE_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    @Column(name = "END_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @Column(name = "RECURRING_FREQUENCY", nullable = false)
    private Integer recurringFrequency;

    @Column(name = "RECURRING_FREQUENCY_PO_T", nullable = false)
    private Integer recurringFrequencyPOT;

    @Column(name = "IS_RUN_AT_END_PO_T", length = 1, nullable = false)
    private Integer isRunAtEndPOT = 0;

    @Column(name = "LAST_RUN_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRunDateTime;

    @Column(name = "TOTAL_RUN_COUNT", nullable = false)
    private Integer totalRunCount = 0;

}