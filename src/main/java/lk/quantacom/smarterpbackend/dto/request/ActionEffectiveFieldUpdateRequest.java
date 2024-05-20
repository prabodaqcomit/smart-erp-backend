package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ActionEffectiveFieldUpdateRequest {

    private Long id;

    private Long actionFieldId;

    private String effectiveFieldAlias;

}