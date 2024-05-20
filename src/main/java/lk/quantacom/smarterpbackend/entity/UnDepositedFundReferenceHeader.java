package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "UNDEPOSITED_FUND_REFERENCE_HEADER")
@Data
public class UnDepositedFundReferenceHeader extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "TRANSACTION_TYPE_DESCRIPTION", length = 100, nullable = false)
    private String transactionTypeDescription;

    @Column(name = "TRANSACTION_IS_INVOICE")
    private Boolean transactionIsInvoice;

    @Column(name = "TRANSACTION_IS_RECEIPT")
    private Boolean transactionIsReceipt;

    //    @Column(name = "TRANSACTION_BRANCH_ID", nullable = false)
//    private Long transactionBranchId;
    //@JoinColumn(name = "TRANSACTION_BRANCH_ID", nullable = false, columnDefinition = "varchar(20) default(NULL)")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TRANSACTION_BRANCH_ID", nullable = false)
    private BranchNetwork transactionBranch;

    @Column(name = "TRANSACTION_ID", nullable = false)
    private String transactionId;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    private Date transactionDate;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private Double transactionAmount;

    @Column(name = "UN_DEPOSITED_TOTAL_AMOUNT", nullable = false)
    private Double unDepositedTotalAmount;

    @Column(name = "UN_DEPOSITED_TOTAL_BALANCE", nullable = false)
    private Double unDepositedTotalBalance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unDepositedFundReferenceHeader")
    private List<UnDepositedFundReferenceDetail> unDepositedFundReferenceDetails;

}