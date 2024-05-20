package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerBankHistoryUpdateRequest {

private Long id;

private Double amount;

private Integer bankAccountId;

private Double creditAccAmount;

private Integer creditAccId;

private Double currentBalance;

private Double debitAccAmount;

private Integer debitAccId;

private String depoType;

private Integer framDocNo;

private String framName;

private Integer ledgerBankHisoryId;

private String transaction;

 }