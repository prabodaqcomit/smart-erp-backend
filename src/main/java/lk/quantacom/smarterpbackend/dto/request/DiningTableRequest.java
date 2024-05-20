package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class DiningTableRequest {

    private String dnTableName;

    private Boolean isActive;

}