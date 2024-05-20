package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ProfileFormsUpdateRequest {

private Integer profileid;

private Integer staffno;

private String layoutname;

private String lastusedlayout;

private String modulename;

private String widgetdata;

private String layoutdata;

private String zoomsettings;

private String widgetsettings;

 }