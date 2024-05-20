package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class ChequeDepositRequest {

private String branchId;

private String chequeDeposit;

private Integer customerId;

private String grandTotal;

private Double depositAmount;

private Integer lineNo;

private Long receivedChequesId;

 private String date;

 }