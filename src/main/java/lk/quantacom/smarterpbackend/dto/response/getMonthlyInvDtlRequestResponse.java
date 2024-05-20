package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

@Data
public class getMonthlyInvDtlRequestResponse {

    private String fld_InvNo;

    private Double fld_Price;

    private Double fld_CostPrice;

}