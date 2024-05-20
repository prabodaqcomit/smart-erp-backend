package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporheaderConfirmApprovalRequest {

    private Boolean porApproveRequest;

    private Boolean porApproved;

    private Integer porId;


}