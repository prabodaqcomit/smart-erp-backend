package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class LedgerBankAccountDebitResponse {

    private Long id;

    private Long branchId;

    private Double cashAmount;

    private Date date;

    private String description;

    private Integer ledgerBankAccountDebitId;

    private String otherAccNo;

    private String otherAccountName;

    private String prNo;

    private Date time;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}