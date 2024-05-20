package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class PromotionPayDetailsResponse {

private String detailCode;

private String paymentMode;

private String payDetailCode;

private Double paymentMin;

private Double payemntMax;

private Double discPer;

private Double discAmt;

private String freeIssueItem;

private String ticketId;

private Double groupCount;

private Double maxDiscAmt;

private String popupMsg;

private Boolean detType;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }