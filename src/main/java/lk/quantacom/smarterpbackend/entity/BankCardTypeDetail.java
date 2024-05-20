package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BANK_CARD_TYPE_DETAIL")
@Data
public class BankCardTypeDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

//    @Column(name = "BANK_CARD_TYPE_ID", nullable = false)
//    private Long bankCardTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Bank_Card_Type_ID")
    private BankCardType bankCardType;


    @Column(length = 50, nullable = false)
    private String description;

//    @Column(name = "BANK_ID", nullable = false)
//    private Long bankId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Bank_ID")
    private Bank bank;

    @Column(name = "SERVICE_CHARGE")
    private Double serviceCharge;

    @Column(name = "SERVICE_CHARGE_PERCENTAGE")
    private Double serviceChargePercentage;

    @Column(name = "IS_DEFAULT", length = 1, nullable = false)
    private Integer isDefault = 0;

}