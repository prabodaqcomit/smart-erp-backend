package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ReceiptPaymentMethodUpdateRequest {

    private Long Id;

    private Integer lineNumber;

    private String paymentDate;

    private Long paymentMethodId;

    private Long currencyTypeId;

    private Double currencyToLocalCurrencyRate;

    //private String currencyAccountCode;

    private String chequeDate;

    private String chequeNumber;

    private Long fromBankId;

    private Long toBankId;

    private String fromBankAccountName;

    private Long toBankAccountId;

    private Long fromWalletId;

    private Long toWalletId;

    private String fromBankCardNumber;

    private String reference;

    private Double paidAmount;

}