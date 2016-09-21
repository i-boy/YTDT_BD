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
@Table(name = "BENH_NHAN_GIO_AN")
@NamedQueries({})
public class BenhNhanGioAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BENH_NHAN_GIO_AN_BNGA_MASO")
    @SequenceGenerator(name = "BENH_NHAN_GIO_AN_BNGA_MASO", sequenceName = "BENH_NHAN_GIO_AN_BNGA_MASO_SEQ", allocationSize = 1)
    @Column(name = "BNGA_MASO", nullable = false)
    private Integer bngaMaso;
    @JoinColumn(name = "BNPBA_MASO", referencedColumnName = "BNPBA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private BenhNhanPhieuBaoAn bnpbaMaso;
    @JoinColumn(name = "DTDMGA_MASO", referencedColumnName = "DTDMGA_MASO")
    @ManyToOne (fetch=FetchType.LAZY)
    private DtDmGioAn dtdmgaMaso;

    public BenhNhanGioAn() {
    }

    public BenhNhanGioAn(Integer bngaMaso) {
        this.bngaMaso = bngaMaso;
    }

    public Integer getBngaMaso() {
        return bngaMaso;
    }

    public void setBngaMaso(Integer bngaMaso) {
        this.bngaMaso = bngaMaso;
    }

    public BenhNhanPhieuBaoAn getBnpbaMaso() {
        return bnpbaMaso;
    }

    public void setBnpbaMaso(BenhNhanPhieuBaoAn bnpbaMaso) {
        this.bnpbaMaso = bnpbaMaso;
    }

    public DtDmGioAn getDtdmgaMaso() {
        return dtdmgaMaso;
    }

    public void setDtdmgaMaso(DtDmGioAn dtdmgaMaso) {
        this.dtdmgaMaso = dtdmgaMaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bngaMaso != null ? bngaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenhNhanGioAn)) {
            return false;
        }
        BenhNhanGioAn other = (BenhNhanGioAn) object;
        if ((this.bngaMaso == null && other.bngaMaso != null) || (this.bngaMaso != null && !this.bngaMaso.equals(other.bngaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.BenhNhanGioAn[bngaMaso=" + bngaMaso + "]";
    }

}
