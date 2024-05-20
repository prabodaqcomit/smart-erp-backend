package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class StockTransferLogResponse {

private Long id;

private String user;

private String itemCode;

private String ItemName;

private Date date;

private Long color;

private Long size;

private Long fit;

private Long fromBranch;

 private String fromBranchName;

 private String toBranchName;

private Long toBranch;

private Double prevQtyfromBranch;

private Double newQtytoBranch;

private Double prevQtytobranch;

private Double newQtyFromBranch;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 private Integer issueNumber;
 private Double unitPrice;

 }