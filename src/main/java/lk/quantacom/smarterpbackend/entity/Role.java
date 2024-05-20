package lk.quantacom.smarterpbackend.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role extends BaseEntity{


    @Column
    private String name;

    @Column
    private String description;
}
