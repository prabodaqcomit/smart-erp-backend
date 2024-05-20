package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CONTACT")
@Data
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "NIC_NUMBER", length = 15)
    private String nicNumber;

    @Column(name = "MOBILE_NUMBER", length = 15)
    private String mobileNumber;

    @Column(length = 100)
    private String email;

    @Column(length = 250)
    private String address;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "SUPPLIER_ID")
    private Long supplierId;

    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

}