package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class StockTransferLogUpdateRequest {

private Long id;

private String user;

private String itemCode;

private String ItemName;

private String date;

private Long color;

private Long size;

private Long fit;

private Long fromBranch;

private Long toBranch;

private Double prevQtyfromBranch;

private Double newQtytoBranch;

private Double prevQtytobranch;

private Double newQtyFromBranch;
 private Integer issueNumber;
 private Double unitPrice;
 }