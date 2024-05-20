package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;


@Data
public class TblpormainmaterialsRequest {

    private String porId;

    private Integer porMainColorId;

    private String porMainItemBatchId;

    private String porMainItemBranchId;

    private Integer porMainSizeId;

    private Integer porMainFitId;

    private String porMainItemCode;

    private String porMainItemDesc;

    private Double porMainItemQty;

}