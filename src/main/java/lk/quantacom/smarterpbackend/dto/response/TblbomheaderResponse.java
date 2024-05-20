package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class TblbomheaderResponse {

    private Integer bomCategoryId;

    private String bomCreatedBranchId;

    private Date bomCreatedDate;

    private String bomCreatedUserId;

    private String bomDescription;

    private Integer bomId;

    private Boolean bomIsDelete;

    private String bomModifiedBranchId;

    private Date bomModifiedDate;

    private String bomModifiedUserId;

    private Boolean bomSleeveLong;

    private Boolean bomSleeveShort;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;


    private Double bomMainItemQty;

    private Integer bomMainSizeId;

    private Integer bomMainFitId;

}