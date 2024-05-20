package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class MainNavButtonsResponse {

private Long id;

private String rootTabName;

private Integer orderNo;

private String caption;

private String iconHex;

private String iconStyle;

private String rout;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }