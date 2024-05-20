package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class GrnHeaderResponse {

    private Long supplierId;

    private String supplierName;

    private Long branchId;

    private String branchName;

    private String grnDate;

    private String supInDate;

    private String supInNo;

    private String salesPerson;

    private String salesPersonTel;

    private String grnAgencyName;

    private String remarks;

    private List<GrnDetailsResponse> grnDetails;

    private List<GrnPaymentsResponse> grnPayments;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}