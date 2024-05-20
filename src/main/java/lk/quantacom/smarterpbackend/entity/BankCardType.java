package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "BANK_CARD_TYPE")
@Data
public class BankCardType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "CARD_TYPE_NAME", length = 50, nullable = false)
    private String cardTypeName;

    @Column(name = "CARD_NUMBER_LENGTH")
    private Integer cardNumberLength;

    @Column(name = "CARD_NUMBER_FORMAT", length = 30)
    private String cardNumberFormat;

}