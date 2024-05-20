package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ExcelSupplierOutstandingResponse {

    public Integer grn_no;

    public String grn_date;

    public String sup_in_date;

    public String sup_in_no;

    public Integer branch_id;

    public Double net_amount;

    public Double due_amount;



}