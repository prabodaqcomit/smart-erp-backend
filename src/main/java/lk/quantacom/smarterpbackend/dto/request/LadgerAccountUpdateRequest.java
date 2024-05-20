package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.Date;

@Data
public class LadgerAccountUpdateRequest {

    private Long id;

    private String accountCategory;

    private String accType;

    private String subAccType;

    private String accNo;

    private String accName;

    private Double obBalance;

    private Double currentBalance;

    private String generatedNo;

    private String ownNo;

    private Boolean isDefault;

    private Long branchId;

    private String drCr;

    private Double amount;

    private Boolean status;

    private String accDate;
}