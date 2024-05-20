package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.entity.BranchNetwork;
import lombok.Data;
import org.springframework.core.annotation.Order;

import java.util.Date;

@Data
public class ReceiptPaymentMethodRequest {

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