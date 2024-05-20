package lk.quantacom.smarterpbackend.dto.request;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;

@Data
public class Department3UpdateRequest {

    private Long id;

    private String departmentName;

    private Deleted isDeleted;

}