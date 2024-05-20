package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GL_ACC_CATEGORY")
@Data
public class GlAccCategory extends BaseEntity {

    @Column
    private Integer account;

    @Column(name = "ACC_NAME")
    private String accName;

}