package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class DiningTableTmpHeadResponse {

    private Long id;

    private Long tableId;

    private String paymentType;

    private Boolean kotPrinted;

    private Boolean botPrinted;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Integer invStatus;

    private String invoiceNo;

    List<DiningTableTmpDetailsResponse> itemDetails;
}