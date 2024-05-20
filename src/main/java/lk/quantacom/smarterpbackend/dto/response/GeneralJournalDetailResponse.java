package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.GeneralJournalHeader;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalDetailResponse {

    private Long id;

    //private GeneralJournalHeader generalJournalHeader;

    private Integer lineNumber;

    //private Long ledgerAccId;

    //private String accountCode;

    private LadgerAccountResponse ledgerAccount;

    private Double debit;

    private Double credit;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}