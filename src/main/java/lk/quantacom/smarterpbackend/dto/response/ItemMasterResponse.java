package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class ItemMasterResponse {

    private String itemCode;

    private String itemName;

    private String genaricName;

    private String posDescription;


    private String barcode;

    private String brand;

    private String strenth;

    private Long categoryId;

    private String categoryName;

    private Long unitId;

    private String unitShort;

    private String unitLong;

    private Double buyingPrice;

    private Double packSize;

    private Double wholesaleSellPrice;

    private Double retailSellPrice;

    private Double unitPriceWholesale;

    private Double unitPriceRetail;

    private String rackNo;

    private Double minStock;

    private Double maxStock;

    private String itemImage;

    private String registrationCode;

    private Double noOfUnits;

    private Double unitPrice;

    private Double wastgValue;

    private Long branchId;

    private String branchName;

    private Boolean isWeightedItem;

    private Boolean isActive;

    private Boolean isMaterial;

    private Boolean isMainMaterial;

    private Double taxClass;

    private List<StockResponse> stockDetails;

    private Long department1Id;
    private String department1Name;

    private Long department2Id;
    private String department2Name;

    private Long department3Id;
    private String department3Name;

    private Long department4Id;
    private String department4Name;

    private Boolean isKOT;

    private Boolean isBOT;

    private Boolean serviceItem;

    private Long currencyId;

//from Base

    private String id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}