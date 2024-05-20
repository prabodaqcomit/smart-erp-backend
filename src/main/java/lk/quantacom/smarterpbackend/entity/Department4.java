package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;
import java.util.Date;
import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name="DEPARTMENT_4")
@Data
public class Department4 extends BaseEntity {

@Column(name="DEPARTMENT_4_NAME",length=100,nullable=false)
private String departmentName;

}