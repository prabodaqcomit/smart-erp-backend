package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TblbomaccessoryResponse {

    private Long id;

    private String bdAccessoryBatchId;

    private String bdAccessoryBranchId;

    private String bdAccessoryId;

    private String bdDesc;

    private Integer bdId;

    private Double bdQty;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;


    private Integer bdAccColorId;

    private Integer bdAccSizeId;

    private Integer bdAccFitId;

}