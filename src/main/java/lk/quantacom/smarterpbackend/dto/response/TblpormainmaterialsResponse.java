package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class TblpormainmaterialsResponse {

private Long id;

private String porId;

private Integer porMainColorId;

private String porMainItemBatchId;

private String porMainItemBranchId;

private String porMainItemCode;

private String porMainItemDesc;

private Double porMainItemQty;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }