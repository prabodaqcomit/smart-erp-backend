package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PRN_TEMPARY")
@Data
public class PrnTempary extends BaseEntity {

    //@Id
    @Column(name = "PRN_TEMPARY_ID", nullable = false)
    private Integer prnTemparyId;

    @Column(name = "PRN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prnDate;

    @Column(name = "SUP_IN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date supInDate;

    @Column(name = "SUP_IN_NO", length = 45)
    private String supInNo;

//    @Column(name = "ITEM_CODE", length = 45, nullable = false)
//    private String itemCode;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double qty;

    @Column(name = "ITEM_PRICE", nullable = false)
    private Double itemPrice;

    @Column(name = "ITEM_DISC", nullable = false)
    private Double itemDisc;

    @Column(name = "ITEM_DIC_PRICE", nullable = false)
    private Double itemDicPrice;

    @Column(name = "ITEM_VAT", nullable = false)
    private Double itemVat;

    @Column(name = "ITEM_VAT_PRICE", nullable = false)
    private Double itemVatPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "ITEM_TOTAL", nullable = false)
    private Double itemTotal;

    @Column(name = "GROSS_AMOUNT", nullable = false)
    private Double grossAmount;

    @Column(name = "TOTAL_DIS", nullable = false)
    private Double totalDis;

    @Column(name = "TOTAL_VAT", nullable = false)
    private Double totalVat;

    @Column(name = "NET_AMOUNT", nullable = false)
    private Double netAmount;

    @Column(name = "PAY_MODE", length = 45)
    private String payMode;

    @Column(name = "PAYING_AMOUNT", nullable = false)
    private Double payingAmount;

    @Column(name = "DUE_AMOUNT", nullable = false)
    private Double dueAmount;

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Integer supplierId;

    @Column(name = "ITEM_NAME", length = 255)
    private String itemName;

    @Lob
    @Column
    private String remarks;

    @Column(length = 255)
    private String image;

    @Column(name = "PRN_UNIT", length = 25)
    private String prnUnit;

    @Column(name = "BATCH_NO", length = 255, nullable = false)
    private String batchNo;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "PRN_CASH_PRICE", nullable = false)
    private Double prnCashPrice;

    @Column(name = "PRN_CREDIT_PRICE", nullable = false)
    private Double prnCreditPrice;

    @Column(name = "ITEM_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date itemTime;

    @Column(name = "PRN_OVERPAID", nullable = false)
    private Double prnOverpaid;

    @Column(name = "PURCHES_ORDER_ID", nullable = false)
    private Long purchesOrderId;

    @Column(name = "BARCODE_NO", length = 255)
    private String barcodeNo;

    @Column(name = "SALES_PERSON", length = 45)
    private String salesPerson;

    @Column(name = "SALES_PERSON_TEL", length = 45)
    private String salesPersonTel;

    @Column(name = "PRN_AGENCY_NAME", length = 255)
    private String prnAgencyName;

//    @Column(name = "BRANCH_ID", length = 18, nullable = false)
//    private String branchId;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column(name = "PROFIT_MARGIN_ITEM", nullable = false)
    private Double profitMarginItem;

    @Column(name = "PROFIT_VALUE_ITEM", nullable = false)
    private Double profitValueItem;

    @Column(name = "PROFIT_VALUE_TOTAL", nullable = false)
    private Double profitValueTotal;

//    @Column(name = "COLOR_ID", nullable = false)
//    private Integer colorId;
//
//    @Column(name = "SIZE_ID", nullable = false)
//    private Integer sizeId;
//
//    @Column(name = "FIT_ID", nullable = false)
//    private Integer fitId;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "SIZE_ID")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "FIT_ID")
    private Fit fit;

    @Column(nullable = false)
    private Double width;

    @Column(name = "IS_PROCESS")
    private Boolean isProcess;

    @Column(name = "UNIT_PRICE_RETAIL", nullable = false)
    private Double unitPriceRetail;

    @Column(name = "UNIT_PRICE_WHOLESALE", nullable = false)
    private Double unitPriceWholesale;

}