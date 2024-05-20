package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="BRANCH_TYPE")
@Data
public class BranchType  extends BaseEntity{

@Column(nullable=false)
private String name;

@Column(name="IS_ACTIVE")
private Boolean isActive;

}