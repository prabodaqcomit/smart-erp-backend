package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GLPayMethodDetailsResponse {

private Long id;

 private Long glPayHeaderId;

private Long payMethodId;

private String payMethodName;

private Double Amount;

private Date ChequeDate;

private String Bank;

private String Account;

private String ChequeNumber;

private String Reference;

private Date TransferDate;

private String FromBank;

private String FromAccount;

private String ToBank;

private String ToAccount;

private String FromWallet;

private String ToWallet;

private String FromCard;

 private String createdBy;
 
 private String createdDateTime;
 
 private String modifiedBy;
 
 private String modifiedDateTime;
 
 private Deleted isDeleted;

 }