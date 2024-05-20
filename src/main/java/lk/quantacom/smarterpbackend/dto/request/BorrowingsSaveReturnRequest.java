package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BorrowingsSaveReturnRequest {

    private Long id;

    private Double balanceAmount;

    private Double returnAmount;

    private String amountInWord;

    private String returnDate;

    private String borrowerName;

    private String branchId;

    private Double noSemiPyingAmount;

    private Double payingAmount;

    private String reason;

    private Integer ledgerCreditId;

    private Integer ledgerDebitId;

    private String DebitAccName;

    private String CreditAccName;


}