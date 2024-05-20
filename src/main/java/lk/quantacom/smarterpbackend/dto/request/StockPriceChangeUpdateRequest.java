package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class StockPriceChangeUpdateRequest {


private Double costPrice;

private Double unitPrice;

private String itemCode;

private Long colorId;

private Long sizeId;

private Long fitId;

private Long branchId;

private String batchNo;

 }