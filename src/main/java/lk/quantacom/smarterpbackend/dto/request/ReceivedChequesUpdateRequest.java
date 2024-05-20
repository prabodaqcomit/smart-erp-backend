package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ReceivedChequesUpdateRequest {

private Long id;

private Double availbleAmount;

private String bankCode;

private String branchCode;

private Long branchId;

private String chequeAccName;

private Double chequeAmount;

private String chequeDate;

private String chequeNo;

private String currencyType;

private Integer customerId;

private String depoBank;

private String depoDate;

private String newChequeNo;

private String pdOwner;

private String receivedDate;

private String remarks;

private String status;

private String statusDate;

private String thisIsPd;

 }