package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPORMAINMATERIALS")
@Data
public class Tblpormainmaterials extends BaseEntity {


    @Column(name = "POR_ID")
    private String porId;

    @Column(name = "POR_MAIN_COLOR_ID")
    private Integer porMainColorId;

    @Column(name = "POR_MAIN_SIZE_ID")
    private Integer porMainSizeId;

    @Column(name = "POR_MAIN_FIT_ID")
    private Integer porMainFitId;

    @Column(name = "POR_MAIN_ITEM_BATCH_ID", length = 50)
    private String porMainItemBatchId;

    @Column(name = "POR_MAIN_ITEM_BRANCH_ID", length = 50)
    private String porMainItemBranchId;

    @Column(name = "POR_MAIN_ITEM_CODE", length = 50)
    private String porMainItemCode;

    @Column(name = "POR_MAIN_ITEM_DESC", length = 50)
    private String porMainItemDesc;

    @Column(name = "POR_MAIN_ITEM_QTY")
    private Double porMainItemQty;

}