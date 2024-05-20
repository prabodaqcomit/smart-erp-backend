package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class RecurringPointOfTimeResponse {

    private Long id;

    private String description;

    private Integer pointOfTimeStart;

    private Integer pointOfTimeEnd;

    private Integer isVariableEndPointOfTime;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}