package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class TblpaymodeResponse {

private Long id;

 private String paymodeCode;

 private String paymodeDesc;

 private String paymodeFormat;

 private String paymodeCardformat;

 private Double paymodeExchangerate;

 private Double paymodeCommission;

 private Boolean paymodeActive;

 private Integer paymodeOrder;

 private String paymodeIsrCode;

 private String paymodePath;

 private String paymodeFOrder;

 private Boolean paymodeGvEnable;

 private Boolean paymodeIsDetail;

 private Boolean paymodeIsCreditcard;

 private Boolean paymodeIsForeigncurrency;

 private Boolean paymodeIsNexus;

 private Boolean paymodeDcEnable;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }