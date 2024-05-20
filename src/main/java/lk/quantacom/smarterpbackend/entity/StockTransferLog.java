package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "STOCK_TRANSFER_LOG")
@Data
public class StockTransferLog extends BaseEntity {

    @Column
    private String user;

    @Column(name = "ITEM_CODE")
    private String itemCode;

    @Column(name = "ITEM_NAME")
    private String ItemName;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column
    private Long color;

    @Column
    private Long size;

    @Column
    private Long fit;

    @Column(name = "FROM_BRANCH")
    private Long fromBranch;

    @Column(name = "TO_BRANCH")
    private Long toBranch;

    @Column(name = "PREV_QTYFROM_BRANCH")
    private Double prevQtyfromBranch;

    @Column(name = "NEW_QTYTO_BRANCH")
    private Double newQtytoBranch;

    @Column(name = "PREV_QTYTOBRANCH")
    private Double prevQtytobranch;

    @Column(name = "NEW_QTY_FROM_BRANCH")
    private Double newQtyFromBranch;

    @Column(name = "ISSUE_NUMBER")
    private Integer issueNumber;

    private Double unitPrice;
}