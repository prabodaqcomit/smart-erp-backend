package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class UserLoggerResponse {

private String form;

private String action;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }