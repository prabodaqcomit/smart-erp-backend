package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import javax.persistence.Column;

@Data
public class GrnRecordUpdateRequest {

    private String barcodeNo;

    private String batchNo;

    private Long branchId;

    private String expireDate;

    private Long grnId;

    //private Integer idgrnRecordUpdate;

    private String itmeId;

    private Double stkQty;

    private Long supplierIddd;

    private Double unitPrice;

    private String updateDate;

    private String updateTime;

   // private StockRequest stockRequest;

    Long colorId;

    Long sizeId;

    Long fitId;

    //private GrnDetailsUpdateRequest grnDetailsRequest;

}