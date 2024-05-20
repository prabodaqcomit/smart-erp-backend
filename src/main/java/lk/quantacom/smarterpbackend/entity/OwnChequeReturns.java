package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "OWN_CHEQUE_RETURNS")
@Data
public class OwnChequeReturns extends BaseEntity {

    @Column(name = "BANK_CODE", length = 45)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 45)
    private String branchCode;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CHEQUE_ISSUE_NOTE_ID", nullable = false)
    private Integer chequeIssueNoteId;

    @Column(name = "CHEQUE_NO", length = 15)
    private String chequeNo;

    @Column(name = "FRAM_NO", length = 45, nullable = false)
    private String framNo;

    @Column
    private Integer idownChequeReturns;

    @Column
    private String remarks;

    @Column(nullable = false)
    private Double value;

    @Column(name = "RETURN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

}