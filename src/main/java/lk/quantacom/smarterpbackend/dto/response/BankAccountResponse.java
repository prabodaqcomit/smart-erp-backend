package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountResponse {

    private Long id;

    private Date accDate;

    private String accName;

    private String accNo;

    private Double bankCode;

    private String bankLedgerId;

    private String bankName;

    private Double branchCode;

    private String branchName;

    private Double currentBalance;

    private String deleteMe;

    private Double obBalance;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}