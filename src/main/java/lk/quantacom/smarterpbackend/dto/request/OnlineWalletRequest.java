package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class OnlineWalletRequest {

    private Long bankId;

    private String description;

    private Double serviceCharge;

    private Double serviceChargePercentage;

}