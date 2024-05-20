package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="TBLMENUDETAIL")
@Data
public class Tblmenudetail  extends BaseEntity{

@Column(name="MENU_ID",length=5,nullable=false)
private String menuId;

@Column(name="MENU_DESC",length=50)
private String menuDesc;

@Column(name="MENU_RIGHT",length=5)
private String menuRight;

@Column(name="CREATED_BY",length=50,nullable=false)
private String createdBy;

@Column(name="CREATED_DATE_TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;


@Column(name="MODIFIED_BY",length=50,nullable=false)
private String modifiedBy;

@Column(name="MODIFIED_DATE_TIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDateTime;

}