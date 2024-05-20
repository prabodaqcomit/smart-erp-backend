package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;

import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;

@Data
public class PromotionHeaderResponse {

    private String promoHedDesc;

    private Boolean promoHedActive;

    private Date promoHedStartDateTime;

    private Date promoHedEndDateTime;

    private String promoHedDaysOfWeek;

    private Boolean promoHedCheckBillNetValue;

    private Boolean promoHedCheckCustomer;

    private Boolean promoHedCheckCustomerProfile;

    private Boolean promoHedCheckPaymentMode;

    private Boolean promoHedCheckCoupon;

    private Boolean promoHedCheckNoOfBillsPerCustomer;

    private Boolean promoHedCheckSupplier;

    private Boolean promoHedCheckGroup;

    private Boolean promoHedCheckItem;

    private Boolean ptomoHedCheckValidItem;

    private Double promoHedBillValStart;

    private Double promoHedBillValEnd;

    private Long promoHedLocationId;

    private String promoHedCode;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private List<PromotionDetailsResponse> promotionDetails;

    private List<PromotionPayDetailsResponse> promotionPayDetails;
}