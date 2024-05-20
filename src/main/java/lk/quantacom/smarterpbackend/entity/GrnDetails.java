package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "GRN_DETAILS")
@Data
public class GrnDetails extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private ItemMaster item;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double qty;

    @Column(name = "ITEM_PRICE", nullable = false)
    private Double itemPrice;

    @Column(name = "ITEM_DISC")
    private Double itemDisc;

    @Column(name = "ITEM_DIC_PRICE")
    private Double itemDicPrice;

    @Column(name = "ITEM_VAT")
    private Double itemVat;

    @Column(name = "ITEM_VAT_PRICE")
    private Double itemVatPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "ITEM_TOTAL", nullable = false)
    private Double itemTotal;

    @Column(name = "GRN_UNIT", length = 25)
    private String grnUnit;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "GRN_CASH_PRICE")
    private Double grnCashPrice;

    @Column(name = "GRN_CREDIT_PRICE")
    private Double grnCreditPrice;

    @Column(name = "BARCODE_NO")
    private String barcodeNo;

    @Column(name = "ITEM_PROFIT_MARGIN")
    private Double itemProfitMargin;

    @Column(name = "ITEM_PROFIT_VALUE")
    private Double itemProfitValue;

    @Column(name = "UNIT_PRICE_RETAIL")
    private Double unitPriceRetail;

    @Column(name = "UNIT_PRICE_WHOLESALE")
    private Double unitPriceWholesale;

    @ManyToOne
    @JoinColumn(name = "GRN_ID", nullable = false)
    private GrnHeader grn;

    @Column(name = "BATCH_NO", nullable = false)
    private String batchNo;

    @Column(name = "LINE_NO")
    private Integer lineNo;

    @Column(name = "COLOR_ID")
    private Long colorId;

    @Column(name = "SIZE_ID")
    private Long sizeId;

    @Column(name = "FIT_ID")
    private Long fitId;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column
    private Long poNumber;
}