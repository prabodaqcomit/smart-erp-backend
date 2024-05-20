package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GiftVoucherUpdateRequest {

private Long id;

private String batchID;

private Integer fldMiddlewareStatus;

private String fldMiddlewareUUID;

private Double giftVoucherAmount;

private String giftVoucherArticleNo;

private String giftVoucherBatchNo;

private String giftVoucherCusName;

private String giftVoucherDate;

private String giftVoucherDateSold;

private String giftVoucherDateUsed;

private String giftVoucherExpiry;

private String GiftVoucherFlag;

private String giftVoucherInvoiceNo;

private String giftVoucherInvoiceNoUsed;

private String giftVoucherLocation;

private String giftVoucherLocationUsed;

private String giftVoucherNo;

private Boolean giftVoucherSAPUpdated;

private String giftVoucherSerialNo;

private Boolean syncStatus;

 }