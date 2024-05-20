package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CurrencyRequest {

    private String currency;

    private Float rate;

    private String currencySymbol;

    private String currencyDescription;

    private String localCurrency;

}