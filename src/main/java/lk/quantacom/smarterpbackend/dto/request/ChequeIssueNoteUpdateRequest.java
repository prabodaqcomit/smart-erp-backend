package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ChequeIssueNoteUpdateRequest {

private Long id;

private String accName;

private Double amount;

private Integer bankAccountId;

private String bankCode;

private String branchCode;

private String framNo;

private String chequeDate;

private String chequeNo;

private Integer framDocNo;

private String issueDate;

private String payeeName;

private String status;

private String statusDate;

private String updateWindow;

 }