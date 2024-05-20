package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class TblporaccessoriesResponse {

private Long id;

private String porAccItemBatchId;

private String porAccItemBranchId;

private String porAccItemCode;

private Double porAccItemConsumptionQty;

private String porAccItemDesc;

private Double porAccItemQty;

private String porId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }