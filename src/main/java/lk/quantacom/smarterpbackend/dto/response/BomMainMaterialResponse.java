package lk.quantacom.smarterpbackend.dto.response;

public interface BomMainMaterialResponse {

    Integer getBOM_ID();

    String getBOM_MAIN_MATERIAL_ID();

    String getBOM_MAIN_MATERIAL_DESC();

    String getBOM_MAIN_MATERIAL_BATCH_ID();

    String getBOM_MAIN_MATERIAL_BRANCH_ID();

    Double getMATERIAL_WIDTH();

    Double getSTORES_QTY();

    Long getCOLOR_ID();

    Long getFIT_ID();

    Long getSIZE_ID();

    Double getBOM_MAIN_ITEM_QTY();

    Integer getBOM_MAIN_SIZE_ID();

    Integer getBOM_MAIN_FIT_ID();

}
