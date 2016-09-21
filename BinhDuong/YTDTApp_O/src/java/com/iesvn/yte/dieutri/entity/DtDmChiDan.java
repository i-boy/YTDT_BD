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
//import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author root
 */
@Entity @org.hibernate.annotations.Proxy(lazy=false)
@Table(name = "DT_DM_CHI_DAN")
@NamedQueries({})
public class DtDmChiDan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_CHI_DAN_DTDMCHIDAN")
    @SequenceGenerator(name = "DT_DM_CHI_DAN_DTDMCHIDAN", sequenceName = "DT_DM_CHI_DAN_DTDMCHIDAN_MASO_", allocationSize = 1)
    @Column(name = "DTDMCHIDAN_MASO", nullable = false)
    private Integer dtdmchidanMaso;
    @Column(name = "DTDMCHIDAN_MA", nullable = false)
    private String dtdmchidanMa;
    @Column(name = "DTDMCHIDAN_MAPHU")
    private String dtdmchidanMaphu;
    @Column(name = "DTDMCHIDAN_TEN")
    private String dtdmchidanTen;
    @Column(name = "DTDMCHIDAN_NGAYGIOCN")
    private Double dtdmchidanNgaygiocn;
    @Column(name = "DTDMCHIDAN_CHON")
    private Boolean dtdmchidanChon;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dtdmchidanMaso")
//    private Collection<DtDmChiDanDvt> dtDmChiDanDvtCollection;
    public DtDmChiDan() {
    }

    public DtDmChiDan(Integer dtdmchidanMaso) {
        this.dtdmchidanMaso = dtdmchidanMaso;
    }

    public DtDmChiDan(Integer dtdmchidanMaso, String dtdmchidanMa) {
        this.dtdmchidanMaso = dtdmchidanMaso;
        this.dtdmchidanMa = dtdmchidanMa;
    }

    public Integer getDtdmchidanMaso() {
        return dtdmchidanMaso;
    }

    public void setDtdmchidanMaso(Integer dtdmchidanMaso) {
        this.dtdmchidanMaso = dtdmchidanMaso;
    }

    public String getDtdmchidanMa() {
        return dtdmchidanMa;
    }

    public void setDtdmchidanMa(String dtdmchidanMa) {
        this.dtdmchidanMa = dtdmchidanMa;
    }

    public String getDtdmchidanMaphu() {
        return dtdmchidanMaphu;
    }

    public void setDtdmchidanMaphu(String dtdmchidanMaphu) {
        this.dtdmchidanMaphu = dtdmchidanMaphu;
    }

    public String getDtdmchidanTen() {
        return dtdmchidanTen;
    }

    public void setDtdmchidanTen(String dtdmchidanTen) {
        this.dtdmchidanTen = dtdmchidanTen;
    }

    public Double getDtdmchidanNgaygiocn() {
        return dtdmchidanNgaygiocn;
    }

    public void setDtdmchidanNgaygiocn(Double dtdmchidanNgaygiocn) {
        this.dtdmchidanNgaygiocn = dtdmchidanNgaygiocn;
    }

    public Boolean getDtdmchidanChon() {
        return dtdmchidanChon;
    }

    public void setDtdmchidanChon(Boolean dtdmchidanChon) {
        this.dtdmchidanChon = dtdmchidanChon;
    }

//    public Collection<DtDmChiDanDvt> getDtDmChiDanDvtCollection() {
//        return dtDmChiDanDvtCollection;
//    }
//
//    public void setDtDmChiDanDvtCollection(Collection<DtDmChiDanDvt> dtDmChiDanDvtCollection) {
//        this.dtDmChiDanDvtCollection = dtDmChiDanDvtCollection;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmchidanMaso != null ? dtdmchidanMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmChiDan)) {
            return false;
        }
        DtDmChiDan other = (DtDmChiDan) object;
        if ((this.dtdmchidanMaso == null && other.dtdmchidanMaso != null) || (this.dtdmchidanMaso != null && !this.dtdmchidanMaso.equals(other.dtdmchidanMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmChiDan[dtdmchidanMaso=" + dtdmchidanMaso + "]";
    }
}


