package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "DEPARTMENT_1")
@Data
public class Department1 extends BaseEntity {

    @Column(name = "DEPARTMENT_1_NAME", length = 100, nullable = false)
    private String departmentName;

}