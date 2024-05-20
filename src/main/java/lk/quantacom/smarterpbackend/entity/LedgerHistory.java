package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "LADGER_HISTORY")
@Data
public class LedgerHistory extends BaseEntity {

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column(name = "LADGER_ACCOUNT_ID", nullable = false)
    private Long ladgerAccountId;

    @Column(name = "UPDATE_FRAM", length = 100, nullable = false)
    private String updateFram;

    @Column(name = "UPDATE_FRAM_DOC_NO", length = 100, nullable = false)
    private String updateFramDocNo;

    @Lob
    @Column
    private String remark;

    @Column(name = "UPDATE_BALANCE", nullable = false)
    private Double updateBalance;

    @Column(name = "TRANS_TYPE", length = 100, nullable = false)
    private String transType;

    @Column(name = "CREDIT_AMOUNT", nullable = false)
    private Double creditAmount;

    @Column(name = "DEBIT_AMOUNT", nullable = false)
    private Double debitAmount;

    @Column(name = "CREDIT_COLUMN_NAME", length = 100)
    private String creditColumnName;

    @Column(name = "DEBIT_COLUMN_NAME", length = 100)
    private String debitColumnName;

}