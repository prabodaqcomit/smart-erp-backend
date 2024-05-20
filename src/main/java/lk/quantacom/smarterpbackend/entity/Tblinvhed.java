package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLINVHED")
@Data
public class Tblinvhed {

    @Column(length = 50)
    private String batchid;

    @Column(nullable = false)
    private Boolean blnconfirmed;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    @Column(name = "FLD_ACCESSUPDATE")
    private Double fldAccessupdate;

    @Column(name = "FLD_BAD_DEPT_AMOUNT")
    private Double fldBadDeptAmount;

    @Column(name = "FLD_BAD_DEPT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldBadDeptDate;

    @Column(name = "FLD_BAD_DEPT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldBadDeptTime;

    @Column(name = "FLD_CANCEL")
    private Boolean fldCancel;

    @Column(name = "FLD_CASH")
    private Double fldCash;

    @Column(name = "FLD_CASHIERID", length = 50)
    private String fldCashierid;

    @Column(name = "FLD_CASHSALE")
    private Double fldCashsale;

    @Column(name = "FLD_CHANGE")
    private Double fldChange;

    @Column(name = "FLD_CHANGEBYNEXUSPOINTS")
    private Double fldChangebynexuspoints;

    @Column(name = "FLD_CHEQUE")
    private Double fldCheque;

    @Column(name = "FLD_CHEQUENO", length = 200)
    private String fldChequeno;

    @Column(name = "FLD_CLOSESALES", length = 1)
    private String fldClosesales;

    @Column(name = "FLD_COUPON")
    private Double fldCoupon;

    @Column(name = "FLD_CPACKER", length = 2)
    private String fldCpacker;

    @Column(name = "FLD_CREDITCARD")
    private Double fldCreditcard;

    @Column(name = "FLD_CREDITCUST")
    private Double fldCreditcust;

    @Column(name = "FLD_DATATRANSFER", nullable = false)
    private Boolean fldDatatransfer;

    @Column(name = "FLD_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldDate;

    @Column(name = "FLD_DISCOUNT")
    private Double fldDiscount;

    @Column(name = "FLD_FORIGNCUSTOMER", nullable = false)
    private Boolean fldForigncustomer;

    @Column(name = "FLD_FRCURENCY")
    private Double fldFrcurency;

    @Column(name = "FLD_FRCURENCYSALE")
    private Double fldFrcurencysale;

    @Column(name = "FLD_GIFTVOUCHER")
    private Double fldGiftvoucher;

    @Column(name = "FLD_GROSSAMOUNT")
    private Double fldGrossamount;

    @Column(name = "FLD_GVSALEINV", nullable = false)
    private Boolean fldGvsaleinv;

    @Column(name = "FLD_HAPPY_STATUS")
    private Boolean fldHappyStatus;

    @Column(name = "FLD_HAPPY_STATUS2")
    private Integer fldHappyStatus2;

    @Id
    @Column(name = "FLD_INVNO", length = 50, nullable = false)
    private String fldInvno;

    @Column(name = "FLD_ITEMWISEDIS")
    private Double fldItemwisedis;

    @Column(name = "FLD_LOCALCUSTOMER")
    private Double fldLocalcustomer;

    @Column(name = "FLD_LOCATION", length = 18, nullable = false)
    private String fldLocation;

    @Column(name = "FLD_MEMBER", length = 19)
    private String fldMember;

    @Column(name = "FLD_MIDDLEWARESTATUS")
    private Integer fldMiddlewarestatus;

    @Column(name = "FLD_MIDDLEWARESTATUS_BW")
    private Integer fldMiddlewarestatusBw;

    @Column(name = "FLD_MIDDLEWAREUUID", length = 100)
    private String fldMiddlewareuuid;

    @Column(name = "FLD_MIDDLEWAREUUID_BW", length = 100)
    private String fldMiddlewareuuidBw;

    @Column(name = "FLD_NETAMOUNT")
    private Double fldNetamount;

    @Column(name = "FLD_NETAMOUNTWITHOUTTAX")
    private Double fldNetamountwithouttax;

    @Column(name = "FLD_OTHER")
    private Double fldOther;

    @Column(name = "FLD_PROMODISC")
    private Double fldPromodisc;

    @Column(name = "FLD_SAP_UPDATED", nullable = false)
    private Boolean fldSapUpdated;

    @Column(name = "FLD_SCRATCH_CARDS")
    private Double fldScratchCards;

    @Column(name = "FLD_SHIFTNO")
    private Boolean fldShiftno;

    @Column(name = "FLD_STARTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldStarttime;

    @Column(name = "FLD_STATIONID", length = 5)
    private String fldStationid;

    @Column(name = "FLD_TAXAMOUNT")
    private Double fldTaxamount;

    @Column(name = "FLD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldTime;

    @Column(name = "FLD_TMPCASHID", length = 50)
    private String fldTmpcashid;

    @Column(name = "FLD_TRXTYPE", length = 10)
    private String fldTrxtype;

    @Column(name = "FLD_VEGAACTIVE", nullable = false)
    private Boolean fldVegaactive;

    @Column(name = "FLD_WEBD")
    private Double fldWebd;

    @Column(length = 50)
    private String nexusmobile;

    @Column(nullable = false)
    private Boolean syncstatus;

    @Column(name = "FLD_TAXPER")
    private Double fldTaxPer;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

}