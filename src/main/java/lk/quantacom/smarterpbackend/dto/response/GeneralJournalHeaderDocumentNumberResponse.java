package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GeneralJournalHeaderDocumentNumberResponse {

    private Date generatedDate;

    private String documentNumber;

    private Boolean isDisplayOnly;
}