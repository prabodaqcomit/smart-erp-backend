package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLBOMSIZE")
@Data
public class Tblbomsize extends BaseEntity {


    @Column(name = "BS_DESC", length = 100, nullable = false)
    private String bsDesc;

    @Column(name = "BS_ID", nullable = false)
    private Integer bsId;

    @Column(name = "BS_SIZE_ID", nullable = false)
    private Integer bsSizeId;

    @Column(name = "BS_SIZE_DESC", length = 50)
    private String bsSizeDesc;


}