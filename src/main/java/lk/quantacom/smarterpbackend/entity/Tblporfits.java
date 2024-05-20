package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPORFITS")
@Data
public class Tblporfits extends BaseEntity {


    @Column(name = "POR_FIT_DESC", length = 50)
    private String porFitDesc;

    @Column(name = "POR_FIT_ID")
    private Integer porFitId;

    @Column(name = "POR_ID")
    private String porId;

}