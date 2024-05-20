package lk.quantacom.smarterpbackend.dto.request;


import lombok.Value;

@Value
public class LedgerCommonSelectionsRequest {

    private Integer creditAccId;

    private String creditAccNum;

    private Integer debitAccId;

    private String debitAccNum;

    private String description;

    private String framName;

    private String frameId;

   // private Integer ledgerCommonSelectionsId;

    private String payMode;

    private String recordRight;

}