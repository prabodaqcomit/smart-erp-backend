package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.Tblpaymode;
import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class BankDepositDetailResponse {

    private Long id;

    private Long depositHeaderId;

    private String depositNumber;

    private Integer lineNumber;

    private Long unDepositedFundReferenceId;

    private Integer unDepositedFundReferenceLineNumber;

    private String transactionTypeDescription;

    private Boolean transactionIsInvoice;

    private Boolean transactionIsReceipt;

    private Long transactionBranchId;

    private String transactionId;

    private String transactionDate;

    private Double transactionAmount;

    //private Long paymentModeId;
    private TblpaymodeResponse paymentMode;

    //private Long currencyTypeId;
    private CurrencyResponse currencyType;

    private Double currencyTypeExchangeRate;

    private String paymentModeRemark;

    private Double unDepositAmount;

    private Double depositAmount;

    private Double unDepositBalance;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}