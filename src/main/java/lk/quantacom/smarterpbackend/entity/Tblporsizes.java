package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPORSIZES")
@Data
public class Tblporsizes extends BaseEntity {


    @Column(name = "POR_ID")
    private String porId;

    @Column(name = "POR_QTY")
    private Double porQty;

    @Column(name = "POR_RATIO")
    private Double porRatio;

    @Column(name = "POR_SIZE_DESC", length = 50)
    private String porSizeDesc;

    @Column(name = "POR_SIZE_ID")
    private Integer porSizeId;

}