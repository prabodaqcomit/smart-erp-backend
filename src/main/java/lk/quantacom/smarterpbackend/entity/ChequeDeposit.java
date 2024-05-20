package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CHEQUE_DEPOSIT")
@Data
public class ChequeDeposit extends BaseEntity {


    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private String branchId;

    @Column(name = "CHEQUE_DEPOSIT", length = 15, nullable = false)
    private String chequeDeposit = "0";

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column(name = "GRAND_TOTAL", length = 18)
    private String grandTotal;

    @Column(name = "DEPOSIT_AMOUNT", nullable = false)
    private Double depositAmount = 0.0;

    @Column(name = "LINE_NO", nullable = false)
    private Integer lineNo = 0;

    @Column(name = "RECEIVED_CHEQUES_ID", nullable = false)
    private Long receivedChequesId;

    @Column(name = "DEPO_BANK_ID", nullable = false)
    private Integer depoBankId;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}