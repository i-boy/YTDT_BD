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
@Table(name = "DT_DM_PL_BHYT")
@NamedQueries({})
public class DtDmPlBhyt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DT_DM_PL_BHYT")
    @SequenceGenerator(name = "DT_DM_PL_BHYT", sequenceName = "DT_DM_PL_BHYT_DTDMPHLOAIBHYT_M", allocationSize = 1)
    @Column(name = "DTDMPHLOAIBHYT_MASO", nullable = false)
    private Integer dtdmphloaibhytMaso;
    @Column(name = "DTDMPHLOAIBHYT_MA", nullable = false)
    private String dtdmphloaibhytMa;
    @Column(name = "DTDMPHLOAIBHYT_TEN", nullable = false)
    private String dtdmphloaibhytTen;
    @Column(name = "DTDMPHLOAIBHYT_GHICHU")
    private String dtdmphloaibhytGhichu;
    @Column(name = "DTDMPHLOAIBHYT_NGAYGIOCN")
    private Double dtdmphloaibhytNgaygiocn;
    @Column(name = "DTDMPHLOAIBHYT_CHON")
    private Boolean dtdmphloaibhytChon;
//    @OneToMany(mappedBy = "tiepdonDoituongbhyt")
//    private Collection<TiepDon> tiepDonCollection;
//    @OneToMany(mappedBy = "dtdmkhoibhytPhanloai")
//    private Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection;
//    @OneToMany(mappedBy = "dtdmkhoibhytPhanloai1")
//    private Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection1;
    public DtDmPlBhyt() {
    }

    public DtDmPlBhyt(Integer dtdmphloaibhytMaso) {
        this.dtdmphloaibhytMaso = dtdmphloaibhytMaso;
    }

    public DtDmPlBhyt(Integer dtdmphloaibhytMaso, String dtdmphloaibhytMa, String dtdmphloaibhytTen) {
        this.dtdmphloaibhytMaso = dtdmphloaibhytMaso;
        this.dtdmphloaibhytMa = dtdmphloaibhytMa;
        this.dtdmphloaibhytTen = dtdmphloaibhytTen;
    }

    public Integer getDtdmphloaibhytMaso() {
        return dtdmphloaibhytMaso;
    }

    public void setDtdmphloaibhytMaso(Integer dtdmphloaibhytMaso) {
        this.dtdmphloaibhytMaso = dtdmphloaibhytMaso;
    }

    public String getDtdmphloaibhytMa() {
        return dtdmphloaibhytMa;
    }

    public void setDtdmphloaibhytMa(String dtdmphloaibhytMa) {
        this.dtdmphloaibhytMa = dtdmphloaibhytMa;
    }

    public String getDtdmphloaibhytTen() {
        return dtdmphloaibhytTen;
    }

    public void setDtdmphloaibhytTen(String dtdmphloaibhytTen) {
        this.dtdmphloaibhytTen = dtdmphloaibhytTen;
    }

    public String getDtdmphloaibhytGhichu() {
        return dtdmphloaibhytGhichu;
    }

    public void setDtdmphloaibhytGhichu(String dtdmphloaibhytGhichu) {
        this.dtdmphloaibhytGhichu = dtdmphloaibhytGhichu;
    }

    public Double getDtdmphloaibhytNgaygiocn() {
        return dtdmphloaibhytNgaygiocn;
    }

    public void setDtdmphloaibhytNgaygiocn(Double dtdmphloaibhytNgaygiocn) {
        this.dtdmphloaibhytNgaygiocn = dtdmphloaibhytNgaygiocn;
    }

    public Boolean getDtdmphloaibhytChon() {
        return dtdmphloaibhytChon;
    }

    public void setDtdmphloaibhytChon(Boolean dtdmphloaibhytChon) {
        this.dtdmphloaibhytChon = dtdmphloaibhytChon;
    }

//    public Collection<TiepDon> getTiepDonCollection() {
//        return tiepDonCollection;
//    }
//
//    public void setTiepDonCollection(Collection<TiepDon> tiepDonCollection) {
//        this.tiepDonCollection = tiepDonCollection;
//    }

//    public Collection<DtDmKhoiBhyt> getDtDmKhoiBhytCollection() {
//        return dtDmKhoiBhytCollection;
//    }
//
//    public void setDtDmKhoiBhytCollection(Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection) {
//        this.dtDmKhoiBhytCollection = dtDmKhoiBhytCollection;
//    }

//    public Collection<DtDmKhoiBhyt> getDtDmKhoiBhytCollection1() {
//        return dtDmKhoiBhytCollection1;
//    }
//
//    public void setDtDmKhoiBhytCollection1(Collection<DtDmKhoiBhyt> dtDmKhoiBhytCollection1) {
//        this.dtDmKhoiBhytCollection1 = dtDmKhoiBhytCollection1;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dtdmphloaibhytMaso != null ? dtdmphloaibhytMaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtDmPlBhyt)) {
            return false;
        }
        DtDmPlBhyt other = (DtDmPlBhyt) object;
        if ((this.dtdmphloaibhytMaso == null && other.dtdmphloaibhytMaso != null) || (this.dtdmphloaibhytMaso != null && !this.dtdmphloaibhytMaso.equals(other.dtdmphloaibhytMaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "temp.DtDmPlBhyt[dtdmphloaibhytMaso=" + dtdmphloaibhytMaso + "]";
    }
}


