package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class SupplierUpdateRequest {

    private Long id;

    private String name;

    private String address;

    private String tHome;

    private String tMobile;

    private String tOffice;

    private String fax;

    private String email;

    private String salesPerson;

    private String vat;

    private Double creditLimit;

    private Double avCreditLimit;

    private String manufacture;

    private String division;

    private String agencyName;

    private String website;

    private String brands;

    private String supImage;

    private String supBankName;

    private String supBankBranch;

    private String supBankAccType;

    private String supBankAccNo;

    private String supBankAccName;

    private Long supplierLedgerId;

    private Long branchId;

    private SupplierAgencyRequest supplierAgencyRequest;

    private SupplierSalesRepRequest supplierSalesRepRequest;

    private Deleted isDeleted;

}