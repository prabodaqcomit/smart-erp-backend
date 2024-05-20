package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class ReceiptHeaderDocumentNumberResponse {

    private Date generatedDate;

    private String documentNumber;

    private Boolean isDisplayOnly;
}