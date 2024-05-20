package lk.quantacom.smarterpbackend.dto.response;



import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class ActionTypeResponse {

    private Long id;

    private String description;

    private String actionLogic;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}