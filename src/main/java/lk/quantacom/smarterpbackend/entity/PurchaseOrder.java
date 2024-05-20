package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PURCHASE_ORDER")
@Data
public class PurchaseOrder extends BaseEntity {

    @Column(name = "PO_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date poDate;

    @Column(name = "DELIVERY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column(name = "INHAND_QTY", nullable = false)
    private Double inhandQty;

    @Column(name = "ORDER_QTY", nullable = false)
    private Double orderQty;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column
    private String remarks;

    @Column(name = "ITEM_TOTAL", nullable = false)
    private Double itemTotal;

    @Column
    private Double dics;

    @Column
    private Double vat;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier supplier;

    @Column(name = "PO_UNIT", length = 20, nullable = false)
    private String poUnit;

    @Column(length = 10, nullable = false)
    private String status;

    @Column(name = "AGENCY_NAME", length = 100)
    private String agencyName;

    @Column(name = "IS_PROCESS", nullable = false)
    private Boolean isProcess;

    @Column(name = "idpurches_order", nullable = false)
    private Long poId;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @Column(name = "ITEM_CODE", nullable = false,length = 20)
    private String itemCode;

    @Column(length = 20)
    private String batchNo;

    @Column
    private Long fitId;

    @Column
    private Long sizeId;

    @Column
    private Long colorId;

    @Column
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column
    private Long currencyId;

    @Column
    private Double grnCompletedQty;

    @Column(length = 35)
    private String authorizedBy;

    @Column
    private Boolean authorized;

    @Column
    private Boolean grnComplete;
}