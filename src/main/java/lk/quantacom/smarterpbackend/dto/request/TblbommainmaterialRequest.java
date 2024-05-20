package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblbommainmaterialRequest {

    private Integer bomId;

    private String bomMainMaterialBatchId;

    private String bomMainMaterialBranchId;

    private Integer bomMainMaterialColorId;

    private String bomMainMaterialDesc;

    private String bomMainMaterialId;

    private Integer bomMainSizeId;

    private Integer bomMainFitId;

    private Double bomMainItemQty;

}