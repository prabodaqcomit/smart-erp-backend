package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class GeneralJournalHeaderUpdateRequest {

    private Long id;

    private String journalNumber;

    private Long branchId;

    private String transactionDate;

    private String remark;

    private String transactionDetail;

    List<GeneralJournalDetailUpdateRequest> journalDetails;

}