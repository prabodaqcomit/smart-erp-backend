package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class PurchaseOrderUpdateRequest {

private Long id;

private String poDate;

private String deliveryDate;

private Double inhandQty;

private Double orderQty;

private Double unitPrice;

private Double itemValue;

private String remarks;

private Double itemTotal;

private Double dics;

private Double vat;

private Double grandTotal;

private Long supplierId;

private String poUnit;

private String status;

private String agencyName;

private Boolean isProcess;

private Long poId;

private String itemId;

private String itemCode;

private Long branchId;

 }