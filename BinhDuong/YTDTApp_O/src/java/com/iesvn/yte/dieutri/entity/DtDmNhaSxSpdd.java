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
 * @author HP
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_NHA_SX_SPDD")
@NamedQueries({})
public class DtDmNhaSxSpdd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_NHA_SX")
    @SequenceGenerator(name = "DT_DM_NHA_SX", sequenceName = "DT_DM_NHA_SX_SPDD_DTDMNSX_MASO", allocationSize = 1)
    @Column(name = "DTDMNSX_MASO", nullable = false)
    private Integer dtdmnsxMaso;
    @Column(name = "DTDMNSX_MA", nullable = false)
    private String dtdmnsxMa;
    @Column(name = "DTDMNSX_TEN")
    private String dtdmnsxTen;
    @Column(name = "DTDMNSX_NGAYGIOCN")
    private Double dtdmnsxNgaygiocn;
    @Column(name = "DTDMNSX_CHON")
    private Boolean dtdmnsxChon;

    public DtDmNhaSxSpdd() {
    }

    public DtDmNhaSxSpdd(Integer dtdmnsxMaso) {
        this.dtdmnsxMaso = dtdmnsxMaso;
    }

    public DtDmNhaSxSpdd(Integer dtdmnsxMaso, String dtdmnsxMa) {
        this.dtdmnsxMaso = dtdmnsxMaso;
        this.dtdmnsxMa = dtdmnsxMa;
    }

    public Integer getDtdmnsxMaso() {
        return dtdmnsxMaso;
    }

    public void setDtdmnsxMaso(Integer dtdmnsxMaso) {
        this.dtdmnsxMaso = dtdmnsxMaso;
    }

    public String getDtdmnsxMa() {
        return dtdmnsxMa;
    }

    public void setDtdmnsxMa(String dtdmnsxMa) {
        this.dtdmnsxMa = dtdmnsxMa;
    }

    public String getDtdmnsxTen() {
        return dtdmnsxTen;
    }

    public void setDtdmnsxTen(String dtdmnsxTen) {
        this.dtdmnsxTen = dtdmnsxTen;
    }

    public Double getDtdmnsxNgaygiocn() {
        return dtdmnsxNgaygiocn;
    }

    public void setDtdmnsxNgaygiocn(Double dtdmnsxNgaygiocn) {
        this.dtdmnsxNgaygiocn = dtdmnsxNgaygiocn;
    }

    public Boolean getDtdmnsxChon() {
        return dtdmnsxChon;
    }

    public void setDtdmnsxChon(Boolean dtdmnsxChon) {
        this.dtdmnsxChon = dtdmnsxChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmnsxMaso != null ? dtdmnsxMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmNhaSxSpdd)) {
            return false;
        }
        DtDmNhaSxSpdd other = (DtDmNhaSxSpdd) object;
        if ((this.dtdmnsxMaso == null && other.dtdmnsxMaso != null) || (this.dtdmnsxMaso != null && !this.dtdmnsxMaso.equals(other.dtdmnsxMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd[dtdmnsxMaso=" + dtdmnsxMaso + "]";
    }

}
