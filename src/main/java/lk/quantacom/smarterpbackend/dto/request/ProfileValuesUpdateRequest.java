package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class ProfileValuesUpdateRequest {

private Integer profileid;

private String fieldname;

private String fieldvalue;

 }