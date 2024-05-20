package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnReturnNoteUpdateRequest {

    private Long id;

    private String returnDate;

    private Long grnId;

    private Double inHandQty;

    private Double returnQty;

    private Double unitPrice;

    private Double itemValue;

    private Double grandTotal;

    private String remarks;

    private String batchNo;

    private String itemId;

    private Long branchId;

}