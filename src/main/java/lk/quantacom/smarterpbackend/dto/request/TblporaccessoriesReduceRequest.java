package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporaccessoriesReduceRequest {

    private String porAccItemBatchId;

    private Long porAccItemBranchId;

    private String porAccItemCode;

    private Double porAccItemQty;

    private Long colourId;

    private Long sizeId;

    private Long fitId;

    private Double porItemQty;

}