package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface getReceivedChequesAndCustomerByChequeNoResponse {

    Long getID();

    Double getAVAILBLE_AMOUNT();

    String getBANK_CODE();

    String getBRANCH_CODE();

    Long getBRANCH_ID();

    String getCHEQUE_ACC_NAME();

    Double getCHEQUE_AMOUNT();

    Date getCHEQUE_DATE();

    String getCHEQUE_NO();

    String getCURRENCY_TYPE();

    Integer getCUSTOMER_ID();

    String getDEPO_BANK();

    Date getDEPO_DATE();

    String getNEW_CHEQUE_NO();

    String getPD_OWNER();

    Date getRECEIVED_DATE();

    String getREMARKS();

    String getSTATUS();

    Date getSTATUS_DATE();

    String getTHIS_IS_PD();

    String getADDRESS();

    Double getAVLB_CREDIT_LIMIT();

    String getCREDIT_ACC_NO();

    Double getCREDIT_LIMIT();

    String getEMAIL();

    String getFAX();

    String getGENDER();

    String getIMAGE();

    String getNAME();

    String getNIC_NUMBR();

    String getT_HOME();

    String getT_MOBILE();

    String getT_OFFICE();

    String getT_PARTY_EMAIL();

    String getT_PARTY_MOBILE();

    String getT_PARTY_NAME();

    String getTYPE();

    String getVAT();

}