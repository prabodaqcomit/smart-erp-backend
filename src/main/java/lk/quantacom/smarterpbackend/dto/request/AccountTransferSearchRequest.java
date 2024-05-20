package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class AccountTransferSearchRequest {

    private String fromTransactionDate;

    private String toTransactionDate;

    private Long fromAccountId;

    private Long toAccountId;

    private Double amount;
}