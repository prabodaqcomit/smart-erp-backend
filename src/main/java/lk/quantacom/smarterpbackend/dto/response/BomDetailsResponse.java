package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class BomDetailsResponse {

    private Long id;

    private String subItemCode;

    private String batchNo;

    private String itemDesc;

    private Double unitCost;

    private String unit;

    private Double quantity;

    private Double cost;

    private String invType;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}