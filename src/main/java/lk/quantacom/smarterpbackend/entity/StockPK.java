/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.quantacom.smarterpbackend.entity;

import lombok.Data;
import lombok.Value;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Dimuthu
 */
@Embeddable
@Data
public class StockPK implements Serializable {

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "ITEM_CODE", nullable = false)
    ItemMaster item;

    @Column(name = "COLOR_ID", nullable = false)
    Long color;

    @Column(name = "SIZE_ID", nullable = false)
    Long size;

    @Column(name = "FIT_ID", nullable = false)
    Long fit;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "BRANCH_ID",nullable = false)
    BranchNetwork branch;

    @Column(name = "BATCH_NO", length = 20)
    String batchNo;

    public StockPK(){}

    public StockPK(ItemMaster item, Long color, Long size, Long fit, BranchNetwork branch, String batchNo) {
        this.item = item;
        this.color = color;
        this.size = size;
        this.fit = fit;
        this.branch = branch;
        this.batchNo = batchNo;
    }
}
