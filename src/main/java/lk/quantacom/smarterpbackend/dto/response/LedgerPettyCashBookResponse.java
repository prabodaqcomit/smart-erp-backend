package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class LedgerPettyCashBookResponse {

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

private Date pettyCashDate;

private Double postage;

private Double stationary;

private Date  timeTake;

private Double travel;

private String voucherNo;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }