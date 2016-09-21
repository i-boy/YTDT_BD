/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author HP
 */
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "BENH_NHAN_CHE_DO_AN")
@NamedQueries({})
public class BenhNhanCheDoAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BENH_NHAN_CHE_DO_AN")
    @SequenceGenerator(name = "BENH_NHAN_CHE_DO_AN", sequenceName = "BENH_NHAN_CHE_DO_AN_BNCDA_MASO", allocationSize = 1)
    @Column(name = "BNCDA_MASO", nullable = false)
    private Integer bncdaMaso;
    @JoinColumn(name = "BNPBA_MASO", referencedColumnName = "BNPBA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhanPhieuBaoAn bnpbaMaso;
    @JoinColumn(name = "DTDMCDA_MASO", referencedColumnName = "DTDMCDA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmCheDoAn dtdmcdaMaso;

    public BenhNhanCheDoAn() {
    }

    public BenhNhanCheDoAn(Integer bncdaMaso) {
        this.bncdaMaso = bncdaMaso;
    }

    public Integer getBncdaMaso() {
        return bncdaMaso;
    }

    public void setBncdaMaso(Integer bncdaMaso) {
        this.bncdaMaso = bncdaMaso;
    }

    public BenhNhanPhieuBaoAn getBnpbaMaso() {
        return bnpbaMaso;
    }

    public void setBnpbaMaso(BenhNhanPhieuBaoAn bnpbaMaso) {
        this.bnpbaMaso = bnpbaMaso;
    }

    public DtDmCheDoAn getDtdmcdaMaso() {
        return dtdmcdaMaso;
    }

    public void setDtdmcdaMaso(DtDmCheDoAn dtdmcdaMaso) {
        this.dtdmcdaMaso = dtdmcdaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bncdaMaso != null ? bncdaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenhNhanCheDoAn)) {
            return false;
        }
        BenhNhanCheDoAn other = (BenhNhanCheDoAn) object;
        if ((this.bncdaMaso == null && other.bncdaMaso != null) || (this.bncdaMaso != null && !this.bncdaMaso.equals(other.bncdaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.BenhNhanCheDoAn[bncdaMaso=" + bncdaMaso + "]";
    }

}
