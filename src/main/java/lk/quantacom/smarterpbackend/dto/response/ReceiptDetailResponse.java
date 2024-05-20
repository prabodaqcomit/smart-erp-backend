package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class ReceiptDetailResponse {

    private Long id;

    private Long receiptHeaderId;

    private String receiptNumber;

    private Integer lineNumber;

    private Long accountId;

    private String accountCode;

    private String accountName;

    private Long invoiceBranchId;

    private String invoiceNumber;

    private Date InvoiceDateTime;

    private Double invoiceAmount;

    private Double invoicePendingAmount;

    private Double paidAmount;

    private Double withholdingTaxAmount;

    private Double stampDutyAmount;

    private Double netAmount;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}