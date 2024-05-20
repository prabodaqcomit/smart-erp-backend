package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "LEDGER_BANK_ACCOUNT_DEBIT")
@Data
public class LedgerBankAccountDebit extends BaseEntity {

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column(name = "CASH_AMOUNT", nullable = false)
    private Double cashAmount = 0.0;

    @Column(length = 150)
    private String description;

    @Column(name = "OTHER_ACC_NO", length = 25)
    private String otherAccNo;

    @Column(name = "OTHER_ACCOUNT_NAME", length = 45)
    private String otherAccountName;

    @Column(name = "PR_NO", length = 25)
    private String prNo;

}