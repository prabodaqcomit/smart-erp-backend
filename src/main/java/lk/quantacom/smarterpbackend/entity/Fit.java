package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "FIT")
@Data
public class Fit extends BaseEntity {

    public Fit(){

    }

    public Fit(Long id){
        this.setId(id);
    }

    @Column(name = "FIT_DESC", length = 25)
    private String fitDesc;

}