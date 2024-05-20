package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "BANK")
@Data
public class Bank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "BANK_NAME", length = 20, nullable = false)
    private String bankName;

    @Column(name = "BANK_CODE", length = 10, nullable = false)
    private String bankCode;

}