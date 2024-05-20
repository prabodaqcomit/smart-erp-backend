package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class TblporheaderReduceFromStockRequest {

    private String porId;

    private List<TblpormainmaterialsReduceRequest> tblpormainmaterialsRequests;

    private List<TblporaccessoriesReduceRequest> tblporaccessoriesRequests;

}