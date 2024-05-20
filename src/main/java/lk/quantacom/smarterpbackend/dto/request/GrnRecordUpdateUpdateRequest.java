package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GrnRecordUpdateUpdateRequest {

    private String barcodeNo;

    private String batchNo;

    private Long branchId;

    private String expireDate;

    private Long grnIddd;

    private Long id;

    private String itmeIddd;

    private Double stkQty;

    private Long supplierIddd;

    private Double unitPrice;

    private String updateDate;

    private String updateTime;

}