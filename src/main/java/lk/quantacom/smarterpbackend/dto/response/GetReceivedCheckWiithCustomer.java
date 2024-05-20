package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface GetReceivedCheckWiithCustomer {

    String getNAME();

    Date getRECEIVED_DATE();

    String getCHEQUE_ACC_NAME();

    Date getCHEQUE_DATE();

    String getCURRENCY_TYPE();

    String getBANK_CODE();

    String getBRANCH_CODE();

    String getCHEQUE_NO();

    Double getCHEQUE_AMOUNT();

    Integer getCUSTOMER_ID();

    String getSTATUS();

    Date getSTATUS_DATE();

    String getCREDIT_ACC_NO();

}
