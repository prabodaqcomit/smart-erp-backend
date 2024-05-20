package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

@Data
public class ProfitByItemResponse {

    private String fld_itemcode;

    private String fld_itemdescription;

    private Double fld_qty;

    private Double fld_price;

    private Double fld_sellingprice;

    private Double fld_sellingamount;

    private Double fld_costprice;

    private Double fld_costamount;

    private Double gp;

    private Double gpmargin;

}