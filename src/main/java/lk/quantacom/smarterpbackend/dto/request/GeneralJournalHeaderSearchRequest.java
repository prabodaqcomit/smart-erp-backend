package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class GeneralJournalHeaderSearchRequest {

    private String transactionFromDate;

    private String transactionToDate;

}