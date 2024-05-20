package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankUpdateRequest {

    private Long id;

    private String bankName;

    private String bankCode;

}