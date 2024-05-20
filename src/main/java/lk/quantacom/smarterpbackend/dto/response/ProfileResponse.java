package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

import java.util.List;

@Data
public class ProfileResponse {

    private Integer id;

    private String profilename;

    private String description;

    private Integer profiletype;

    private String pathref;

    private Integer hashval;

    private Integer parentid;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

    private Long tokenExpiration;

    private List<MenuItemsResponse> menuItems;

    private List<TabFormResponse> tabForms;
}