package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblbomaccessoryRequest {

    private String bdAccessoryBatchId;

    private String bdAccessoryBranchId;

    private String bdAccessoryId;

    private String bdDesc;

    private Integer bdId;

    private Double bdQty;

    private Integer bdAccColorId;

    private Integer bdAccSizeId;

    private Integer bdAccFitId;

}