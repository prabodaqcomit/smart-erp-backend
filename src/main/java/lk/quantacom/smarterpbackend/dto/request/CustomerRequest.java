package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CustomerRequest {

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

    private Double billDiscLimit;

    private Double lineDiscLimit;

}