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
@Table(name = "DT_DM_GIO_AN")
@NamedQueries({})
public class DtDmGioAn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_GIO_AN")
    @SequenceGenerator(name = "DT_DM_GIO_AN", sequenceName = "DT_DM_GIO_AN_DTDMGA_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMGA_MASO", nullable = false)
    private Integer dtdmgaMaso;
    @Column(name = "DTDMGA_MA", nullable = false)
    private String dtdmgaMa;
    @Column(name = "DTDMGA_TEN")
    private String dtdmgaTen;
    @Column(name = "DTDMGA_NGAYGIOCN")
    private Double dtdmgaNgaygiocn;
    @Column(name = "DTDMGA_CHON")
    private Boolean dtdmgaChon;

    public DtDmGioAn() {
    }

    public DtDmGioAn(Integer dtdmgaMaso) {
        this.dtdmgaMaso = dtdmgaMaso;
    }

    public DtDmGioAn(Integer dtdmgaMaso, String dtdmgaMa) {
        this.dtdmgaMaso = dtdmgaMaso;
        this.dtdmgaMa = dtdmgaMa;
    }

    public Integer getDtdmgaMaso() {
        return dtdmgaMaso;
    }

    public void setDtdmgaMaso(Integer dtdmgaMaso) {
        this.dtdmgaMaso = dtdmgaMaso;
    }

    public String getDtdmgaMa() {
        return dtdmgaMa;
    }

    public void setDtdmgaMa(String dtdmgaMa) {
        this.dtdmgaMa = dtdmgaMa;
    }

    public String getDtdmgaTen() {
        return dtdmgaTen;
    }

    public void setDtdmgaTen(String dtdmgaTen) {
        this.dtdmgaTen = dtdmgaTen;
    }

    public Double getDtdmgaNgaygiocn() {
        return dtdmgaNgaygiocn;
    }

    public void setDtdmgaNgaygiocn(Double dtdmgaNgaygiocn) {
        this.dtdmgaNgaygiocn = dtdmgaNgaygiocn;
    }

    public Boolean getDtdmgaChon() {
        return dtdmgaChon;
    }

    public void setDtdmgaChon(Boolean dtdmgaChon) {
        this.dtdmgaChon = dtdmgaChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmgaMaso != null ? dtdmgaMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmGioAn)) {
            return false;
        }
        DtDmGioAn other = (DtDmGioAn) object;
        if ((this.dtdmgaMaso == null && other.dtdmgaMaso != null) || (this.dtdmgaMaso != null && !this.dtdmgaMaso.equals(other.dtdmgaMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmGioAn[dtdmgaMaso=" + dtdmgaMaso + "]";
    }

}
