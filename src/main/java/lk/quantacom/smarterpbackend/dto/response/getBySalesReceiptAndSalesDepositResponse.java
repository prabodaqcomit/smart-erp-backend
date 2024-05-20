package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface getBySalesReceiptAndSalesDepositResponse {

    Long getSALES_DEPOSIT_ID();

    Date getDATE();

    Double getSALES_DEPOSIT_AMOUNT();

    String getNAME();

    String getBANK_NAME();

    String getBRANCH_NAME();

    String getACC_NAME();

    Long getSALES_RECEIPT_ID();

    Date getIN_PAY_DATE();

    Double getGRAND_TOTAL();

    Double getSALES_RECEIPT_DEPOSIT_AMOUNT();

}