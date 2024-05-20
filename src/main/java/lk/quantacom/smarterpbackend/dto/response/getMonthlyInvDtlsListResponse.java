package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class getMonthlyInvDtlsListResponse {


    private String batch_no;

    private Long color_id;

    private Long fit_id;

    private Long size_id;

    private Double fld_Qty;

}