package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ProfitabilityByItemRequest {

    private Integer locationId;

    private String fromDate;

    private String toDate;

    private String ItemCode;

    private String types;


}