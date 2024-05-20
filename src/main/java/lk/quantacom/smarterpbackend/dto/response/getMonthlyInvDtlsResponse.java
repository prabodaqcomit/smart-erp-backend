package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class getMonthlyInvDtlsResponse {

    private String item_code;

    private Long branch_id;

    private String fld_InvNo;

    private String invDate;

    private Double fld_Price;

    private Double fld_CostPrice;

    private String fld_ItemDescription;

    private String fld_PayTypeCode;

    private String name;

    private Double tot;

    private Double costvalue;

    private List<getMonthlyInvDtlsListResponse> responseList;

}