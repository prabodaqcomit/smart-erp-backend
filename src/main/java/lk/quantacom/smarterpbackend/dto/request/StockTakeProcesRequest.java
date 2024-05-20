package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import javax.persistence.Column;

@Data
public class StockTakeProcesRequest {

    private Double availabQty;

    private Double avrgPrice;

    private String barcodeNo;

    private String batchNo;

    private Long branchId;

    private Double cashDisValue;

    private Long colorId;

    private String createdBy;

    private Double creditDisValue;

    private Double damgQty;

    private String expireDate;

    private Long fitId;

    private Deleted isDeleted;

    private String itemCode;

    private Double materialWidth;

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

    private Double physicalQty;

    private Double varianceQty;

    private Integer pendingProcessId;

    private String itemName;

    private String sizeDesc;

    private String colorDesc;

    private String fitDesc;

    private String branchName;

    private Boolean isZeroProcess;
}