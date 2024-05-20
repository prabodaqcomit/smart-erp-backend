package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class MenuItemsResponse {

    private Long id;

    private Long parentId;

    private String parentMenuName;

    private String menuName;

    private String iconHex;

    private String iconStyle;

    private String rout;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Boolean isPopup;

    private Integer orderNo;
}