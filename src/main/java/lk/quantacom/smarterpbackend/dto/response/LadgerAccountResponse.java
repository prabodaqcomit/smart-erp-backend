package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class LadgerAccountResponse {

private String accountCategory;

private String accType;

private String subAccType;

private String accNo;

private String accName;

private Double obBalance;

private Double currentBalance;

private String generatedNo;

private String ownNo;

private Boolean isDefault;

private Long branchId;

private String branchName;

    private String drCr;

    private Double amount;

    private Status status;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private String accDate;

}