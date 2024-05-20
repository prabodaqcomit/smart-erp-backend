package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class TblmenudetailResponse {

private String menuId;

private String menuDesc;

private String menuRight;


private String createdBy;

private Date createdDateTime;

private String modifiedBy;

private Date modifiedDateTime;

 
 private Deleted isDeleted;

 }