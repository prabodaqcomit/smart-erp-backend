package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ReceiptDetailUpdateRequest {

    private Long id;

    //private Long receiptHeaderId;

    //private String receiptNumber;

    private Integer lineNumber;

    private Long accountId;

    private Long invoiceBranchId;

    private String invoiceNumber;

    private Double paidAmount;

    private Double withholdingTaxAmount;

    private Double stampDutyAmount;

    private Double netAmount;

}