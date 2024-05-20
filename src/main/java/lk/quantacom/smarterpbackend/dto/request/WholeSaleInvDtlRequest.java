package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import javax.persistence.Column;

@Data
public class WholeSaleInvDtlRequest {

    private String invno;

    private String itemCode;

    private String ItemName;

    private Integer sizeId;

    private String sizeName;

    private Double QtyByitem;

    private Double sizeQty;

    private Double mrp;

    private Double disPrecentage;

    private Double disAmount;

    private Double amount;

    private Long color;

    private Long fit;

    private String batchNo;

    private Double itemCost;


}