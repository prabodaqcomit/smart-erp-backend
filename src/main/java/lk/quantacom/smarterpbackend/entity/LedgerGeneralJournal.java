package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "LEDGER_GENERAL_JOURNAL")
@Data
public class LedgerGeneralJournal extends BaseEntity {


    @Column(length = 150)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "PR_NO", length = 25)
    private String prNo;

    @Column(name = "DEBIT_ACC_ID", nullable = false)
    private Long debitAccId;

    @Column(name = "CREDIT_ACC_ID")
    private Long creditAccId;

    @Column(name = "CREDIT_AMOUNT", nullable = false)
    private Double creditAmount;

    @Column(length = 100)
    private String remarks;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

}