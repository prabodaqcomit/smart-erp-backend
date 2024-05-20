package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class  ExcelOutstandingResponse {

    public String fld_InvNo;

    public String fld_Date;

    public Integer days;

    public Double invoice_amount;

    public Double amount_paid;

    public Double amount_payable;


}