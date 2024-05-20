package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class OnlineWalletUpdateRequest {

    private Long id;

    private Long bankId;

    private String description;

    private Double serviceCharge;

    private Double serviceChargePercentage;

}