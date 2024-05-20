package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "SUPPLIER_AGENCY")
@Data
public class SupplierAgency extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier supplier;

    @Column(name = "SUP_AGENCY_NAME", length = 50, nullable = false)
    private String supAgencyName;

    @Column(name = "SUP_AGENCY_ADDRESS", length = 150)
    private String supAgencyAddress;

    @Column(name = "SUP_AEMAIL", length = 100)
    private String supAEmail;

    @Column(name = "SUP_AMOBILE_NO", length = 15)
    private String supAMobileNo;

    @Column(name = "SUP_AHOME_NO", length = 15)
    private String supAHomeNo;

    @Column(name = "SUP_AFAX_NO", length = 15)
    private String supAFaxNo;

    @Column(name = "SUP_AWEB_SITE", length = 100)
    private String supAWebSite;



}