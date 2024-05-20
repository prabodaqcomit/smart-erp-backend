package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBLPORHEADER")
@Data
public class Tblporheader extends BaseEntity {


    @Column(name = "POR_ADJUST_QTY", nullable = false)
    private Double porAdjustQty = 0.0;

    @Column(name = "POR_APPROVE_REQUEST", nullable = false)
    private Boolean porApproveRequest;

    @Column(name = "POR_APPROVED", nullable = false)
    private Boolean porApproved;

    @Column(name = "POR_APPROVED_BRANCH_ID", length = 18)
    private String porApprovedBranchId;

    @Column(name = "POR_APPROVED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date porApprovedDate;

    @Column(name = "POR_APPROVED_USER_ID", length = 45)
    private String porApprovedUserId;

    @Column(name = "POR_CONSUMPTION_QTY", nullable = false)
    private Double porConsumptionQty = 0.0;

    @Column(name = "POR_CONSUMPTION_RATE", nullable = false)
    private Double porConsumptionRate = 0.0;

    @Column(name = "POR_CONVERTED_TO_ITEM")
    private Boolean porConvertedToItem;

    @Column(name = "POR_CREATED_BRANCH_ID", length = 18)
    private String porCreatedBranchId;

    @Column(name = "POR_CREATED_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date porCreatedDate;

    @Column(name = "POR_CREATED_USER_ID", length = 45)
    private String porCreatedUserId;

    @Column(name = "POR_DAMAGE_QTY", nullable = false)
    private Double porDamageQty = 0.0;

    @Column(name = "POR_DESCRIPTION", length = 50)
    private String porDescription;

    @Column(name = "POR_ID", unique = true, nullable = false)
    private String porId;

    @Column(name = "POR_IS_DAMAGE", nullable = false)
    private Boolean porIsDamage;

    @Column(name = "POR_IS_MATERIAL_QTY", nullable = false)
    private Boolean porIsMaterialQty;

    @Column(name = "POR_IS_QUALITY", nullable = false)
    private Boolean porIsQuality;

    @Column(name = "POR_IS_WIDTH_LENGTH", nullable = false)
    private Boolean porIsWidthLength;

    @Column(name = "POR_LONG_SLEEVE", nullable = false)
    private Boolean porLongSleeve;

    @Column(name = "POR_MATERIAL_COMMENT", length = 400, nullable = false)
    private String porMaterialComment = "0";

    @Column(name = "POR_REMARK", length = 100)
    private String porRemark;

    @Column(name = "POR_SHORT_SLEEVE", nullable = false)
    private Boolean porShortSleeve;

    @Column(name = "POR_STYLE_DESC", length = 50)
    private String porStyleDesc;

    @Column(name = "POR_STYLE_ID")
    private Integer porStyleId;

}