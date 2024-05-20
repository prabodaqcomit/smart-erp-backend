package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ChequeReturnsSaveAllRequest {

private String bankCode;

private String branchCode;

private String branchId;


private String chequePayDate;

private Integer chequeReturns;

private Integer customerId;

private String invoiceNum;

private String remarks;

private Double value;

private Double paidAmount;

 }