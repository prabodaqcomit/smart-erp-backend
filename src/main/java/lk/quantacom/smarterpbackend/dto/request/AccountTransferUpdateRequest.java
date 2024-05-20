package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class AccountTransferUpdateRequest {

    private Long id;

    private Long branchId;

    private String transferDate;

    private Long fromAccountId;

    private Long toAccountId;

    private String remark;

    private String transferDetail;

    private Double amount;

}