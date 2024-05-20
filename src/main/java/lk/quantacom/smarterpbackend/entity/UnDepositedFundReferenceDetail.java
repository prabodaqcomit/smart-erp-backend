package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "UNDEPOSITED_FUND_REFERENCE_DETAIL")
@Data
public class UnDepositedFundReferenceDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //@Column(name = "UNDEPOSITED_FUND_REFERENCE_HEADER_ID", nullable = false)
    //private Long unDepositedFundReferenceHeaderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNDEPOSITED_FUND_REFERENCE_HEADER_ID")
    private UnDepositedFundReferenceHeader unDepositedFundReferenceHeader;

    @Column(name = "LINE_NUMBER", nullable = false)
    private Integer lineNumber;

    //    @Column(name = "PAYMENT_MODE_ID", nullable = false)
//    private Long paymentModeId;
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(name = "PAYMENT_MODE_ID", nullable = false)
    @JoinColumn(name = "PAYMENT_MODE_ID", referencedColumnName = "paymode_code", nullable = false)
    private Tblpaymode paymentMode;

    //@Column(name = "CURRENCY_TYPE_ID", nullable = true)
    //private Long currencyTypeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_TYPE_ID", nullable = true)
    private Currency currencyType;

    @Column(name = "CURRENCY_TYPE_EXCHANGE_RATE", nullable = false)
    private Double currencyTypeExchangeRate;

    @Column(name = "PAYMENT_MODE_REMARK", length = 100, nullable = false)
    private String paymentModeRemark;

    @Column(name = "PAYMENT_MODE_PAID_AMOUNT", nullable = false)
    private Double paymentModePaidAmount;

    @Column(name = "PAID_AMOUNT_VALUE", nullable = false)
    private Double paidAmountValue;

    @Column(name = "UNDEPOSITED_BALANCE", nullable = false)
    private Double unDepositedBalance;

}