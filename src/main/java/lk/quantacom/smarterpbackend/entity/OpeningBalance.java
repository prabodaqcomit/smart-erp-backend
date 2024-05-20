package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "OPENING_BALANCE")
@Data
public class OpeningBalance extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private ItemMaster item;

    @Column(name = "BATCH_NO", length = 20, nullable = false)
    private String batchNo;

    @Column(name = "idopening_balance", nullable = false)
    private Integer obNo;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column(name = "OB_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date obDate;

    @Column(name = "OB_QTY", nullable = false)
    private Double obQty;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

}