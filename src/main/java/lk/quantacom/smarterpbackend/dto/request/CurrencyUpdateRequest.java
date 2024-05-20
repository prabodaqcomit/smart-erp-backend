package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class CurrencyUpdateRequest {

    private Long id;

    private String currency;

    private Float rate;

    private String currencySymbol;

    private String currencyDescription;

    private String localCurrency;

    private Deleted isDeleted;

}