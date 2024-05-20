package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class CustomerResponse {

    private String type;

    private String vat;

    private String name;

    private String address;

    private String gender;

    private String tHome;

    private String tMobile;

    private String tOffice;

    private String fax;

    private String email;

    private Double creditLimit;

    private String tPartyName;

    private String tPartyMobile;

    private String tPartyEmail;

    private String image;

    private Double avlbCreditLimit;

    private String nicNumbr;

    private String creditAccNo;

    private Long branchId;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
//
    private Double billDiscLimit;

    private Double lineDiscLimit;
}