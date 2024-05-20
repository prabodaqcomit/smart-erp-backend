package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class AccountTransferResponse {

    private Long id;

    private String transferDate;

    private String transferNumber;

    private BranchNetworkResponse branch;

    private LadgerAccountResponse fromAccount;

    private Double fromAccountAfterBalance;

    private LadgerAccountResponse toAccount;

    private Double toAccountAfterBalance;

    private String remark;

    private String transferDetail;

    private Double amount;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}