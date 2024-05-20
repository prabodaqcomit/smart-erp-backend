package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class getMontlyInvRequest {

    private String fromDate;

    private String toDate;

    private Long supId;

    private Long cusId;

    private Long catId;

    private String itemCode;

    private Long locationId;

}