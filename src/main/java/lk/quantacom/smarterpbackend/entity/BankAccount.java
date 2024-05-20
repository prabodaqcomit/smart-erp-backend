package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BANK_ACCOUNT")
@Data
public class BankAccount extends BaseEntity {


    @Column(name = "ACC_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accDate;

    @Column(name = "ACC_NAME", length = 145)
    private String accName;

    @Column(name = "ACC_NO", length = 45)
    private String accNo;

    @Column(name = "BANK_CODE", nullable = false)
    private Double bankCode = 0.0;

    @Column(name = "BANK_LEDGER_ID", length = 10, nullable = false)
    private String bankLedgerId = "0";

    @Column(name = "BANK_NAME", length = 85)
    private String bankName;

    @Column(name = "BRANCH_CODE", nullable = false)
    private Double branchCode = 0.0;

    @Column(name = "BRANCH_NAME", length = 45)
    private String branchName;

    @Column(name = "CURRENT_BALANCE", nullable = false)
    private Double currentBalance = 0.0;


    @Column(name = "OB_BALANCE", nullable = false)
    private Double obBalance = 0.0;

}