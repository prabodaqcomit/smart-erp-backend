package lk.quantacom.smarterpbackend.dto.response;

import java.util.Date;

public interface getActionFieldByReferenceAliasAndProfileIdResponse {

    Long getID();

    String getCREATED_BY();

    Date getCREATED_DATE_TIME();

    Integer getIS_DELETED();

    String getMODIFIED_BY();

    Date getMODIFIED_DATE_TIME();

    String getACTION_ALIAS();

    String getACTION_DESCRIPTION();

    Boolean getIS_HIDDEN();

    Boolean getIS_INPUT_UPPER_CASE();

    Boolean getREAD_ONLY();

    String getREFERENCE_ALIAS();

    String getFIELD_EVENT();

    String getFORMULA();

    Long getACTION_TYPE_ID();

    Long getACTION_FIELD_ID();

    Long getPROFILE_ID();

}