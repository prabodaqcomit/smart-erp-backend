/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.quantacom.smarterpbackend.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Dimuthu
 */
@Entity
@Table(name = "ledger_common_selections")
@NamedQueries({
    @NamedQuery(name = "LedgerCommonSelections.findAll", query = "SELECT l FROM LedgerCommonSelections l"),
    @NamedQuery(name = "LedgerCommonSelections.findByLedgerCommonSelectionsId", query = "SELECT l FROM LedgerCommonSelections l WHERE l.ledgerCommonSelectionsId = :ledgerCommonSelectionsId"),
    @NamedQuery(name = "LedgerCommonSelections.findByFrameId", query = "SELECT l FROM LedgerCommonSelections l WHERE l.frameId = :frameId"),
    @NamedQuery(name = "LedgerCommonSelections.findByFramName", query = "SELECT l FROM LedgerCommonSelections l WHERE l.framName = :framName"),
    @NamedQuery(name = "LedgerCommonSelections.findByDescription", query = "SELECT l FROM LedgerCommonSelections l WHERE l.description = :description"),
    @NamedQuery(name = "LedgerCommonSelections.findByPayMode", query = "SELECT l FROM LedgerCommonSelections l WHERE l.payMode = :payMode"),
    @NamedQuery(name = "LedgerCommonSelections.findByDebitAccId", query = "SELECT l FROM LedgerCommonSelections l WHERE l.debitAccId = :debitAccId"),
    @NamedQuery(name = "LedgerCommonSelections.findByDebitAccNum", query = "SELECT l FROM LedgerCommonSelections l WHERE l.debitAccNum = :debitAccNum"),
    @NamedQuery(name = "LedgerCommonSelections.findByCreditAccId", query = "SELECT l FROM LedgerCommonSelections l WHERE l.creditAccId = :creditAccId"),
    @NamedQuery(name = "LedgerCommonSelections.findByCreditAccNum", query = "SELECT l FROM LedgerCommonSelections l WHERE l.creditAccNum = :creditAccNum"),
    @NamedQuery(name = "LedgerCommonSelections.findByRecordRight", query = "SELECT l FROM LedgerCommonSelections l WHERE l.recordRight = :recordRight")})
public class LedgerCommonSelections implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ledger_common_selections_id")
    private Integer ledgerCommonSelectionsId;
    @Basic(optional = false)
    @Column(name = "frame_id")
    private String frameId;
    @Basic(optional = false)
    @Column(name = "fram_name")
    private String framName;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "pay_mode")
    private String payMode;
    @Basic(optional = false)
    @Column(name = "debit_acc_id")
    private int debitAccId;
    @Basic(optional = false)
    @Column(name = "debit_acc_num")
    private String debitAccNum;
    @Basic(optional = false)
    @Column(name = "credit_acc_id")
    private int creditAccId;
    @Basic(optional = false)
    @Column(name = "credit_acc_num")
    private String creditAccNum;
    @Basic(optional = false)
    @Column(name = "record_right")
    private String recordRight;

    public LedgerCommonSelections() {
    }

    public LedgerCommonSelections(Integer ledgerCommonSelectionsId) {
        this.ledgerCommonSelectionsId = ledgerCommonSelectionsId;
    }

    public LedgerCommonSelections(Integer ledgerCommonSelectionsId, String frameId, String framName, String description, String payMode, int debitAccId, String debitAccNum, int creditAccId, String creditAccNum, String recordRight) {
        this.ledgerCommonSelectionsId = ledgerCommonSelectionsId;
        this.frameId = frameId;
        this.framName = framName;
        this.description = description;
        this.payMode = payMode;
        this.debitAccId = debitAccId;
        this.debitAccNum = debitAccNum;
        this.creditAccId = creditAccId;
        this.creditAccNum = creditAccNum;
        this.recordRight = recordRight;
    }

    public Integer getLedgerCommonSelectionsId() {
        return ledgerCommonSelectionsId;
    }

    public void setLedgerCommonSelectionsId(Integer ledgerCommonSelectionsId) {
        this.ledgerCommonSelectionsId = ledgerCommonSelectionsId;
    }

    public String getFrameId() {
        return frameId;
    }

    public void setFrameId(String frameId) {
        this.frameId = frameId;
    }

    public String getFramName() {
        return framName;
    }

    public void setFramName(String framName) {
        this.framName = framName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public int getDebitAccId() {
        return debitAccId;
    }

    public void setDebitAccId(int debitAccId) {
        this.debitAccId = debitAccId;
    }

    public String getDebitAccNum() {
        return debitAccNum;
    }

    public void setDebitAccNum(String debitAccNum) {
        this.debitAccNum = debitAccNum;
    }

    public int getCreditAccId() {
        return creditAccId;
    }

    public void setCreditAccId(int creditAccId) {
        this.creditAccId = creditAccId;
    }

    public String getCreditAccNum() {
        return creditAccNum;
    }

    public void setCreditAccNum(String creditAccNum) {
        this.creditAccNum = creditAccNum;
    }

    public String getRecordRight() {
        return recordRight;
    }

    public void setRecordRight(String recordRight) {
        this.recordRight = recordRight;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ledgerCommonSelectionsId != null ? ledgerCommonSelectionsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LedgerCommonSelections)) {
            return false;
        }
        LedgerCommonSelections other = (LedgerCommonSelections) object;
        if ((this.ledgerCommonSelectionsId == null && other.ledgerCommonSelectionsId != null) || (this.ledgerCommonSelectionsId != null && !this.ledgerCommonSelectionsId.equals(other.ledgerCommonSelectionsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entity.LedgerCommonSelections[ ledgerCommonSelectionsId=" + ledgerCommonSelectionsId + " ]";
    }
    
}
