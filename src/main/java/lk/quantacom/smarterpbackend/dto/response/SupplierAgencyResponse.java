package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class SupplierAgencyResponse {

private Long supplierId;

private String supAgencyName;

private String supAgencyAddress;

private String supAEmail;

private String supAMobileNo;

private String supAHomeNo;

private String supAFaxNo;

private String supAWebSite;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }