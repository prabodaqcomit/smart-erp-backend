package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ActionEffectiveFieldResponse {

    private Long id;

    private Long actionFieldId;

    private String effectiveFieldAlias;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}