package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class SalesDepositResponse {

private Long id;

private Double balance;

private Integer bankAccountId;

private String branchId;

private Integer customerId;

private Date date;

private Double depositAmount;

private Double grandTotalDeposit;

private Integer idsalesDeposit;

private Integer lineNo;

private Integer salesReceiptId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }