package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class ChequeDepositResponse {

    private Long id;

    private String branchId;

    private String chequeDeposit;

    private Integer customerId;

    private String grandTotal;

    private Double depositAmount;


    private Integer lineNo;

    private Long receivedChequesId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private String date;

}