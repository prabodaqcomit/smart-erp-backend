package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CUSTOMER_OPENING_BALANCE")
@Data
public class CustomerOpeningBalance extends BaseEntity {


    @Column(name = "CUSTOMER_OPENING_BALANCE_ID", nullable = false)
    private Integer customerOpeningBalanceId;

    @Column(name = "FLD_CUSTOMER_ID", length = 50)
    private String fldCustomerId;

    @Column(name = "FLD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldDate;

    @Column(name = "FLD_DUE_BALANCE", nullable = false)
    private Double fldDueBalance = 0.0;

    @Column(name = "FLD_NET_AMOUNT", nullable = false)
    private Double fldNetAmount = 0.0;

    @Column(name = "FLD_PAID_AMOUNT", nullable = false)
    private Double fldPaidAmount = 0.0;

    @Column(name = "FLD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fldTime;

}