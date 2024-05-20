package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class TblbomheaderUpdateRequest {

private Integer bomCategoryId;

private String bomCreatedBranchId;

private String bomCreatedDate;

private String bomCreatedUserId;

private String bomDescription;

private Integer bomId;

private Boolean bomIsDelete;

private String bomModifiedBranchId;

private String bomModifiedDate;

private String bomModifiedUserId;

private Boolean bomSleeveLong;

private Boolean bomSleeveShort;

 }