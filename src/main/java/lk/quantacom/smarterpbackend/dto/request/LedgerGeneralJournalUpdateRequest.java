package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class LedgerGeneralJournalUpdateRequest {

private Long id;

private String description;

private Double amount;

private String prNo;

private Long debitAccId;

private Long creditAccId;

private Double creditAmount;

private String remarks;

private Long branchId;

 }