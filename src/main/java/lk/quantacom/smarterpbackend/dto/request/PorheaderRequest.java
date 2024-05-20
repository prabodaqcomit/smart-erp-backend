package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PorheaderRequest {

    private TblporheaderRequest tblporheaderRequest;

    private List<TblporaccessoriesRequest> tblporaccessoriesRequest;

    private List<TblporsizesRequest> tblporsizesRequest;

    private List<TblporothercostsRequest> tblporothercostsRequest;
}