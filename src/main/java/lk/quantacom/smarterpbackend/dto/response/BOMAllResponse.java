package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BOMAllResponse {

    private TblbomheaderResponse bomHeader;

    private List<TblbomaccessoryResponse> bomAccessory;

    private List<TblbomsizeResponse> bomSize;

    private List<TblbomfitResponse> bomFit;

    private List<BomMainMaterialResponse> bomMainMaterial;

}
