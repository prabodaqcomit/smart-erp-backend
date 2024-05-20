package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BinCardUpdateRequest {

    private Long id;

    private String itemId;

    private String binCardDate;

    private String docType;

    private String docNo;

    private Double recQty;

    private Double isueQty;

    private Double balanceQty;

    private String batchNo;

    private Long branchId;

}