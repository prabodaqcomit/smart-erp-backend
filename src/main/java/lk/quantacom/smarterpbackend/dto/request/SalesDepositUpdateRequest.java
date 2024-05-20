package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class SalesDepositUpdateRequest {

private Long id;

private Double balance;

private Integer bankAccountId;

private String branchId;

private Integer customerId;

private String date;

private Double depositAmount;

private Double grandTotalDeposit;

private Integer idsalesDeposit;

private Integer lineNo;

private Integer salesReceiptId;

 }