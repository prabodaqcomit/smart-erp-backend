package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankCardTypeRequest {

    private String cardTypeName;

    private Integer cardNumberLength;

    private String cardNumberFormat;

}