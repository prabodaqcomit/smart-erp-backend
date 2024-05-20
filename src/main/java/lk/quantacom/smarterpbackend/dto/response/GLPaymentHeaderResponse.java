package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class GLPaymentHeaderResponse {

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

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private List<GLPaymentDetailResponse> glPaymentDetailResponseList;

    private List<GLPayMethodDetailsResponse> glPayMethodDetailsResponseList;

    private List<GLSupPayDetailResponse> glSupPayDetailResponseList;

}