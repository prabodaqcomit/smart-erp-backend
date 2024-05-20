package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class RecurringPointOfTimeRequest {

    private String description;

    private Integer pointOfTimeStart;

    private Integer pointOfTimeEnd;

    private Integer isVariableEndPointOfTime;

}