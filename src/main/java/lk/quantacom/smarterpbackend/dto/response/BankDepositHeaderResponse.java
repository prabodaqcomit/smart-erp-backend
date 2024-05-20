package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class BankDepositHeaderResponse {

    private Long id;

    //private Long branchId;
    private BranchNetworkResponse branch;

    private String depositNumber;

    private String transactionDate;

    //private Long fromAccountId;
    private LadgerAccountResponse fromAccount;

    private String fromAccountCode;

    private String fromAccountName;

    private Double fromAccountBeforeBalance;

    private Double fromAccountAfterBalance;

    //private Long toAccountId;
    private LadgerAccountResponse toAccount;

    private String toAccountCode;

    private String toAccountName;

    private Double toAccountBeforeBalance;

    private Double toAccountAfterBalance;

    private String remark;

    private String transactionDetail;

    private Double totalAmount;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    List<BankDepositDetailResponse> bankDepositDetails;
}