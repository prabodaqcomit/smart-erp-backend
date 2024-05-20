package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class BranchNetworkResponse {

    private String branchId;

    private String branchName;

    private String fldLocation;

    private String fldProvince;

    private Long branchLevelId;

    private Boolean isPublicBranch;

    private Boolean damageLocation;

    private Long branchType;

    private Boolean isHoShop;
//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}