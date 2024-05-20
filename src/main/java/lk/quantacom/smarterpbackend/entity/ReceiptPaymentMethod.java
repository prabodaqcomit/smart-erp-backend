package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RECEIPT_PAYMENT_METHOD")
@Data
public class ReceiptPaymentMethod extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

//    @Column(name = "RECEIPT_HEADER_ID", nullable = false)
//    private Long receiptHeaderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIPT_HEADER_ID")
    private ReceiptHeader receiptHeader;

    @Column(name = "RECEIPT_NUMBER", length = 20, nullable = false)
    private String receiptNumber;

    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;

    @Column(name = "PAYMENT_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate = new Date();

//    @Column(name = "PAYMENT_METHOD_ID", nullable = false)
//    private Long paymentMethodId = 1L;

    @Column(name = "PAYMENT_METHOD_ID")
    private Long paymentMethodId;

//    @Column(name = "CURRENCY_TYPE_ID", nullable = true)
//    private Long currencyTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_TYPE_ID", nullable = true)
    private Currency currencyType;

//    @Column(name = "CURRENCY_ACCOUNT_CODE", nullable = true)
//    private String currencyAccountCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ACCOUNT_ID", nullable = true)
    private LadgerAccount currencyAccount;

    @Column(name = "CURRENCY_TO_LOCAL_CURRENCY_RATE", nullable = true)
    private Double currencyToLocalCurrencyRate;

    @Column(name = "CHEQUE_DATE", nullable = true)
    private Date chequeDate;

    @Column(name = "CHEQUE_NUMBER", nullable = true)
    private String chequeNumber;

    //    @Column(name = "FROM_BANK_ID", nullable = true)
    //    private Long fromBankId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_BANK_ID", referencedColumnName = "ID", nullable = true)
    private Bank fromBank;

    //@Column(name = "TO_BANK_ID", nullable = true)
    //private Long toBankId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_BANK_ID", referencedColumnName = "ID", nullable = true)
    private Bank toBank;

    //@Column(name = "FROM_BANK_ACCOUNT_ID", nullable = true)
    //private Long fromBankAccountId;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ACCOUNT_ID")
//    @Column(name = "FROM_BANK_ACCOUNT_ID", nullable = true)
//    private LadgerAccount fromBankAccount;
//
//    @Column(name = "FROM_BANK_ACCOUNT_CODE", nullable = true)
//    private String fromBankAccountCode;
//
    @Column(name = "FROM_BANK_ACCOUNT_DESCRIPTION", nullable = true)
    private String fromBankAccountDescription;

    //@Column(name = "TO_BANK_ACCOUNT_ID", nullable = true)
    //private Long toBankAccountId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_BANK_ACCOUNT_ID", referencedColumnName = "ID")
    private LadgerAccount toBankAccount;
//
//    @Column(name = "TO_BANK_ACCOUNT_CODE", nullable = true)
//    private String toBankAccountCode;
//
//    @Column(name = "TO_BANK_ACCOUNT_NAME", nullable = true)
//    private String toBankAccountName;

    //@Column(name = "FROM_WALLET_ID", nullable = true)
    //private Long fromWalletId;
    //@Column(name = "FROM_WALLET_ID", nullable = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_WALLET_ID", referencedColumnName = "ID", nullable = true)
    private OnlineWallet fromWallet;

    //@Column(name = "TO_WALLET_ID", nullable = true)
    //private Long toWalletId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_WALLET_ID", referencedColumnName = "ID", nullable = true)
    private LadgerAccount toWallet;

    @Column(name = "FROM_BANK_CARD_NUMBER", nullable = true)
    private String fromBankCardNumber;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private Double paidAmount;

    @Column(name = "REFERENCE", nullable = true)
    private String reference;
}