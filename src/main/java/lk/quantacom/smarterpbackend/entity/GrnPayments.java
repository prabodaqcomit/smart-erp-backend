package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "GRN_PAYMENTS")
@Data
public class GrnPayments extends BaseEntity {

    @Column(name = "GROSS_AMOUNT")
    private Double grossAmount;

    @Column(name = "TOTAL_DIS")
    private Double totalDis;

    @Column(name = "TOTAL_VAT")
    private Double totalVat;

    @Column(name = "NET_AMOUNT")
    private Double netAmount;

    @Column(name = "PAID_AMOUNT")
    private Double paidAmount;

    @Column(name = "DUE_AMOUNT")
    private Double dueAmount;

    @Column(name = "PAY_MODE", length = 20)
    private String payMode;

    @Column
    private String remarks;

    @Column(name = "GRN_OVERPAID")
    private Double grnOverpaid;

    @Column(name = "TOTAL_PROFIT_VALUE")
    private Double totalProfitValue;

    @Column(name = "NET_PROFIT_VALUE")
    private Double netProfitValue;

    @ManyToOne
    @JoinColumn(name = "GRN_ID", nullable = false)
    private GrnHeader grn;

    @Column(name = "LINE_NO")
    private Integer lineNo;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

}