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
@Table(name = "DT_DM_LOAI_AN")
@NamedQueries({})
public class DtDmLoaiAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOAI_AN")
    @SequenceGenerator(name = "DT_DM_LOAI_AN", sequenceName = "DT_DM_LOAI_AN_DTDMLA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMLA_MASO", nullable = false)
    private Integer dtdmlaMaso;
    @Column(name = "DTDMLA_MA", nullable = false)
    private String dtdmlaMa;
    @Column(name = "DTDMLA_TEN")
    private String dtdmlaTen;
    @Column(name = "DTDMLA_NGAYGIOCN")
    private Double dtdmlaNgaygiocn;
    @Column(name = "DTDMLA_CHON")
    private Boolean dtdmlaChon;

    public DtDmLoaiAn() {
    }

    public DtDmLoaiAn(Integer dtdmlaMaso) {
        this.dtdmlaMaso = dtdmlaMaso;
    }

    public DtDmLoaiAn(Integer dtdmlaMaso, String dtdmlaMa) {
        this.dtdmlaMaso = dtdmlaMaso;
        this.dtdmlaMa = dtdmlaMa;
    }

    public Integer getDtdmlaMaso() {
        return dtdmlaMaso;
    }

    public void setDtdmlaMaso(Integer dtdmlaMaso) {
        this.dtdmlaMaso = dtdmlaMaso;
    }

    public String getDtdmlaMa() {
        return dtdmlaMa;
    }

    public void setDtdmlaMa(String dtdmlaMa) {
        this.dtdmlaMa = dtdmlaMa;
    }

    public String getDtdmlaTen() {
        return dtdmlaTen;
    }

    public void setDtdmlaTen(String dtdmlaTen) {
        this.dtdmlaTen = dtdmlaTen;
    }

    public Double getDtdmlaNgaygiocn() {
        return dtdmlaNgaygiocn;
    }

    public void setDtdmlaNgaygiocn(Double dtdmlaNgaygiocn) {
        this.dtdmlaNgaygiocn = dtdmlaNgaygiocn;
    }

    public Boolean getDtdmlaChon() {
        return dtdmlaChon;
    }

    public void setDtdmlaChon(Boolean dtdmlaChon) {
        this.dtdmlaChon = dtdmlaChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmlaMaso != null ? dtdmlaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLoaiAn)) {
            return false;
        }
        DtDmLoaiAn other = (DtDmLoaiAn) object;
        if ((this.dtdmlaMaso == null && other.dtdmlaMaso != null) || (this.dtdmlaMaso != null && !this.dtdmlaMaso.equals(other.dtdmlaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmLoaiAn[dtdmlaMaso=" + dtdmlaMaso + "]";
    }

}
