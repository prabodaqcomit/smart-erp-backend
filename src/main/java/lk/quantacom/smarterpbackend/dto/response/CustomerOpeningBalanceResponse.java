package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerOpeningBalanceResponse {

    private Integer customerOpeningBalanceId;

    private String fldCustomerId;

    private String fldDate;

    private Double fldDueBalance;

    private Double fldNetAmount;

    private Double fldPaidAmount;

    private Date fldTime;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}