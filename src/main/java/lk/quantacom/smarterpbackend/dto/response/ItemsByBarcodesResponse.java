package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class ItemsByBarcodesResponse {

    private List<BarcodesResponse> barcodesResponseList;

    private List<ItemMasterResponse> itemMasterResponseList;

}