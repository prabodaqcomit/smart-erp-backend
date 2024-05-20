package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class OrderFormUpdateRequest {

private Long id;

private Integer orderId;

private Integer lineNo;

private Integer branchId;

private String itemCode;

private String itemDesc;

private Integer categoryCode;

private Integer customerId;

private String orderDate;

private String orderTime;

private Double sellPrice;

private Double orderQty;

private Double itemValue;

private Double grandTotal;

private String status;

private String userDetId;

private String batchNo;

private String closesales;

private Boolean isdiscountenable;

 }