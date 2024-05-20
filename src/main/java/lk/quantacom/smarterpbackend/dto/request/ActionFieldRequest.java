package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ActionFieldRequest {

    private String actionAlias;

    private String actionDescription;

    private Boolean readOnly;

    private Boolean isInputUpperCase;

    private Boolean isHidden;

    private Boolean isMandatory;

    private String referenceAlias;

    private String fieldEvent;

    private String formula;

    private Long actionTypeId;

    private Boolean isNumeric;
}