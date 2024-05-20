package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

@Data
public class GetInvOfCheque {

    Integer idsales_receipt;

    String fld_InvNo;

    String in_pay_date;

    String fld_NetAmount;

    String fld_PayTypeAmt;

    String fld_CreditCust;
}
