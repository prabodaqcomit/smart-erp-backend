package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankCardTypeUpdateRequest {

    private Long id;

    private String cardTypeName;

    private Integer cardNumberLength;

    private String cardNumberFormat;

}