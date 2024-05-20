package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class GrnHeaderRequest {

    private Long supplierId;

    private Long branchId;

    private String grnDate;

    private String supInDate;

    private String supInNo;

    private String salesPerson;

    private String salesPersonTel;

    private String grnAgencyName;

    private String remarks;



    private List<GrnDetailsRequest> grnDetails;

    private List<GrnPaymentsRequest> grnPayments;

    private SupplierObLedgerDetails obLedgerDetails;

}