package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ExcelCustomerOutstandingResponse {

    public String cusName;

    public String cusCode;

    public String fromDate;

    public String toDate;

    public List<ExcelOutstandingResponse> list;




}