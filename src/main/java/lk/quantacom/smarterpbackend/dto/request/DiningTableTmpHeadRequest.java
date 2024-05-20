package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class DiningTableTmpHeadRequest {

    private Long tableId;

    private String paymentType;

    private Boolean kotPrinted;

    private Boolean botPrinted;

    private DiningTableTmpDetailsRequest itemDetails;

}