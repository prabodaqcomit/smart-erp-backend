package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class OpeningBalanceResponse {

    private String itemId;

    private String itemName;

    private String batchNo;

    private Long branchId;

    private String obDate;

    private Double obQty;

    private Double unitPrice;

    private Double itemValue;

    private Double grandTotal;

    private String expireDate;

    private Integer obNo;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}