package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ProfileMenuMappingResponse {

    private Long id;

    private Integer profileId;

    private String profileName;

    private Integer parentProfileId;

    private Long menuId;

    private String menuItemName;

    private Long parentMenuId;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}