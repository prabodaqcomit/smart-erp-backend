package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class DiningTableTmpHeadUpdateRequest {

    private Long id;

    private Long tableId;

    private String paymentType;

    private Boolean kotPrinted;

    private Boolean botPrinted;

    private Integer invStatus;

    private String invoiceNo;

    private DiningTableTmpDetailsRequest itemDetails;
}