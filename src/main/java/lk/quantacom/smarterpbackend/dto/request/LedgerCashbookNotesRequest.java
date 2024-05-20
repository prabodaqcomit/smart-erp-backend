package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerCashbookNotesRequest {

private Double amount;

private String amountWord;

private String branchId;

private String date;

private Integer ledgerIdCredit;

private Integer ledgerIdDebit;

private String payeeName;

private String reason;

private String remarks;

private String time;

 }