package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class StockResponse {

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

    private String itemCode;

    private String itemName;

    private Long colorId;

    private String colorDesc;

    private Long sizeId;

    private String sizeDesc;

    private Long fitId;

    private String fitDesc;

    private Long branchId;

    private String branchName;

    private String batchNo;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}