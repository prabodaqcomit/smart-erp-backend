package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GLPAY_METHOD_DETAILS")
@Data
public class GLPayMethodDetails extends BaseEntity {

    @Column(name = "GL_PAY_HEADER_ID", nullable = false)
    private Long glPayHeaderId;

    @Column(name = "PAY_METHOD_ID", nullable = false)
    private Long payMethodId;

    @Column(name = "PAY_METHOD_NAME", nullable = false)
    private String payMethodName;

    @Column(nullable = false)
    private Double Amount;

    @Column(name = "CHEQUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ChequeDate;

    @Column
    private String Bank;

    @Column
    private String Account;

    @Column(name = "CHEQUE_NUMBER")
    private String ChequeNumber;

    @Column
    private String Reference;

    @Column(name = "TRANSFER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date TransferDate;

    @Column(name = "FROM_BANK")
    private String FromBank;

    @Column(name = "FROM_ACCOUNT")
    private String FromAccount;

    @Column(name = "TO_BANK")
    private String ToBank;

    @Column(name = "TO_ACCOUNT")
    private String ToAccount;

    @Column(name = "FROM_WALLET")
    private String FromWallet;

    @Column(name = "TO_WALLET")
    private String ToWallet;

    @Column(name = "FROM_CARD")
    private String FromCard;


}