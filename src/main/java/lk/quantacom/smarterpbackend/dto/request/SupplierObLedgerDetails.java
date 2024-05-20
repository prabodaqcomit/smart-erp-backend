package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class SupplierObLedgerDetails {

    private Long creditAccId;

    private String creditAccName;

    private Long debitAccId;

    private String debitAccName;

    private Long branchId;
}
