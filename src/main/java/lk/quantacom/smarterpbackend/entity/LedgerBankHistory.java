package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="LEDGER_BANK_HISTORY")
@Data
public class LedgerBankHistory  extends BaseEntity{

@Column(nullable=false)
private Double amount = 0.0;

@Column(name="BANK_ACCOUNT_ID",nullable=false)
private Integer bankAccountId;

@Column(name="CREDIT_ACC_AMOUNT",nullable=false)
private Double creditAccAmount = 0.0;

@Column(name="CREDIT_ACC_ID")
private Integer creditAccId;

@Column(name="CURRENT_BALANCE",nullable=false)
private Double currentBalance = 0.0;

@Column(name="DEBIT_ACC_AMOUNT",nullable=false)
private Double debitAccAmount = 0.0;

@Column(name="DEBIT_ACC_ID")
private Integer debitAccId;

@Column(name="DEPO_TYPE",length=45)
private String depoType;

@Column(name="FRAM_DOC_NO")
private Integer framDocNo;

@Column(name="FRAM_NAME",length=45)
private String framName;

@Column(length=45)
private String transaction;

}