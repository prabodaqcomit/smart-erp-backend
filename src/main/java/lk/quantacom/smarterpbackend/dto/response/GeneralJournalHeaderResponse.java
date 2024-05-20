package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.GeneralJournalDetail;
import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalHeaderResponse {

    private Long id;

    private BranchNetworkResponse branch;

    private String journalNumber;

    private Date transactionDate;

    private String remark;

    private String transactionDetail;

    private Double totalDebit;

    private Double totalCredit;

    private List<GeneralJournalDetailResponse> generalJournalDetails;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}