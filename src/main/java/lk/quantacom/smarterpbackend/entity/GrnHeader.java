package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import lk.quantacom.smarterpbackend.enums.*;


@Entity
@Table(name = "GRN_HEADER")
@Data
public class GrnHeader extends BaseEntity {

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long supplierId;

    @Column(name = "BRANCH_ID", nullable = false)
    private Long branchId;

    @Column(name = "GRN_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date grnDate;

    @Column(name = "SUP_IN_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date supInDate;

    @Column(name = "SUP_IN_NO", length = 25)
    private String supInNo;

    @Column(name = "SALES_PERSON", length = 100)
    private String salesPerson;

    @Column(name = "SALES_PERSON_TEL")
    private String salesPersonTel;

    @Column(name = "GRN_AGENCY_NAME")
    private String grnAgencyName;

    @Column
    private String remarks;

    @OneToMany(mappedBy = "grn")
    private List<GrnDetails> grnDetails;

    @OneToMany(mappedBy = "grn")
    private List<GrnPayments> grnPayments;
}