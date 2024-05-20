package lk.quantacom.smarterpbackend.dto.response;



import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class TabFormResponse {

    private Long id;

    private String formAlias;

    private String rootFormAlias;

    private Boolean isNavTab;

    private String iconHex;

    private String iconStyle;

    private Integer navIndex;

    private String formName;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}