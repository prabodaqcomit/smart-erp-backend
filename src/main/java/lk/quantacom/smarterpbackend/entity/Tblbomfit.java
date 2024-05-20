package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLBOMFIT")
@Data
public class Tblbomfit extends BaseEntity {


    @Column(name = "BF_DESC", length = 100, nullable = false)
    private String bfDesc;

    @Column(name = "BF_FIT_ID", nullable = false)
    private Integer bfFitId;

    @Column(name = "BF_ID", nullable = false)
    private Integer bfId;

}