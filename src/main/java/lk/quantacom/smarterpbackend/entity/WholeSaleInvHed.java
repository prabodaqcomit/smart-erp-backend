package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "WHOLESALEINVHED")
@Data
@EntityListeners(AuditingEntityListener.class)
public class WholeSaleInvHed {

    @Id
    @Column(name = "INV_NO", nullable = false)
    private Integer invno;

    @Column(name = "INV_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date invDate;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer locationId;

    @Column(name = "SALES_BY", nullable = false)
    private String salesBy;

    @Column(nullable = false)
    private Integer salesById;

    @Column()
    private String customer;

    @Column(nullable = false)
    private Integer customerId;

    @Column(name = "TOTAL_QTY", nullable = false)
    private Double totalQty;

    @Column(name = "NUM_OF_ITEM", nullable = false)
    private Integer numOfItem;

    @Column(name = "GROSS_AMOUNT", nullable = false)
    private Double grossAmount;

    @Column(name = "BILL_DIS_PRECENTAGE", nullable = false)
    private Double billDisPrecentage;

    @Column(name = "BILL_DIS_AMOUNT", nullable = false)
    private Double billDisAmount;

    @Column(name = "NET_AMOUNT", nullable = false)
    private Double NetAmount;

    @Column(name = "PAY_TYPE")
    private Integer payType;

    @Column(name = "IS_CANCEL", nullable = false)
    private Boolean isCancel;

    @Column(name = "PAY_TYPE_INFO")
    private String payTypeInfo;

    @CreatedBy
    @Column(name = "CREATED_BY", length = 50, nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME", nullable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", length = 50, nullable = false)
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE_TIME", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDateTime;

    @Column(name = "IS_DELETED", nullable = false)
    private Deleted isDeleted = Deleted.NO;

    @Column(name = "CREDIT_CARD_NUMBER")
    private String creditCardNumber;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;


}