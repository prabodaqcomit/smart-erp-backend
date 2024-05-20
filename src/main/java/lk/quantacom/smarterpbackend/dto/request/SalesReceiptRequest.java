package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class SalesReceiptRequest {

private String branchId;

private Double changesOverPaid;

private String chequeDate;

private String chequeNo;

private String chequePaymentStatus;

private Integer customerId;

private Double depositAmount;

private Double disPrese;

private Double dueBalance;

private Double grandTotal;

private Integer idsalesReceipt;

private String inPayDate;

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

 }