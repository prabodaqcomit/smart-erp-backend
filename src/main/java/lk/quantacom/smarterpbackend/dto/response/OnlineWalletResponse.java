package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class OnlineWalletResponse {

    private Long id;

    //private Long bankId;
    private BankResponse bank;

    private String description;

    private Double serviceCharge;

    private Double serviceChargePercentage;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}