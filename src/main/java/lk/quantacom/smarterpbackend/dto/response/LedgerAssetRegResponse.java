package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class LedgerAssetRegResponse {

    private Long id;

    private String assetCode;

    private String assetName;

    private Long categoryId;

    private String assertType;

    private Long supplierId;

    private String manufacture;

    private String brand;

    private String serialNo;

    private String modelNo;

    private String barcodeNo;

    private String itemConditionUse;

    private Date purchaseDate;

    private String poNo;

    private Double purchaseValue;

    private Date invoiceDate;

    private Double currentMarketValue;

    private Double depreciation;

    private String issues;

    private Long departmentId;

    private String assignPerson;

    private String serviceTimePeriod;

    private String serviceTimePeriodType;

    private Date nextServiceDate;

    private String lifeTimeYears;

    private String lifeTimeMonths;

    private Double salvageAmount;

    private String businessUse;

    private Date acquiredDate;

    private Date soldDate;

    private String soldPerson;

    private String remarks;

    private String assertImage;

    private Long branchId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}