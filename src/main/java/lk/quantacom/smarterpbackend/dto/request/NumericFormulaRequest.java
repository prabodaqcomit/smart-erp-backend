package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class NumericFormulaRequest {

    private  String formula;

    private List<ReplacementRequest> replacementRequests;




}