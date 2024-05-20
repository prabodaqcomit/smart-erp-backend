package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class PromotionPayDetailsUpdateRequest {

private Long id;

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

 }