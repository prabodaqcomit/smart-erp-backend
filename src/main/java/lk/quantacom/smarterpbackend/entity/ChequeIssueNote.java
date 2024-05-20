package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CHEQUE_ISSUE_NOTE")
@Data
public class ChequeIssueNote extends BaseEntity {

    @Column(name = "ACC_NAME", length = 45)
    private String accName;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "BANK_ACCOUNT_ID", nullable = false)
    private Integer bankAccountId;

    @Column(name = "BANK_CODE", length = 45)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 45)
    private String branchCode;

    @Column(name = "FRAM_NO", length = 18, nullable = false)
    private String framNo;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chequeDate;

    @Column(name = "CHEQUE_NO", length = 45)
    private String chequeNo;

    @Column(name = "FRAM_DOC_NO")
    private Integer framDocNo;

    @Column(name = "ISSUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Column(name = "PAYEE_NAME", length = 45)
    private String payeeName;

    @Column(length = 45)
    private String status;

    @Column(name = "STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;

    @Column(name = "UPDATE_WINDOW", length = 45)
    private String updateWindow;

}