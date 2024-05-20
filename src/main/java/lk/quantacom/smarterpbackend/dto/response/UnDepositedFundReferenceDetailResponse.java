package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

import lk.quantacom.smarterpbackend.enums.Deleted;

@Data
public class UnDepositedFundReferenceDetailResponse {

    private Long id;

    private Long unDepositedFundReferenceHeaderId;

    private Integer lineNumber;

    //private Long paymentModeId;
    private TblpaymodeResponse paymentMode;

    //private Long currencyTypeId;
    private CurrencyResponse currencyType;

    private Double currencyTypeExchangeRate;

    private String paymentModeRemark;

    private Double unDepositedTotalAmount;

    private Double unDepositedBalance;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}