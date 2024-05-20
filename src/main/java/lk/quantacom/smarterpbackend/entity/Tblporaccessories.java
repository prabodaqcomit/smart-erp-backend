package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPORACCESSORIES")
@Data
public class Tblporaccessories extends BaseEntity {


    @Column(name = "POR_ACC_ITEM_BATCH_ID", length = 50)
    private String porAccItemBatchId;

    @Column(name = "POR_ACC_ITEM_BRANCH_ID", length = 50)
    private String porAccItemBranchId;

    @Column(name = "POR_ACC_COLOR_ID")
    private Integer porAccColorId;

    @Column(name = "POR_ACC_SIZE_ID")
    private Integer porAccSizeId;

    @Column(name = "POR_ACC_FIT_ID")
    private Integer porAccFitId;

    @Column(name = "POR_ACC_ITEM_CODE", length = 50)
    private String porAccItemCode;

    @Column(name = "POR_ACC_ITEM_CONSUMPTION_QTY")
    private Double porAccItemConsumptionQty;

    @Column(name = "POR_ACC_ITEM_DESC", length = 50)
    private String porAccItemDesc;

    @Column(name = "POR_ACC_ITEM_QTY")
    private Double porAccItemQty;

    @Column(name = "POR_ID")
    private String porId;

}