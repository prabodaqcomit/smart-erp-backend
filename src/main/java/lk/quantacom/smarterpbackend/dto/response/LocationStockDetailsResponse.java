package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class  LocationStockDetailsResponse {

    public String batch_no;

    public String color;
    public Long color_id;

    public String fit;
    public Long fit_id;

    public String size;
    public Long size_id;

    public String item_code;

    public String item_name;

    public Double totStores_qty;

    public Long branch_id;

    public String branch_name;

    public List<StockLocationListResponse> responseList;

}