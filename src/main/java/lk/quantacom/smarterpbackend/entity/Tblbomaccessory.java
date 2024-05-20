package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLBOMACCESSORY")
@Data
public class Tblbomaccessory extends BaseEntity {


    @Column(name = "BD_ACCESSORY_BATCH_ID", length = 45)
    private String bdAccessoryBatchId;

    @Column(name = "BD_ACCESSORY_BRANCH_ID", length = 45)
    private String bdAccessoryBranchId;

    @Column(name = "BD_ACCESSORY_ID", length = 45)
    private String bdAccessoryId;

    @Column(name = "BD_DESC", length = 100, nullable = false)
    private String bdDesc;

    @Column(name = "BD_ID", nullable = false)
    private Integer bdId;

    @Column(name = "BD_QTY")
    private Double bdQty;

    @Column(name = "BD_ACC_COLOR_ID")
    private Integer bdAccColorId;

    @Column(name = "BD_ACC_SIZE_ID")
    private Integer bdAccSizeId;

    @Column(name = "BD_ACC_FIT_ID")
    private Integer bdAccFitId;

}