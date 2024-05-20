package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class PettyCashResponse {

private Long id;

private Double amountReceived;

private String branchId;

private String description;

private Double entertainment;

private Double foods;

private Double grandTotal;

private Integer idpettyCash;

private Double noSemiGrandTotal;

private Double noSemiPayingAmount;

private Double otherEx;

private String payeeName;

private Double payingAmount;

private Date pettyCashDate;

private Double postage;

private String reference;

private Double stationary;

private Date timeTake;

private Double travel;

private String voucherNo;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }