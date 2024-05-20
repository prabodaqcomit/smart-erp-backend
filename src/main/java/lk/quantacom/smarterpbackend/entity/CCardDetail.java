package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "tblcarddtl")
@Data
public class CCardDetail  {

    @Id
    @Column(name = "fld_ccarddetcode", length = 10, nullable = false)
    private String cCardDetCode;

    @Column(name = "fld_ccardhedcode", length = 10, nullable = false)
    private String cCardHedCode;

    @Column(name = "fld_ccardformat", length = 50, nullable = false)
    private String cCardFormat;

    @Column(name = "fld_ccardstartingstr", length = 15, nullable = false)
    private String cCardStartingStr;

}