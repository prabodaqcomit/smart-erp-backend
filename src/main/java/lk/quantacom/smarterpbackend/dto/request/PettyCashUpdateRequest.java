package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class PettyCashUpdateRequest {

    private Long id;

    private Double amountReceived;

    private String branchId;

    private String description;

    private Double entertainment;

    private Double foods;

    private Double grandTotal;

    private Integer idpettyCash;

    private Double noSemiGrandTotal;

    private Double noSemiPayingAmount;

    private Double otherEx;

    private String payeeName;

    private Double payingAmount;

    private String pettyCashDate;

    private Double postage;

    private String reference;

    private Double stationary;

    private String timeTake;

    private Double travel;

    private String voucherNo;

}