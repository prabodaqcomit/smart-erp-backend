package lk.quantacom.smarterpbackend.dto.response;

import lombok.Data;


public interface ChequeDepoReq {

      String getCHEQUE_NO();

      Integer getDEPO_BANK_ID();

      String getBANK_NAME();

      String getACC_NO();

      String getDATE();

}
