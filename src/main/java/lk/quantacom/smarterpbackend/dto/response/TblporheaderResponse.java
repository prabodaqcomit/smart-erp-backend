package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class TblporheaderResponse {

    private Long id;

    private Double porAdjustQty;

    private Boolean porApproveRequest;

    private Boolean porApproved;

    private String porApprovedBranchId;

    private Date porApprovedDate;

    private String porApprovedUserId;

    private Double porConsumptionQty;

    private Double porConsumptionRate;

    private Boolean porConvertedToItem;

    private String porCreatedBranchId;

    private Date porCreatedDate;

    private String porCreatedUserId;

    private Double porDamageQty;

    private String porDescription;

    private String porId;

    private Boolean porIsDamage;

    private Boolean porIsMaterialQty;

    private Boolean porIsQuality;

    private Boolean porIsWidthLength;

    private Boolean porLongSleeve;

    private String porMaterialComment;

    private String porRemark;

    private Boolean porShortSleeve;

    private String porStyleDesc;

    private Integer porStyleId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}