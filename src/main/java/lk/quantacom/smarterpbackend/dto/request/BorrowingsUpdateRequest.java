package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BorrowingsUpdateRequest {

private Long id;

private String amountInWord;

private Double balanceAmount;

private String borrowDate;

private String borrowerName;

private String branchId;

private Double noSemiPyingAmount;

private Double payingAmount;

private String reason;

private Double returnAmount;

private String status;

 }