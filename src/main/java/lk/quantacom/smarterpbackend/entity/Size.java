package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "SIZE")
@Data
public class Size extends BaseEntity {

    public Size(){

    }

    public Size(Long id){
        this.setId(id);
    }

    @Column(name = "SIZE_DESC", length = 25)
    private String sizeDesc;

}