package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerPettyCashBookUpdateRequest {

private Long id;

private String branchId;

private String cartage;

private Double cashReceived;

private String cbFollow;

private String desctription;

private Double entartainment;

private Double foods;

private Double grandTotal;

private Double ledgerAcc;

private Integer ledgerPettyCashBookId;

private Integer lineNo;

private String payeeName;

private Double payingAmount;

private String pettyCashDate;

private Double postage;

private Double stationary;

private String timeTake;

private Double travel;

private String voucherNo;

 }