package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerHistoryUpdateRequest {

private Long id;

private Long branchId;

private Long ladgerAccountId;

private String updateFram;

private String updateFramDocNo;

private String remark;

private Double updateBalance;

private String transType;

private Double creditAmount;

private Double debitAmount;

private String creditColumnName;

private String debitColumnName;

 }