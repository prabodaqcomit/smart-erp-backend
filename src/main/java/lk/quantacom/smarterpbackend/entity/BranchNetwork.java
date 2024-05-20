package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "BRANCH_NETWORK")
@Data
public class BranchNetwork extends BaseEntity {

    @Column(name = "BRANCH_ID", length = 10, nullable = false,unique = true)
    private String branchId;

    @Column(name = "BRANCH_NAME", length = 200, nullable = false)
    private String branchName;

    @Column(name = "FLD_LOCATION", length = 50, nullable = false)
    private String fldLocation;

    @Column(name = "FLD_PROVINCE", length = 50, nullable = false)
    private String fldProvince;

    @Column(name = "BRANCH_LEVEL_ID")
    private Long branchLevelId;

    @Column(name = "BRANCH_TYPE", nullable = false)
    private Long branchType;

    @Column
    private Boolean isPublicBranch;

    @Column
    private Boolean damageLocation;


}