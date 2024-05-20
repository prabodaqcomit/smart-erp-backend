package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CustomerOpeningBalanceLedgerRequest {

    private String fldCustomerId;

    private String fldDate;

    private Double fldDueBalance;

    private Double fldNetAmount;

    private String fldTime;

    private Integer customerOpeningBalanceId;


    private Long branchId;

    //history request

    private Long ladgerCrediAccountId;

    private Long ladgerDebitAccountId;

    private String updateFramDocNo;

    private String remark;

    private Double updateBalance;

    private String transType;

    private Double creditAmount;

    private Double debitAmount;

    private String creditColumnName;

    private String debitColumnName;

    //ledger Sales Journam request

    private Double cashAmount;

    private String date;

    private String description;

    private String invoiceDate;

    private String invoiceNo;

    private Integer ledgerSalesJournalId;

    private String prNo;

    private String time;

}