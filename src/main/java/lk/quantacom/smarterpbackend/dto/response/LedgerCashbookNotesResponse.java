package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class LedgerCashbookNotesResponse {

private Double amount;

private String amountWord;

private String branchId;

private Date date;

private Integer ledgerCashbookNotesId;

private Integer ledgerIdCredit;

private Integer ledgerIdDebit;

private String payeeName;

private String reason;

private String remarks;

private String time;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }