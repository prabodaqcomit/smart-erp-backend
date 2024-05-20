package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "RECEIPT_HEADER")
@Data
public class ReceiptHeader extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "RECEIPT_NUMBER", length = 20, nullable = false)
    private String receiptNumber;

    @Column(name = "TRANSACTION_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    @Column(length = 50, nullable = false)
    private String remark;

    @Column(name = "RECEIPT_DETAIL", length = 250, nullable = false)
    private String receiptDetail;

    //    @Column(name = "PAYER_CONTACT_ID", nullable = true)
    //    private Long payerContactId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PAYER_CONTACT_ID", referencedColumnName = "ID", nullable = true)
    private Contact payerContact;

    //    @Column(name = "CUSTOMER_ID", nullable = true)
    //    private Long customerId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = true)
    private Customer customer;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Double totalAmount;

    @Column(name = "TOTAL_AMOUNT_IN_WORD", length = 250, nullable = false)
    private String totalAmountInWord;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiptHeader")
    private List<ReceiptDetail> receiptDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiptHeader")
    private List<ReceiptPaymentMethod> paymentMethods;
}