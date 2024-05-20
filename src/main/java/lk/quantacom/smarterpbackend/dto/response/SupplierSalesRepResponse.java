package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class SupplierSalesRepResponse {

private Long supplierId;

private String salesrepName;

private String repNicNo;

private String repVehicleNo;

private String repEmail;

private String repMobileNo;

private String repHomeNo;

private String repFaxNo;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }