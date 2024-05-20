package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "WHOLESALEINVDTL")
@Data
public class WholeSaleInvDtl extends BaseEntity {

    @Column(name = "INVNO", nullable = false)
    private Integer invno;

    @Column(name = "ITEM_CODE", nullable = false)
    private String itemCode;

    @Column(name = "ITEM_NAME", nullable = false)
    private String ItemName;

    @Column(name = "SIZE_ID", nullable = false)
    private Integer sizeId;

    @Column(name = "SIZE_NAME")
    private String sizeName;

    @Column(name = "QTY_BYITEM", nullable = false)
    private Double QtyByitem;

    @Column(name = "SIZE_QTY", nullable = false)
    private Double sizeQty;

    @Column(nullable = false)
    private Double mrp;

    @Column(name = "DIS_PRECENTAGE", nullable = false)
    private Double disPrecentage;

    @Column(name = "DIS_AMOUNT", nullable = false)
    private Double disAmount;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "COLOR_ID", nullable = false)
    Long color;

    @Column(name = "FIT_ID", nullable = false)
    Long fit;

    @Column(name = "BATCH_NO", length = 20)
    String batchNo;

    @Column(name = "ITEM_COST", nullable = false)
    private Double itemCost;

}