package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class ActionFieldResponse {

    private Long id;

    private String actionAlias;

    private String actionDescription;

    private Boolean readOnly;

    private Boolean isInputUpperCase;

    private Boolean isHidden;

    private Boolean isMandatory;

    private String fieldEvent;

    private String formula;

    private String referenceAlias;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Long actionTypeId;

    private Boolean isNumeric;

    private List<ProfileResponse> profiles;

    private List<ActionEffectiveFieldResponse> actionEffectiveFieldResponses;

}