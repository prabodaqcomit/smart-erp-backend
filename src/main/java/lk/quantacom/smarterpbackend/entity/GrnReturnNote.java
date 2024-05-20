package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "GRN_RETURN_NOTE")
@Data
public class GrnReturnNote extends BaseEntity {

    @Column(name = "RETURN_ID", nullable = false)
    private Long returnId;

    @Column(name = "RETURN_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "GRN_ID", nullable = false)
    private GrnHeader grn;

    @Column(name = "IN_HAND_QTY", nullable = false)
    private Double inHandQty;

    @Column(name = "RETURN_QTY", nullable = false)
    private Double returnQty;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Lob
    @Column
    private String remarks;

    @Column(name = "BATCH_NO", length = 50, nullable = false)
    private String batchNo;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

}