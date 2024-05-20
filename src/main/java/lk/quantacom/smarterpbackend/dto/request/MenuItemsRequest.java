package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;


@Data
public class MenuItemsRequest {

    private Long parentId;

    private String parentMenuName;

    private String menuName;

    private String iconHex;

    private String iconStyle;

    private String rout;

    private Boolean isPopup;

    private Integer orderNo;

}