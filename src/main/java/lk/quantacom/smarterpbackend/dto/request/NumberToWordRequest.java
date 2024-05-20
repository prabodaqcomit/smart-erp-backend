package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class NumberToWordRequest {

    private Long number;

    private String language;
}
