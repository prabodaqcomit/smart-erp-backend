package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_ASSET_REG")
@Data
public class LedgerAssetReg extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "ASSET_CODE", length = 10, nullable = false)
    private String assetCode;

    @Column(name = "ASSET_NAME", length = 100, nullable = false)
    private String assetName;

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(name = "ASSERT_TYPE", nullable = false)
    private String assertType;

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long supplierId;

    @Column(nullable = false)
    private String manufacture;

    @Column(nullable = false, length = 20)
    private String brand;

    @Column(name = "SERIAL_NO", length = 20 ,nullable = false)
    private String serialNo;

    @Column(name = "MODEL_NO", length = 20)
    private String modelNo;

    @Column(name = "BARCODE_NO", length = 20)
    private String barcodeNo;

    @Column(name = "ITEM_CONDITION_USE")
    private String itemConditionUse;

    @Column(name = "PURCHASE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "PO_NO", length = 10, nullable = false)
    private String poNo;

    @Column(name = "PURCHASE_VALUE", nullable = false)
    private Double purchaseValue;

    @Column(name = "INVOICE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @Column(name = "CURRENT_MARKET_VALUE", nullable = false)
    private Double currentMarketValue;

    @Column(nullable = false)
    private Double depreciation;

    @Column(nullable = false)
    private String issues;

    @Column(name = "DEPARTMENT_ID")
    private Long departmentId;

    @Column(name = "ASSIGN_PERSON", length = 50)
    private String assignPerson;

    @Column(name = "SERVICE_TIME_PERIOD")
    private String serviceTimePeriod;

    @Column(name = "SERVICE_TIME_PERIOD_TYPE")
    private String serviceTimePeriodType;

    @Column(name = "NEXT_SERVICE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextServiceDate;

    @Column(name = "LIFE_TIME_YEARS", nullable = false)
    private String lifeTimeYears;

    @Column(name = "LIFE_TIME_MONTHS", nullable = false)
    private String lifeTimeMonths;

    @Column(name = "SALVAGE_AMOUNT", nullable = false)
    private Double salvageAmount;

    @Column(name = "BUSINESS_USE", length = 50)
    private String businessUse;

    @Column(name = "ACQUIRED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date acquiredDate;

    @Column(name = "SOLD_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date soldDate;

    @Column(name = "SOLD_PERSON", length = 50)
    private String soldPerson;

    @Lob
    @Column(nullable = false)
    private String remarks;

    @Lob
    @Column(name = "ASSERT_IMAGE", nullable = false)
    private String assertImage;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

}