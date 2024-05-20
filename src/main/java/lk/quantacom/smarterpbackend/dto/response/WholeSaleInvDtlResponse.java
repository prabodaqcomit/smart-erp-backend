package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class WholeSaleInvDtlResponse {

private Long id;

 private Integer invno;

private String itemCode;

private String ItemName;

private Integer sizeId;

private String sizeName;

private Double QtyByitem;

private Double sizeQty;

private Double mrp;

private Double disPrecentage;

private Double disAmount;

private Double amount;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 private Long color;

 private Long fit;

 private String batchNo;

 private Double itemCost;
 }