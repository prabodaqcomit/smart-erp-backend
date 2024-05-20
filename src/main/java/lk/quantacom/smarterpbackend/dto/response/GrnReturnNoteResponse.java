package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class GrnReturnNoteResponse {

    private String returnDate;

    private Long grnId;

    private Long returnId;

    private Double inHandQty;

    private Double returnQty;

    private Double unitPrice;

    private Double itemValue;

    private Double grandTotal;

    private String remarks;

    private String batchNo;

    private String itemId;

    private Long branchId;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;
}