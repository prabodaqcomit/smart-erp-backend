package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "SUPPLIER_SALESREP")
@Data
public class SupplierSalesRep extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier supplier;

    @Column(name = "SALESREP_NAME", length = 50, nullable = false)
    private String salesrepName;

    @Column(name = "REP_NIC_NO", length = 15)
    private String repNicNo;

    @Column(name = "REP_VEHICLE_NO", length = 20)
    private String repVehicleNo;

    @Column(name = "REP_EMAIL", length = 100)
    private String repEmail;

    @Column(name = "REP_MOBILE_NO", length = 15)
    private String repMobileNo;

    @Column(name = "REP_HOME_NO", length = 15)
    private String repHomeNo;

    @Column(name = "REP_FAX_NO", length = 15)
    private String repFaxNo;

}