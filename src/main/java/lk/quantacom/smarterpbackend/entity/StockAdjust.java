package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "STK_ADJ")
@Data
public class StockAdjust extends BaseEntity {

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "INHAND_QTY", nullable = false)
    private Double inhandQty;

    @Column(name = "LATEST_QTY", nullable = false)
    private Double latestQty;

    @Column(name = "ADJT_QTY", nullable = false)
    private Double adjtQty;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @Column(name = "STK_ADJ_BATCH_NO", length = 50, nullable = false)
    private String stkAdjBatchNo;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

}