package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerAssetRegRequest {

private String assetCode;

private String assetName;

private Long categoryId;

private String assertType;

private Long supplierId;

private String manufacture;

private String brand;

private String serialNo;

private String modelNo;

private String barcodeNo;

private String itemConditionUse;

private String purchaseDate;

private String poNo;

private Double purchaseValue;

private String invoiceDate;

private Double currentMarketValue;

private Double depreciation;

private String issues;

private Long departmentId;

private String assignPerson;

private String serviceTimePeriod;

private String serviceTimePeriodType;

private String nextServiceDate;

private String lifeTimeYears;

private String lifeTimeMonths;

private Double salvageAmount;

private String businessUse;

private String acquiredDate;

private String soldDate;

private String soldPerson;

private String remarks;

private String assertImage;

private Long branchId;

 }