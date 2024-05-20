package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "BOM_DETAILS")
@Data
public class BomDetails extends BaseEntity {

    @Column
    private Long headerId;

    @Column(name = "SUB_ITEM_CODE", length = 20, nullable = false)
    private String subItemCode;

    @Column(name = "BATCH_NO", length = 30, nullable = false)
    private String batchNo;

    @Column(name = "UNIT_COST", nullable = false)
    private Double unitCost;

    @Column(length = 25, nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private Double cost;

    @Column(name = "INV_TYPE", length = 50, nullable = false)
    private String invType;

}