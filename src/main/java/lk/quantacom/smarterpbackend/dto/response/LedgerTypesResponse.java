package lk.quantacom.smarterpbackend.dto.response;


import lombok.Data;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;
@Data
public class LedgerTypesResponse {

private String accCategory;

private String mainAccType;

private String mainAccTypeCode;

private String subAccType;

private String subAccTypeCode;

private String subAccSinhala;

private String mainAccTypeSinhala;

//from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted; }