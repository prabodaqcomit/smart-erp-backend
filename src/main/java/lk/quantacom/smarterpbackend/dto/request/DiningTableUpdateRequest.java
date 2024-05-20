package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class DiningTableUpdateRequest {

private Long id;

private String dnTableName;

private Boolean isActive;

 }