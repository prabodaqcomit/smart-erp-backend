package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface getChequeReturnByDateRangeResponse {

    String getFLD_INVNO();

    Date getFLD_DATE();

    Date getCREATED_DATE_TIME();

    String getNAME();

    Date getCHEQUE_PAY_DATE();

    String getBANK_CODE();

    String getBRANCH_CODE();

    String getCHEQUE_NO();

    String getREMARKS();

}