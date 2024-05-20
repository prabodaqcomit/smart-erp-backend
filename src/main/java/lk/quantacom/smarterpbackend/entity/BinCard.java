package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "BIN_CARD")
@Data
public class BinCard extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @Column(name = "BIN_CARD_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date binCardDate;

    @Column(name = "DOC_TYPE", length = 50)
    private String docType;

    @Column(name = "DOC_NO", length = 25)
    private String docNo;

    @Column(name = "REC_QTY")
    private Double recQty;

    @Column(name = "ISUE_QTY")
    private Double isueQty;

    @Column(name = "BALANCE_QTY")
    private Double balanceQty;

    @Column(name = "BATCH_NO", length = 20)
    private String batchNo;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "COLOR_ID")
    private Long color;

    @Column(name = "SIZE_ID")
    private Long size;

    @Column(name = "FIT_ID")
    private Long fit;
}