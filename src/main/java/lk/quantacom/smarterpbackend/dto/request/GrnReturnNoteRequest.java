package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnReturnNoteRequest {

    private String returnDate;

    private Long grnId;

    private Double inHandQty;

    private Double returnQty;

    private Double newStockQty;

    private Double unitPrice;

    private Double itemValue;

    private Double returnQtyValue;

    private Double grandTotal;

    private Double creditLimitAvlbl;

    private String remarks;

    private String batchNo;

    private String itemId;

    private Long branchId;

    private Long ledgerCreditId;

    private String debitAccName;

    private String creditAccName;



}