package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ProfileRequest {

    private Integer id;

    private String profilename;

    private String description;

    private Integer profiletype;

    private String pathref;

    private Integer hashval;

    private Integer parentid;

    private Long tokenExpiration;

}