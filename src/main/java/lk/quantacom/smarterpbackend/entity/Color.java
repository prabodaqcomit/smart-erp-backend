package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "COLOR")
@Data
public class Color extends BaseEntity {

    public Color(){
    }

    public Color(Long id){
        this.setId(id);
    }

    @Column(name = "COLOR_DESC", length = 25)
    private String colorDesc;

}