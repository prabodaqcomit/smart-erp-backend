package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="TBLPOROTHERCOSTS")
@Data
public class Tblporothercosts  extends BaseEntity{

@Column(length=50)
private String description;

@Column(name="LINE_NO",nullable=false)
private Integer lineNo;

@Column(name="PER_UNIT_COST")
private Double perUnitCost;

@Column(name="POR_ID",nullable=false)
private String porId;

@Column(name="TOTAL_COST")
private Double totalCost;

@Column(name="UNIT_QTY")
private Double unitQty;

}