package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GenaralPaymentsUpdateRequest {

private Long id;

private String amountInWord;

private String bankCode;

private String branchCode;

private String branchId;

private String chequeNo;

private String genaralDate;

private Double noSemiPyingAmount;

private String payMode;

private String payeeName;

private Double payingAmount;

private String reason;

private String remarks;

 }