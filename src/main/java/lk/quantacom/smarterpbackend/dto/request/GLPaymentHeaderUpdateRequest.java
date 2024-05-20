package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class GLPaymentHeaderUpdateRequest {

    private Long id;

    private String paymentDate;

    private Long branchId;

    private Integer payeeId;

    private String payeeName;

    private String voucherNumber;

    private String paymentDetails;

    private String remark;

    private Long glPayModId;

    private String glPayModName;

    private Double glPayModAmount;

    private Double glTotalAmount;

    private String glTotalAmtWord;

    private Long glPaymentDetailId;

    private String glPaymentDetailName;

}