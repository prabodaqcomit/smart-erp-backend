package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "UNIT_REF")
@Data
public class UnitRef extends BaseEntity {

    @Column(name = "UNIT_SHORT", length = 5, nullable = false)
    private String unitShort;

    @Column(name = "UNIT_LONG", length = 25, nullable = false)
    private String unitLong;

}