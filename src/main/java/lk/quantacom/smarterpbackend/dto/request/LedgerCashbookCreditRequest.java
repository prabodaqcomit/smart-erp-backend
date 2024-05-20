package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerCashbookCreditRequest {

private Double bankAmount;

private String branchId;

private Double cashAmount;

private String description;

private Double discountAllowed;


private String otherAccNo;

private String otherAccountName;

private String prNo;

private String vcharNo;

 }