package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankCardTypeDetailUpdateRequest {

    private Long id;

    private Long bankCardTypeId;

    private String description;

    private Long bankId;

    private Double serviceCharge;

    private Double serviceChargePercentage;

    private Integer isDefault;

}