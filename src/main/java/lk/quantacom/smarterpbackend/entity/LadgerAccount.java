package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lk.quantacom.smarterpbackend.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LADGER_ACCOUNT")
@Data
public class LadgerAccount extends BaseEntity {

    @Column(name = "ACCOUNT_CATEGORY", length = 100)
    private String accountCategory;

    @Column(name = "ACC_TYPE", length = 50)
    private String accType;

    @Column(name = "SUB_ACC_TYPE", length = 50)
    private String subAccType;

    @Column(name = "ACC_NO", length = 50, nullable = false,unique = true)
    private String accNo;

    @Column(name = "ACC_NAME", length = 100, nullable = false,unique = true)
    private String accName;

    @Column(name = "OB_BALANCE", nullable = false)
    private Double obBalance;

    @Column(name = "CURRENT_BALANCE", nullable = false)
    private Double currentBalance;

    @Column(name = "GENERATED_NO", length = 50, nullable = false)
    private String generatedNo;

    @Column(name = "OWN_NO", length = 50, nullable = false)
    private String ownNo;

    @Column(name = "IS_DEFAULT", nullable = false)
    private Boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column(name = "DRCR", length = 50, nullable = false)
    private String drCr;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "STATUS", nullable = false)
    private Status status= Status.ACTIVE;

    @Column(name = "ACC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accDate;
}