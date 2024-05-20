package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class BinCardStockResponse {

    private String itemCode;

    private String itemName;

    private String batchNo;

    private List<BinCardSizeStockResponse> responseList;

}