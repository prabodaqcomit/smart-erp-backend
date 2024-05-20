package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;


@Data
public class TblporaccessoriesRequest {

    private Integer porAccColorId;

    private Integer porAccSizeId;

    private Integer porAccFitId;

    private String porAccItemBatchId;

    private String porAccItemBranchId;

    private String porAccItemCode;

    private Double porAccItemConsumptionQty;

    private String porAccItemDesc;

    private Double porAccItemQty;

    private String porId;

}