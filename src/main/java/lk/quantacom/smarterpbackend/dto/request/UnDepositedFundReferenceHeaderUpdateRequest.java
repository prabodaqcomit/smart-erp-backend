package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class UnDepositedFundReferenceHeaderUpdateRequest {

    private Long id;

    private String transactionTypeDescription;

    private Boolean transactionIsInvoice;

    private Boolean transactionIsReceipt;

    private String transactionBranchId;

    private String transactionId;

    private String transactionDate;

    private Double transactionAmount;

    private Double unDepositedTotalAmount;

    private Double unDepositedTotalBalance;

}