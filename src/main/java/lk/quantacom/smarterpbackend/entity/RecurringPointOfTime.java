package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "RECURRING_POINT_OF_TIME")
@Data
public class RecurringPointOfTime extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 100, nullable = false)
    private String description = "Day";

    @Column(name = "POINT_OF_TIME_START", length = 10, nullable = false)
    private Integer pointOfTimeStart = 1;

    @Column(name = "POINT_OF_TIME_END", length = 10, nullable = false)
    private Integer pointOfTimeEnd = 10;

    @Column(name = "IS_VARIABLE_END_POINT_OF_TIME", length = 1, nullable = false)
    private Integer isVariableEndPointOfTime = 0;

}