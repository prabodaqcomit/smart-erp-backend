package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface ReceivedChequesInterfaceResponse {

    Long getEMP_ID();

    String getCREATED_BY();

    Date getCREATED_DATE_TIME();

    Integer getIS_DELETED();

    String getMODIFIED_BY();

    Date getMODIFIED_DATE_TIME();

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

    String getNAME();



}