package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "GRN_RECORD_UPDATE")
@Data
public class GrnRecordUpdate extends BaseEntity {

    @Column(name = "BARCODE_NO", length = 45)
    private String barcodeNo;

    @Column(name = "BATCH_NO", length = 45, nullable = false)
    private String batchNo;

//    @Column(name = "BRANCH_ID", length = 18, nullable = false)
//    private String branchId;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private BranchNetwork branch;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "GRN_IDDD", length = 15)
    private Long grnIddd;

//    @Column(name = "IDGRN_RECORD_UPDATE", nullable = false)
//    private Integer idgrnRecordUpdate;

//    @Column(name = "ITME_IDDD", length = 15)
//    private String itmeIddd;

    @ManyToOne
    @JoinColumn(name = "ITME_IDDD", nullable = false)
    private ItemMaster item;

    @Column(name = "STK_QTY", nullable = false)
    private Double stkQty = 0.0;

//    @Column(name = "SUPPLIER_IDDD", length = 15)
//    private String supplierIddd;

    @ManyToOne
    @JoinColumn(name = "SUPPLIER_IDDD", nullable = false)
    private Supplier supplier;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice = 0.0;

    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "UPDATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Column(name = "COLOR_ID", nullable = false)
    Long color;

    @Column(name = "SIZE_ID", nullable = false)
    Long size;

    @Column(name = "FIT_ID", nullable = false)
    Long fit;

}