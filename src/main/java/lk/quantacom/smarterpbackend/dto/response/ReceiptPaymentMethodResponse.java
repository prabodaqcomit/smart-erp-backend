package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.Currency;
import lk.quantacom.smarterpbackend.entity.LadgerAccount;
import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReceiptPaymentMethodResponse {

    private Long id;

    private Integer lineNumber;

    private String paymentDate;

    private BranchNetworkResponse branch;

    private Long receiptHeaderId;

    private Long paymentMethodId;

    private CurrencyResponse currencyType;

    //private LadgerAccount currencyAccount;

    private Double currencyToLocalCurrencyRate;

    private String chequeDate;

    private String chequeNumber;

    private BankResponse fromBank;

    private BankResponse toBank;

    private String fromBankAccountName;

    private LadgerAccountResponse toBankAccount;

    private OnlineWalletResponse fromWallet;

    private LadgerAccountResponse toWallet;

    private String fromBankCardNumber;

    //private BankCardTypeDetailResponse toBankCard;

    private String reference;

    private Double paidAmount;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}