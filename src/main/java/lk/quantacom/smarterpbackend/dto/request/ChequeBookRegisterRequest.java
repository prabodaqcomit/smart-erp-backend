package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ChequeBookRegisterRequest {

private String bankAccountId;

private Double chequeAmount;

private String chequeBookNo;

private Integer chequeBookRegisterId;

private String chequeDate;

private Integer chequeNo;

private String payIssueDate;

private String payIssueTime;

private String payWindow;

private String payeeName;

private String registerDate;

private String status;

 }