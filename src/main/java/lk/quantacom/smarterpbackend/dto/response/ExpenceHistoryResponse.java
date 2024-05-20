package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class ExpenceHistoryResponse {

private Long id;

private Date date;

private Integer docNo;

private String docType;

private String description;

private Double payAmount;

private String payMode;

private String remarks;

private String branchId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }