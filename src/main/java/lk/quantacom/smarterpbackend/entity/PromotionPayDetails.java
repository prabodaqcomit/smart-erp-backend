package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "m_tblpromotion_pay_details")
@Data
public class PromotionPayDetails extends BaseEntity {

    @Column(name = "DETAIL_CODE", length = 10)
    private String detailCode;

    @Column(name = "PAYMENT_MODE", length = 10)
    private String paymentMode;

    @Column(name = "PAY_DETAIL_CODE", length = 10)
    private String payDetailCode;

    @Column(name = "PAYMENT_MIN")
    private Double paymentMin;

    @Column(name = "PAYMENT_MAX")
    private Double payemntMax;

    @Column(name = "DISC_PER")
    private Double discPer;

    @Column(name = "DISC_AMT")
    private Double discAmt;

    @Column(name = "FREE_ISSUE_ITEM", length = 18)
    private String freeIssueItem;

    @Column(name = "TICKET_ID", length = 50)
    private String ticketId;

    @Column(name = "GROUP_COUNT")
    private Double groupCount;

    @Column(name = "MAX_DISC_AMT")
    private Double maxDiscAmt;

    @Column(name = "POPUP_MSG", length = 500)
    private String popupMsg;

    @Column(name = "DET_TYPE")
    private Boolean detType;

    @ManyToOne
    @JoinColumn(name = "PROMO_HEAD_ID", nullable = false)
    private PromotionHeader promoHead;

}