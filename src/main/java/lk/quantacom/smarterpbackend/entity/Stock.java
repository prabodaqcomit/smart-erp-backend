package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "STOCK")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Stock implements Serializable {

    @EmbeddedId
    private StockPK stockPK;

    @Column(name = "STK_QTY", nullable = false)
    private Double stkQty;

    @Column(name = "DAMG_QTY")
    private Double damgQty;

    @Column(name = "AVAILAB_QTY")
    private Double availabQty;

    @Column(name = "AVRG_PRICE")
    private Double avrgPrice;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "STK_CASH_PRICE")
    private Double stkCashPrice;

    @Column(name = "STK_CREDIT_PRICE")
    private Double stkCreditPrice;

    @Column(name = "STORES_QTY")
    private Double storesQty;

    @Column(name = "TOTAL_QTY")
    private Double totalQty;

    @Column(name = "BARCODE_NO", length = 20)
    private String barcodeNo;

    @Column(name = "STOCK_UNIT_PRICE_RETAIL")
    private Double stockUnitPriceRetail;

    @Column(name = "STOCK_UNIT_PRICE_WHOLESALE")
    private Double stockUnitPriceWholesale;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier supplier;

    @Column(name = "sales_person")
    private String salesPerson;

    @Column(name = "OB_STOCK")
    private Double obStock;

    @Column(name = "CASH_DIS_VALUE")
    private Double cashDisValue;

    @Column(name = "CREDIT_DIS_VALUE")
    private Double creditDisValue;

    @Column(name = "SALES_DISCO_PRESENTAGE")
    private Double salesDiscoPresentage;

    @Column(name = "MATERIAL_WIDTH")
    private Double materialWidth;

    @CreatedBy
    @Column(name = "CREATED_BY",length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY",length = 50, nullable = false)
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE_TIME", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDateTime;

    @Column(name = "IS_DELETED", nullable = false)
    private Deleted isDeleted=Deleted.NO;

//    @ManyToOne
//    @JoinColumn(name = "ITEM_ID", nullable = false)
//    private ItemMaster item;
//
//    @ManyToOne
//    @JoinColumn(name = "COLOR_ID")
//    private Color color;
//
//    @ManyToOne
//    @JoinColumn(name = "SIZE_ID")
//    private Size size;
//
//    @ManyToOne
//    @JoinColumn(name = "FIT_ID")
//    private Fit fit;
//
//    @ManyToOne
//    @JoinColumn(name = "BRANCH_ID")
//    private BranchNetwork branch;
//
//    @Column(name = "BATCH_NO", length = 20)
//    private String batchNo;



}