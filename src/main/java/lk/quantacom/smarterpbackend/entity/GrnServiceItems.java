package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GRN_SERVICE_ITEMS")
@Data
public class GrnServiceItems extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private ItemMaster item;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double qty;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "ITEM_TOTAL", nullable = false)
    private Double itemTotal;

    @Column(name = "GRN_UNIT", length = 25)
    private String grnUnit;

    @ManyToOne
    @JoinColumn(name = "GRN_ID", nullable = false)
    private GrnHeader grn;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column
    private Long poNumber;
}