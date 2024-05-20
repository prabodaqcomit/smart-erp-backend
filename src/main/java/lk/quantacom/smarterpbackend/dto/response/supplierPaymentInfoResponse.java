package lk.quantacom.smarterpbackend.dto.response;

public interface supplierPaymentInfoResponse {

    String getNAME();

    String getMANUFACTURE();

    Long getHEADERID();

    Long getSUPID();

    Double getTOTAL_VAT();

    Double getTOTAL_PROFIT_VALUE();

    Double getTOTAL_DIS();

    String getPAY_MODE();

    Double getPAID_AMOUNT();

    Double getNET_PROFIT_VALUE();

    Double getNET_AMOUNT();

    Double getGROSS_AMOUNT();

    Double getGRN_OVERPAID();

    Double getDUE_AMOUNT();

    String getCREATED_DATE_TIME();

    String getMODIFIED_DATE_TIME();

    String getSUP_IN_NO();

    Long getBRANCH_ID();

}