package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class CCardDetailUpdateRequest {

private Long id;

private String cCardDetCode;

private String cCardHedCode;

private String cCardFormat;

private String cCardStartingStr;

 }