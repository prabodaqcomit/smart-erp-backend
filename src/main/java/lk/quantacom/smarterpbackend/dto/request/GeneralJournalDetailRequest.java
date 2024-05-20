package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GeneralJournalDetailRequest {

    //private Long generalJournalId;

    //private String journalNumber;

    private Integer lineNumber;

    private Long accountId;

    private Double debit;

    private Double credit;

}