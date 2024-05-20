package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BranchNetworkRequest {

    private String branchId;

    private String branchName;

    private String fldLocation;

    private String fldProvince;

    private Long branchLevelId;

    private Boolean isPublicBranch;

    private Boolean damageLocation;

    private Long branchType;

    private Boolean isHoShop;
}