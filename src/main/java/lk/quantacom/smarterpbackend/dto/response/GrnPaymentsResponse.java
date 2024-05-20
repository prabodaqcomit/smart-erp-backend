package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class GrnPaymentsResponse {

private Double grossAmount;

private Double totalDis;

private Double totalVat;

private Double netAmount;

private Double paidAmount;

private Double dueAmount;

private String payMode;

private String remarks;

private Double grnOverpaid;

private Double totalProfitValue;

private Double netProfitValue;

private Long grnId;

private Integer lineNo;

private Long branchId;

// for sup ob

private Long supplierId;

private String supplierName;

private String grnDate;



//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }