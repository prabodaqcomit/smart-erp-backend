package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CHEQUE_RETURNS")
@Data
public class ChequeReturns extends BaseEntity {

    @Column(name = "BANK_CODE", length = 45)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 45)
    private String branchCode;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CHEQUE_NO", length = 45)
    private String chequeNo;

    @Column(name = "CHEQUE_PAY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chequePayDate;

    @Column(name = "CHEQUE_RETURNS", nullable = false)
    private Integer chequeReturns;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column(name = "PR_NO", nullable = false)
    private Integer prNo;

    @Column(name = "INVOICE_NUM", length = 18, nullable = false)
    private String invoiceNum;

    @Column
    private String remarks;

    @Column(nullable = false)
    private Double value;

}