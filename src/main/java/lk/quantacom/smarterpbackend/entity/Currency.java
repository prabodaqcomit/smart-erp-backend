package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "tblcurrency")
@Data
public class Currency extends BaseEntity {

    @Column(length = 20, nullable = false)
    private String currency;

    @Column(nullable = false)
    private Float rate;

    @Column( length = 50, nullable = false)
    private String currencySymbol;

    @Column( length = 100, nullable = false)
    private String currencyDescription;

    @Column( length = 20, nullable = false)
    private String localCurrency;

}