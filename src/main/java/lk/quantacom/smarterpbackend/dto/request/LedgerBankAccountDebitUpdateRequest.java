package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerBankAccountDebitUpdateRequest {

private Long id;

private Long branchId;

private Double cashAmount;

private String description;

private Integer ledgerBankAccountDebitId;

private String otherAccNo;

private String otherAccountName;

private String prNo;

 }