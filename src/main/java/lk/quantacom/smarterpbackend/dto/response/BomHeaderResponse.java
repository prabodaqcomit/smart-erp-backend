package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class BomHeaderResponse {

    private Long id;

    private String bomItemCode;

    private String bomDescription;

    private String unit;

    private Double markup;

    private Boolean onCost;

    private Double costPriceDining;

    private Double costPriceTakeAway;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Double sellPrice;

    private Long categoryId;

    private Long branchId;

    private Boolean isKOT;

    private Boolean isBOT;

    private List<BomDetailsResponse> detailsList;

}