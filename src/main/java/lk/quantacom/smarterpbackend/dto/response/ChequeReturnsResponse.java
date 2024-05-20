package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class ChequeReturnsResponse {

    private Long id;

    private String bankCode;

    private String branchCode;

    private String branchId;

    private String chequeNo;

    private Date chequePayDate;

    private Integer chequeReturns;

    private Integer customerId;

    private String invoiceNum;

    private String remarks;

    private Double value;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}