package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "BANK_DEPOSIT_HEADER")
@Data
public class BankDepositHeader extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
//    private Long id;

    //@Column(name = "BRANCH_ID", nullable = false)
    //private Long branchId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "DEPOSIT_NUMBER", length = 20, nullable = false)
    private String depositNumber;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(length = 50, nullable = false)
    private String remark;

    @Column(name = "TRANSACTION_DETAIL", length = 250, nullable = false)
    private String transactionDetail;

    //    @Column(name = "FROM_ACCOUNT_ID", nullable = false)
//    private Long fromAccountId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_ACCOUNT_ID")
    private LadgerAccount fromAccount;

    @Column(name = "FROM_ACCOUNT_BEFORE_BALANCE", nullable = false)
    private Double fromAccountBeforeBalance;

    @Column(name = "FROM_ACCOUNT_AFTER_BALANCE", nullable = false)
    private Double fromAccountAfterBalance;

    //@Column(name = "TO_ACCOUNT_ID", nullable = false)
    //private Long toAccountId;
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

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "bankDepositHeader")
    private List<BankDepositDetail> bankDepositDetails;

}