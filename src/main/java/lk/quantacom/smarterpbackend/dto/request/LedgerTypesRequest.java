package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class LedgerTypesRequest {

private String accCategory;

private String mainAccType;

private String mainAccTypeCode;

private String subAccType;

private String subAccTypeCode;

private String subAccSinhala;

private String mainAccTypeSinhala;

 }