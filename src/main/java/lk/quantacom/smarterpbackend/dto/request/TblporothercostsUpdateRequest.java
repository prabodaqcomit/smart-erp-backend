package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporothercostsUpdateRequest {

private String description;

private Integer lineNo;

private Double perUnitCost;

private String porId;

private Double totalCost;

private Double unitQty;

 private Long id;

 }