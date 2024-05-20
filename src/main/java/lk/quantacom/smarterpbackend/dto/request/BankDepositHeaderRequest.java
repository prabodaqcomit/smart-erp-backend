package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.entity.BankDepositDetail;
import lombok.Data;

import java.util.List;

@Data
public class BankDepositHeaderRequest {

    private Long branchId;

    //private String depositNumber;

    private Long fromAccountId;

//    private Double fromAccountBeforeBalance;
//
//    private Double fromAccountAfterBalance;

    private Long toAccountId;

//    private String toAccountCode;

//    private String toAccountName;

//    private Double toAccountBeforeBalance;
//
//    private Double toAccountAfterBalance;

    private String transactionDate;

    private String remark;

    private String transactionDetail;

//    private Double totalAmount;

    private List<BankDepositDetailRequest> bankDepositDetails;
}