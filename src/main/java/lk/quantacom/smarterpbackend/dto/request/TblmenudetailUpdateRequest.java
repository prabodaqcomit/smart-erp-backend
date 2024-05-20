package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class TblmenudetailUpdateRequest {

private String menuId;

private String menuDesc;

private String menuRight;

private Deleted isDeleted;

private String modifiedBy;

 }