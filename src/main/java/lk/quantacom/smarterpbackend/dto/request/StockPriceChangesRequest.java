package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class StockPriceChangesRequest {

    private String itemId;

    private Long colorId;

    private Long sizeId;

    private Long fitId;

    private Long branchId;

    private String batchNo;


}