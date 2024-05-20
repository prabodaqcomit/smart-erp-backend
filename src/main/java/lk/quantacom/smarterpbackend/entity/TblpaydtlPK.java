/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.quantacom.smarterpbackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class TblpaydtlPK implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="FLD_INVPAYDTLKEY",nullable=false)
    Integer fldInvpaydtlkey;

    @Column(name="FLD_DATE",nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    Date fldDate;

    @Column(name="FLD_LOCATION",length=18,nullable=false)
    String fldLocation;

    @Column(name="FLD_INVNO",length=50,nullable=false)
    String fldInvno;


    public TblpaydtlPK(){}

    public TblpaydtlPK(Date fldDate,String fldLocation,String fldInvno) {
        this.fldDate = fldDate;
        this.fldLocation = fldLocation;
        this.fldInvno = fldInvno;
    }


}
