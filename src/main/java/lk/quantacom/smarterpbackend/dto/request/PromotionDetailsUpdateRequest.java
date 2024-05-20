package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class PromotionDetailsUpdateRequest {

private Long id;

private String promoDetCode;

private Double promoDetCouple;

private String promoDetCouponRegex;

private String promoDetCustomerGroup;

private String promoDetCustomerProfile;

private Double promoDetDiscAmt;

private Double promoDetDiscPer;

private Double promoDetEndQty;

private String promoDetFreeIssueItm;

private String promoDetGroupCode;

private Double promoDetGroupCount;

private String promoDetGroupLevel;

private String promoDetItem;

private Double promoDetMaxDisAmt;

private Double promoDetMaxQty;

private String promoDetPopupMessage;

private Double promoDetStartQty;

private String promoDetSupCode;

private String promoDetTicketId;

private Long promoDetType;

private Long promoHeadId;

 }