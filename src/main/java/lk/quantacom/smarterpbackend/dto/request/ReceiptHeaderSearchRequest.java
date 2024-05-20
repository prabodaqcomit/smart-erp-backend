package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ReceiptHeaderSearchRequest {

    private String receiptNumber;

    private String transactionFromDate;

    private String transactionToDate;

    //private String payerName;

    private Long payerId;

    private Double receiptAmount;

    private Long payModeId;
}