package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

import javax.validation.Valid;


@Data
@Valid
public class RoleSaveRequest {
    private Long id;
    private String name;
    private String description;
}
