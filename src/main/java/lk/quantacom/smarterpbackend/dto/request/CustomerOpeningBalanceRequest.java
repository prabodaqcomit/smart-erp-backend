package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CustomerOpeningBalanceRequest {

    private String fldCustomerId;

    private String fldDate;

    private Double fldDueBalance;

    private Double fldNetAmount;

    private Double fldPaidAmount;

    private String fldTime;

    private Integer customerOpeningBalanceId;

}