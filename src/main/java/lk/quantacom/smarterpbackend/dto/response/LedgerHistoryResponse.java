package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class LedgerHistoryResponse {

private Long branchId;

private Long ladgerAccountId;

private String updateFram;

private String updateFramDocNo;

private String remark;

private Double updateBalance;

private String transType;

private Double creditAmount;

private Double debitAmount;

private String creditColumnName;

private String debitColumnName;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }