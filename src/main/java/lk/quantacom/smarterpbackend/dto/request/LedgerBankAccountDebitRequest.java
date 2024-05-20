package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerBankAccountDebitRequest {

private Long branchId;

private Double cashAmount;

private String date;

private String description;

private Integer ledgerBankAccountDebitId;

private String otherAccNo;

private String otherAccountName;

private String prNo;

private String time;

 }