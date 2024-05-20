package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BomDetailsRequest {

    private String subItemCode;

    private String batchNo;

    private Double unitCost;

    private String unit;

    private Double quantity;

    private Double cost;

    private String invType;



}