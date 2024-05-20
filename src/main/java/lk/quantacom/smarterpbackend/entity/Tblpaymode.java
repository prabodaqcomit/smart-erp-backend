package lk.quantacom.smarterpbackend.entity;

import lk.quantacom.smarterpbackend.enums.Deleted;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "TBLPAYMODE")
@Data
//public class Tblpaymode  extends BaseEntity{
public class Tblpaymode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

//    @Id
    @Column(name = "PAYMODE_CODE", length = 3, nullable = false, unique = true)
    private String paymodeCode;

    @Column(name = "PAYMODE_DESC", length = 50)
    private String paymodeDesc;

    @Column(name = "PAYMODE_FORMAT", length = 40)
    private String paymodeFormat;

    @Column(name = "PAYMODE_CARDFORMAT", length = 40)
    private String paymodeCardformat;

    @Column(name = "PAYMODE_EXCHANGERATE")
    private Double paymodeExchangerate;

    @Column(name = "PAYMODE_COMMISSION")
    private Double paymodeCommission;

    @Column(name = "PAYMODE_ACTIVE")
    private Boolean paymodeActive;

    @Column(name = "PAYMODE_ORDER")
    private Integer paymodeOrder = 99;

    @Column(name = "PAYMODE_ISR_CODE", length = 4)
    private String paymodeIsrCode;

    @Lob
    @Column(name = "PAYMODE_PATH")
    private String paymodePath;

    @Column(name = "PAYMODE_FORDER", length = 10)
    private String paymodeFOrder;

    @Column(name = "PAYMODE_GV_ENABLE")
    private Boolean paymodeGvEnable;

    @Column(name = "PAYMODE_IS_DETAIL")
    private Boolean paymodeIsDetail;

    @Column(name = "PAYMODE_IS_CREDITCARD")
    private Boolean paymodeIsCreditcard;

    @Column(name = "PAYMODE_IS_FOREIGNCURRENCY")
    private Boolean paymodeIsForeigncurrency;

    @Column(name = "PAYMODE_IS_NEXUS")
    private Boolean paymodeIsNexus;

    @Column(name = "PAYMODE_DC_ENABLE")
    private Boolean paymodeDcEnable;

    @Column(name = "IS_DELETED", nullable = false)
    private Deleted isDeleted= Deleted.NO;

}