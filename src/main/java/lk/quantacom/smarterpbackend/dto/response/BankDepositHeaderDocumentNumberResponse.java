package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class BankDepositHeaderDocumentNumberResponse {

    private Date generatedDate;

    private String documentNumber;

    private Boolean isDisplayOnly;
}