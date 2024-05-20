package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ProfileFieldsResponse {

private String fieldname;

private Integer profiletype;

private String defaultvalue;

private String description;

private Integer version;

private Integer typeinfoid;

private Integer constraintid;

private String progname;

private String modulename;

private String functionname;

private Integer fieldlevel;

private String notes;

private Integer exodbversion;

private String keywords;

private String fieldproperties;

private Integer dbscope;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }