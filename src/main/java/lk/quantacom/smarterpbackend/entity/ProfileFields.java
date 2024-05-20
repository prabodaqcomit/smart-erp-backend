package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "PROFILE_FIELDS")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileFields {

    @Id
    @Column(length = 40, nullable = false)
    private String fieldname;

    @Column(nullable = false)
    private Integer profiletype;

    @Lob
    @Column
    private String defaultvalue;

    @Lob
    @Column
    private String description;

    @Column(nullable = false)
    private Integer version;

    @Column
    private Integer typeinfoid;

    @Column
    private Integer constraintid;

    @Lob
    @Column
    private String progname;

    @Lob
    @Column
    private String modulename;

    @Lob
    @Column
    private String functionname;

    @Column
    private Integer fieldlevel;

    @Lob
    @Column
    private String notes;

    @Column
    private Integer exodbversion = 0;

    @Lob
    @Column
    private String keywords;

    @Lob
    @Column
    private String fieldproperties;

    @Column
    private Integer dbscope = 0;

    @CreatedBy
    @Column(name = "CREATED_BY",length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY",length = 50, nullable = false)
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE_TIME", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDateTime;

    @Column(name = "IS_DELETED", nullable = false)
    private Deleted isDeleted= Deleted.NO;
}