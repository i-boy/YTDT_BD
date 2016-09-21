/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_BAN_KHAM_SEQUENCE")
@NamedQueries({})
public class DtDmBanKhamSequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_BAN_KHAM_SEQUENCE")
    @SequenceGenerator(name = "DT_DM_BAN_KHAM_SEQUENCE", sequenceName = "DT_DM_BAN_KHAM_SEQUENCE_ID_SEQ", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "MA", nullable = false)
    private String ma;
    @Column(name = "CURRENT_VALUE")
    private Integer currentValue;
    @Column(name = "CURRENT_NEXT")
    private Integer currentNext;
    @Column(name = "STEP")
    private Short step;

    public DtDmBanKhamSequence() {
    }

    public DtDmBanKhamSequence(Integer id) {
        this.id = id;
    }

    public DtDmBanKhamSequence(Integer id, String ma) {
        this.id = id;
        this.ma = ma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getCurrentNext() {
        return currentNext;
    }

    public void setCurrentNext(Integer currentNext) {
        this.currentNext = currentNext;
    }

    public Short getStep() {
        return step;
    }

    public void setStep(Short step) {
        this.step = step;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmBanKhamSequence)) {
            return false;
        }
        DtDmBanKhamSequence other = (DtDmBanKhamSequence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmBanKhamSequence[id=" + id + "]";
    }

}
