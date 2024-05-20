package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class TblbommainmaterialResponse {

private Long id;

private Integer bomId;

private String bomMainMaterialBatchId;

private String bomMainMaterialBranchId;

private Integer bomMainMaterialColorId;

private String bomMainMaterialDesc;

private String bomMainMaterialId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }