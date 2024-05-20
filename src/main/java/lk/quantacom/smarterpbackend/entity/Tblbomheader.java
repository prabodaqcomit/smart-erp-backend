package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLBOMHEADER")
@Data
public class Tblbomheader extends BaseEntity {

    @Column(name = "BOM_CATEGORY_ID")
    private Integer bomCategoryId;

    @Column(name = "BOM_CREATED_BRANCH_ID", length = 18)
    private String bomCreatedBranchId;

    @Column(name = "BOM_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bomCreatedDate;

    @Column(name = "BOM_CREATED_USER_ID", length = 50)
    private String bomCreatedUserId;

    @Column(name = "BOM_DESCRIPTION", length = 100)
    private String bomDescription;


    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOM_ID", nullable = false)
    private Integer bomId;

    @Column(name = "BOM_IS_DELETE")
    private Boolean bomIsDelete;

    @Column(name = "BOM_MODIFIED_BRANCH_ID", length = 18)
    private String bomModifiedBranchId;

    @Column(name = "BOM_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bomModifiedDate;

    @Column(name = "BOM_MODIFIED_USER_ID", length = 50)
    private String bomModifiedUserId;

    @Column(name = "BOM_SLEEVE_LONG", nullable = false)
    private Boolean bomSleeveLong ;

    @Column(name = "BOM_SLEEVE_SHORT", nullable = false)
    private Boolean bomSleeveShort ;

}