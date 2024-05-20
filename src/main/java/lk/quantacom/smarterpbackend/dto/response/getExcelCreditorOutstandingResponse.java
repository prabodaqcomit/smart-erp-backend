package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class  getExcelCreditorOutstandingResponse {

    public String supName;

    public String supCode;

    public String fromDate;

    public String toDate;

    public List<ExcelSupplierOutstandingResponse> list;

}