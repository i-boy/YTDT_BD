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
@Table(name = "DT_DM_PHONG")
@NamedQueries({})
public class DtDmPhong implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PHONG")
    @SequenceGenerator(name = "DT_DM_PHONG", sequenceName = "DT_DM_PHONG_DTDMP_MASO_SEQ", allocationSize = 1)
    @Column(name = "DTDMP_MASO", nullable = false)
    private Integer dtdmpMaso;
    @Column(name = "DTDMP_MA", nullable = false)
    private String dtdmpMa;
    @Column(name = "DTDMP_TEN", nullable = false)
    private String dtdmpTen;
    @Column(name = "DTDMP_NGAYGIOCN", nullable = false)
    private double dtdmpNgaygiocn;
    @Column(name = "DTDMP_CHON")
    private Integer dtdmpChon;
    

    public DtDmPhong() {
    }

    public DtDmPhong(Integer dtdmpMaso) {
        this.dtdmpMaso = dtdmpMaso;
    }

    public DtDmPhong(Integer dtdmpMaso, String dtdmpMa, String dtdmpTen, double dtdmpNgaygiocn) {
        this.dtdmpMaso = dtdmpMaso;
        this.dtdmpMa = dtdmpMa;
        this.dtdmpTen = dtdmpTen;
        this.dtdmpNgaygiocn = dtdmpNgaygiocn;
    }

    public Integer getDtdmpMaso() {
        return dtdmpMaso;
    }

    public void setDtdmpMaso(Integer dtdmpMaso) {
        this.dtdmpMaso = dtdmpMaso;
    }

    public String getDtdmpMa() {
        return dtdmpMa;
    }

    public void setDtdmpMa(String dtdmpMa) {
        this.dtdmpMa = dtdmpMa;
    }

    public String getDtdmpTen() {
        return dtdmpTen;
    }

    public void setDtdmpTen(String dtdmpTen) {
        this.dtdmpTen = dtdmpTen;
    }

    public double getDtdmpNgaygiocn() {
        return dtdmpNgaygiocn;
    }

    public void setDtdmpNgaygiocn(double dtdmpNgaygiocn) {
        this.dtdmpNgaygiocn = dtdmpNgaygiocn;
    }

    public Integer getDtdmpChon() {
        return dtdmpChon;
    }

    public void setDtdmpChon(Integer dtdmpChon) {
        this.dtdmpChon = dtdmpChon;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmpMaso != null ? dtdmpMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPhong)) {
            return false;
        }
        DtDmPhong other = (DtDmPhong) object;
        if ((this.dtdmpMaso == null && other.dtdmpMaso != null) || (this.dtdmpMaso != null && !this.dtdmpMaso.equals(other.dtdmpMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.iesvn.yte.test.DtDmPhong[dtdmpMaso=" + dtdmpMaso + "]";
    }

}
