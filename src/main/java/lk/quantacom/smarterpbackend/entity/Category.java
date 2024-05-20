package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORY")
@Data
public class Category extends BaseEntity {

    @Column(name = "CATEGORY_NAME", length = 50, nullable = false)
    private String categoryName;

    @Column(name = "IS_MATERIAL_CATEGORY", nullable = false)
    private Boolean isMaterialCategory;

}