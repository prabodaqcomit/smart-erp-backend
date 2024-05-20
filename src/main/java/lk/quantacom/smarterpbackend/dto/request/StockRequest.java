package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class StockRequest {

    private Double stkQty;

    private Double damgQty;

    private Double availabQty;

    private Double avrgPrice;

    private String expireDate;

    private Double stkCashPrice;

    private Double stkCreditPrice;

    private Double storesQty;

    private Double totalQty;

    private String barcodeNo;

    private Double stockUnitPriceRetail;

    private Double stockUnitPriceWholesale;

    private Long supplierId;

    private String salesPerson;

    private Double obStock;

    private String agencyName;

    private Double cashDisValue;

    private Double creditDisValue;

    private Double salesDiscoPresentage;

    private Double materialWidth;

    private String itemImage;

    private String itemId;

    private Long colorId;

    private Long sizeId;

    private Long fitId;

    private Long branchId;

    private String batchNo;


}