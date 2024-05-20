package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;
@Data
public class SalesReceiptResponse {

private Long id;

private String branchId;

private Double changesOverPaid;

private Date chequeDate;

private String chequeNo;

private String chequePaymentStatus;

private Integer customerId;

private Double depositAmount;

private Double disPrese;

private Double dueBalance;

private Double grandTotal;

private Integer idsalesReceipt;

private Date inPayDate;

private Double invoiceDisc;

private String invoiceId;

private Integer lineNo;

private Double netAmount;

private Double paidAdvanced;

private String paymentMethod;

private String remark;

private Double returnAmount;

private Double subTotal;

private Date time;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }