package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class BankCardTypeDetailResponse {

    private Long id;

    //private Long bankCardTypeId;
    private BankCardTypeResponse cardType;

    private String description;

    //private Long bankId;
    private BankResponse bank;

    private Double serviceCharge;

    private Double serviceChargePercentage;

    private Integer isDefault;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}