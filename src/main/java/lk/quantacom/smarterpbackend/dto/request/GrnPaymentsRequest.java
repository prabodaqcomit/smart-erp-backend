package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnPaymentsRequest {

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

 }