package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ContactRequest {

    private String firstName;

    private String lastName;

    private String nicNumber;

    private String mobileNumber;

    private String email;

    private String address;

    private Long customerId;

    private Long supplierId;

    private Long employeeId;

}