package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GLSupPayDetailUpdateRequest {

    private Long id;

    private Long glPayHeaderId;

    private String supInvDate;

    private String invNumber;

    private Double pendingAmount;

    private Double payAmount;

    private Double grossAmount;

    private Double wht;

    private Double stampDuty;

    private Double netAmount;

    private String amountInWord;

    private Double invoiceAmount;

}