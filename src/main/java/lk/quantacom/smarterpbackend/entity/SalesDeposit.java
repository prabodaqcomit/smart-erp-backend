package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SALES_DEPOSIT")
@Data
public class SalesDeposit extends BaseEntity {

    @Column(nullable = false)
    private Double balance;

    @Column(name = "BANK_ACCOUNT_ID", nullable = false)
    private Integer bankAccountId;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "DEPOSIT_AMOUNT", nullable = false)
    private Double depositAmount;

    @Column(name = "GRAND_TOTAL_DEPOSIT", nullable = false)
    private Double grandTotalDeposit;

    @Column(name = "IDSALES_DEPOSIT", nullable = false)
    private Integer idsalesDeposit;

    @Column(name = "LINE_NO", nullable = false)
    private Integer lineNo;

    @Column(name = "SALES_RECEIPT_ID", nullable = false)
    private Integer salesReceiptId;

}