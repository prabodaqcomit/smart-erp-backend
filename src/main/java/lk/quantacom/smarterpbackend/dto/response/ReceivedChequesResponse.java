package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ReceivedChequesResponse {

 private Double availbleAmount;

 private String bankCode;

 private String branchCode;

 private Long branchId;

 private String chequeAccName;

 private Double chequeAmount;

 private Date chequeDate;

 private String chequeNo;

 private String currencyType;

 private Integer customerId;

 private String depoBank;

 private Date depoDate;

 private String newChequeNo;

 private String pdOwner;

 private Date receivedDate;

 private String remarks;

 private String status;

 private Date statusDate;

 private String thisIsPd;

 private Long id;

 private String createdBy;

 private String createdDateTime;

 private String modifiedBy;

 private String modifiedDateTime;

 private Deleted isDeleted;

 }