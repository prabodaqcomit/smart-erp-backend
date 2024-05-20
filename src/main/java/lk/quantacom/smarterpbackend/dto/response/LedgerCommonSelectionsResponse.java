package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class LedgerCommonSelectionsResponse {

    private Integer creditAccId;

    private String creditAccNum;

    private Integer debitAccId;

    private String debitAccNum;

    private String description;

    private String framName;

    private String frameId;

    private Integer ledgerCommonSelectionsId;

    private String payMode;

    private String recordRight;

}