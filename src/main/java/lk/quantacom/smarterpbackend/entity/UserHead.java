package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "USER_HEAD")
@Data
public class UserHead extends BaseEntity{

    @Column(name = "FLD_USER_ID",unique = true)
    private String fldUserId;

    @Column(name = "FLD_TITLE")
    private String fldTitle;

    @Column(name = "FLD_FNAME")
    private String fldFName;

    @Column(name = "FLD_LNAME")
    private String fldLName;

    @Column(name = "FLD_ADDRESS")
    private String fldAddress;

    @Column(name = "FLD_TEL")
    private String fldTel;

    @Column(name = "FLD_EMAIL")
    private String fldEmail;

    @Column(name = "FLD_PASSWORD")
    private String fldPassword;

    @Column(name = "FLD_PW_CHANGED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldPwChangedDate;

    @Column(name = "FLD_CAN_COPY", nullable = false)
    private Boolean fldCanCopy;

    @Column(name = "FLD_CAN_PRINT", nullable = false)
    private Boolean fldCanPrint;

    @Column(name = "FLD_ENABLED", nullable = false)
    private Boolean fldEnabled;

    @Column(name = "FLD_PW_CHANGE_ON_NEXT_LOGON", nullable = false)
    private Boolean fldPwChangeOnNextLogon;

    @Column(name = "FLD_USER_CATEGORY")
    private String fldUserCategory;

    @Column(name = "FLD_SIGNED_ON")
    private Boolean fldSignedOn;

    @Column(name = "FLD_SIGNED_OFF")
    private Boolean fldSignedOff;

    @Column(name = "FLD_STATION_ID")
    private String fldStationId;

    @Column(name = "FLD_SIGN_ONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldSignOndate;

    @Column(name = "FLD_FLOAT")
    private Float fldFloat;

    @Column(name = "FLD_MANAGER_SIGNED_OFF")
    private Boolean fldManagerSignedOff;

    @Column(name = "FLD_USER_GROUP")
    private String fldUserGroup;

    @Column(name = "FLD_USERHED_SHIFTNO")
    private Integer fldUserhedShiftno;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

//    @Column
//    private Long branchId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLES",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")})
    private List<Role> roles;

}