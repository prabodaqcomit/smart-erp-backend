package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class DiningTableTmpDetailsResponse {

    private Long id;

    private Long headId;

    private Long tableId;

    private String itemCode;

    private String batchNo;

    private String itemDesc;

    private Double selectedPrice;

    private Double quantity;

    private Boolean isKOT;

    private Boolean isBOT;

    private Integer invStatus;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}