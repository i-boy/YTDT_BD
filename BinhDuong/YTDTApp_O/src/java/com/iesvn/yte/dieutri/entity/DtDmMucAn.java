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
@Table(name = "DT_DM_MUC_AN")
@NamedQueries({})
public class DtDmMucAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_MUC_AN_")
    @SequenceGenerator(name = "DT_DM_MUC_AN_", sequenceName = "DT_DM_MUC_AN_DTDMMA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMMA_MASO", nullable = false)
    private Integer dtdmmaMaso;
    @Column(name = "DTDMMA_MA", nullable = false)
    private String dtdmmaMa;
    @Column(name = "DTDMMA_TEN")
    private String dtdmmaTen;
    @Column(name = "DTDMMA_NGAYGIOCN")
    private Double dtdmmaNgaygiocn;
    @Column(name = "DTDMMA_CHON")
    private Boolean dtdmmaChon;

    public DtDmMucAn() {
    }

    public DtDmMucAn(Integer dtdmmaMaso) {
        this.dtdmmaMaso = dtdmmaMaso;
    }

    public DtDmMucAn(Integer dtdmmaMaso, String dtdmmaMa) {
        this.dtdmmaMaso = dtdmmaMaso;
        this.dtdmmaMa = dtdmmaMa;
    }

    public Integer getDtdmmaMaso() {
        return dtdmmaMaso;
    }

    public void setDtdmmaMaso(Integer dtdmmaMaso) {
        this.dtdmmaMaso = dtdmmaMaso;
    }

    public String getDtdmmaMa() {
        return dtdmmaMa;
    }

    public void setDtdmmaMa(String dtdmmaMa) {
        this.dtdmmaMa = dtdmmaMa;
    }

    public String getDtdmmaTen() {
        return dtdmmaTen;
    }

    public void setDtdmmaTen(String dtdmmaTen) {
        this.dtdmmaTen = dtdmmaTen;
    }

    public Double getDtdmmaNgaygiocn() {
        return dtdmmaNgaygiocn;
    }

    public void setDtdmmaNgaygiocn(Double dtdmmaNgaygiocn) {
        this.dtdmmaNgaygiocn = dtdmmaNgaygiocn;
    }

    public Boolean getDtdmmaChon() {
        return dtdmmaChon;
    }

    public void setDtdmmaChon(Boolean dtdmmaChon) {
        this.dtdmmaChon = dtdmmaChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmmaMaso != null ? dtdmmaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmMucAn)) {
            return false;
        }
        DtDmMucAn other = (DtDmMucAn) object;
        if ((this.dtdmmaMaso == null && other.dtdmmaMaso != null) || (this.dtdmmaMaso != null && !this.dtdmmaMaso.equals(other.dtdmmaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmMucAn[dtdmmaMaso=" + dtdmmaMaso + "]";
    }

}
