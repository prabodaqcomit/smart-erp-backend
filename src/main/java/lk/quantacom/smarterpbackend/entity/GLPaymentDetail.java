package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "GLPAYMENT_DETAIL")
@Data
public class GLPaymentDetail extends BaseEntity {

    @Column(name = "GL_PAY_HEADER_ID", nullable = false)
    private Long glPayHeaderId;

    @Column(name = "ACC_CODE")
    private Integer accCode;

    @Column(name = "ACC_NAME")
    private String accName;

    @Column(name = "LEDGER_AMOUNT")
    private Double ledgerAmount;

    @Column
    private Double wht;

    @Column(name = "STAMP_DUTY")
    private Double stampDuty;

    @Column(name = "NET_AMOUNT")
    private Double netAmount;

    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;

    @Column(name = "AMOUNT_IN_WORD")
    private String amountInWord;


}