package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class ChequeReturnsSaveListRequest {

 private List<ChequeReturnsSaveAllRequest> chequeReturnsRequests;

 private Long branchId;

 private Long ledgerCreditId;

 private Long ledgerDebitId;

 private Double chequeAmount;

 private String chequeNo;

 private String creditAccountName;

 private String debitAccountName;

 private Integer bankAccountId;


// private Long ladgerAccountId;
//
// private Double updateBalance;
//
// private String transType;
//
// private Double creditAmount;
//
// private Double debitAmount;
//
// private String creditColumnName;

// private String debitColumnName;


 }