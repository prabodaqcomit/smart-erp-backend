package lk.quantacom.smarterpbackend.dto.response;

public interface getByStockAndTblPorAccessoriesResponse {

    String getPOR_ID();

    String getPOR_ACC_ITEM_CODE();

    String getPOR_ACC_ITEM_DESC();

    Double getPOR_ACC_ITEM_CONSUMPTION_QTY();

    Double getPOR_ACC_ITEM_QTY();

    String getPOR_ACC_ITEM_BATCH_ID();

    String getPOR_ACC_ITEM_BRANCH_ID();

    Integer getCOLOR_ID();

    Integer getFIT_ID();

    Integer getSIZE_ID();

    Double getSTORES_QTY();

}