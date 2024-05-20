package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class ActionFieldAndEffectiveUpdateRequest {

    private Long id;

    private String actionAlias;

    private String actionDescription;

    private Boolean readOnly;

    private Boolean isInputUpperCase;

    private Boolean isHidden;

    private Boolean isNumeric;

    private Boolean isMandatory;

    private String referenceAlias;

    private String fieldEvent;

    private String formula;

    private Long actionTypeId;

    private List<Long> profileIds;

    private List<ActionEffectiveFieldRequest> actionEffectiveFieldRequests;


}