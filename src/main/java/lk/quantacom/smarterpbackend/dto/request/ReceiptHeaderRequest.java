package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.entity.ReceiptPaymentMethod;
import lombok.Data;

import java.util.List;

@Data
public class ReceiptHeaderRequest {

    //private String receiptNumber;

    private String transactionDate;

    private Long branchId;

    private String remark;

    private String receiptDetail;

    private Long payerContactId;

    private Long customerId;

    //private Double totalAmount;

    //private String totalAmountInWord;

    private List<ReceiptDetailRequest> receiptDetails;

    private List<ReceiptPaymentMethodRequest> paymentMethods;

}