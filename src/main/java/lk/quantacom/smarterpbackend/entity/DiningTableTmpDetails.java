package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "DINING_TABLE_TMP_DETAILS")
@Data
public class DiningTableTmpDetails extends BaseEntity {


    @Column(name = "HEAD_ID", nullable = false)
    private Long headId;

    @Column(name = "TABLE_ID", nullable = false)
    private Long tableId;

    @Column(name = "ITEM_CODE", length = 20, nullable = false)
    private String itemCode;

    @Column(name = "BATCH_NO", length = 50)
    private String batchNo;

    @Column(name = "SELECTED_PRICE", nullable = false)
    private Double selectedPrice;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "IS_KOT", nullable = false)
    private Boolean isKOT;

    @Column(name = "IS_BOT", nullable = false)
    private Boolean isBOT;

    @Column(name = "INV_STATUS", nullable = false)
    private Integer invStatus;

}