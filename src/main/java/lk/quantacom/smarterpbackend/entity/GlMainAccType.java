package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="GL_MAIN_ACC_TYPE")
@Data
public class GlMainAccType  extends BaseEntity{


@Column
private String categoty;

@Column(name="CATEGORY_ID")
private Long categoryId;

@Column(name="CATEGORY_ACCOUNT")
private Integer categoryAccount;

@Column
private Integer account;

@Column
private String name;

}