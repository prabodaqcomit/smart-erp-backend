package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "tblgiftvoucher")
@Data
public class GiftVoucher extends BaseEntity {

    @Column(name = "BATCH_ID", length = 10)
    private String batchID;

    @Column(name = "FLD_MIDDLEWARE_STATUS")
    private Integer fldMiddlewareStatus;

    @Column(name = "FLD_MIDDLEWARE_UU_ID")
    private String fldMiddlewareUUID;

    @Column(name = "GIFT_VOUCHER_AMOUNT", nullable = false)
    private Double giftVoucherAmount;

    @Column(name = "GIFT_VOUCHER_ARTICLE_NO")
    private String giftVoucherArticleNo;

    @Column(name = "GIFT_VOUCHER_BATCH_NO", length = 20)
    private String giftVoucherBatchNo;

    @Column(name = "GIFT_VOUCHER_CUS_NAME", length = 100)
    private String giftVoucherCusName;

    @Column(name = "GIFT_VOUCHER_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date giftVoucherDate;

    @Column(name = "GIFT_VOUCHER_DATE_SOLD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giftVoucherDateSold;

    @Column(name = "GIFT_VOUCHER_DATE_USED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giftVoucherDateUsed;

    @Column(name = "GIFT_VOUCHER_EXPIRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date giftVoucherExpiry;

    @Column(name = "GIFT_VOUCHER_FLAG", length = 2)
    private String GiftVoucherFlag;

    @Column(name = "GIFT_VOUCHER_INVOICE_NO", length = 10)
    private String giftVoucherInvoiceNo;

    @Column(name = "GIFT_VOUCHER_INVOICE_NO_USED", length = 10)
    private String giftVoucherInvoiceNoUsed;

    @Column(name = "GIFT_VOUCHER_LOCATION", length = 50)
    private String giftVoucherLocation;

    @Column(name = "GIFT_VOUCHER_LOCATION_USED", length = 50)
    private String giftVoucherLocationUsed;

    @Column(name = "GIFT_VOUCHER_NO", length = 20, nullable = false,unique = true)
    private String giftVoucherNo;

    @Column(name = "GIFT_VOUCHER_SAP_UPDATED")
    private Boolean giftVoucherSAPUpdated;

    @Column(name = "GIFT_VOUCHER_SERIAL_NO", length = 50)
    private String giftVoucherSerialNo;

    @Column(name = "SYNC_STATUS")
    private Boolean syncStatus;

}