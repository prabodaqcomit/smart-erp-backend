package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "ITEM_MASTER")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ItemMaster  {

    public String getId(){
        return itemCode;
    }

    public void setId(String itemId){
        itemCode=itemId;
    }

    @Id
    @Column(name = "ITEM_CODE", nullable = false,unique = true,length = 20)
    private String itemCode;

    @Column(name = "ITEM_NAME", length = 500, nullable = false)
    private String itemName;

    @Column(name = "GENARIC_NAME", length = 500)
    private String genaricName;

    @Column(name = "POS_DESCRIPTION", length = 100)
    private String posDescription;

    @Column(length = 50)
    private String barcode;

    @Column(length = 50)
    private String brand;

    @Column(length = 50)
    private String strenth;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "UNIT_ID", nullable = false)
    private UnitRef unit;

    @Column(name = "BUYING_PRICE")
    private Double buyingPrice;

    @Column(name = "PACK_SIZE")
    private Double packSize;

    @Column(name = "WHOLESALE_SELL_PRICE")
    private Double wholesaleSellPrice;

    @Column(name = "RETAIL_SELL_PRICE")
    private Double retailSellPrice;

    @Column(name = "UNIT_PRICE_WHOLESALE")
    private Double unitPriceWholesale;

    @Column(name = "UNIT_PRICE_RETAIL")
    private Double unitPriceRetail;

    @Column(name = "RACK_NO", length = 20)
    private String rackNo;

    @Column(name = "MIN_STOCK")
    private Double minStock;

    @Column(name = "MAX_STOCK")
    private Double maxStock;

    @Column(name = "ITEM_IMAGE", length = 200)
    private String itemImage;

    @Column(name = "REGISTRATION_CODE", length = 100)
    private String registrationCode;

    @Column(name = "NO_OF_UNITS")
    private Double noOfUnits;

    @Column(name = "UNIT_PRICE")
    private Double unitPrice;

    @Column(name = "WASTG_VALUE")
    private Double wastgValue;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column(name = "IS_WEIGHTED_ITEM", nullable = false)
    private Boolean isWeightedItem;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    @Column(name = "IS_MATERIAL", nullable = false)
    private Boolean isMaterial;

    @Column(name = "IS_MAIN_MATERIAL", nullable = false)
    private Boolean isMainMaterial;

    @Column(name = "TAX_CLASS")
    private Double taxClass;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_1_ID")
    private Department1 department1;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_2_ID")
    private Department2 department2;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_3_ID")
    private Department3 department3;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_4_ID")
    private Department4 department4;

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
    @ColumnDefault("0")
    private Deleted isDeleted=Deleted.NO;

    @Column(name = "IS_KOT", nullable = false)
    private Boolean isKOT;

    @Column(name = "IS_BOT", nullable = false)
    private Boolean isBOT;

    @Column
    private Boolean serviceItem;

    @Column
    private Long currencyId;

    //mappings

    @OneToMany(mappedBy = "stockPK.item")
    private List<Stock> stock;

}