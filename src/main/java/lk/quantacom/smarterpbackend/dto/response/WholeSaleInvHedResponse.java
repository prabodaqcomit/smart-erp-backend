package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class WholeSaleInvHedResponse {


private Integer invno;

private Date invDate;

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

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 private String creditCardNumber;

 private Integer customerId;

 private Integer salesById;

 private Integer locationId;
 private String invoiceNumber;

 }