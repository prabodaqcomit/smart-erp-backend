package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ACCOUNT_TRANSFER")
@Data
public class AccountTransfer extends BaseEntity {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
//    private Long id;
    @Column(name = "TRANSFER_NUMBER", length = 20, nullable = false)
    private String transferNumber;


    //    @Column(name = "BRANCH_ID", nullable = false)
//    private Long branchId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

//    @Column(name = "FROM_ACCOUNT_ID", nullable = false)
//    private Long fromAccountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_ACCOUNT_ID")
    private LadgerAccount fromAccount;

    @Column(name = "FROM_ACCOUNT_CODE", length = 20, nullable = false)
    private String fromAccountCode;

    @Column(name = "FROM_ACCOUNT_NAME", length = 100, nullable = false)
    private String fromAccountName;

    @Column(name = "FROM_ACCOUNT_BEFORE_BALANCE", nullable = false)
    private Double fromAccountBeforeBalance;

    @Column(name = "FROM_ACCOUNT_AFTER_BALANCE", nullable = false)
    private Double fromAccountAfterBalance;

//    @Column(name = "TO_ACCOUNT_ID", nullable = false)
//    private Long toAccountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_ACCOUNT_ID")
    private LadgerAccount toAccount;

    @Column(name = "TO_ACCOUNT_CODE", length = 20, nullable = false)
    private String toAccountCode;

    @Column(name = "TO_ACCOUNT_NAME", length = 100, nullable = false)
    private String toAccountName;

    @Column(name = "TO_ACCOUNT_BEFORE_BALANCE", nullable = false)
    private Double toAccountBeforeBalance;

    @Column(name = "TO_ACCOUNT_AFTER_BALANCE", nullable = false)
    private Double toAccountAfterBalance;

    @Column(length = 50)
    private String remark;

    @Column(name = "TRANSACTION_DETAIL", length = 250, nullable = false)
    private String transactionDetail;

    @Column(nullable = false)
    private Double amount;
}