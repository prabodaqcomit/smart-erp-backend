package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "m_tblpromotion_details")
@Data
public class  PromotionDetails extends BaseEntity {

    @Column(name = "PROMO_DET_CODE", length = 20)
    private String promoDetCode;

    @Column(name = "PROMO_DET_COUPLE")
    private Double promoDetCouple;

    @Column(name = "PROMO_DET_COUPON_REGEX")
    private String promoDetCouponRegex;

    @Column(name = "PROMO_DET_CUSTOMER_GROUP")
    private String promoDetCustomerGroup;

    @Column(name = "PROMO_DET_CUSTOMER_PROFILE")
    private String promoDetCustomerProfile;

    @Column(name = "PROMO_DET_DISC_AMT")
    private Double promoDetDiscAmt;

    @Column(name = "PROMO_DET_DISC_PER")
    private Double promoDetDiscPer;

    @Column(name = "PROMO_DET_END_QTY")
    private Double promoDetEndQty;

    @Column(name = "PROMO_DET_FREE_ISSUE_ITM")
    private String promoDetFreeIssueItm;

    @Column(name = "PROMO_DET_GROUP_CODE")
    private String promoDetGroupCode;

    @Column(name = "PROMO_DET_GROUP_COUNT")
    private Double promoDetGroupCount;

    @Column(name = "PROMO_DET_GROUP_LEVEL")
    private String promoDetGroupLevel;

    @Column(name = "PROMO_DET_ITEM")
    private String promoDetItem;

    @Column(name = "PROMO_DET_MAX_DIS_AMT")
    private Double promoDetMaxDisAmt;

    @Column(name = "PROMO_DET_MAX_QTY")
    private Double promoDetMaxQty;

    @Column(name = "PROMO_DET_POPUP_MESSAGE")
    private String promoDetPopupMessage;

    @Column(name = "PROMO_DET_START_QTY")
    private Double promoDetStartQty;

    @Column(name = "PROMO_DET_SUP_CODE")
    private String promoDetSupCode;

    @Column(name = "PROMO_DET_TICKET_ID")
    private String promoDetTicketId;

    @Column(name = "PROMO_DET_TYPE")
    private Long promoDetType;

    @ManyToOne
    @JoinColumn(name = "PROMO_HEAD_ID", nullable = false)
    private PromotionHeader promoHead;

}