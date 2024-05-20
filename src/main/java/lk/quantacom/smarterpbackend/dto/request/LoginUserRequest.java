package lk.quantacom.smarterpbackend.dto.request;

import lombok.Data;

import javax.validation.Valid;

@Data
@Valid
public class LoginUserRequest {

    private String username;
    private String password;

}
