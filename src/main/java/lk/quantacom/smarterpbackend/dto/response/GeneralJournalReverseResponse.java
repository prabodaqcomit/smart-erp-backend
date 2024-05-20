package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalReverseResponse {

    private Long id;

    private String reverseForJournalNumber;

    private String reverseAtJournalNumber;

    private Long reverseForGeneralJournalId;

    private Long reverseAtGeneralJournalId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}