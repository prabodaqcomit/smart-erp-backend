package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CHEQUE_BOOK_REGISTER")
@Data
public class ChequeBookRegister extends BaseEntity {


    @Column(name = "BANK_ACCOUNT_ID", length = 18)
    private String bankAccountId;

    @Column(name = "CHEQUE_AMOUNT", nullable = false)
    private Double chequeAmount = 0.0;

    @Column(name = "CHEQUE_BOOK_NO", length = 15, nullable = false)
    private String chequeBookNo;

    @Column(name = "CHEQUE_BOOK_REGISTER_ID", nullable = false)
    private Integer chequeBookRegisterId;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chequeDate;

    @Column(name = "CHEQUE_NO", nullable = false)
    private Integer chequeNo = 0;

    @Column(name = "PAY_ISSUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payIssueDate;

    @Column(name = "PAY_ISSUE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payIssueTime;

    @Column(name = "PAY_WINDOW", length = 50)
    private String payWindow;

    @Column(name = "PAYEE_NAME", length = 50)
    private String payeeName;

    @Column(name = "REGISTER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;

    @Column(length = 20)
    private String status;

}