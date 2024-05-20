package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GLPAYMENT_HEADER")
@Data
public class GLPaymentHeader extends BaseEntity {

    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "PAYEE_ID", nullable = false)
    private Integer payeeId;

    @Column(name = "PAYEE_NAME", nullable = false)
    private String payeeName;

    @Column(name = "VOUCHER_NUMBER")
    private String voucherNumber;

    @Column(name = "PAYMENT_DETAILS")
    private String paymentDetails;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "GL_PAY_MOD_ID")
    private Long glPayModId;

    @Column(name = "GL_PAY_MOD_NAME")
    private String glPayModName;

    @Column(name = "GL_PAY_MOD_AMOUNT", nullable = false)
    private Double glPayModAmount;

    @Column(name = "GL_TOTAL_AMOUNT")
    private Double glTotalAmount;

    @Column(name = "GL_TOTAL_AMT_WORD")
    private String glTotalAmtWord;

    @Column(name = "GL_PAYMENT_DETAIL_ID")
    private Long glPaymentDetailId;

    @Column(name = "GL_PAYMENT_DETAIL_NAME")
    private String glPaymentDetailName;

    @Column(name = "GL_PAYMENT_DETAIL_TYPE",length = 1)
    private String glPaymentDetailType;

    @OneToMany(mappedBy = "glPayHeaderId")
    private List<GLPaymentDetail> glPaymentDetail;

    @OneToMany(mappedBy = "glPayHeaderId")
    private List<GLPayMethodDetails> glPayMethodDetail;

    @OneToMany(mappedBy = "glPayHeaderId")
    private List<GLSupPayDetail> glSupPayDetail;


}