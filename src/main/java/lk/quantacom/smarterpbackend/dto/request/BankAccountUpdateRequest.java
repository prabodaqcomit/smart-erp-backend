package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BankAccountUpdateRequest {

private Long id;

private String accDate;

private String accName;

private String accNo;

private Double bankCode;

private String bankLedgerId;

private String bankName;

private Double branchCode;

private String branchName;

private Double currentBalance;

private String deleteMe;

private Double obBalance;

 }