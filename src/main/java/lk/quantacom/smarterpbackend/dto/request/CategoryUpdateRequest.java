package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class CategoryUpdateRequest {

    private Long id;

    private String categoryName;

    private Boolean isMaterialCategory;

    private Deleted isDeleted;

}