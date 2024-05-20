package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ORDER_FORM")
@Data
public class OrderForm extends BaseEntity {


    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;

    @Column(name = "LINE_NO", nullable = false)
    private Integer lineNo;

    @Column(name = "BRANCH_ID", nullable = false)
    private Integer branchId;

    @Column(name = "ITEM_CODE", length = 20, nullable = false)
    private String itemCode;

    @Column(name = "ITEM_DESC", length = 200, nullable = false)
    private String itemDesc;

    @Column(name = "CATEGORY_CODE", nullable = false)
    private Integer categoryCode;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @CreatedDate
    @Column(name = "ORDER_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @CreationTimestamp
    @Column(name = "ORDER_TIME", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date orderTime;

    @Column(name = "SELL_PRICE")
    private Double sellPrice;

    @Column(name = "ORDER_QTY")
    private Double orderQty;

    @Column(name = "ITEM_VALUE")
    private Double itemValue;

    @Column(name = "GRAND_TOTAL")
    private Double grandTotal;

    @Column(length = 20, nullable = false)
    private String status;

    @CreatedBy
    @Column(name = "UserDet_ID", length = 50, nullable = false)
    private String userDetId;

    @Column(name = "BATCH_NO", length = 100, nullable = false)
    private String batchNo;

    @Column(length = 45, nullable = false)
    private String closesales;

    @Column(nullable = false)
    private Boolean isdiscountenable;

}