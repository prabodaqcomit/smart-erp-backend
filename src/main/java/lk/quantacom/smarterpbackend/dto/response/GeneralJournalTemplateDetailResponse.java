package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.GeneralJournalTemplateHeader;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalTemplateDetailResponse {

    private Long id;

    private GeneralJournalTemplateHeader journalTemplateHeader;

    private Integer lineNumber;

    //private String accountCode;
    private LadgerAccount ladgerAccount;

    private Double debit;

    private Double credit;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}