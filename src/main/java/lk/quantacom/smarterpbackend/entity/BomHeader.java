package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "BOM_HEADER")
@Data
public class BomHeader extends BaseEntity {

    @Column(name = "BOM_ITEM_CODE", length = 20, nullable = false)
    private String bomItemCode;

    @Column(name = "BOM_DESCRIPTION", length = 100, nullable = false)
    private String bomDescription;

    @Column(length = 30, nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double markup;

    @Column(name = "ON_COST", nullable = false)
    private Boolean onCost;

    @Column(name = "COST_PRICE_DINING", nullable = false)
    private Double costPriceDining;

    @Column(name = "COST_PRICE_TAKE_AWAY", nullable = false)
    private Double costPriceTakeAway;

    @Column(nullable = false)
    private Double sellPrice;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long branchId;

    @Column(name = "IS_KOT", nullable = false)
    private Boolean isKOT;

    @Column(name = "IS_BOT", nullable = false)
    private Boolean isBOT;

}