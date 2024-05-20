package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class LedgerBankHistoryResponse {

    private Long id;

    private Double amount;

    private Integer bankAccountId;

    private Double creditAccAmount;

    private Integer creditAccId;

    private Double currentBalance;

    private Double debitAccAmount;

    private Integer debitAccId;

    private String depoType;

    private Integer framDocNo;

    private String framName;


    private String transaction;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}