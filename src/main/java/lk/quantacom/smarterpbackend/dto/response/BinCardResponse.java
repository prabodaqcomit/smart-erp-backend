package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class BinCardResponse {

private String itemId;

private String binCardDate;

private String docType;

private String docNo;

private Double recQty;

private Double isueQty;

private Double balanceQty;

private String batchNo;

private Long branchId;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }