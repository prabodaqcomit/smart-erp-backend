package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ExpenceHistoryUpdateRequest {

private Long id;

private String date;

private Integer docNo;

private String docType;

private String description;

private Double payAmount;

private String payMode;

private String remarks;

private String branchId;

 }