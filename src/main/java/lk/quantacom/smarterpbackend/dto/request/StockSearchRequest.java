package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

@Data
public class StockSearchRequest {

    private Long branchId;

    private String searchType;

    private String searchText;

    // for print

    private String jasonList;

    private String totItemValue;

    private String totStoresQty;

    private String totShopQty;

    private String totalQty;


}
