package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class LedgerPettyCashBookSaveListRequest {

// private List<LedgerPettyCashBookRequest> ledgerPettyCashBookRequests;


    private String branchId;

    private String cartage;

    private String desctription;

    private Double entartainment;

    private Double foods;

    private Double grandTotal;

    private Double ledgerAcc;

    private Integer ledgerPettyCashBookId;

    private String payeeName;

    private Double payingAmount;

    private String pettyCashDate;

    private Double postage;

    private Double stationary;

    private String timeTake;

    private Double travel;

    private String voucherNo;

    private String debitAccName;

    private String creditAccName;

    private Long ledgerDebitId;

    private Long ledgerCreditId;

}