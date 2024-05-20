package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface getByOwnChequeReturnResponse {

    Date getRETURN_DATE();

    String getPAYEE_NAME();

    Date getISSUE_DATE();

    String getUPDATE_WINDOW();

    String getREMARKS();

    String getBANK_CODE();

    String getBRANCH_CODE();

    String getCHEQUE_NO();

    Double getVALUE();

}