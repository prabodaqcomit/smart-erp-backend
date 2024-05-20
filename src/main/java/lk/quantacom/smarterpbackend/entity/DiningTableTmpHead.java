package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "DINING_TABLE_TMP_HEAD")
@Data
public class DiningTableTmpHead extends BaseEntity {


    @Column(name = "TABLE_ID", nullable = false)
    private Long tableId;

    @Column(name = "PAYMENT_TYPE", length = 100, nullable = false)
    private String paymentType;

    @Column(name = "KOT_PRINTED", nullable = false)
    private Boolean kotPrinted;

    @Column(name = "BOT_PRINTED", nullable = false)
    private Boolean botPrinted;

    @Column(name = "INV_STATUS", nullable = false)
    private Integer invStatus=0;   // 0 = pending, 1 = closed , -1 = cancelled

    @Column(name = "INVOICE_NO")
    private String invoiceNo;
}