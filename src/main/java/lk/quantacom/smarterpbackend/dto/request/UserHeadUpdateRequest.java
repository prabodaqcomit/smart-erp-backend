package lk.quantacom.smarterpbackend.dto.request;


import lombok.Data;

@Data
public class UserHeadUpdateRequest {

    private Long id;

    private String fldUserId;

    private String fldTitle;

    private String fldFName;

    private String fldLName;

    private String fldAddress;

    private String fldTel;

    private String fldEmail;

    private String fldPassword;

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

}