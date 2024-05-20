package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class GeneralJournalTemplateHeaderUpdateRequest {

    private Long id;

    private Long branchId;

    private String templateName;

    private String remark;

    private Double totalDebit;

    private Double totalCredit;

    private String lastUsedDateTime;

    private List<GeneralJournalTemplateDetailUpdateRequest> templateDetails;
}