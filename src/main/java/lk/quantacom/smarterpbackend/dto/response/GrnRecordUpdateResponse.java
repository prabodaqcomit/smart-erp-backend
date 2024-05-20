package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;


@Data
public class GrnRecordUpdateResponse {

    private String barcodeNo;

    private String batchNo;

    private String branchId;

    private Date expireDate;

    private Long grnIddd;

    private Integer idgrnRecordUpdate;

    private String itmeIddd;

    private Double stkQty;

    private String supplierIddd;

    private Double unitPrice;

    private Date updateDate;

    private Date updateTime;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}