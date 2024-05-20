package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class userAuthLimitsResponse {

private Long id;

private String username;

private Double poAuthLimit;

private String poAuthNextUser;

private Double grnAuthLimit;

private String grnAuthNextUser;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }