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

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    @Column(name = "CREATED_BY",length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime=new Date();

    @LastModifiedBy
    @Column(name = "MODIFIED_BY",length = 50, nullable = false)
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE_TIME", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDateTime=new Date();

    @Column(name = "IS_DELETED", nullable = false)
    private Deleted isDeleted= Deleted.NO;



}
