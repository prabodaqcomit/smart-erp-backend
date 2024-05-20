package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class PayeeDetailsResponse {

private Long id;

private String name;

private String gender;

private String add1;

private String add2;

private String add3;

private String location;

private Integer mobile;

private String email;

private String nic;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }