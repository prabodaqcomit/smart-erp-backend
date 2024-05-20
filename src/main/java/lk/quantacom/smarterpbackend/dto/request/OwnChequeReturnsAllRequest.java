package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class OwnChequeReturnsAllRequest {

private String bankCode;

private String branchCode;

private String branchId;

private Integer chequeIssueNoteId;

private String chequeNo;

private String framNo;

private String remarks;

private Double value;

private Date returnDate;

 }