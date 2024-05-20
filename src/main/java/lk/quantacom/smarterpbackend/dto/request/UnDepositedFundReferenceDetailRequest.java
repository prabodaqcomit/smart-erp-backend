package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class UnDepositedFundReferenceDetailRequest {

private Long unDepositedFundReferenceHeaderId;

private Integer lineNumber;

private Long paymentModeId;

private Long currencyTypeId;

private Double currencyTypeExchangeRate;

private String paymentModeRemark;

private Double unDepositedTotalAmount;

private Double unDepositedBalance;

 }