package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblpaydtlRequest {

private String fldDate;

private String fldLocation;

private String fldInvno;

private String fldPaymode;

private String fldPaytype;

private String fldPaytypecode;

private String fldCrdcardno;

private Double fldFcuramt;

private Double fldPaytypeamt;

private Double fldExchngrate;

private Boolean fldDatatransfer;

private String fldGvdisno;

private String fldCreditno;

private String fldComno;

private String fldCashierid;

private String fldEncrkey;

private Boolean blnconfirmed;

private Boolean blnmodechange;

private Boolean fldCancel;

private Double fldAccessupdate;

private String fldMiddlewareuuid;

private Integer fldMiddlewarestatus;

private String fldMiddlewareuuidCreditcust;

private Integer fldMiddlewarestatusCreditcust;

private String fldMiddlewareuuidBw;

private Integer fldMiddlewarestatusBw;

private String createdon;

 private Integer fldInvpaydtlkey;
 }