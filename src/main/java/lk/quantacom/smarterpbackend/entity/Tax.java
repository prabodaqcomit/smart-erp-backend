package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="TAX")
@Data
public class Tax  extends BaseEntity{

@Column(name="TAX_NAME",nullable=false)
private String taxName;

@Column(name="TAX_RATE",nullable=false)
private Double taxRate;

@Column(name="TAX_STATUS",nullable=false)
private Boolean taxStatus;

}