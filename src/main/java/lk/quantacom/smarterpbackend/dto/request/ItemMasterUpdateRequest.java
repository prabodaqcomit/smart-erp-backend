package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ItemMasterUpdateRequest {

    private String id;

    private String itemCode;

    private String itemName;

    private String barcode;

    private String genaricName;

    private String posDescription;

    private String brand;

    private String strenth;

    private Long categoryId;

    private Long unitId;

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

    private Boolean isWeightedItem;

    private Boolean isActive;

    private Boolean isMaterial;

    private Boolean isMainMaterial;

    private Double taxClass;

    private Long department1Id;

    private Long department2Id;

    private Long department3Id;

    private Long department4Id;

    private Boolean isKOT;

    private Boolean isBOT;

    private Boolean serviceItem;

    private Long currencyId;

}