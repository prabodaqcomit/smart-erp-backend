package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.entity.ReceiptHeader;
import lk.quantacom.smarterpbackend.entity.Tblinvhed;
import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class UnDepositedFundReferenceHeaderResponse {

    private Long id;

    private String transactionTypeDescription;

    private Boolean transactionIsInvoice;

    private Boolean transactionIsReceipt;

    //private Long transactionBranchId;
    private BranchNetworkResponse transactionBranch;

    private String transactionId;

    private String transactionDate;

    private TblinvhedResponse invoiceTransaction;

    private ReceiptHeaderResponse receiptTransaction;

    private Double transactionAmount;

    private Double unDepositedTotalAmount;

    private Double unDepositedTotalBalance;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private List<UnDepositedFundReferenceDetailResponse> unDepositedFundReferenceDetails;

}