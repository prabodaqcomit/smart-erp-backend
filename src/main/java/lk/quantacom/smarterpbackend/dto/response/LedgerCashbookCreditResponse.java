package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class LedgerCashbookCreditResponse {

    private Long id;

    private Double bankAmount;

    private String branchId;

    private Double cashAmount;

    private String description;

    private Double discountAllowed;


    private String otherAccNo;

    private String otherAccountName;

    private String prNo;

    private String vcharNo;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}