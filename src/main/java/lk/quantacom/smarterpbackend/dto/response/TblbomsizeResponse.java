package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class TblbomsizeResponse {

    private Long id;

    private String bsDesc;

    private Integer bsId;

    private Integer bsSizeId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private String bsSizeDesc;

}