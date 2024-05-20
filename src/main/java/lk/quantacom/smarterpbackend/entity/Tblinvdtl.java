package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLINVDTL")
@Data
public class Tblinvdtl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLD_INVNO", length = 50, nullable = false)
    private String fldInvno;

    @Column(nullable = false)
    private Boolean blnconfirmed;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    @Column(name = "FLD_ACCESSUPDATE")
    private Double fldAccessupdate;

    @Column(name = "FLD_AMOUNT")
    private Double fldAmount;

    @Column(name = "FLD_AVGCOST")
    private Double fldAvgcost;

    @Column(name = "FLD_BARCODEREADSTATUS", length = 5)
    private String fldBarcodereadstatus;

    @Column(name = "FLD_BARCODEUSED", length = 50)
    private String fldBarcodeused;

    @Column(name = "FLD_BILLDISPER")
    private Double fldBilldisper;

    @Column(name = "FLD_BILLPROMODISPER")
    private Double fldBillpromodisper;

    @Column(name = "FLD_CANCEL", nullable = false)
    private Boolean fldCancel;

    @Column(name = "FLD_CASHIERID", length = 50)
    private String fldCashierid;

    @Column(name = "FLD_CLOSESALES", length = 1)
    private String fldClosesales;

    @Column(name = "FLD_COSTPRICE")
    private Double fldCostprice;

    @Column(name = "FLD_DATATRANSFER", nullable = false)
    private Boolean fldDatatransfer;

    @Column(name = "FLD_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldDate;

    @Column(name = "FLD_DOUBLEREFOLDINVNO", length = 50)
    private String fldDoublerefoldinvno;

    @Column(name = "FLD_DOUBLEREFPROMOAMT")
    private Double fldDoublerefpromoamt;

    @Column(name = "FLD_EXTRANUMERICFIELD1")
    private Double fldExtranumericfield1;

    @Column(name = "FLD_EXTRANUMERICFIELD2")
    private Double fldExtranumericfield2;

    @Column(name = "FLD_EXTRANUMERICFIELD3")
    private Double fldExtranumericfield3;

    @Column(name = "FLD_EXTRANUMERICFIELD4")
    private Double fldExtranumericfield4;

    @Column(name = "FLD_EXTRANUMERICFIELD5")
    private Double fldExtranumericfield5;

    @Column(name = "FLD_EXTRATEXTFIELD1", length = 500)
    private String fldExtratextfield1;

    @Column(name = "FLD_EXTRATEXTFIELD2", length = 500)
    private String fldExtratextfield2;

    @Column(name = "FLD_EXTRATEXTFIELD3", length = 500)
    private String fldExtratextfield3;

    @Column(name = "FLD_EXTRATEXTFIELD4", length = 500)
    private String fldExtratextfield4;

    @Column(name = "FLD_EXTRATEXTFIELD5", length = 500)
    private String fldExtratextfield5;

    @Column(name = "FLD_EXTRATEXTFIELD6", length = 500)
    private String fldExtratextfield6;

    @Column(name = "FLD_EXTRATEXTFIELD7", length = 500)
    private String fldExtratextfield7;

    @Column(name = "FLD_EXTRATEXTFIELD8", length = 500)
    private String fldExtratextfield8;

    @Column(name = "FLD_EXTRATEXTFIELD9", length = 500)
    private String fldExtratextfield9;

    @Column(name = "FLD_INTCOLORID")
    private Integer fldIntcolorid;

    @Column(name = "FLD_INTFITID")
    private Integer fldIntfitid;

    @Column(name = "FLD_INTSIZEID")
    private Integer fldIntsizeid;

    @Column(name = "FLD_ITEMCODE", length = 45)
    private String fldItemcode;

    @Column(name = "FLD_ITEMDESCRIPTION", length = 200)
    private String fldItemdescription;

    @Column(name = "FLD_LINEDISAMT")
    private Double fldLinedisamt;

    @Column(name = "FLD_LINEDISCOUNTAUTHORIZEDBY", length = 50)
    private String fldLinediscountauthorizedby;

    @Column(name = "FLD_LINEDISCOUNTREASONCODE")
    private Integer fldLinediscountreasoncode;

    @Column(name = "FLD_LINEDISPER")
    private Double fldLinedisper;

    @Column(name = "FLD_LINEDISVAL")
    private Double fldLinedisval;

    @Column(name = "FLD_LINENO", nullable = false)
    private Integer fldLineno;

    @Column(name = "FLD_LOCATION", length = 18, nullable = false)
    private String fldLocation;

    @Column(name = "FLD_MIDDLEWARESTATUS", nullable = false)
    private Integer fldMiddlewarestatus;

    @Column(name = "FLD_MIDDLEWARESTATUS_BW", nullable = false)
    private Integer fldMiddlewarestatusBw;

    @Column(name = "FLD_MIDDLEWAREUUID", length = 100)
    private String fldMiddlewareuuid;

    @Column(name = "FLD_MIDDLEWAREUUID_BW", length = 100)
    private String fldMiddlewareuuidBw;

    @Column(name = "FLD_ORG_SELLING")
    private Double fldOrgSelling;

    @Column(name = "FLD_OUDRIVENPROMOAMT")
    private Double fldOudrivenpromoamt;

    @Column(name = "FLD_PRICE")
    private Double fldPrice;

    @Column(name = "FLD_PROMOCODE", length = 50)
    private String fldPromocode;

    @Column(name = "FLD_PROMODISAMT")
    private Double fldPromodisamt;

    @Column(name = "FLD_PROMODISPER")
    private Double fldPromodisper;

    @Column(name = "FLD_PROMODISVAL")
    private Double fldPromodisval;

    @Column(name = "FLD_PROMOMODE", length = 50)
    private String fldPromomode;

    @Column(name = "FLD_PROMOTYPE", length = 50)
    private String fldPromotype;

    @Column(name = "FLD_QTY")
    private Double fldQty;

    @Column(name = "FLD_REFUNDAUTHORIZEDBY", length = 50)
    private String fldRefundauthorizedby;

    @Column(name = "FLD_REFUNDREASONCODE")
    private Integer fldRefundreasoncode;

    @Column(name = "FLD_SAP_UPDATED", nullable = false)
    private Boolean fldSapUpdated;

    @Column(name = "FLD_SAP_VENDOR", length = 18)
    private String fldSapVendor;

    @Column(name = "FLD_SCANMODE", length = 5)
    private String fldScanmode;

    @Column(name = "FLD_SPLITTEDBILLDISAMT")
    private Double fldSplittedbilldisamt;

    @Column(name = "FLD_STOCKCODE", length = 50)
    private String fldStockcode;

    @Column(name = "FLD_TAXAMOUNT")
    private Double fldTaxamount;

    @Column(name = "FLD_TAXCLASS", length = 5)
    private String fldTaxclass;

    @Column(name = "FLD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldTime;

    @Column(name = "FLD_TRXTYPE", length = 10)
    private String fldTrxtype;

    @Column(name = "FLD_VOID", nullable = false)
    private Boolean fldVoid;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;
}