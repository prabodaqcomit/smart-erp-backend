package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class BankDepositHeaderSearchRequest {

    private String transactionFromDate;

    private String transactionToDate;

    private String depositNumber;

    private Long toAccountId;

    private String transactionBranchId;

    private String transactionId;

    private Double amount;

}