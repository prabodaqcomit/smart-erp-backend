package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ActionProfileMapResponse {

private Long id;

private Long actionFieldId;

private Long profileId;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }