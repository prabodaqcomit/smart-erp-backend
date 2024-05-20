package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class WholeSaleInvRequest {

//private Integer invno;

private String invDate;

private String location;

private String salesBy;

private String customer;

private Double totalQty;

private Integer numOfItem;

private Double grossAmount;

private Double billDisPrecentage;

private Double billDisAmount;

private Double NetAmount;

private Integer payType;

private Boolean isCancel;

private String payTypeInfo;


 private Double fldTaxPer;

 private String creditCardNumber;

private List<WholeSaleInvDtlRequest> invDtlRequests;

 private Integer customerId;
 private Integer salesById;
 private Integer locationId;

 private String taxName;
 private Double taxRate;

 private String remarks;
 private String invoiceNumber;

 }