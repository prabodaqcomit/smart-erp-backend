package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class WholeSaleInvHedUpdateRequest {



private Integer invno;

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

 private String creditCardNumber;

 private Integer customerId;

 private Integer salesById;

 private Integer locationId;


 }