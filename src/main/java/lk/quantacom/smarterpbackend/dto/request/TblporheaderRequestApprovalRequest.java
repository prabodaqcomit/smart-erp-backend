package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class TblporheaderRequestApprovalRequest {

    private Boolean porApproveRequest;

    private Boolean porApproved;
    private Double porConsumptionQty;

    private Double porConsumptionRate;

    private String porId;

    private Boolean porIsDamage;

    private Boolean porIsMaterialQty;

    private Boolean porIsQuality;

    private Boolean porIsWidthLength;
    private String porMaterialComment;

    private List<TblporaccessoriesApprovalRequest> tblporaccessoriesRequests;

    private List<TblporsizesApprovalRequest> tblporsizesRequests;

    private List<TblporothercostsRequest> tblporothercostsRequests;

}