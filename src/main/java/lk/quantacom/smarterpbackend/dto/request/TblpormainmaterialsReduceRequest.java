package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblpormainmaterialsReduceRequest {


    private String porMainItemBatchId;

    private Long porMainItemBranchId;

    private String porMainItemCode;

    private Long colourId;

    private Long sizeId;

    private Long fitId;

    private Double porItemQty;

    //private String porMainItemId;
//
    private Double porMainItemQty;

}