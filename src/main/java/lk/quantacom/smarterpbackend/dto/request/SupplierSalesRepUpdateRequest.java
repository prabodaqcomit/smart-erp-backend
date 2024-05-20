package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class SupplierSalesRepUpdateRequest {

private Long id;

private Long supplierId;

private String salesrepName;

private String repNicNo;

private String repVehicleNo;

private String repEmail;

private String repMobileNo;

private String repHomeNo;

private String repFaxNo;

 }