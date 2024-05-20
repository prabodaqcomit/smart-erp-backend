package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class StockTransferRequest {

 private Long fromBranchId;

 private Long toBranchId;

 private Integer isHo;

 private List<StockTransferUpdateRequest> updateRequestList;


}