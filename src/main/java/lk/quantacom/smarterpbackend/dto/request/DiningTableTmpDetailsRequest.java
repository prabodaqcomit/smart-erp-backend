package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class DiningTableTmpDetailsRequest {


    private Long tableId;

    private String itemCode;

    private String batchNo;

    private Double selectedPrice;

    private Double quantity;

    private Boolean isKOT;

    private Boolean isBOT;

    private Integer invStatus;

}