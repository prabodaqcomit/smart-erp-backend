package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class ReceiptHeaderResponse {

    private Long id;

    private String receiptNumber;

    private Date transactionDate;

    private BranchNetworkResponse branch;

    private String remark;

    private String receiptDetail;

    //private Long payerContactId;
    private ContactResponse payerContact;

    //private Long customerId;
    private CustomerResponse customer;

    private Double totalAmount;

    private String totalAmountInWord;

    private List<ReceiptDetailResponse> receiptDetails;

    private List<ReceiptPaymentMethodResponse> paymentMethods;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}