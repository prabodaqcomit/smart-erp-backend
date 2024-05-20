package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class StockTakeProcesUpdateRequest {

private Double availabQty;

private Double avrgPrice;

private String barcodeNo;

private String batchNo;

private Long branchId;

private Double cashDisValue;

private Long colorId;

private Double creditDisValue;

private Double damgQty;

private String expireDate;

private Long fitId;

private Deleted isDeleted;

private String itemCode;

private Double materialWidth;

private String modifiedBy;

private Double obStock;

private Double salesDiscoPresentage;

private String salesPerson;

private Long sizeId;

private Double stkCashPrice;

private Double stkCreditPrice;

private Double stkQty;

private Double stockUnitPriceRetail;

private Double stockUnitPriceWholesale;

private Double storesQty;

private Long supplierId;

private Double totalQty;

private Boolean stockProces;

private Integer id;

 }