package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "DINING_TABLE")
@Data
public class DiningTable extends BaseEntity {

    @Column(name = "DN_TABLE_NAME", length = 50, nullable = false)
    private String dnTableName;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

}