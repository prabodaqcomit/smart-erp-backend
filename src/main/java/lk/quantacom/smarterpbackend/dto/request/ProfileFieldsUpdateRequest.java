package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ProfileFieldsUpdateRequest {

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

 }