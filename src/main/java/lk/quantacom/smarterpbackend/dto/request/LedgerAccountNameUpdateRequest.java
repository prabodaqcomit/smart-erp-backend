package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerAccountNameUpdateRequest {

    private Long id;

    private String accName;

    private String accNo;

    private Double bankCode;

    private String bankName;

    private Double branchCode;

    private String branchName;

    private Long ledgerAccountId;


}