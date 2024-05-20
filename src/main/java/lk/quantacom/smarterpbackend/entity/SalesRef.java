package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="SALES_REF")
@Data
public class SalesRef  extends BaseEntity{

@Column(nullable=false)
private String repcode;

@Column(nullable=false)
private String name;

@Column
private String address;

@Column(nullable=false)
private String gender;

@Column(name="T_HOME")
private String tHome;

@Column(name="T_MOBILE",nullable=false)
private String tMobile;

@Column(name="T_OFFICE")
private String tOffice;

@Column
private String fax;

@Column(nullable=false)
private String email;

@Column(name="T_PARTY_NAME")
private String tPartyName;

@Column(name="T_PARTY_MOBILE")
private String tPartyMobile;

@Column(name="T_PARTY_EMAIL")
private String tPartyEmail;

@Column
private String image;

@Column(name="NIC_NUMBR",nullable=false)
private String nicNumbr;

@Column(name="BRANCH_ID",nullable=false)
private Long branchId;

@Column(name="BILL_DISC_LIMIT",nullable=false)
private Double billDiscLimit;

@Column(name="LINE_DISC_LIMIT",nullable=false)
private Double lineDiscLimit;

@Column(name="SALES_AREA",nullable=false)
private String SalesArea;

}