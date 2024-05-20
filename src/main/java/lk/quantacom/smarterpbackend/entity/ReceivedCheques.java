package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RECEIVED_CHEQUES")
@Data
public class ReceivedCheques extends BaseEntity {

    @Column(name = "AVAILBLE_AMOUNT", nullable = false)
    private Double availbleAmount = 0.0;

    @Column(name = "BANK_CODE", length = 45)
    private String bankCode;

    @Column(name = "BRANCH_CODE", length = 45)
    private String branchCode;

    @Column(name = "BRANCH_ID", length = 18, nullable = false)
    private Long branchId;

    @Column(name = "CHEQUE_ACC_NAME", length = 45)
    private String chequeAccName;

    @Column(name = "CHEQUE_AMOUNT", nullable = false)
    private Double chequeAmount = 0.0;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date chequeDate;

    @Column(name = "CHEQUE_NO", length = 45)
    private String chequeNo;

    @Column(name = "CURRENCY_TYPE", length = 45)
    private String currencyType;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column(name = "DEPO_BANK", length = 45)
    private String depoBank;

    @Column(name = "DEPO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depoDate;

    @Column(name = "NEW_CHEQUE_NO", length = 25)
    private String newChequeNo;

    @Column(name = "PD_OWNER", length = 45)
    private String pdOwner;

    @Column(name = "RECEIVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedDate;

    @Column(length = 45)
    private String remarks;

    @Column(length = 45)
    private String status;

    @Column(name = "STATUS_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;

    @Column(name = "THIS_IS_PD", length = 45)
    private String thisIsPd;

}