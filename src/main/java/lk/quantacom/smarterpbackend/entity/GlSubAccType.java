package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="GL_SUB_ACC_TYPE")
@Data
public class GlSubAccType  extends BaseEntity{


@Column
private String categoty;

@Column(name="CATEGORY_ID")
private Long categoryId;

@Column(name="CATEGORY_ACCOUNT")
private Integer categoryAccount;

@Column(name="MAIN_ACCOUNT")
private Integer mainAccount;

@Column(name="MAIN_ACCOUNT_ID")
private Long mainAccountId;

@Column(name="MAIN_ACC_NAME")
private String mainAccName;

@Column
private String name;

@Column
private Integer account;

}