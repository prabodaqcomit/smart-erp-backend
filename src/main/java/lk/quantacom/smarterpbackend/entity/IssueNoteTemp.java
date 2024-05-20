package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ISSUE_NOTE_TEMP")
@Data
public class IssueNoteTemp extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ITEM_CODE", nullable = false)
    private ItemMaster item;

    @Column(name = "idissue_note", nullable = false)
    private Long issueId;

    @Column(name = "BATCH_NO", length = 10, nullable = false)
    private String batchNo;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private BranchNetwork branch;

    @Column(name = "ISSUE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Column(name = "TRANSFER_METHOD", length = 100, nullable = false)
    private String transferMethod;

    @Column(name = "TR_FROM", length = 100, nullable = false)
    private String trFrom;

    @Column(name = "TR_TO", length = 100, nullable = false)
    private String trTo;

    @Column(name = "UNIT_PRICE", nullable = false)
    private Double unitPrice;

    @Column(name = "ITEM_VALUE", nullable = false)
    private Double itemValue;

    @Column(name = "ISSUE_QTY", nullable = false)
    private Double issueQty;

    @Column(name = "IN_HAND_QTY", nullable = false)
    private Double inHandQty;

    @Column(name = "CURRENT_BALANCE", nullable = false)
    private Double currentBalance;

    @Column(name = "GRAND_TOTAL", nullable = false)
    private Double grandTotal;

    @Column(name = "ISSUE_TOTAL_QTY", nullable = false)
    private Double issueTotalQty;

    @Column(name = "ITEM_CREDIT_VALUE", nullable = false)
    private Double itemCreditValue;

    @Column(name = "ITEM_CASH_VALUE", nullable = false)
    private Double itemCashValue;

    @Column(name = "TOTAL_CREDIT_AMOUNT", nullable = false)
    private Double totalCreditAmount;

    @Column(name = "TOTAL_CASH_AMOUNT", nullable = false)
    private Double totalCashAmount;

    @Column(name = "CASH_PRICE", nullable = false)
    private Double cashPrice;

    @Column(name = "CREDIT_PRICE", nullable = false)
    private Double creditPrice;

    @Column(name = "STORES_INHAND", nullable = false)
    private Double storesInhand;

    @Column(name = "STORES_NEW", nullable = false)
    private Double storesNew;

    @Column(name = "TRANSFER_BATCH_NO", length = 20)
    private String transferBatchNo;

    @Column(name = "TR_FROM_BRANCH_ID")
    private Long trFromBranchId;

    @Column(name = "TR_TO_BRANCH_ID")
    private Long trToBranchId;

    @Column(length = 20)
    private String status;

    @Column(name = "ISSUE_NOTE_SAVED_ID", length = 45)
    private String issueNoteSavedId;

    @Column(name = "ITEM_NAME", length = 45)
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "COLOR_ID")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "SIZE_ID")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "FIT_ID")
    private Fit fit;

}