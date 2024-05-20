package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "SUPPLIER")
@Data
public class Supplier extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(name = "T_HOME", length = 15)
    private String tHome;

    @Column(name = "T_MOBILE", length = 15)
    private String tMobile;

    @Column(name = "T_OFFICE", length = 15)
    private String tOffice;

    @Column(length = 15)
    private String fax;

    @Column(length = 100)
    private String email;

    @Column(name = "SALES_PERSON", length = 100)
    private String salesPerson;

    @Column(length = 50)
    private String vat;

    @Column(name = "CREDIT_LIMIT")
    private Double creditLimit;

    @Column(name = "AV_CREDIT_LIMIT")
    private Double avCreditLimit;

    @Column(length = 100)
    private String manufacture;

    @Column(length = 100)
    private String division;

    @Column(name = "AGENCY_NAME", length = 100)
    private String agencyName;

    @Column(length = 50)
    private String website;

    private String brands;

    @Column(name = "SUP_IMAGE")
    private String supImage;

    @Column(name = "SUP_BANK_NAME", length = 100)
    private String supBankName;

    @Column(name = "SUP_BANK_BRANCH", length = 100)
    private String supBankBranch;

    @Column(name = "SUP_BANK_ACC_TYPE", length = 100)
    private String supBankAccType;

    @Column(name = "SUP_BANK_ACC_NO", length = 50)
    private String supBankAccNo;

    @Column(name = "SUP_BANK_ACC_NAME", length = 100)
    private String supBankAccName;

    @Column(name = "SUPPLIER_LEDGER_ID")
    private Long supplierLedgerId;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @OneToOne(mappedBy = "supplier")
    private SupplierAgency supplierAgency;

    @OneToOne(mappedBy = "supplier")
    private SupplierSalesRep supplierSalesRep;

}