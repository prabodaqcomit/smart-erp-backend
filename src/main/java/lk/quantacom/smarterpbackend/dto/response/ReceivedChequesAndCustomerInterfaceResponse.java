package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface ReceivedChequesAndCustomerInterfaceResponse {

    Long getID();

    Date getRECEIVED_DATE();

    String getNAME();

    Date getCHEQUE_DATE();

    String getCHEQUE_ACC_NAME();

    String getCHEQUE_NO();

    String getTHIS_IS_PD();

    String getBANK_CODE();

    String getBRANCH_CODE();

    Double getCHEQUE_AMOUNT();

    Date getDEPO_DATE();

    String getDEPO_BANK();

    String getREMARKS();

    String getSTATUS();

    String getNEW_CHEQUE_NO();

    Date getSTATUS_DATE();

}