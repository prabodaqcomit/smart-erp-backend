package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class IssueNoteResponse {

    private Long issueId;

    private String itemId;

    private String itemCode;

    private String batchNo;

    private Long branchId;

    private String issueDate;

    private String transferMethod;

    private String trFrom;

    private String trTo;

    private Double unitPrice;

    private Double itemValue;

    private Double issueQty;

    private Double inHandQty;

    private Double currentBalance;

    private Double grandTotal;

    private Double issueTotalQty;

    private Double itemCreditValue;

    private Double itemCashValue;

    private Double totalCreditAmount;

    private Double totalCashAmount;

    private Double cashPrice;

    private Double creditPrice;

    private Double storesInhand;

    private Double storesNew;

    private String transferBatchNo;

    private Long trFromBranchId;

    private Long trToBranchId;

    private Long colorId;

    private Long sizeId;

    private Long fitId;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}