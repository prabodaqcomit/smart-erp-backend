package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class PromotionHeaderUpdateRequest {

    private Long id;

    private String promoHedDesc;

    private Boolean promoHedActive;

    private String promoHedStartDateTime;

    private String promoHedEndDateTime;

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

    private List<PromotionDetailsRequest> promotionDetails;

    private List<PromotionPayDetailsRequest> promotionPayDetails;

}