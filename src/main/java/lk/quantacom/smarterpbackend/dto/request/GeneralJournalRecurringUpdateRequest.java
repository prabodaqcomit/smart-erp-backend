package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GeneralJournalRecurringUpdateRequest {

    private Long id;

    private String recurringName;

    private Integer templateId;

    private Long branchId;

    private String startDateTime;

    private String endDateTime;

    private Integer recurringFrequency;

    private Integer recurringFrequencyPOT;

    private Integer isRunAtEndPOT;

    private String lastRunDateTime;

    private Integer totalRunCount;

}