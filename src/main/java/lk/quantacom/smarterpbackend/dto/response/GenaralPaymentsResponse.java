package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class GenaralPaymentsResponse {

private Long id;

private String amountInWord;

private String bankCode;

private String branchCode;

private String branchId;

private String chequeNo;

private Date genaralDate;

private Double noSemiPyingAmount;

private String payMode;

private String payeeName;

private Double payingAmount;

private String reason;

private String remarks;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }