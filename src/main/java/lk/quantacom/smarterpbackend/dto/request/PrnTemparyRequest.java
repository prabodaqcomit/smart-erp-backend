package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class PrnTemparyRequest {

    private Integer prnTemparyId;

    private String prnDate;

    private String supInDate;

    private String supInNo;

    private String itemId;

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

    private Double grossAmount;

    private Double totalDis;

    private Double totalVat;

    private Double netAmount;

    private String payMode;

    private Double payingAmount;

    private Double dueAmount;

    private Integer supplierId;

    private String itemName;

    private String remarks;

    private String image;

    private String prnUnit;

    private String batchNo;

    private String expireDate;

    private Double prnCashPrice;

    private Double prnCreditPrice;

    private String itemTime;

    private Double prnOverpaid;

    private Long purchesOrderId;

    private String barcodeNo;

    private String salesPerson;

    private String salesPersonTel;

    private String prnAgencyName;

    private Long branchId;

    private Double profitMarginItem;

    private Double profitValueItem;

    private Double profitValueTotal;

    private Long colorId;

    private Long sizeId;

    private Long fitId;

    private Double width;

    private Boolean isProcess;

    private Double unitPriceRetail;

    private Double unitPriceWholesale;

}