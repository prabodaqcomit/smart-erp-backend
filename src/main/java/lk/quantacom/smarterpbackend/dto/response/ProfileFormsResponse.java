package lk.quantacom.smarterpbackend.dto.response;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ProfileFormsResponse {

private Integer profileid;

private Integer staffno;

private String layoutname;

private String lastusedlayout;

private String modulename;

private String widgetdata;

private String layoutdata;

private String zoomsettings;

private String widgetsettings;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }