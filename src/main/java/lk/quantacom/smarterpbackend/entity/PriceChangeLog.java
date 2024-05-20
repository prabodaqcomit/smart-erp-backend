package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="PRICE_CHANGE_LOG")
@Data
public class PriceChangeLog  extends BaseEntity{


@Column
private String user;

@Column(name="ITEM_CODE")
private String itemCode;

@Column(name="ITEM_NAME")
private String ItemName;

@Column
@Temporal(TemporalType.TIMESTAMP)
private Date date;

@Column(name="PREV_COST_PRICE")
private Double prevCostPrice;

@Column(name="PREV_UNIT_PRICE")
private Double prevUnitPrice;

@Column
private Long color;

@Column
private Long size;

@Column
private Long fit;

@Column
private Long branch;

@Column(name="BATCH_NO")
private String batchNo;

@Column(name="STK_CASH_PRICE")
private Double stkCashPrice;

@Column(name="STK_CREDIT_PRICE")
private Double stkCreditPrice;

@Column(name="STOCK_UNIT_PRICE_RETAIL")
private Double stockUnitPriceRetail;

@Column(name="STOCK_UNIT_PRICE_WHOLESALE")
private Double stockUnitPriceWholesale;

@Column(name="CASH_DIS_VALUE")
private Double cashDisValue;

@Column(name="CREDIT_DIS_VALUE")
private Double creditDisValue;

@Column(name="SALES_DISCO_PRESENTAGE")
private Double salesDiscoPresentage;

@Column(name="NEW_COST_PRICE")
private Double newCostPrice;

@Column(name="NEW_UNIT_PRICE")
private Double newUnitPrice;

}