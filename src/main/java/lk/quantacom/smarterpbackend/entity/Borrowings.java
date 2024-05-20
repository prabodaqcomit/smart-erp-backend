package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BORROWINGS")
@Data
public class Borrowings extends BaseEntity {

    @Column(name = "AMOUNT_IN_WORD", length = 45)
    private String amountInWord;

    @Column(name = "BALANCE_AMOUNT", nullable = false)
    private Double balanceAmount;

    @Column(name = "BORROW_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowDate;

    @Column(name = "BORROWER_NAME", length = 45)
    private String borrowerName;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "NO_SEMI_PYING_AMOUNT", nullable = false)
    private Double noSemiPyingAmount;

    @Column(name = "PAYING_AMOUNT", nullable = false)
    private Double payingAmount;

    @Column(length = 145)
    private String reason;

    @Column(name = "RETURN_AMOUNT", nullable = false)
    private Double returnAmount;

    @Column(length = 15)
    private String status;

}