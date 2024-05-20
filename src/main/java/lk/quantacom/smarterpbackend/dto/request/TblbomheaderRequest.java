package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

import java.util.List;

@Data
public class TblbomheaderRequest {

    private Integer bomId;

    private Integer bomCategoryId;

    private String bomCreatedBranchId;

    private String bomCreatedDate;

    private String bomCreatedUserId;

    private String bomDescription;

    private Boolean bomIsDelete;

    private String bomModifiedBranchId;

    private String bomModifiedDate;

    private String bomModifiedUserId;

    private Boolean bomSleeveLong;

    private Boolean bomSleeveShort;

    private List<TblbommainmaterialRequest> tblbommainmaterialRequests;

    private List<TblbomfitRequest> tblbomfitRequests;

    private List<TblbomaccessoryRequest> tblbomaccessoryRequests;

    private List<TblbomsizeRequest> tblbomsizeRequests;

}