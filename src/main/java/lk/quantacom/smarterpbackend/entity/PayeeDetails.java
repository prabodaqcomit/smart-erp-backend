package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="PAYEE_DETAILS")
@Data
public class PayeeDetails  extends BaseEntity{

@Column(nullable=false)
private String name;

@Column
private String gender;

@Column
private String add1;

@Column
private String add2;

@Column
private String add3;

@Column
private String location;

@Column
private Integer mobile;

@Column
private String email;

@Column
private String nic;

}