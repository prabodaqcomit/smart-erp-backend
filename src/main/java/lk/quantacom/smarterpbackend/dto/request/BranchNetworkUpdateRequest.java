package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class BranchNetworkUpdateRequest {

    private Long id;

    private String branchId;

    private String branchName;

    private String fldLocation;

    private String fldProvince;

    private Long branchLevelId;

    private Deleted isDeleted;

    private Boolean isPublicBranch;

    private Boolean damageLocation;

    private Long branchType;

    private Boolean isHoShop;
}