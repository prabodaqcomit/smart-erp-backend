package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblpaymodeRequest {

private String paymodeCode;

private String paymodeDesc;

private String paymodeFormat;

private String paymodeCardformat;

private Double paymodeExchangerate;

private Double paymodeCommission;

private Boolean paymodeActive;

private Integer paymodeOrder;

private String paymodeIsrCode;

private String paymodePath;

private String paymodeFOrder;

private Boolean paymodeGvEnable;

private Boolean paymodeIsDetail;

private Boolean paymodeIsCreditcard;

private Boolean paymodeIsForeigncurrency;

private Boolean paymodeIsNexus;

private Boolean paymodeDcEnable;

 }