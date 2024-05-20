package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class SalesRefUpdateRequest {

private Long id;

private String repcode;

private String name;

private String address;

private String gender;

private String tHome;

private String tMobile;

private String tOffice;

private String fax;

private String email;

private String tPartyName;

private String tPartyMobile;

private String tPartyEmail;

private String image;

private String nicNumbr;

private Long branchId;

private Double billDiscLimit;

private Double lineDiscLimit;

private String SalesArea;

 }