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
@Table(name = "DT_DM_DONG_THEM")
@NamedQueries({})
public class DtDmDongThem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_DONG_THEM")
    @SequenceGenerator(name = "DT_DM_DONG_THEM", sequenceName = "DT_DM_DONG_THEM_DTDMDT_MASO_SE", allocationSize = 1)
    @Column(name = "DTDMDT_MASO", nullable = false)
    private Integer dtdmdtMaso;
    @Column(name = "DTDMDT_MA", nullable = false)
    private String dtdmdtMa;
    @Column(name = "DTDMDT_TEN")
    private String dtdmdtTen;
    @Column(name = "DTDMDT_NGAYGIOCN")
    private Double dtdmdtNgaygiocn;
    @Column(name = "DTDMDT_CHON")
    private Boolean dtdmdtChon;

    public DtDmDongThem() {
    }

    public DtDmDongThem(Integer dtdmdtMaso) {
        this.dtdmdtMaso = dtdmdtMaso;
    }

    public DtDmDongThem(Integer dtdmdtMaso, String dtdmdtMa) {
        this.dtdmdtMaso = dtdmdtMaso;
        this.dtdmdtMa = dtdmdtMa;
    }

    public Integer getDtdmdtMaso() {
        return dtdmdtMaso;
    }

    public void setDtdmdtMaso(Integer dtdmdtMaso) {
        this.dtdmdtMaso = dtdmdtMaso;
    }

    public String getDtdmdtMa() {
        return dtdmdtMa;
    }

    public void setDtdmdtMa(String dtdmdtMa) {
        this.dtdmdtMa = dtdmdtMa;
    }

    public String getDtdmdtTen() {
        return dtdmdtTen;
    }

    public void setDtdmdtTen(String dtdmdtTen) {
        this.dtdmdtTen = dtdmdtTen;
    }

    public Double getDtdmdtNgaygiocn() {
        return dtdmdtNgaygiocn;
    }

    public void setDtdmdtNgaygiocn(Double dtdmdtNgaygiocn) {
        this.dtdmdtNgaygiocn = dtdmdtNgaygiocn;
    }

    public Boolean getDtdmdtChon() {
        return dtdmdtChon;
    }

    public void setDtdmdtChon(Boolean dtdmdtChon) {
        this.dtdmdtChon = dtdmdtChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmdtMaso != null ? dtdmdtMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmDongThem)) {
            return false;
        }
        DtDmDongThem other = (DtDmDongThem) object;
        if ((this.dtdmdtMaso == null && other.dtdmdtMaso != null) || (this.dtdmdtMaso != null && !this.dtdmdtMaso.equals(other.dtdmdtMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.dieutri.entity.DtDmDongThem[dtdmdtMaso=" + dtdmdtMaso + "]";
    }

}
