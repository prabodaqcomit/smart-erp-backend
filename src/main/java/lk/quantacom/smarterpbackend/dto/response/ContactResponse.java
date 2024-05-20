package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class ContactResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String nicNumber;

    private String mobileNumber;

    private String email;

    private String address;

    private Long customerId;

    private Long supplierId;

    private Long employeeId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}