package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblporsizesUpdateRequest {

private Long id;

private String porId;

private Double porQty;

private Double porRatio;

private String porSizeDesc;

private Integer porSizeId;

 }