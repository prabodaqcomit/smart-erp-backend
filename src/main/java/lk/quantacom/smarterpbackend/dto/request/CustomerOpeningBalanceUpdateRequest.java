package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CustomerOpeningBalanceUpdateRequest {

private Integer customerOpeningBalanceId;

private String fldCustomerId;

private String fldDate;

private Double fldDueBalance;

private Double fldNetAmount;

private Double fldPaidAmount;

private String fldTime;

 }