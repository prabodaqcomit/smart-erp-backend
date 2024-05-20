package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class BorrowingsResponse {

private Long id;

private String amountInWord;

private Double balanceAmount;

private Date borrowDate;

private String borrowerName;

private String branchId;

private Integer idborrowings;

private Double noSemiPyingAmount;

private Double payingAmount;

private String reason;

private Double returnAmount;

private String status;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }