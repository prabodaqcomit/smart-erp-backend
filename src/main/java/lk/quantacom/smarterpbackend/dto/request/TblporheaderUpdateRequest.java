package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporheaderUpdateRequest {

private Long id;

private Double porAdjustQty;

private Boolean porApproveRequest;

private Boolean porApproved;

private String porApprovedBranchId;

private String porApprovedDate;

private String porApprovedUserId;

private Double porConsumptionQty;

private Double porConsumptionRate;

private Boolean porConvertedToItem;

private String porCreatedBranchId;

private String porCreatedDate;

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

 }