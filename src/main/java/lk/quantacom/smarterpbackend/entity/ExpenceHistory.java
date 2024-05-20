package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "EXPENCE_HISTORY")
@Data
public class ExpenceHistory extends BaseEntity {


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "DOC_NO", nullable = false)
    private Integer docNo;

    @Column(name = "DOC_TYPE", nullable = false)
    private String docType;

    @Column(nullable = false)
    private String description;

    @Column(name = "PAY_AMOUNT", nullable = false)
    private Double payAmount;

    @Column(name = "PAY_MODE", nullable = false)
    private String payMode;

    @Column(nullable = false)
    private String remarks;

    @Column(name = "BRANCH_ID", nullable = false)
    private String branchId;

}