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
@Table(name = "TAB_FORM")
@Data
@EntityListeners(AuditingEntityListener.class)
public class TabForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "FORM_ALIAS", length = 50, nullable = false)
    private String formAlias;

    @Column(length = 50)
    private String rootFormAlias;

    @Column(name = "IS_NAV_TAB", nullable = false)
    private Boolean isNavTab = false;

    @Column(name = "ICON_HEX", length = 50)
    private String iconHex;

    @Column(name = "ICON_STYLE", length = 50)
    private String iconStyle;

    @Column(name = "NAV_INDEX")
    private Integer navIndex;

    @Column(name = "FORM_NAME", length = 150, nullable = false)
    private String formName;

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