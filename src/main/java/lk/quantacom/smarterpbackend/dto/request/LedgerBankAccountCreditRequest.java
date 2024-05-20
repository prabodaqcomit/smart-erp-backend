package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerBankAccountCreditRequest {

private Long branchId;

private Double cashAmount;

private String description;

private Integer currentBalance;

private String otherAccNo;

private String otherAccountName;

private String prNo;

 }