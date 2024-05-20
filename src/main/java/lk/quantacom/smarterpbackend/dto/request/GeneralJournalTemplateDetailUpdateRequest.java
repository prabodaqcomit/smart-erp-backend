package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GeneralJournalTemplateDetailUpdateRequest {

    private Long id;

    private Long templateId;

    private Integer lineNumber;

    private Long accountId;

    private Double debit;

    private Double credit;

}