package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class GlSubAccTypeResponse {

private Long id;

private String categoty;

private Long categoryId;

private Integer categoryAccount;

private Integer mainAccount;

private Long mainAccountId;

private String mainAccName;

private String name;

private Integer account;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }