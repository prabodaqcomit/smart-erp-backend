package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporaccessoriesApprovalRequest {

    private String porAccItemBatchId;

    private String porAccItemBranchId;

    private String porAccItemCode;

    private Double porAccItemConsumptionQty;

//private String porAccItemDesc;
//
//private Double porAccItemQty;

    private Integer porAccColorId;

    private Integer porAccFitId;

    private Integer porAccSizeId;

    private String porId;

}