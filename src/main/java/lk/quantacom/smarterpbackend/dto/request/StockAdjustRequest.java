package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class StockAdjustRequest {

    private Double grandTotal;

    private Double inhandQty;

    private Double latestQty;

    private Double adjtQty;

    private Double unitPrice;

    private Double itemValue;

    private String itemId;

    private String stkAdjBatchNo;

    private Long branchId;

}