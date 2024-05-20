package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLBOMMAINMATERIAL")
@Data
public class Tblbommainmaterial extends BaseEntity {


    @Column(name = "BOM_ID")
    private Integer bomId;

    @Column(name = "BOM_MAIN_MATERIAL_BATCH_ID", length = 50)
    private String bomMainMaterialBatchId;

    @Column(name = "BOM_MAIN_MATERIAL_BRANCH_ID", length = 50)
    private String bomMainMaterialBranchId;

    @Column(name = "BOM_MAIN_MATERIAL_COLOR_ID")
    private Integer bomMainMaterialColorId;

    @Column(name = "BOM_MAIN_MATERIAL_DESC", length = 50)
    private String bomMainMaterialDesc;

    @Column(name = "BOM_MAIN_MATERIAL_ID", length = 50)
    private String bomMainMaterialId;

    @Column(name = "BOM_MAIN_ITEM_QTY")
    private Double bomMainItemQty;

    @Column(name = "BOM_MAIN_SIZE_ID")
    private Integer bomMainSizeId;

    @Column(name = "BOM_MAIN_FIT_ID")
    private Integer bomMainFitId;
}