package lk.quantacom.smarterpbackend.dto.response;

public interface getPorByPorIdResponse {

    String getPOR_ID();

    String getPOR_MAIN_ITEM_CODE();

    String getPOR_MAIN_ITEM_DESC();

    String getPOR_MAIN_ITEM_BATCH_ID();

    String getPOR_MAIN_ITEM_BRANCH_ID();

    Double getPOR_MAIN_ITEM_QTY();

    Integer getCOLOR_ID();

    Integer getFIT_ID();

    Integer getSIZE_ID();

    Double getMATERIAL_WIDTH();

    Double getSTORES_QTY();

}