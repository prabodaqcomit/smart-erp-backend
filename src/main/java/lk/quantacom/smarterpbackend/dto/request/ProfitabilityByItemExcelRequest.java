package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class ProfitabilityByItemExcelRequest {

    private Integer locationId;

    private String fromDate;

    private String toDate;

    private String ItemCode;

    private Long catId;

    private Long supId;

}

