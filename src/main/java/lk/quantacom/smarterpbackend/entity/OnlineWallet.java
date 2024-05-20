package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ONLINE_WALLET")
@Data
public class OnlineWallet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    //    @Column(name = "BANK_ID", nullable = false)
//    private Long bankId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Bank_ID")
    private Bank bank;

    @Column(length = 50, nullable = false)
    private String description;

    @Column(name = "SERVICE_CHARGE")
    private Double serviceCharge;

    @Column(name = "SERVICE_CHARGE_PERCENTAGE")
    private Double serviceChargePercentage;

}