package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporaccessoriesUpdateRequest {

private Long id;

private String porAccItemBatchId;

private String porAccItemBranchId;

private String porAccItemCode;

private Double porAccItemConsumptionQty;

private String porAccItemDesc;

private Double porAccItemQty;

private String porId;

 }