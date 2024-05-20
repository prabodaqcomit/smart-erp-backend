package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class ChequeBookRegisterResponse {

private Long id;

private String bankAccountId;

private Double chequeAmount;

private String chequeBookNo;

private Integer chequeBookRegisterId;

private Date chequeDate;

private Integer chequeNo;

private Date payIssueDate;

private Date payIssueTime;

private String payWindow;

private String payeeName;

private Date registerDate;

private String status;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }