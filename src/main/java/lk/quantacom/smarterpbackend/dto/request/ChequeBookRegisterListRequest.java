package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ChequeBookRegisterListRequest {

private String bankAccountId;

private String chequeBookNo;

private Integer chequeNo;

private String registerDate;


 }