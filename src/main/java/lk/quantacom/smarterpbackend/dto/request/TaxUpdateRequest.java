package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TaxUpdateRequest {

private Long id;

private String taxName;

private Double taxRate;

private Boolean taxStatus;

 }