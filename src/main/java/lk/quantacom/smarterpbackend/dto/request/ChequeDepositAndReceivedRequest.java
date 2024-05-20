package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ChequeDepositAndReceivedRequest {

    private String branchId;

    private String chequeDeposit;

    private Integer customerId;

    private String grandTotal;

    private Double depositAmount;

    private Long receivedChequesId;

    private String remarks;

//    private String status;

    private String depoBank;

    private String depoDate;

    private Long ledgerDebitId;

    private Long ledgerCreditId;

    private String creditAccountName;

    private String debitAccountName;

}