package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="GLSUP_PAY_DETAIL_TEMPLATE")
@Data
public class GLSupPayDetailTemplate extends BaseEntity{

@Column(name="GL_PAY_HEADER_ID",nullable=false)
private Long glPayHeaderId;

@Column(name="SUP_INV_DATE")
@Temporal(TemporalType.TIMESTAMP)
private Date supInvDate;

@Column(name="INV_NUMBER")
private String invNumber;

@Column(name="PENDING_AMOUNT")
private Double pendingAmount;

@Column(name="PAY_AMOUNT")
private Double payAmount;

@Column(name="GROSS_AMOUNT")
private Double grossAmount;

@Column
private Double wht;

@Column(name="STAMP_DUTY")
private Double stampDuty;

@Column(name="NET_AMOUNT")
private Double netAmount;

@Column(name="AMOUNT_IN_WORD")
private String amountInWord;

}