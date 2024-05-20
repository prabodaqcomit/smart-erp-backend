package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class SupplierResponse {

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

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private SupplierAgencyResponse supplierAgencyResponse;

    private SupplierSalesRepResponse supplierSalesRepResponse;

}