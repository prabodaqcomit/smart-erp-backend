package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_CASHBOOK_CREDIT")
@Data
public class LedgerCashbookCredit extends BaseEntity {


    @Column(name = "BANK_AMOUNT", nullable = false)
    private Double bankAmount;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CASH_AMOUNT", nullable = false)
    private Double cashAmount;

    @Column(length = 150)
    private String description;

    @Column(name = "DISCOUNT_ALLOWED", nullable = false)
    private Double discountAllowed;

    @Column(name = "OTHER_ACC_NO", length = 25)
    private String otherAccNo;

    @Column(name = "OTHER_ACCOUNT_NAME", length = 45)
    private String otherAccountName;

    @Column(name = "PR_NO", length = 25)
    private String prNo;

    @Column(name = "VCHAR_NO", length = 25)
    private String vcharNo;

}