package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import javax.persistence.*;


@Entity
@Table(name = "DEPARTMENT_REG")
@Data
public class DepartmentReg extends BaseEntity {


    @Column(name = "DEPARTMENT_NAME", length = 100, nullable = false)
    private String department_name;

}