package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class GeneralJournalHeaderRequest {

    //private String journalNumber;

    private Long branchId;

    private String transactionDate;

    private String remark;

    private String transactionDetail;

    private List<GeneralJournalDetailRequest> journalDetails;

}