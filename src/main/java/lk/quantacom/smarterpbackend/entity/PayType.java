package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="PAYTYPE")
@Data
public class PayType  extends BaseEntity{

@Column(nullable=false)
private String name;

}