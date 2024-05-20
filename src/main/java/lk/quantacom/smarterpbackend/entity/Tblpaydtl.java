package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPAYDTL")
@Data
public class Tblpaydtl {

    @EmbeddedId
    private TblpaydtlPK tblpaydtlPK;

    @Column(name = "FLD_PAYMODE", length = 3)
    private String fldPaymode;

    @Column(name = "FLD_PAYTYPE", length = 50)
    private String fldPaytype;

    @Column(name = "FLD_PAYTYPECODE", length = 250)
    private String fldPaytypecode;

    @Column(name = "FLD_CRDCARDNO", length = 50)
    private String fldCrdcardno;

    @Column(name = "FLD_FCURAMT")
    private Double fldFcuramt;

    @Column(name = "FLD_PAYTYPEAMT")
    private Double fldPaytypeamt;

    @Column(name = "FLD_EXCHNGRATE")
    private Double fldExchngrate;

    @Column(name = "FLD_DATATRANSFER", nullable = false)
    private Boolean fldDatatransfer;

    @Column(name = "FLD_GVDISNO", length = 50)
    private String fldGvdisno;

    @Column(name = "FLD_CREDITNO", length = 50)
    private String fldCreditno;

    @Column(name = "FLD_COMNO", length = 50)
    private String fldComno;

    @Column(name = "FLD_CASHIERID", length = 50, nullable = false)
    private String fldCashierid;

    @Lob
    @Column(name = "FLD_ENCRKEY")
    private String fldEncrkey;

    @Column
    private Boolean blnconfirmed;

    @Column
    private Boolean blnmodechange;

    @Column(name = "FLD_CANCEL")
    private Boolean fldCancel;

    @Column(name = "FLD_ACCESSUPDATE")
    private Double fldAccessupdate;

    @Column(name = "FLD_MIDDLEWAREUUID", length = 100)
    private String fldMiddlewareuuid;

    @Column(name = "FLD_MIDDLEWARESTATUS")
    private Integer fldMiddlewarestatus;

    @Column(name = "FLD_MIDDLEWAREUUID_CREDITCUST", length = 100)
    private String fldMiddlewareuuidCreditcust;

    @Column(name = "FLD_MIDDLEWARESTATUS_CREDITCUST")
    private Integer fldMiddlewarestatusCreditcust;

    @Column(name = "FLD_MIDDLEWAREUUID_BW", length = 100)
    private String fldMiddlewareuuidBw;

    @Column(name = "FLD_MIDDLEWARESTATUS_BW")
    private Integer fldMiddlewarestatusBw;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdon;

    @Column(nullable = true, columnDefinition = "bit default (0)")
    private Boolean isOverrideByReceipt = false;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

}