package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name="DEPARTMENT_3")
@Data
public class Department3 extends BaseEntity {

@Column(name="DEPARTMENT_3_NAME",length=100,nullable=false)
private String departmentName;

}