package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class BinCardItemStockResponse {

    private String itemCode;

    private String itemName;

    private String batchNo;


}