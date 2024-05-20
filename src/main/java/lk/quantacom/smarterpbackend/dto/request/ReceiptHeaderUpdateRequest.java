package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class ReceiptHeaderUpdateRequest {

    private Long Id;

    private String transactionDate;

    private Long branchId;

    private String remark;

    private String receiptDetail;

    private Long payerContactId;

    private Long customerId;

    private List<ReceiptDetailUpdateRequest> receiptDetails;

    private List<ReceiptPaymentMethodUpdateRequest> paymentMethods;
}