package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class StockTakeProcesResponse {

    private Double availabQty;

    private Double avrgPrice;

    private String barcodeNo;

    private String batchNo;

    private Long branchId;

    private Double cashDisValue;

    private Long colorId;

    private String createdBy;

    private Date createdDateTime;

    private Double creditDisValue;

    private Double damgQty;

    private Date expireDate;

    private Long fitId;

    private Deleted isDeleted;

    private String itemCode;

    private Double materialWidth;

    private String modifiedBy;

    private Date modifiedDateTime;

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

}