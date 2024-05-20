package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 20)
    private String vat;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String gender;

    @Column(name = "T_HOME", length = 15)
    private String tHome;

    @Column(name = "T_MOBILE", length = 15)
    private String tMobile;

    @Column(name = "T_OFFICE", length = 15)
    private String tOffice;

    @Column(length = 15)
    private String fax;

    @Column(length = 20)
    private String email;

    @Column(name = "CREDIT_LIMIT")
    private Double creditLimit;

    @Column(name = "T_PARTY_NAME", length = 100)
    private String tPartyName;

    @Column(name = "T_PARTY_MOBILE", length = 15)
    private String tPartyMobile;

    @Column(name = "T_PARTY_EMAIL", length = 20)
    private String tPartyEmail;

    private String image;

    @Column(name = "AVLB_CREDIT_LIMIT")
    private Double avlbCreditLimit;

    @Column(name = "NIC_NUMBR", length = 15)
    private String nicNumbr;

    @Column(name = "CREDIT_ACC_NO")
    private String creditAccNo;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column
    private Double billDiscLimit;

    @Column
    private Double lineDiscLimit;

}