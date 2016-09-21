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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author thanh
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_LOP_BHYT")
@NamedQueries({@NamedQuery(name = "DtDmLopBhyt.findByDtdmlopbhytMaso", query = "SELECT d FROM DtDmLopBhyt d WHERE d.dtdmlopbhytMaso = :dtdmlopbhytMaso"), @NamedQuery(name = "DtDmLopBhyt.findByDtdmlopbhytMa", query = "SELECT d FROM DtDmLopBhyt d WHERE d.dtdmlopbhytMa = :dtdmlopbhytMa"), @NamedQuery(name = "DtDmLopBhyt.findByDtdmlopbhytTen", query = "SELECT d FROM DtDmLopBhyt d WHERE d.dtdmlopbhytTen = :dtdmlopbhytTen"), @NamedQuery(name = "DtDmLopBhyt.findByDtdmlopbhytNgaygiocn", query = "SELECT d FROM DtDmLopBhyt d WHERE d.dtdmlopbhytNgaygiocn = :dtdmlopbhytNgaygiocn"), @NamedQuery(name = "DtDmLopBhyt.findByDtdmlopbhytChon", query = "SELECT d FROM DtDmLopBhyt d WHERE d.dtdmlopbhytChon = :dtdmlopbhytChon")})
public class DtDmLopBhyt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_LOP_BHYT")
    @SequenceGenerator(name = "DT_DM_LOP_BHYT", sequenceName = "DT_DM_LOP_BHYT_DTDMLOPBHYT_MAS", allocationSize = 1)
    @Column(name = "DTDMLOPBHYT_MASO", nullable = false)
    private Integer dtdmlopbhytMaso;
    @Column(name = "DTDMLOPBHYT_MA", nullable = false)
    private String dtdmlopbhytMa;
    @Column(name = "DTDMLOPBHYT_TEN", nullable = false)
    private String dtdmlopbhytTen;
    @Column(name = "DTDMLOPBHYT_NGAYGIOCN")
    private Double dtdmlopbhytNgaygiocn;
    @Column(name = "DTDMLOPBHYT_CHON")
    private Boolean dtdmlopbhytChon;

    public DtDmLopBhyt() {
    }

    public DtDmLopBhyt(Integer dtdmlopbhytMaso) {
        this.dtdmlopbhytMaso = dtdmlopbhytMaso;
    }

    public DtDmLopBhyt(Integer dtdmlopbhytMaso, String dtdmlopbhytMa, String dtdmlopbhytTen) {
        this.dtdmlopbhytMaso = dtdmlopbhytMaso;
        this.dtdmlopbhytMa = dtdmlopbhytMa;
        this.dtdmlopbhytTen = dtdmlopbhytTen;
    }

    public Integer getDtdmlopbhytMaso() {
        return dtdmlopbhytMaso;
    }

    public void setDtdmlopbhytMaso(Integer dtdmlopbhytMaso) {
        this.dtdmlopbhytMaso = dtdmlopbhytMaso;
    }

    public String getDtdmlopbhytMa() {
        return dtdmlopbhytMa;
    }

    public void setDtdmlopbhytMa(String dtdmlopbhytMa) {
        this.dtdmlopbhytMa = dtdmlopbhytMa;
    }

    public String getDtdmlopbhytTen() {
        return dtdmlopbhytTen;
    }

    public void setDtdmlopbhytTen(String dtdmlopbhytTen) {
        this.dtdmlopbhytTen = dtdmlopbhytTen;
    }

    public Double getDtdmlopbhytNgaygiocn() {
        return dtdmlopbhytNgaygiocn;
    }

    public void setDtdmlopbhytNgaygiocn(Double dtdmlopbhytNgaygiocn) {
        this.dtdmlopbhytNgaygiocn = dtdmlopbhytNgaygiocn;
    }

    public Boolean getDtdmlopbhytChon() {
        return dtdmlopbhytChon;
    }

    public void setDtdmlopbhytChon(Boolean dtdmlopbhytChon) {
        this.dtdmlopbhytChon = dtdmlopbhytChon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmlopbhytMaso != null ? dtdmlopbhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmLopBhyt)) {
            return false;
        }
        DtDmLopBhyt other = (DtDmLopBhyt) object;
        if ((this.dtdmlopbhytMaso == null && other.dtdmlopbhytMaso != null) || (this.dtdmlopbhytMaso != null && !this.dtdmlopbhytMaso.equals(other.dtdmlopbhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.DtDmLopBhyt[dtdmlopbhytMaso=" + dtdmlopbhytMaso + "]";
    }

}
