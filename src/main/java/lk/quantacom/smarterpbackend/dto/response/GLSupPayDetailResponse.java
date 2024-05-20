package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GLSupPayDetailResponse {

private Long id;

private Long glPayHeaderId;

private Date supInvDate;

private String invNumber;

private Double pendingAmount;

private Double payAmount;

private Double grossAmount;

private Double wht;

private Double stampDuty;

private Double netAmount;

private String amountInWord;

 private Double invoiceAmount;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }