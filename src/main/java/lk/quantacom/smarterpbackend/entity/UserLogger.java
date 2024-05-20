package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "USER_LOGGER")
@Data
public class UserLogger extends BaseEntity {

    @Column(length = 100, nullable = false)
    private String form;

    @Column(length = 100, nullable = false)
    private String action;

}