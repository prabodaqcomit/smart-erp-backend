package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GLPaymentDetailRequest {

    private Long glPayHeaderId;

    private Integer accCode;

    private String accName;

    private Double ledgerAmount;

    private Double wht;

    private Double stampDuty;

    private Double netAmount;

    private Double totalAmount;

    private String amountInWord;

}