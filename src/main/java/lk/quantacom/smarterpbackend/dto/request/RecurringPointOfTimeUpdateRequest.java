package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class RecurringPointOfTimeUpdateRequest {

    private Long id;

    private String description;

    private Integer pointOfTimeStart;

    private Integer pointOfTimeEnd;

    private Integer isVariableEndPointOfTime;

}