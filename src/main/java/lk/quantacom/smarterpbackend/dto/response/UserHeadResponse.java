package lk.quantacom.smarterpbackend.dto.response;


import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import java.util.Date;

@Data
public class UserHeadResponse {


    private String fldUserId;

    private String fldTitle;

    private String fldFName;

    private String fldLName;

    private String fldAddress;

    private String fldTel;

    private String fldEmail;

    private String fldPwChangedDate;

    private Boolean fldCanCopy;

    private Boolean fldCanPrint;

    private Boolean fldEnabled;

    private Boolean fldPwChangeOnNextLogon;

    private String fldUserCategory;

    private Boolean fldSignedOn;

    private Boolean fldSignedOff;

    private String fldStationId;

    private String fldSignOndate;

    private Float fldFloat;

    private Boolean fldManagerSignedOff;

    private String fldUserGroup;

    private Integer fldUserhedShiftno;

    private Long branchId;

    private String roleName;

    //from Base

    private Long id;

    private String createdBy;

    private String createdDateTime;

    private String modifiedBy;

    private String modifiedDateTime;

    private Deleted isDeleted;

}