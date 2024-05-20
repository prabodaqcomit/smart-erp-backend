package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TaxRequest {

private String taxName;

private Double taxRate;

private Boolean taxStatus;

 }