package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class TblmenudetailRequest {

private String menuId;

private String menuDesc;

private String menuRight;

private String createdBy;

private Deleted isDeleted;

 }