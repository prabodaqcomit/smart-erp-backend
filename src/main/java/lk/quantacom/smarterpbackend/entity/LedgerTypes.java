package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "LEDGER_TYPES")
@Data
public class LedgerTypes extends BaseEntity {

    @Column(name = "ACC_CATEGORY", length = 50, nullable = false)
    private String accCategory;

    @Column(name = "MAIN_ACC_TYPE", length = 50, nullable = false)
    private String mainAccType;

    @Column(name = "MAIN_ACC_TYPE_CODE", length = 50, nullable = false)
    private String mainAccTypeCode;

    @Column(name = "SUB_ACC_TYPE", length = 200, nullable = false)
    private String subAccType;

    @Column(name = "SUB_ACC_TYPE_CODE", length = 200, nullable = false)
    private String subAccTypeCode;

    @Column(name = "SUB_ACC_SINHALA", length = 200, nullable = false)
    private String subAccSinhala;

    @Column(name = "MAIN_ACC_TYPE_SINHALA", length = 200, nullable = false)
    private String mainAccTypeSinhala;

}