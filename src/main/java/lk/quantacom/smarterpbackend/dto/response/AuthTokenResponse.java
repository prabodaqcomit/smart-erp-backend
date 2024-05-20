package lk.quantacom.smarterpbackend.dto.response;

import lombok.Value;

@Value
public class AuthTokenResponse {

    private Object user;
    private String token;

}
