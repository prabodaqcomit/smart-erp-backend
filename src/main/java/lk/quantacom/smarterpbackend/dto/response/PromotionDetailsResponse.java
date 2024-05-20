package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class PromotionDetailsResponse {

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

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}