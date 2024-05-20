package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

@Data
public class NumberToWordResponse {

    private Long number;

    private String language;

    private String numberInWord;
}
