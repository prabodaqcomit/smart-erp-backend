package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class PurchaseOrderResponse {

    private String poDate;

    private String deliveryDate;

    private Double inhandQty;

    private Double orderQty;

    private Double unitPrice;

    private Double itemValue;

    private String remarks;

    private Double itemTotal;

    private Double dics;

    private Double vat;

    private Double grandTotal;

    private Long supplierId;

    private String supplierName;

    private String poUnit;

    private String status;

    private String agencyName;

    private Boolean isProcess;

    private Long poId;

    private String itemId;

    private String itemCode;

    private String itemName;

    private Long branchId;

    private String batchNo;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Long currencyId;


    private Double grnCompletedQty;


    private String authorizedBy;


    private Boolean authorized;

    private Boolean grnComplete;


    private Boolean canApprove;



    private Long fitId;
    private Long sizeId;
    private Long colorId;
    private String barcode;


}