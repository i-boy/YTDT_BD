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
 * @author james
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CHE_DO_AN")
@NamedQueries({})
public class DtDmCheDoAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CHE_DO_AN_DTDMCDA")
    @SequenceGenerator(name = "DT_DM_CHE_DO_AN_DTDMCDA", sequenceName = "DT_DM_CHE_DO_AN_DTDMCDA_MASO_S", allocationSize = 1)
    @Column(name = "DTDMCDA_MASO", nullable = false)
    private Integer dtdmcdaMaso;
    @Column(name = "DTDMCDA_MA", nullable = false)
    private String dtdmcdaMa;
    @Column(name = "DTDMCDA_TEN")
    private String dtdmcdaTen;
    @Column(name = "DTDMCDA_NGAYGIOCN")
    private Double dtdmcdaNgaygiocn;
    @Column(name = "DTDMCDA_CHON")
    private Boolean dtdmcdaChon;

    public DtDmCheDoAn() {
    }

    public DtDmCheDoAn(Integer dtdmcdaMaso) {
        this.dtdmcdaMaso = dtdmcdaMaso;
    }

    public DtDmCheDoAn(Integer dtdmcdaMaso, String dtdmcdaMa) {
        this.dtdmcdaMaso = dtdmcdaMaso;
        this.dtdmcdaMa = dtdmcdaMa;
    }

    public Integer getDtdmcdaMaso() {
        return dtdmcdaMaso;
    }

    public void setDtdmcdaMaso(Integer dtdmcdaMaso) {
        this.dtdmcdaMaso = dtdmcdaMaso;
    }

    public String getDtdmcdaMa() {
        return dtdmcdaMa;
    }

    public void setDtdmcdaMa(String dtdmcdaMa) {
        this.dtdmcdaMa = dtdmcdaMa;
    }

    public String getDtdmcdaTen() {
        return dtdmcdaTen;
    }

    public void setDtdmcdaTen(String dtdmcdaTen) {
        this.dtdmcdaTen = dtdmcdaTen;
    }

    public Double getDtdmcdaNgaygiocn() {
        return dtdmcdaNgaygiocn;
    }

    public void setDtdmcdaNgaygiocn(Double dtdmcdaNgaygiocn) {
        this.dtdmcdaNgaygiocn = dtdmcdaNgaygiocn;
    }

    public Boolean getDtdmcdaChon() {
        return dtdmcdaChon;
    }

    public void setDtdmcdaChon(Boolean dtdmcdaChon) {
        this.dtdmcdaChon = dtdmcdaChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmcdaMaso != null ? dtdmcdaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmCheDoAn)) {
            return false;
        }
        DtDmCheDoAn other = (DtDmCheDoAn) object;
        if ((this.dtdmcdaMaso == null && other.dtdmcdaMaso != null) || (this.dtdmcdaMaso != null && !this.dtdmcdaMaso.equals(other.dtdmcdaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmCheDoAn[dtdmcdaMaso=" + dtdmcdaMaso + "]";
    }

}
