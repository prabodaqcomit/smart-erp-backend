package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblpormainmaterialsApprovalRequest {

private Integer porId;

private Integer porMainColorId;

private String porMainItemBatchId;

private String porMainItemBranchId;

private String porMainItemCode;

private String porMainItemDesc;

private Double porMainItemQty;

 }