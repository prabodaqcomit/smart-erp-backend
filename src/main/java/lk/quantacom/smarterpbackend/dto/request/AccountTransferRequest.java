package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class AccountTransferRequest {

    private String transferDate;

    private Long branchId;

    private Long fromAccountId;

    private Long toAccountId;

    private String remark;

    private String transferDetail;

    private Double amount;

}