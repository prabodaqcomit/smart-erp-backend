package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class BomHeaderRequest {

    private String bomItemCode;

    private String bomDescription;

    private String unit;

    private Double markup;

    private Boolean onCost;

    private Double costPriceDining;

    private Double costPriceTakeAway;

    private Double sellPrice;

    private Long categoryId;

    private Long branchId;

    private Boolean isKOT;

    private Boolean isBOT;

    List<BomDetailsRequest> bomDetails;

}