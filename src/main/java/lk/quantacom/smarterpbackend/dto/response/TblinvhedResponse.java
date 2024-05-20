package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class TblinvhedResponse {

    private Long id;

    private String batchid;

    private Boolean blnconfirmed;

    private Date createdon;

    private Double fldAccessupdate;

    private Double fldBadDeptAmount;

    private Date fldBadDeptDate;

    private Date fldBadDeptTime;

    private Boolean fldCancel;

    private Double fldCash;

    private String fldCashierid;

    private Double fldCashsale;

    private Double fldChange;

    private Double fldChangebynexuspoints;

    private Double fldCheque;

    private String fldChequeno;

    private String fldClosesales;

    private Double fldCoupon;

    private String fldCpacker;

    private Double fldCreditcard;

    private Double fldCreditcust;

    private Boolean fldDatatransfer;

    private Date fldDate;

    private Double fldDiscount;

    private Boolean fldForigncustomer;

    private Double fldFrcurency;

    private Double fldFrcurencysale;

    private Double fldGiftvoucher;

    private Double fldGrossamount;

    private Boolean fldGvsaleinv;

    private Boolean fldHappyStatus;

    private Integer fldHappyStatus2;

    private String fldInvno;

    private Double fldItemwisedis;

    private Double fldLocalcustomer;

    private String fldLocation;

    private String fldMember;

    private Integer fldMiddlewarestatus;

    private Integer fldMiddlewarestatusBw;

    private String fldMiddlewareuuid;

    private String fldMiddlewareuuidBw;

    private Double fldNetamount;

    private Double fldNetamountwithouttax;

    private Double fldOther;

    private Double fldPromodisc;

    private Boolean fldSapUpdated;

    private Double fldScratchCards;

    private Boolean fldShiftno;

    private Date fldStarttime;

    private String fldStationid;

    private Double fldTaxamount;

    private Date fldTime;

    private String fldTmpcashid;

    private String fldTrxtype;

    private Boolean fldVegaactive;

    private Double fldWebd;

    private String nexusmobile;

    private Boolean syncstatus;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}