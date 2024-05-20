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
@Table(name = "PROFILE_TYPE_INFO")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileTypeInfo {


    @Id
    @Column(nullable = false)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String typename;

    @Column(length = 30, nullable = false)
    private String datatype;

    @Column(length = 100)
    private String typesql;

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