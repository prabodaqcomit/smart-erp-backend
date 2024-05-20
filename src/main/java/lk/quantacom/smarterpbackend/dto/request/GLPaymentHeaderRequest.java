package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class GLPaymentHeaderRequest {

    private String paymentDate;

    private Integer payeeId;

    private Long branchId;

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

//    private List<GLPaymentDetailRequest> requestList;

}