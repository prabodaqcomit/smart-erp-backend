package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblbommainmaterialUpdateRequest {

private Long id;

private Integer bomId;

private String bomMainMaterialBatchId;

private String bomMainMaterialBranchId;

private Integer bomMainMaterialColorId;

private String bomMainMaterialDesc;

private String bomMainMaterialId;

 }