package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class BarcodesUpdateRequest {

private Long id;

private String barcode;

private String itemCode;

 }