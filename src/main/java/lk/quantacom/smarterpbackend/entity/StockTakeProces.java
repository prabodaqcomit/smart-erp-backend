package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STOCK_TAKE_PROCES")
@Data
@EntityListeners(AuditingEntityListener.class)
public class StockTakeProces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "AVAILAB_QTY")
    private Double availabQty;

    @Column(name = "AVRG_PRICE")
    private Double avrgPrice;

    @Column(name = "BARCODE_NO", length = 20)
    private String barcodeNo;

    @Column(name = "BATCH_NO", length = 20, nullable = false)
    private String batchNo;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column(name = "CASH_DIS_VALUE")
    private Double cashDisValue;

    @Column(name = "COLOR_ID", nullable = false)
    private Long colorId;


    @Column(name = "CREDIT_DIS_VALUE")
    private Double creditDisValue;

    @Column(name = "DAMG_QTY")
    private Double damgQty;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "FIT_ID", nullable = false)
    private Long fitId;


    @Column(name = "ITEM_CODE", length = 100, nullable = false)
    private String itemCode;

    @Column(name = "MATERIAL_WIDTH")
    private Double materialWidth;


    @Column(name = "OB_STOCK")
    private Double obStock;

    @Column(name = "SALES_DISCO_PRESENTAGE")
    private Double salesDiscoPresentage;

    @Column(name = "SALES_PERSON", length = 255)
    private String salesPerson;

    @Column(name = "SIZE_ID", nullable = false)
    private Long sizeId;

    @Column(name = "STK_CASH_PRICE")
    private Double stkCashPrice;

    @Column(name = "STK_CREDIT_PRICE")
    private Double stkCreditPrice;

    @Column(name = "STK_QTY", nullable = false)
    private Double stkQty;

    @Column(name = "STOCK_UNIT_PRICE_RETAIL")
    private Double stockUnitPriceRetail;

    @Column(name = "STOCK_UNIT_PRICE_WHOLESALE")
    private Double stockUnitPriceWholesale;

    @Column(name = "STORES_QTY")
    private Double storesQty;

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long supplierId;

    @Column(name = "TOTAL_QTY")
    private Double totalQty;

    @Column(name = "STOCK_PROCES", nullable = false)
    private Boolean stockProces;

    @Column(name = "PHYSICAL_QTY")
    private Double physicalQty;

    @Column(name = "VARIANCE_QTY")
    private Double varianceQty;

    @Column(name = "PENDING_PROCESS_ID")
    private Integer pendingProcessId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "SIZE_DESC")
    private String sizeDesc;

    @Column(name = "COLOR_DESC")
    private String colorDesc;

    @Column(name = "FIT_DESC")
    private String fitDesc;

    @Column(name = "BRANCH_NAME")
    private String branchName;

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
    private Deleted isDeleted= Deleted.NO;




}