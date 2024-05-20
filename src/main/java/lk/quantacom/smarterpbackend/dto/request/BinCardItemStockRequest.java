package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BinCardItemStockRequest {

    private String fromDate;

    private String toDate;

    private String itemCode;

    private Long branchId;

    private Long catId;

}