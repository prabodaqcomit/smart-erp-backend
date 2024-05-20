package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class StockAdjustResponse {

private Double grandTotal;

private Double inhandQty;

private Double latestQty;

private Double adjtQty;

private Double unitPrice;

private Double itemValue;

private String itemId;

private String stkAdjBatchNo;

private Long branchId;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }