package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class GrnDetailsResponse {

    private String itemId;

    private String itemCode;

    private String itemName;

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


//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}