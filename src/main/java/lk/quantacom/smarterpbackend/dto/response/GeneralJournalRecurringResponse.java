package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalRecurringResponse {

    private Long id;

    private BranchNetworkResponse branch;

    private String recurringName;

    private Integer templateId;

    private Date startDateTime;

    private Date endDateTime;

    private Integer recurringFrequency;

    private Integer recurringFrequencyPOT;

    private Integer isRunAtEndPOT;

    private Date lastRunDateTime;

    private Integer totalRunCount;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}