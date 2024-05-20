package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class LedgerGeneralJournalResponse {

private Long id;

private String description;

private Double amount;

private String prNo;

private Long debitAccId;

private Long creditAccId;

private Double creditAmount;

private String remarks;

private Long branchId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }