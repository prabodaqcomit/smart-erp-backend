package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnHeaderUpdateRequest {

private Long id;

private Long supplierId;

private Long branchId;

private String grnDate;

private String supInDate;

private String supInNo;

private String salesPerson;

private String salesPersonTel;

private String grnAgencyName;

private String remarks;

 }