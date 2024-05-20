package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnDetailsRequest {

    private String itemId;

    private Double unitPrice;

    private Double qty;

    private Double itemPrice;

    private Double itemDisc;

    private Double itemDicPrice;

    private Double itemVat;

    private Double itemVatPrice;

    private Double itemValue;

    private Double itemTotal;

    private String grnUnit;

    private String expireDate;

    private Double grnCashPrice;

    private Double grnCreditPrice;

    private String barcodeNo;

    private Double itemProfitMargin;

    private Double itemProfitValue;

    private Double unitPriceRetail;

    private Double unitPriceWholesale;

    private Long grnId;

    private String batchNo;

    private Integer lineNo;

    private Long colorId;

    private Long sizeId;

    private Long fitId;

    private Long branchId;

    private Long poNumber;

    private String stockOption;

    // stock

    private Double salesDiscPer;

    private Double cashDiscValue;

    private Double creditDiscValue;


    private Double width;

    //item

    private Double buyingPrice;

    private Boolean isService;

}