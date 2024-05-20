package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ProfileTypesResponse {

private Integer id;

private String description;

private Integer defprofileid;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }