package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class OrderFormResponse {

    private Long id;

    private Integer orderId;

    private Integer lineNo;

    private Integer branchId;

    private String itemCode;

    private String itemDesc;

    private Integer categoryCode;

    private Integer customerId;

    private Date orderDate;

    private Date orderTime;

    private Double sellPrice;

    private Double orderQty;

    private Double itemValue;

    private Double grandTotal;

    private String status;

    private String userDetId;

    private String batchNo;

    private String closesales;

    private Boolean isdiscountenable;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}