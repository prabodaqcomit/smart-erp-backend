package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.dto.response.PromotionDetailsResponse;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "m_tblpromotion_header")
@Data
public class PromotionHeader extends BaseEntity {

    @Column(name = "PROMO_HED_DESC", nullable = false)
    private String promoHedDesc;

    @Column(name = "PROMO_HED_ACTIVE")
    private Boolean promoHedActive;

    @Column(name = "PROMO_HED_START_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promoHedStartDateTime;

    @Column(name = "PROMO_HED_END_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date promoHedEndDateTime;

    @Column(name = "PROMO_HED_DAYS_OF_WEEK")
    private String promoHedDaysOfWeek;

    @Column(name = "PROMO_HED_CHECK_BILL_NET_VALUE")
    private Boolean promoHedCheckBillNetValue;

    @Column(name = "PROMO_HED_CHECK_CUSTOMER")
    private Boolean promoHedCheckCustomer;

    @Column(name = "PROMO_HED_CHECK_CUSTOMER_PROFILE")
    private Boolean promoHedCheckCustomerProfile;

    @Column(name = "PROMO_HED_CHECK_PAYMENT_MODE")
    private Boolean promoHedCheckPaymentMode;

    @Column(name = "PROMO_HED_CHECK_COUPON")
    private Boolean promoHedCheckCoupon;

    @Column(name = "PROMO_HED_CHECK_NO_OF_BILLS_PER_CUSTOMER")
    private Boolean promoHedCheckNoOfBillsPerCustomer;

    @Column(name = "PROMO_HED_CHECK_SUPPLIER")
    private Boolean promoHedCheckSupplier;

    @Column(name = "PROMO_HED_CHECK_GROUP")
    private Boolean promoHedCheckGroup;

    @Column(name = "PROMO_HED_CHECK_ITEM")
    private Boolean promoHedCheckItem;

    @Column(name = "PROMO_HED_CHECK_VALID_ITEM")
    private Boolean ptomoHedCheckValidItem;

    @Column(name = "PROMO_HED_BILL_VAL_START")
    private Double promoHedBillValStart;

    @Column(name = "PROMO_HED_BILL_VAL_END")
    private Double promoHedBillValEnd;

    @Column(name = "PROMO_HED_LOCATION_ID")
    private Long promoHedLocationId;

    @Column(name = "PROMO_HED_CODE",length = 20)
    private String promoHedCode;

    @OneToMany(mappedBy = "promoHead")
    private List<PromotionDetails> promotionDetails;

    @OneToMany(mappedBy = "promoHead")
    private List<PromotionPayDetails> promotionPayDetails;

}