package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnDetailsUpdateRequest {

    private Long id;

    private String itemCode;

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

}