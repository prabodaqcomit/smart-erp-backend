package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GLPaymentDetailResponse {

    private Long id;

    private Long glPayHeaderId;

    private Integer accCode;

    private String accName;

    private Double ledgerAmount;

    private Double wht;

    private Double stampDuty;

    private Double netAmount;

    private Double totalAmount;

    private String amountInWord;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}