package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GENARAL_PAYMENTS")
@Data
public class GenaralPayments extends BaseEntity {


    @Column(name = "AMOUNT_IN_WORD", length = 45)
    private String amountInWord;

    @Column(name = "BANK_CODE", length = 45)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 45)
    private String branchCode;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CHEQUE_NO", length = 45)
    private String chequeNo;

    @Column(name = "GENARAL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date genaralDate;

    @Column(name = "NO_SEMI_PYING_AMOUNT", nullable = false)
    private Double noSemiPyingAmount;

    @Column(name = "PAY_MODE", length = 45)
    private String payMode;

    @Column(name = "PAYEE_NAME", length = 45)
    private String payeeName;

    @Column(name = "PAYING_AMOUNT", nullable = false)
    private Double payingAmount;

    @Column(length = 45)
    private String reason;

    @Column(length = 45)
    private String remarks;

}