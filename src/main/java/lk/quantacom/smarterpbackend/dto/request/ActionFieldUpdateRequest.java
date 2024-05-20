package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ActionFieldUpdateRequest {

    private Long id;

    private String actionAlias;

    private String actionDescription;

    private Boolean readOnly;

    private String fieldEvent;

    private String formula;

    private Boolean isNumeric;

    private Boolean isMandatory;

    private Boolean isInputUpperCase;

    private Boolean isHidden;

    private String referenceAlias;

    private Long actionTypeId;


}