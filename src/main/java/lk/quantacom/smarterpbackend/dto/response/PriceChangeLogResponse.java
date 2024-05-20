package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class PriceChangeLogResponse {

private Long id;


private String user;

private String itemCode;

private String ItemName;

private String date;

private Double prevCostPrice;

private Double prevUnitPrice;

private Long color;

private Long size;

private Long fit;

private Long branch;

private String batchNo;

private Double stkCashPrice;

private Double stkCreditPrice;

private Double stockUnitPriceRetail;

private Double stockUnitPriceWholesale;

private Double cashDisValue;

private Double creditDisValue;

private Double salesDiscoPresentage;

private Double newCostPrice;

private Double newUnitPrice;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }