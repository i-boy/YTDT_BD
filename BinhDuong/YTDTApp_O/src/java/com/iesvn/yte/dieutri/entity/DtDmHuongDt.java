/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_HUONG_DT")
@NamedQueries({})
public class DtDmHuongDt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_HUONG_DT")
    @SequenceGenerator(name = "DT_DM_HUONG_DT", sequenceName = "DT_DM_HUONG_DT_SEQ", allocationSize = 1)
    @Column(name = "DTDMHUONGDT_MASO", nullable = false)
    private Integer dtdmhuongdtMaso;
    @Column(name = "DTDMHUONGDT_TEN", nullable = false)
    private String dtdmhuongdtTen;
    @Column(name = "DTDMHUONGDT__NGAYGIOCN")
    private Double dtdmhuongdtNgaygiocn;
    @Column(name = "DTDMHUONGDT_MA")
    private String dtdmhuongdtMa;
    @Column(name = "DTDMHUONGDT__CHON")
    private Boolean dtdmhuongdtChon;

    public DtDmHuongDt() {
    }

    public DtDmHuongDt(Integer dtdmhuongdtMaso) {
        this.dtdmhuongdtMaso = dtdmhuongdtMaso;
    }

    public DtDmHuongDt(Integer dtdmhuongdtMaso, String dtdmhuongdtTen) {
        this.dtdmhuongdtMaso = dtdmhuongdtMaso;
        this.dtdmhuongdtTen = dtdmhuongdtTen;
    }

    public Integer getDtdmhuongdtMaso() {
        return dtdmhuongdtMaso;
    }

    public void setDtdmhuongdtMaso(Integer dtdmhuongdtMaso) {
        this.dtdmhuongdtMaso = dtdmhuongdtMaso;
    }

    public String getDtdmhuongdtTen() {
        return dtdmhuongdtTen;
    }

    public void setDtdmhuongdtTen(String dtdmhuongdtTen) {
        this.dtdmhuongdtTen = dtdmhuongdtTen;
    }

    public Double getDtdmhuongdtNgaygiocn() {
        return dtdmhuongdtNgaygiocn;
    }

    public void setDtdmhuongdtNgaygiocn(Double dtdmhuongdtNgaygiocn) {
        this.dtdmhuongdtNgaygiocn = dtdmhuongdtNgaygiocn;
    }

    public String getDtdmhuongdtMa() {
        return dtdmhuongdtMa;
    }

    public void setDtdmhuongdtMa(String dtdmhuongdtMa) {
        this.dtdmhuongdtMa = dtdmhuongdtMa;
    }

    public Boolean getDtdmhuongdtChon() {
        return dtdmhuongdtChon;
    }

    public void setDtdmhuongdtChon(Boolean dtdmhuongdtChon) {
        this.dtdmhuongdtChon = dtdmhuongdtChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmhuongdtMaso != null ? dtdmhuongdtMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmHuongDt)) {
            return false;
        }
        DtDmHuongDt other = (DtDmHuongDt) object;
        if ((this.dtdmhuongdtMaso == null && other.dtdmhuongdtMaso != null) || (this.dtdmhuongdtMaso != null && !this.dtdmhuongdtMaso.equals(other.dtdmhuongdtMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmHuongDt[dtdmhuongdtMaso=" + dtdmhuongdtMaso + "]";
    }
}


