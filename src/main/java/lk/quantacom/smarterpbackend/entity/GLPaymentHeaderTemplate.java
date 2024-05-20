package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="GLPAYMENT_HEADER_TEMPLATE")
@Data
public class GLPaymentHeaderTemplate extends BaseEntity{

@Column(name="PAYMENT_DATE")
@Temporal(TemporalType.TIMESTAMP)
private Date paymentDate;

@Column(name="PAYEE_ID",nullable=false)
private Integer payeeId;

@Column(name="PAYEE_NAME",nullable=false)
private String payeeName;

@Column(name="VOUCHER_NUMBER")
private String voucherNumber;

@Column(name="PAYMENT_DETAILS")
private String paymentDetails;

@Column(name="GL_PAY_MOD_ID")
private Long glPayModId;

@Column(name="GL_PAY_MOD_NAME")
private String glPayModName;

@Column(name="GL_PAY_MOD_AMOUNT",nullable=false)
private Double glPayModAmount;

@Column(name="GL_TOTAL_AMOUNT")
private Double glTotalAmount;

@Column(name="GL_TOTAL_AMT_WORD")
private String glTotalAmtWord;

@Column(name="GL_PAYMENT_DETAIL_ID")
private Long glPaymentDetailId;

@Column(name="GL_PAYMENT_DETAIL_NAME")
private String glPaymentDetailName;

}