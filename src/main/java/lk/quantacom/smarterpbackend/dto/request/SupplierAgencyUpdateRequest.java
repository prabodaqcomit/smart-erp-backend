package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class SupplierAgencyUpdateRequest {

    private Long id;

    private Long supplierId;

    private String supAgencyName;

    private String supAgencyAddress;

    private String supAEmail;

    private String supAMobileNo;

    private String supAHomeNo;

    private String supAFaxNo;

    private String supAWebSite;

}