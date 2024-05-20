package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class OpeningBalanceRequest {

    private String itemId;

    private String batchNo;

    private Long branchId;

    private String obDate;

    private Double obQty;

    private Double unitPrice;

    private Double itemValue;

    private Double grandTotal;

    private String expireDate;

    private StockRequest stockRequest;

    private BinCardRequest binCardRequest;

}