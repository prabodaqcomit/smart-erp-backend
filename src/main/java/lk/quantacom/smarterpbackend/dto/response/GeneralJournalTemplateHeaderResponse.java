package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GeneralJournalTemplateHeaderResponse {

    private Long id;

    private BranchNetworkResponse branch;

    private String templateName;

    private String remark;

    private Date lastUsedDateTime;

    private List<GeneralJournalTemplateDetailResponse> templateDetails;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}