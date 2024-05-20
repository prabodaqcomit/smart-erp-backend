package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BANK_DEPOSIT_DETAIL")
@Data
public class BankDepositDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //    @Column(name = "DEPOSIT_HEADER_ID", nullable = false)
//    private Long depositHeaderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_DEPOSIT_HEADER_ID")
    private BankDepositHeader bankDepositHeader;

    @Column(name = "DEPOSIT_NUMBER", length = 20, nullable = false)
    private String depositNumber;

    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;

    @Column(name = "UNDEPOSITED_FUND_REFERENCE_ID", nullable = false)
    private Long unDepositedFundReferenceId;

    @Column(name = "UNDEPOSITED_FUND_REFERENCE_LINE_NUMBER", nullable = false)
    private Integer unDepositedFundReferenceLineNumber;

    @Column(name = "TRANSACTION_TYPE_DESCRIPTION", length = 100, nullable = false)
    private String transactionTypeDescription;

    @Column(name = "TRANSACTION_IS_INVOICE")
    private Boolean transactionIsInvoice;

    @Column(name = "TRANSACTION_IS_RECEIPT")
    private Boolean transactionIsReceipt;

    @Column(name = "TRANSACTION_BRANCH_ID", nullable = false)
    private Long transactionBranchId;

    @Column(name = "TRANSACTION_ID", length = 50, nullable = false)
    private String transactionId;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private Double transactionAmount;

    //    @Column(name = "PAYMENT_MODE_ID", nullable = false)
//    private Long paymentModeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYMENT_MODE_ID", referencedColumnName = "paymode_code")
    private Tblpaymode paymentMode;

    //    @Column(name = "CURRENCY_TYPE_ID", nullable = false)
//    private Long currencyTypeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_TYPE_ID")
    private Currency currencyType;

    @Column(name = "CURRENCY_TYPE_EXCHANGE_RATE", nullable = false)
    private Double currencyTypeExchangeRate;

    @Column(name = "PAYMENT_MODE_REMARK", length = 200, nullable = true)
    private String paymentModeRemark;

    @Column(name = "UNDEPOSIT_AMOUNT", nullable = false)
    private Double unDepositAmount;

    @Column(name = "DEPOSIT_AMOUNT", nullable = false)
    private Double depositedAmount;

    @Column(name = "UNDEPOSIT_BALANCE", nullable = false)
    private Double unDepositBalance;

}