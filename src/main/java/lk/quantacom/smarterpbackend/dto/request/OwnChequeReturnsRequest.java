package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class OwnChequeReturnsRequest {

private String bankCode;

private String branchCode;

private String branchId;

private Integer chequeIssueNoteId;

private String chequeNo;

private String framNo;

private Integer idownChequeReturns;

private String remarks;

private Double value;

 }