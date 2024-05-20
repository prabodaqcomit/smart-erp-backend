package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class UnitRefUpdateRequest {

    private Long id;

    private String unitShort;

    private String unitLong;

    private Deleted isDeleted;

}