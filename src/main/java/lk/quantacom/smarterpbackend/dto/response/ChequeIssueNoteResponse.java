package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class ChequeIssueNoteResponse {

    private Long id;

    private String accName;

    private Double amount;

    private Integer bankAccountId;

    private String bankCode;

    private String branchCode;

    private String framNo;

    private Date chequeDate;

    private String chequeNo;

    private Integer framDocNo;

    private Date issueDate;

    private String payeeName;

    private String status;

    private Date statusDate;

    private String updateWindow;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}