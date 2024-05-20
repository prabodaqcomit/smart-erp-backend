package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GeneralJournalReverseUpdateRequest {

    private Long id;

    private Long reverseForGeneralJournalId;

    private Long reverseAtGeneralJournalId;

}