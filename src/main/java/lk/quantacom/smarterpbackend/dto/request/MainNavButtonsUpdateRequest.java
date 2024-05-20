package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class MainNavButtonsUpdateRequest {

private Long id;

private String rootTabName;

private Integer orderNo;

private String caption;

private String iconHex;

private String iconStyle;

private String rout;

 }