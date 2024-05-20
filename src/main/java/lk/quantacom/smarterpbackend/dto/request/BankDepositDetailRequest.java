package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankDepositDetailRequest {

    private Integer lineNumber;

    private Long unDepositedFundReferenceId;

    private Integer unDepositedFundReferenceLineNumber;

//    private String transactionTypeDescription;
//
//    private Boolean transactionIisInvoice;
//
//    private Boolean transactionIsReceipt;
//
//    private Long transactionBranchId;
//
//    private String transactionId;
//
//    private String transactionDate;
//
//    private Double transactionAmount;

//    private Long paymentModeId;
//
//    private Long currencyTypeId;
//
//    private Double currencyTypeExchangeRate;

    private String lineRemark;

//    private Double unDepositAmount;

    private Double depositedAmount;

//    private Double unDepositBalance;

}